package lk.ijse.travelservice.service.impl;

import lk.ijse.travelservice.entity.TravelPackage;
import lk.ijse.travelservice.exception.AlreadyExistsException;
import lk.ijse.travelservice.exception.NotFoundException;
import lk.ijse.travelservice.repository.HotelRepository;
import lk.ijse.travelservice.service.HotelService;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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
    public void registerHotel(RequestDto requestDto) throws AlreadyExistsException {
        if (hotelRepository.findById(requestDto.getHotelId()).isPresent()) {
            throw new AlreadyExistsException("Hotel already exists. Hotel Id : " + requestDto.getHotelId());
        } else {
            hotelRepository.save(requestMapper.requestDtoToHotel(requestDto));
        }
    }

    @Override
    public void updateHotel(RequestDto requestDto) {
        Optional<TravelPackage> byId = hotelRepository.findById(requestDto.getHotelId());
        if (byId.isEmpty()) {
            throw new NotFoundException("Hotel not found. Hotel Id : " + requestDto.getHotelId());
        } else {
            hotelRepository.save(requestMapper.requestDtoToHotel(requestDto));
        }
    }

    @Override
    public void deleteHotel(long hotelId) {
        Optional<TravelPackage> byId = hotelRepository.findById(hotelId);
        if (byId.isEmpty()) {
            throw new NotFoundException("Hotel not found. Hotel Id : " + hotelId);
        } else {
            hotelRepository.delete(byId.get());
        }
    }

    @Override
    public ResponseDto findHotelByHotelId(long hotelId) {
        Optional<TravelPackage> byId = hotelRepository.findById(hotelId);
        if (byId.isEmpty()) {
            throw new NotFoundException("Hotel not found. Hotel Id : " + hotelId);
        } else {
            return requestMapper.hotelToResponseDto(byId.get());
        }
    }

    @Override
    public List<ResponseDto> findAllHotels() {
        return hotelRepository.findAll().stream().map(requestMapper::hotelToResponseDto).collect(toList());
    }

    @Override
    public List<ResponseDto> findHotelsByHotelCategory(int hotelCategory) {
        return hotelRepository.findHotelsByHotelCategory(hotelCategory)
                .stream()
                .map(requestMapper::hotelToResponseDto)
                .collect(toList());
    }
}
