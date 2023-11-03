package lk.ijse.userservice.util.mappers;

import jakarta.validation.Valid;
import lk.ijse.userservice.dto.RequestDto;
import lk.ijse.userservice.dto.ResponseDto;
import lk.ijse.userservice.dto.embedded.NICorPassportDto;
import lk.ijse.userservice.entity.User;
import lk.ijse.userservice.entity.embedded.NICorPassport;
import lk.ijse.userservice.exception.RequestDtoValidationException;
import lk.ijse.userservice.util.validator.RequestDtoValidator;
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
    @Mapping(source = "userId", qualifiedByName = "userId", target = "userId")
    @Mapping(source = "password", qualifiedByName = "password", target = "password")
    @Mapping(source = "frontImg", target = "nicOrPassport.frontImg")
    @Mapping(source = "backImg", target = "nicOrPassport.backImg")
    @Mapping(target = "gender", expression = "java(lk.ijse.userservice.util.constants.Gender.valueOf(requestDto.getGender().toUpperCase()))")
    @Mapping(target = "role", expression = "java(lk.ijse.userservice.util.constants.Role.valueOf(\"ROLE_\" + requestDto.getRole().toUpperCase()))")
    User requestDtoToUser(RequestDto requestDto) throws RequestDtoValidationException;


    @InheritInverseConfiguration
    ResponseDto requestDtoToUser(User user);


    default String multipartFileToByteArray(MultipartFile file) throws IOException {
        return Base64.getEncoder().encodeToString(file.getBytes());
    }

    @Mapping(source = "nicOrPassport", target = "niCorPassportDto")
    ResponseDto userToResponseDto(@Valid User user);

    NICorPassportDto requestDtoToNicOrPassportDto(NICorPassport niCorPassport);
}
