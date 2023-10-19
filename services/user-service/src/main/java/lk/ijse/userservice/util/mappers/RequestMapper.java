package lk.ijse.userservice.util.mappers;

import lk.ijse.userservice.dto.RequestDto;
import lk.ijse.userservice.dto.ResponseDto;
import lk.ijse.userservice.entity.User;
import lk.ijse.userservice.exception.RequestDtoValidationException;
import lk.ijse.userservice.util.validator.RequestDtoValidator;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;

@Mapper(uses = {RequestDtoValidator.class},
        componentModel = "spring")
public interface RequestMapper {
    RequestMapper INSTANCE = Mappers.getMapper(RequestMapper.class);

    @Mapping(source = "nicOrPassport", target = "nicOrPassport.id")
    @Mapping(source = "userId", qualifiedByName = "userId", target = "userId")
    @Mapping(source = "password", qualifiedByName = "password", target = "password")
    @Mapping(source = "frontImg", target = "nicOrPassport.frontImg")
    @Mapping(source = "backImg", target = "nicOrPassport.backImg")
    @Mapping(target = "gender" , expression = "java(lk.ijse.userservice.util.constants.Gender.valueOf(requestDto.getGender().toUpperCase()))")
    User requestDtoToUser(RequestDto requestDto) throws RequestDtoValidationException;


    @InheritInverseConfiguration
    ResponseDto requestDtoToUser(User user);

    default byte[] multipartFileToByteArray(MultipartFile file) throws IOException {
        return file.getBytes();
    }

    default MultipartFile multipartFileToByteArray(byte[] byteArray) throws IOException {
        return new MultipartFile() {
            @Override
            public String getName() {
                return null;
            }

            @Override
            public String getOriginalFilename() {
                return null;
            }

            @Override
            public String getContentType() {
                return null;
            }

            @Override
            public boolean isEmpty() {
                return byteArray == null || byteArray.length == 0;
            }

            @Override
            public long getSize() {
                return byteArray.length;
            }

            @Override
            public byte[] getBytes() throws IOException {
                return byteArray;
            }

            @Override
            public InputStream getInputStream() throws IOException {
                return new ByteArrayInputStream(byteArray);
            }

            @Override
            public void transferTo(File dest) throws IOException, IllegalStateException {
                try (FileOutputStream fileOutputStream = new FileOutputStream(dest);) {
                    fileOutputStream.write(byteArray);
                }
            }
        };
    }

    ResponseDto userToResponseDto(User save);
}
