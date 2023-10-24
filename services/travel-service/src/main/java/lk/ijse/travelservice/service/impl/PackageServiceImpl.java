package lk.ijse.travelservice.service.impl;

import lk.ijse.travelservice.dto.PackageDto;
import lk.ijse.travelservice.entity.Package;
import lk.ijse.travelservice.exception.AlreadyExistsException;
import lk.ijse.travelservice.exception.CannotDeleteException;
import lk.ijse.travelservice.exception.NotFoundException;
import lk.ijse.travelservice.repository.PackageRepository;
import lk.ijse.travelservice.service.PackageService;
import lk.ijse.travelservice.service.TravelService;
import lk.ijse.travelservice.util.constants.VehicleCategory;
import lk.ijse.travelservice.util.mappers.PackageMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

@Service
@Transactional
public class PackageServiceImpl implements PackageService {

    private final TravelService travelService;
    private final PackageRepository repository;
    private final PackageMapper mapper;
    private final WebClient.Builder webClient;

    @Autowired
    public PackageServiceImpl(TravelService travelService, PackageRepository repository, PackageMapper mapper, WebClient.Builder webClient) {
        this.travelService = travelService;
        this.repository = repository;
        this.mapper = mapper;
        this.webClient = webClient;
    }

    @Override
    public String createNewPackage(PackageDto packageInfo) {
//        Validation
        if (repository.findById(packageInfo.getPackageName()).isPresent()) {
            throw new AlreadyExistsException("Package already exists : " + packageInfo.getPackageName());
        } else {

//            Check Hotels
            if (checkHotels(packageInfo) && checkVehicles(packageInfo)) {
                Package packageEntity = mapper.toEntity(packageInfo);
                repository.save(packageEntity);
                return packageEntity.getPackageName();
            }
            return null;
        }
    }

    private boolean checkVehicles(PackageDto packageInfo) {
        String[] vehicleCategories = packageInfo.getVehicleCategories();
        List<String> list = Arrays.stream(vehicleCategories)
                .map(String::trim)
                .map(String::toUpperCase)
                .filter(category -> {
                    VehicleCategory.valueOf(category);
                    return true;
                })
                .toList();
        if (list.isEmpty() || list.size() != vehicleCategories.length) {
            throw new NotFoundException("Vehicle Categories not found");
        } else return true;
    }

    private boolean checkHotels(PackageDto packageInfo) {
        List<Long> givenHotelIds = Arrays.stream(packageInfo.getHotelIds()).map(Long::parseLong).toList();
        Map existHotelIds = webClient.build().get().uri("lb://hotel-service/api/hotel/checkHotelsByIds",
                        uriBuilder -> uriBuilder.queryParam("hotelId", givenHotelIds).build())
                .retrieve()
                .bodyToMono(Map.class)
                .block();
        if (existHotelIds == null) {
            throw new NotFoundException("Hotels not found : " + givenHotelIds);
        } else if (existHotelIds.size() != givenHotelIds.size()) {
            String[] hotels = (String[])
                    Arrays.stream(givenHotelIds.stream().filter(hotelId -> !existHotelIds.containsKey(hotelId)).toArray())
                            .map(String::valueOf).toArray();
            throw new NotFoundException("These hotels are not found : " + String.join(",", hotels));
        } else {
            return true;
        }
    }

    @Override
    public String updateExistingPackage(PackageDto packageInfo) {
//        Validation
        if (repository.findById(packageInfo.getPackageName()).isEmpty()) {
            throw new NotFoundException("Package not found : " + packageInfo.getPackageName());
        } else {
            if (checkHotels(packageInfo) && checkVehicles(packageInfo)) {
                Package packageEntity = mapper.toEntity(packageInfo);
                repository.save(packageEntity);
                return packageEntity.getPackageName();
            }
            return null;
        }
    }

    @Override
    public List<PackageDto> getAllPackages() {
        return mapper.toDto(repository.findAll());
    }

    @Override
    public String deleteExistingPackage(String packageName) {
        Package aPackage = repository
                .findById(packageName)
                .orElseThrow(
                        () -> new NotFoundException("Package not found : " + packageName)
                );
        if (travelService.findTravelsByPackageName(packageName).size() == 0) {
            repository.delete(aPackage);
            return packageName;
        } else throw new CannotDeleteException("Package is in use");
    }

    @Override
    public PackageDto findPackageByPackageName(String packageName) {
        return mapper.toDto(
                repository.findById(packageName).orElseThrow(
                        () -> new NotFoundException("Package not found : " + packageName)));
    }
}
