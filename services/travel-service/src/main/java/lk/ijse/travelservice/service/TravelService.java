package lk.ijse.travelservice.service;

import lk.ijse.travelservice.dto.RequestTravelDto;

import java.util.List;

public interface TravelService {
    List<RequestTravelDto> findTravelsByPackageName(String packageName);

    String bookingTravel(RequestTravelDto requestTravelDto);

    String updateBooking(RequestTravelDto requestTravelDto);
}
