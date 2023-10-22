package lk.ijse.travelservice.service.impl;

import lk.ijse.travelservice.dto.PackageDto;
import lk.ijse.travelservice.entity.Package;
import lk.ijse.travelservice.exception.AlreadyExistsException;
import lk.ijse.travelservice.exception.CannotDeleteException;
import lk.ijse.travelservice.exception.NotFoundException;
import lk.ijse.travelservice.repository.PackageRepository;
import lk.ijse.travelservice.service.PackageService;
import lk.ijse.travelservice.service.TravelService;
import lk.ijse.travelservice.util.mappers.PackageMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class PackageServiceImpl implements PackageService {

    private final TravelService travelService;
    private final PackageRepository repository;
    private final PackageMapper mapper;

    @Autowired
    public PackageServiceImpl(TravelService travelService, PackageRepository repository, PackageMapper mapper) {
        this.travelService = travelService;
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public String createNewPackage(PackageDto packageInfo) {
//        Validation
        if (repository.findById(packageInfo.getPackageName()).isPresent()) {
            throw new AlreadyExistsException("Package already exists : " + packageInfo.getPackageName());
        } else {
//        TODO: Check Hotels and Vehicles are really exist


//        Convert into package entity
            Package packageEntity = mapper.toEntity(packageInfo);
            repository.save(packageEntity);
            return packageEntity.getPackageName();
        }
    }

    @Override
    public String updateExistingPackage(PackageDto packageInfo) {
//        Validation
        if (repository.findById(packageInfo.getPackageName()).isEmpty()) {
            throw new NotFoundException("Package not found : " + packageInfo.getPackageName());
        } else {
//        TODO: Check Hotels and Vehicles are really exist


//        Convert into package entity
            Package packageEntity = mapper.toEntity(packageInfo);
            repository.save(packageEntity);
            return packageEntity.getPackageName();
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
