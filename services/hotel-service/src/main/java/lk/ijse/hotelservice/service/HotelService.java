package lk.ijse.hotelservice.service;


import lk.ijse.hotelservice.dto.RequestDto;
import lk.ijse.hotelservice.dto.ResponseDto;

import java.util.List;

public interface HotelService {
    public void registerHotel(RequestDto vehicle);

    public void updateHotel(RequestDto vehicle);

    public void deleteHotel(long vehicleId);

    public ResponseDto findHotelByHotelId(long vehicleId);

    public List<ResponseDto> findAllHotels();

    public List<ResponseDto> findHotelsByHotelCategory(int hotelCategory);
}
