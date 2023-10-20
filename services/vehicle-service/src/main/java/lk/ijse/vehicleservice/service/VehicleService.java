package lk.ijse.vehicleservice.service;

import lk.ijse.vehicleservice.dto.RequestDto;
import lk.ijse.vehicleservice.dto.ResponseDto;
import lk.ijse.vehicleservice.exception.NotFoundException;
import lk.ijse.vehicleservice.util.constants.Role;

import java.util.List;

public interface VehicleService {
    public void registerUser(RequestDto user);

    public Long updateUser(RequestDto user) throws NotFoundException;

    public ResponseDto deleteUser(String username);

    public ResponseDto findUserByUsername(String username);

    public ResponseDto findUserByEmail(String email);

    public List<ResponseDto> findAllUsers();

    public List<ResponseDto> findUserByRole(Role role);

}
