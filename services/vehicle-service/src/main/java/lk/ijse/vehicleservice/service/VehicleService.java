package lk.ijse.vehicleservice.service;

import lk.ijse.vehicleservice.dto.RequestDto;
import lk.ijse.vehicleservice.dto.ResponseDto;

import java.util.List;

public interface VehicleService {
    public void registerVehicle(RequestDto vehicle);

    public void updateVehicle(RequestDto vehicle);

    public void deleteVehicle(long vehicleId);

    public ResponseDto findVehicleByVehicleId(long vehicleId);

    public List<ResponseDto> findAllVehicles();

}
