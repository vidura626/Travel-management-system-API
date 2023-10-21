package lk.ijse.hotelservice.service.impl;

import lk.ijse.hotelservice.dto.RequestDto;
import lk.ijse.hotelservice.dto.ResponseDto;
import lk.ijse.hotelservice.entity.Hotel;
import lk.ijse.hotelservice.exception.AlreadyExistsException;
import lk.ijse.hotelservice.exception.NotFoundException;
import lk.ijse.hotelservice.repository.HotelRepository;
import lk.ijse.hotelservice.service.HotelService;
import lk.ijse.hotelservice.util.mappers.RequestMapper;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static java.util.stream.Collectors.toList;

@Slf4j
@Service
public class HotelServiceImpl implements HotelService {

    private final Logger loggeer = LoggerFactory.getLogger(HotelServiceImpl.class);
    private final HotelRepository hotelRepository;
    private final RequestMapper requestMapper;

    @Autowired
    public HotelServiceImpl(HotelRepository hotelRepository, RequestMapper requestMapper) {
        this.hotelRepository = hotelRepository;
        this.requestMapper = requestMapper;
    }

    @Override
    public void registerVehicle(RequestDto requestDto) throws AlreadyExistsException {
        if (hotelRepository.findVehicleByVehicleId(requestDto.getVehicleId()) != null) {
            throw new AlreadyExistsException("Vehicle already exists. Vehicle Id : "
                    + requestDto.getVehicleId());
        } else {
            hotelRepository.save(requestMapper.requestDtoToVehicle(requestDto));
        }
    }

    @Override
    public void updateVehicle(RequestDto vehicle) {
        if (hotelRepository.findVehicleByVehicleId(vehicle.getVehicleId()) == null) {
            throw new NotFoundException("Vehicle not found. Vehicle Id : " + vehicle.getVehicleId());
        } else {
//            For validation
            Hotel hotel1 = requestMapper.requestDtoToVehicle(vehicle);
            ResponseDto responseDto = requestMapper.requestDtoToUser(hotel1);
            responseDto.setAuto(true);
            hotelRepository.save(hotel1);
        }
    }

    @Override
    public void deleteVehicle(long vehicleId) {
        Hotel hotel = hotelRepository.findVehicleByVehicleId(vehicleId);
        if (hotel == null) {
            throw new NotFoundException("Vehicle not found. Vehicle Id : " + vehicleId);
        } else {
            hotelRepository.delete(hotel);
            requestMapper.userToResponseDto(hotel);
        }
    }

    @Override
    public ResponseDto findVehicleByVehicleId(long vehicleId) {
        Hotel hotel = hotelRepository.findVehicleByVehicleId(vehicleId);
        if (hotel == null) {
            throw new NotFoundException("Vehicle not found. Vehicle Id : " + vehicleId);
        } else {
            return requestMapper.userToResponseDto(hotelRepository.findVehicleByVehicleId(vehicleId));
        }
    }

    @Override
    public List<ResponseDto> findAllVehicles() {
        return hotelRepository
                .findAll()
                .stream()
                .map(requestMapper::userToResponseDto)
                .collect(toList());
    }
}
