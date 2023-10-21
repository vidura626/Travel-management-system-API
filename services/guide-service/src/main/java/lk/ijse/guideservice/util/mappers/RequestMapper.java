package lk.ijse.guideservice.util.mappers;

import jakarta.validation.Valid;
import lk.ijse.guideservice.dto.RequestDto;
import lk.ijse.guideservice.dto.ResponseDto;
import lk.ijse.guideservice.dto.embedded.NICorPassportDto;
import lk.ijse.guideservice.entity.Guide;
import lk.ijse.guideservice.entity.embedded.NICorPassport;
import lk.ijse.guideservice.exception.RequestDtoValidationException;
import lk.ijse.guideservice.util.validator.RequestDtoValidator;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Base64;

@Mapper(uses = {RequestDtoValidator.class},
        componentModel = "spring")
public interface RequestMapper {
    RequestMapper INSTANCE = Mappers.getMapper(RequestMapper.class);

    @Mapping(source = "nicOrPassport", target = "nicOrPassport.id")
    @Mapping(source = "guideId", qualifiedByName = "guide", target = "guideId")
    @Mapping(source = "frontImg", target = "nicOrPassport.frontImg")
    @Mapping(source = "backImg", target = "nicOrPassport.backImg")
    @Mapping(target = "gender", expression = "java(lk.ijse.guideservice.util.constants.Gender.valueOf(requestDto.getGender().toUpperCase()))")
    Guide guideToRequestDto(RequestDto requestDto) throws RequestDtoValidationException;


    @InheritInverseConfiguration
    ResponseDto guideToRequestDto(Guide guide);

    default String multipartFileToByteArray(MultipartFile file) throws IOException {
        return Base64.getEncoder().encodeToString(file.getBytes());
    }

    @Mapping(source = "nicOrPassport", target = "nicOrPassportDto")
    ResponseDto guideToResponseDto(@Valid Guide guide);

    NICorPassportDto nicToNicDto(NICorPassport niCorPassport);
}
