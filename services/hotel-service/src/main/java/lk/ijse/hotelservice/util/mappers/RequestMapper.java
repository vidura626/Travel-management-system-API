package lk.ijse.hotelservice.util.mappers;

import lk.ijse.hotelservice.dto.RequestDto;
import lk.ijse.hotelservice.dto.ResponseDto;
import lk.ijse.hotelservice.entity.Hotel;
import lk.ijse.hotelservice.exception.RequestDtoValidationException;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface RequestMapper {
    RequestMapper INSTANCE = Mappers.getMapper(RequestMapper.class);

    @Mapping(source = "feeOpt1", target = "fee.feeOpt1")
    @Mapping(source = "feeOpt2", target = "fee.feeOpt2")
    @Mapping(source = "feeOpt3", target = "fee.feeOpt3")
    @Mapping(source = "feeOpt4", target = "fee.feeOpt4")
    @Mapping(source = "contact1", target = "contacts.contact1")
    @Mapping(source = "contact2", target = "contacts.contact2")
    @Mapping(source = "locationName", target = "location.locationName")
    @Mapping(source = "googleMapLocation", target = "location.googleMapLocation")
    Hotel requestDtoToHotel(RequestDto requestDto) throws RequestDtoValidationException;


    @InheritInverseConfiguration
    RequestDto requestDtoToHotel(Hotel hotel) throws RequestDtoValidationException;

    ResponseDto hotelToResponseDto(Hotel hotel);
}
