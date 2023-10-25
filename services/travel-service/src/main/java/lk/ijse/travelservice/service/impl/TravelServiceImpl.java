package lk.ijse.travelservice.service.impl;

import lk.ijse.travelservice.dto.PackageDto;
import lk.ijse.travelservice.dto.RequestTravelDto;
import lk.ijse.travelservice.dto.ResponseTravelDto;
import lk.ijse.travelservice.dto.guide.Guide;
import lk.ijse.travelservice.dto.hotel.Hotel;
import lk.ijse.travelservice.dto.vehicle.Vehicle;
import lk.ijse.travelservice.entity.Travel;
import lk.ijse.travelservice.exception.InvalidTravelDetailException;
import lk.ijse.travelservice.exception.NotFoundException;
import lk.ijse.travelservice.repository.TravelRepository;
import lk.ijse.travelservice.service.PackageService;
import lk.ijse.travelservice.service.TravelService;
import lk.ijse.travelservice.util.mappers.PackageMapper;
import lk.ijse.travelservice.util.mappers.TravelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class TravelServiceImpl implements TravelService {
    private final TravelRepository repository;
    private PackageService packageService;
    private final TravelMapper travelMapper;
    private final PackageMapper packageMapper;

    private WebClient.Builder webClient;

    @Autowired
    public TravelServiceImpl(TravelRepository repository, @Lazy PackageService packageService, TravelMapper travelMapper, PackageMapper packageMapper, WebClient.Builder webClient) {
        this.repository = repository;
        this.packageService = packageService;
        this.travelMapper = travelMapper;
        this.packageMapper = packageMapper;
        this.webClient = webClient;
    }

    @Override
    public List<ResponseTravelDto> findTravelsByPackageName(String packageName) {
        return travelMapper.toDto(repository.findTravelPackagesByPackageID(packageName));
    }

    @Override
    public String bookingTravel(RequestTravelDto requestTravelDto) {
        if (repository.findById(requestTravelDto.getPackageID()).isPresent()) {
            throw new NotFoundException("Package already found, Package ID: " + requestTravelDto.getPackageID());
        }
        Travel travel = validateDetailsOfTravelDto(requestTravelDto);
        if (travel != null) {
            return repository.save(travel).getPackageID();
        } else {
            throw new InvalidTravelDetailException("Invalid Travel Details");
        }
    }

    @Override
    public String updateBooking(RequestTravelDto requestTravelDto) {
        if (repository.findById(requestTravelDto.getPackageID()).isEmpty()) {
            throw new NotFoundException("Package not found");
        } else {
//            TODO:check role( user or admin )
//             If user -> Check booking time (Only can update some details within 48 hours after booking time)
//             If admin -> ok

            Travel travel = validateDetailsOfTravelDto(requestTravelDto);
            if (travel != null) {
//                TODO: If cancel hotel : add cancellation fee to travel package
//                                        check about pets and validate
                return repository.save(travel).getPackageID();
            }
        }
        return null;
    }

    @Override
    public List<ResponseTravelDto> getAllTravels() {
        return travelMapper.toResponseDto(repository.findAll());
    }

    @Override
    public ResponseTravelDto deleteTravel(String packageId) {
        Optional<Travel> byId = repository.findById(packageId);
        if (byId.isEmpty()) {
            throw new NotFoundException("Package not found");
        } else {
//           TODO:check role( user or admin )
//            Check booking time (Only can delete (cancel) package within 48 hours after booking time)
//            If user -> If time is out of 48 hours -> throw TimeOutException("Booking time is out of 48 hours")
//            If admin -> ok
//            If cancel hotel : add cancellation fee to total of response
            repository.delete(byId.get());
            return travelMapper.toDto(byId.get());
        }
    }

    private Travel validateDetailsOfTravelDto(RequestTravelDto requestTravelDto) {
        WebClient webFluxClient = webClient.build();
        PackageDto packageByPackageName = packageService.findPackageByPackageName(requestTravelDto.getPackageInfo());
        if (packageByPackageName == null) throw new InvalidTravelDetailException("Package not found");
        webFluxClient.get().uri("lb://vehicle-service/api/vehicle", uriBuilder ->
                        uriBuilder.queryParam("vehicleId", requestTravelDto.getVehicleDetails().getVehicleId()).build())
                .retrieve()
                .bodyToMono(Vehicle.class)
                .doOnError(throwable -> {
                    throw new InvalidTravelDetailException("Vehicle not found, Vehicle : " + requestTravelDto.getVehicleDetails().getVehicleId());
                })
                .block();

        webFluxClient.get().uri("lb://hotel-service/api/hotel/" + requestTravelDto.getHotelDetails().getHotelId())
                .retrieve()
                .bodyToMono(Hotel.class)
                .doOnError(throwable -> {
                    throw new InvalidTravelDetailException("Hotel not found, Hotel : " + requestTravelDto.getHotelDetails().getHotelId());
                })
                .block();
        if (requestTravelDto.getGuideDetails() != null && requestTravelDto.getGuideDetails().getGuideId() < 0)
            throw new InvalidTravelDetailException("Given Guide not found");
        else if (requestTravelDto.getGuideDetails().getGuideId() > 0) {
            webFluxClient.get().uri("lb://guide-service/api/guide/" + requestTravelDto.getGuideDetails().getGuideId())
                    .retrieve()
                    .bodyToMono(Guide.class)
                    .doOnError(throwable -> {
                        throw new InvalidTravelDetailException("Guide not found, Guide : " + requestTravelDto.getGuideDetails().getGuideId());
                    })
                    .block();
        }
        return travelMapper.toEntity(requestTravelDto);
    }

    private String createNextId() {
        return String.format("NEXT_%06d", repository.count());
    }
}
