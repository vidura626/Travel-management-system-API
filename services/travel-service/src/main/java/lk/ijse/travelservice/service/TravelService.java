package lk.ijse.travelservice.service;

import lk.ijse.travelservice.dto.RequestTravelDto;
import lk.ijse.travelservice.dto.ResponseTravelDto;

import java.util.List;

public interface TravelService {
    List<ResponseTravelDto> findTravelsByPackageName(String packageName);

    String bookingTravel(RequestTravelDto requestTravelDto);

    String updateBooking(RequestTravelDto requestTravelDto);

    List<ResponseTravelDto> getAllTravels();

    ResponseTravelDto deleteTravel(String packageId);
}
