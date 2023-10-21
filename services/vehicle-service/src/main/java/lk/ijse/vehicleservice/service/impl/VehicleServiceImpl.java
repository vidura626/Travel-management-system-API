package lk.ijse.vehicleservice.service.impl;

import jakarta.validation.Valid;
import lk.ijse.vehicleservice.dto.RequestDto;
import lk.ijse.vehicleservice.dto.ResponseDto;
import lk.ijse.vehicleservice.entity.Vehicle;
import lk.ijse.vehicleservice.exception.AlreadyExistsException;
import lk.ijse.vehicleservice.exception.NotFoundException;
import lk.ijse.vehicleservice.repository.VehicleRepository;
import lk.ijse.vehicleservice.service.VehicleService;
import lk.ijse.vehicleservice.util.mappers.RequestMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static java.util.stream.Collectors.mapping;
import static java.util.stream.Collectors.toList;

@Service
public class VehicleServiceImpl implements VehicleService {
    private final VehicleRepository vehicleRepository;
    private final RequestMapper requestMapper;

    @Autowired
    public VehicleServiceImpl(VehicleRepository vehicleRepository, RequestMapper requestMapper) {
        this.vehicleRepository = vehicleRepository;
        this.requestMapper = requestMapper;
    }

    @Override
    public void registerVehicle(RequestDto requestDto) throws AlreadyExistsException {
        if (vehicleRepository.findVehicleByVehicleId(requestDto.getVehicleId()) != null) {
            throw new AlreadyExistsException("Vehicle already exists. Vehicle Id : "
                    + requestDto.getVehicleId());
        } else {
            vehicleRepository.save(requestMapper.requestDtoToVehicle(requestDto));
        }
    }

    @Override
    public void updateVehicle(RequestDto vehicle) {
        if (vehicleRepository.findVehicleByVehicleId(vehicle.getVehicleId()) == null) {
            throw new NotFoundException("Vehicle not found. Vehicle Id : " + vehicle.getVehicleId());
        } else {
//            For validation
            Vehicle vehicle1 = requestMapper.requestDtoToVehicle(vehicle);
            ResponseDto responseDto = requestMapper.requestDtoToUser(vehicle1);
            responseDto.setAuto(true);
            vehicleRepository.save(vehicle1);
        }
    }

    @Override
    public ResponseDto deleteVehicle(long vehicleId) {
        Vehicle vehicle = vehicleRepository.findVehicleByVehicleId(vehicleId);
        if (vehicle == null) {
            throw new NotFoundException("Vehicle not found. Vehicle Id : " + vehicleId);
        } else {
            vehicleRepository.delete(vehicle);
            return requestMapper.userToResponseDto(vehicle);
        }
    }

    @Override
    public ResponseDto findVehicleByVehicleId(long vehicleId) {
        Vehicle vehicle = vehicleRepository.findVehicleByVehicleId(vehicleId);
        if (vehicle == null) {
            throw new NotFoundException("Vehicle not found. Vehicle Id : " + vehicleId);
        } else {
            return requestMapper.userToResponseDto(vehicleRepository.findVehicleByVehicleId(vehicleId));
        }
    }

    @Override
    public List<ResponseDto> findAllVehicles() {
        return vehicleRepository
                .findAll()
                .stream()
                .map(requestMapper::userToResponseDto)
                .collect(toList());
    }
}
