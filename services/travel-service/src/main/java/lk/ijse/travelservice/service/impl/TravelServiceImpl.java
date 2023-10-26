package lk.ijse.travelservice.service.impl;

import lk.ijse.travelservice.dto.PackageDto;
import lk.ijse.travelservice.dto.RequestTravelDto;
import lk.ijse.travelservice.dto.ResponseTravelDto;
import lk.ijse.travelservice.dto.guide.Guide;
import lk.ijse.travelservice.dto.hotel.Hotel;
import lk.ijse.travelservice.dto.vehicle.Vehicle;
import lk.ijse.travelservice.entity.Package;
import lk.ijse.travelservice.entity.Travel;
import lk.ijse.travelservice.entity.embedded.PackageValueDetails;
import lk.ijse.travelservice.exception.InvalidTravelDetailException;
import lk.ijse.travelservice.exception.NotFoundException;
import lk.ijse.travelservice.exception.TimeOutException;
import lk.ijse.travelservice.repository.TravelRepository;
import lk.ijse.travelservice.service.PackageService;
import lk.ijse.travelservice.service.TravelService;
import lk.ijse.travelservice.util.constants.TravelStatus;
import lk.ijse.travelservice.util.mappers.PackageMapper;
import lk.ijse.travelservice.util.mappers.TravelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.*;

@Service
@Transactional
public class TravelServiceImpl implements TravelService {
    private final TravelRepository repository;
    private PackageService packageService;
    private final TravelMapper travelMapper;
    private final PackageMapper packageMapper;

    private WebClient.Builder webClient;

    @Autowired
    public TravelServiceImpl(TravelRepository repository, @Lazy PackageService packageService,
                             TravelMapper travelMapper, PackageMapper packageMapper, WebClient.Builder webClient) {
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
        Optional<Travel> travelByIdOptional = repository.findById(requestTravelDto.getPackageID());
        if (travelByIdOptional.isEmpty()) {
            throw new NotFoundException("Package not found");
        } else {

            if (canModifyBookings(travelByIdOptional.get())) {
                Travel travel = validateDetailsOfTravelDto(requestTravelDto, travelByIdOptional.get());
                if (travel != null) {
                    return repository.save(travel).getPackageID();
                }
            } else throw new TimeOutException("Booking time is out of 48 hours");
        }
        return null;
    }

    private boolean canModifyBookings(Travel travel) {
        Date bookingDate = travel.getBookingDate();
        return bookingDate.compareTo(new Date(bookingDate.getTime() + 48 * 60 * 60 * 1000)) <= 0;
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
            if (canModifyBookings(byId.get())) {
                repository.delete(byId.get());
                return travelMapper.toDto(byId.get());
            } else throw new TimeOutException("Booking time is out of 48 hours");
        }
    }

    @Override
    public Map<TravelStatus, Boolean> hasActiveTravels(long userId) {
        List<Travel> travelsByUserIDIn = repository.findTravelsByUserID(userId);
        HashMap<TravelStatus, Boolean> stringBooleanHashMap = new HashMap<>();
        return Map.of(
                TravelStatus.BOOKING_TRAVEL,
                travelsByUserIDIn.stream().filter(this::canModifyBookings).toList().size() > 0,
                TravelStatus.ACTIVE_TRAVEL,
                travelsByUserIDIn.stream().filter(this::checkActiveTravels).toList().size() > 0
        );
    }

    @Override
    public boolean checkActiveTravels(Travel travel) {
        Date startDate = travel.getTravelDuration().getStartDate();
        Date endDate = travel.getTravelDuration().getEndDate();
        return true;
    }

    private Travel validateDetailsOfTravelDto(RequestTravelDto requestTravelDto) {
        WebClient webFluxClient = webClient.build();

        validatePackageInfo(requestTravelDto);
        validateVehicle(requestTravelDto, webFluxClient);
        validateHotelInfo(requestTravelDto, webFluxClient);
        validateGuideInfo(requestTravelDto, webFluxClient);

        return travelMapper.toEntity(requestTravelDto);
    }

    private Travel validateDetailsOfTravelDto(RequestTravelDto requestTravelDto, Travel travel) {
        WebClient webFluxClient = webClient.build();

        validatePackageInfo(requestTravelDto);
        validateVehicle(requestTravelDto, webFluxClient);
        Hotel hotel = validateHotelInfo(requestTravelDto, webFluxClient);
        validateGuideInfo(requestTravelDto, webFluxClient);

        //        Check hotel and pets
        if (!(travel.getWithPets() && hotel.getIsPetsAllowed()))
            throw new InvalidTravelDetailException("Pets are not allowed with this hotel. Hotel id : " + hotel.getHotelId());

        if (travel.getHotelDetails().getHotelId() != hotel.getHotelId()) setCancellationFee(travel);

        return travelMapper.toEntity(requestTravelDto);
    }

    private void setCancellationFee(Travel travel) {
        if (travel.getPackageValueDetails() == null) travel.setPackageValueDetails(new PackageValueDetails());
        travel.getPackageValueDetails().setTotalHotelCancellationFees(
                travel.getPackageValueDetails().getTotalHotelCancellationFees()
                        + travel.getHotelDetails().getHotelCancellationFee());
    }


    private Package validatePackageInfo(RequestTravelDto requestTravelDto) {
        PackageDto packageByPackageName = packageService.findPackageByPackageName(requestTravelDto.getPackageInfo());
        if (packageByPackageName == null) throw new InvalidTravelDetailException("Package not found");
        return packageMapper.toEntity(packageByPackageName);
    }

    private Guide validateGuideInfo(RequestTravelDto requestTravelDto, WebClient webFluxClient) {
        if (requestTravelDto.getGuideDetails() != null && requestTravelDto.getGuideDetails().getGuideId() < 0)
            throw new InvalidTravelDetailException("Given Guide not found");
        else {
            assert requestTravelDto.getGuideDetails() != null;
            if (requestTravelDto.getGuideDetails().getGuideId() > 0) {
                return webFluxClient.get().uri("lb://guide-service/api/guide/" + requestTravelDto.getGuideDetails().getGuideId())
                        .retrieve()
                        .bodyToMono(Guide.class)
                        .doOnError(throwable -> {
                            throw new InvalidTravelDetailException("Guide not found, Guide : " + requestTravelDto.getGuideDetails().getGuideId());
                        })
                        .block();
            }
        }
        return null;
    }

    private Hotel validateHotelInfo(RequestTravelDto requestTravelDto, WebClient webFluxClient) {
        return webFluxClient.get().uri("lb://hotel-service/api/hotel/" + requestTravelDto.getHotelDetails().getHotelId())
                .retrieve()
                .bodyToMono(Hotel.class)
                .doOnError(throwable -> {
                    throw new InvalidTravelDetailException("Hotel not found, Hotel : " + requestTravelDto.getHotelDetails().getHotelId());
                })
                .block();
    }

    private Vehicle validateVehicle(RequestTravelDto requestTravelDto, WebClient webFluxClient) {
        return webFluxClient.get().uri("lb://vehicle-service/api/vehicle", uriBuilder ->
                        uriBuilder.queryParam("vehicleId", requestTravelDto.getVehicleDetails().getVehicleId()).build())
                .retrieve()
                .bodyToMono(Vehicle.class)
                .doOnError(throwable -> {
                    throw new InvalidTravelDetailException("Vehicle not found, Vehicle : " + requestTravelDto.getVehicleDetails().getVehicleId());
                })
                .block();
    }

    private String createNextId() {
        return String.format("NEXT_%06d", repository.count());
    }
}
