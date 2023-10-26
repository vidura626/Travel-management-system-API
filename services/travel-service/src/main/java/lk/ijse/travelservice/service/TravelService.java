package lk.ijse.travelservice.service;

import lk.ijse.travelservice.dto.RequestTravelDto;
import lk.ijse.travelservice.dto.ResponseTravelDto;
import lk.ijse.travelservice.entity.Travel;
import lk.ijse.travelservice.util.constants.TravelStatus;

import java.util.List;
import java.util.Map;

public interface TravelService {
    List<ResponseTravelDto> findTravelsByPackageName(String packageName);

    String bookingTravel(RequestTravelDto requestTravelDto);

    String updateBooking(RequestTravelDto requestTravelDto);

    List<ResponseTravelDto> getAllTravels();

    ResponseTravelDto deleteTravel(String packageId);

    Map<TravelStatus, Boolean> hasActiveTravels(long userId);

    boolean checkActiveTravels(Travel travel);
}
