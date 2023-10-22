package lk.ijse.travelservice.service;



import java.util.List;

public interface PackageDetailsService {
    public void registerHotel(RequestDto vehicle);

    public void updateHotel(RequestDto vehicle);

    public void deleteHotel(long vehicleId);

    public ResponseDto findHotelByHotelId(long vehicleId);

    public List<ResponseDto> findAllHotels();

    public List<ResponseDto> findHotelsByHotelCategory(int hotelCategory);
}
