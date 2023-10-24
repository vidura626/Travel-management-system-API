package lk.ijse.travelservice.service.impl;

import lk.ijse.travelservice.dto.RequestTravelDto;
import lk.ijse.travelservice.dto.ResponseTravelDto;
import lk.ijse.travelservice.entity.Travel;
import lk.ijse.travelservice.exception.InvalidTravelDetailException;
import lk.ijse.travelservice.exception.NotFoundException;
import lk.ijse.travelservice.repository.TravelRepository;
import lk.ijse.travelservice.service.TravelService;
import lk.ijse.travelservice.util.mappers.TravelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class TravelServiceImpl implements TravelService {
    private final TravelRepository repository;
    private final TravelMapper mapper;

    private WebClient.Builder webClient;
    @Autowired
    public TravelServiceImpl(TravelRepository repository, TravelMapper mapper, WebClient.Builder webClient) {
        this.repository = repository;
        this.mapper = mapper;
        this.webClient = webClient;
    }

    @Override
    public List<ResponseTravelDto> findTravelsByPackageName(String packageName) {
        return mapper.toDto(repository.findTravelPackagesByPackageID(packageName));
    }

    @Override
    public String bookingTravel(RequestTravelDto requestTravelDto) {
        Travel travel = validateDetailsOfTravelDto(requestTravelDto);
        if (travel != null) {
            return repository.save(mapper.toEntity(requestTravelDto)).getPackageID();
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
        return mapper.toResponseDto(repository.findAll());
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
            return mapper.toDto(byId.get());
        }
    }

    private Travel validateDetailsOfTravelDto(RequestTravelDto requestTravelDto) {
//        TODO: 1 Check package isExist and correct
//              2 Check vehicle isExist and correct
//              3 Check guide isExist and correct (If needGuide is > 0)
//              4 Check hotel isExist and correct

//        webClient.get()
//                .uri("http://localhost:8082/travel")
//                .retrieve()
//                .bodyToMono(String.class)
//                .block();


//        If there some error throw InvalidTravelDetailException and return null
        throw new InvalidTravelDetailException("Invalid Travel Details");
    }
}
