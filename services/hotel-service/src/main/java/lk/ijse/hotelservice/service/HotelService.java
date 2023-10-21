package lk.ijse.hotelservice.service;


import lk.ijse.hotelservice.dto.RequestDto;
import lk.ijse.hotelservice.dto.ResponseDto;

import java.util.List;

public interface HotelService {
    public void registerVehicle(RequestDto vehicle);

    public void updateVehicle(RequestDto vehicle);

    public void deleteVehicle(long vehicleId);

    public ResponseDto findVehicleByVehicleId(long vehicleId);

    public List<ResponseDto> findAllVehicles();

}
