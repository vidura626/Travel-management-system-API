package lk.ijse.hotelservice.util.mappers;

import lk.ijse.hotelservice.dto.RequestDto;
import lk.ijse.hotelservice.dto.ResponseDto;
import lk.ijse.hotelservice.entity.Hotel;
import lk.ijse.hotelservice.exception.RequestDtoValidationException;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Base64;

@Mapper(componentModel = "spring")
public interface RequestMapper {
    RequestMapper INSTANCE = Mappers.getMapper(RequestMapper.class);


    @Mapping(target = "driver.driverName", source = "driverName")
    @Mapping(target = "driver.licenceFrontImg", source = "licenceFrontImg")
    @Mapping(target = "driver.licenceBackImg", source = "licenceBackImg")
    @Mapping(target = "images.frontImg", source = "frontImg")
    @Mapping(target = "images.backImg", source = "backImg")
    @Mapping(target = "images.rearImg", source = "rearImg")
    @Mapping(target = "fuelType", expression = "java(lk.ijse.vehicleservice.util.constants.FuelType.valueOf(requestDto.getFuelType().toString().toUpperCase()))")
    Hotel requestDtoToVehicle(RequestDto requestDto) throws RequestDtoValidationException;


    @InheritInverseConfiguration
    ResponseDto requestDtoToUser(Hotel hotel) throws RequestDtoValidationException;

    default String multipartFileToBase64String(MultipartFile file) throws IOException {
        return Base64.getEncoder().encodeToString(file.getBytes());
    }

    ResponseDto userToResponseDto(Hotel hotel);
}
