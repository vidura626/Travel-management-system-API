package lk.ijse.vehicleservice.util.mappers;

import jakarta.validation.Valid;
import lk.ijse.vehicleservice.dto.RequestDto;
import lk.ijse.vehicleservice.dto.ResponseDto;
import lk.ijse.vehicleservice.entity.Vehicle;
import lk.ijse.vehicleservice.exception.RequestDtoValidationException;
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
    @Mapping(target = "fee.feeForDay", source = "feeForDay")
    @Mapping(target = "fee.feeFor1km", source = "feeFor1km")
    @Mapping(target = "fuelType", expression = "java(lk.ijse.vehicleservice.util.constants.FuelType.valueOf(requestDto.getFuelType().toString().toUpperCase()))")
    Vehicle requestDtoToVehicle(RequestDto requestDto) throws RequestDtoValidationException;


    @InheritInverseConfiguration
    ResponseDto requestDtoToUser(Vehicle vehicle) throws RequestDtoValidationException;

    default String multipartFileToBase64String(MultipartFile file) throws IOException {
        return Base64.getEncoder().encodeToString(file.getBytes());
    }

    ResponseDto userToResponseDto(Vehicle vehicle);
}
