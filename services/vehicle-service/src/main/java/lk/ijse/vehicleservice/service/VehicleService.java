package lk.ijse.vehicleservice.service;

import lk.ijse.vehicleservice.dto.RequestDto;
import lk.ijse.vehicleservice.dto.ResponseDto;
import lk.ijse.vehicleservice.exception.AlreadyExistsException;
import lk.ijse.vehicleservice.exception.NotFoundException;

import java.util.List;

public interface VehicleService {
    public void registerVehicle(RequestDto vehicle) throws AlreadyExistsException;

    public Long updateVehicle(RequestDto vehicle) throws NotFoundException;

    public ResponseDto deleteVehicle(long vehicleId) throws NotFoundException;;

    public ResponseDto findVehicleByVehicleId(long vehicleId) throws NotFoundException;

    public List<ResponseDto> findAllVehicles();

}
