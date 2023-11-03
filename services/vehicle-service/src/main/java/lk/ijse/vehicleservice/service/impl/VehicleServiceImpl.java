package lk.ijse.vehicleservice.service.impl;

import lk.ijse.vehicleservice.dto.RequestDto;
import lk.ijse.vehicleservice.dto.ResponseDto;
import lk.ijse.vehicleservice.entity.Vehicle;
import lk.ijse.vehicleservice.exception.AlreadyExistsException;
import lk.ijse.vehicleservice.exception.NotFoundException;
import lk.ijse.vehicleservice.repository.VehicleRepository;
import lk.ijse.vehicleservice.service.VehicleService;
import lk.ijse.vehicleservice.util.mappers.RequestMapper;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static java.util.stream.Collectors.toList;

@Slf4j
@Service
public class VehicleServiceImpl implements VehicleService {

    private final Logger loggeer = LoggerFactory.getLogger(VehicleServiceImpl.class);
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
            vehicleRepository.save(vehicle1);
        }
    }

    @Override
    public void deleteVehicle(long vehicleId) {
        Vehicle vehicle = vehicleRepository.findVehicleByVehicleId(vehicleId);
        if (vehicle == null) {
            throw new NotFoundException("Vehicle not found. Vehicle Id : " + vehicleId);
        } else {
            vehicleRepository.delete(vehicle);
            requestMapper.userToResponseDto(vehicle);
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

    @Override
    public ResponseDto findById(long vehicleId) {
        Vehicle vehicle = vehicleRepository.findVehicleByVehicleId(vehicleId);
        if (vehicle == null) {
            throw new NotFoundException("Vehicle not found. Vehicle Id : " + vehicleId);
        } else {
            return requestMapper.userToResponseDto(vehicle);
        }
    }
}
