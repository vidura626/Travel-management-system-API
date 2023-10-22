package lk.ijse.travelservice.service.impl;

import lk.ijse.travelservice.dto.RequestTravelDto;
import lk.ijse.travelservice.entity.Travel;
import lk.ijse.travelservice.exception.InvalidTravelDetailException;
import lk.ijse.travelservice.exception.NotFoundException;
import lk.ijse.travelservice.repository.TravelRepository;
import lk.ijse.travelservice.service.TravelService;
import lk.ijse.travelservice.util.mappers.TravelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class TravelServiceImpl implements TravelService {
    private final TravelRepository repository;
    private final TravelMapper mapper;

    @Autowired
    public TravelServiceImpl(TravelRepository repository, TravelMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public List<RequestTravelDto> findTravelsByPackageName(String packageName) {
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
//           TODO: Check booking time

            Travel travel = validateDetailsOfTravelDto(requestTravelDto);
            if (travel != null) {
//                TODO: If cancel hotel add cancellation fee to travel package
//                TODO: If change check about pets and validate
                return repository.save(mapper.toEntity(requestTravelDto)).getPackageID();
            }
        }
        return null;
    }

    private Travel validateDetailsOfTravelDto(RequestTravelDto requestTravelDto) {
//        TODO: 1 Check package isExist and correct
//        TODO: 2 Check vehicle isExist and correct
//        TODO: 3 Check guide isExist and correct (If needGuide is > 0)
//        TODO: 4 Check hotel isExist and correct

//        If there some error throw InvalidTravelDetailException and return null
        return null;
    }
}
