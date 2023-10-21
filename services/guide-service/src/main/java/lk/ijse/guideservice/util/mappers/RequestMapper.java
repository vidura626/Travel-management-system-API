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
    @Mapping(source = "password", qualifiedByName = "password", target = "password")
    @Mapping(source = "frontImg", target = "nicOrPassport.frontImg")
    @Mapping(source = "backImg", target = "nicOrPassport.backImg")
    @Mapping(target = "gender", expression = "java(lk.ijse.userservice.util.constants.Gender.valueOf(requestDto.getGender().toUpperCase()))")
    Guide guideToRequestDto(RequestDto requestDto) throws RequestDtoValidationException;


    @InheritInverseConfiguration
    ResponseDto guideToRequestDto(Guide guide);

    default String multipartFileToByteArray(MultipartFile file) throws IOException {
        return Base64.getEncoder().encodeToString(file.getBytes());
    }

   /* default MultipartFile multipartFileToByteArray(byte[] byteArray) throws IOException {
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
    }*/

    @Mapping(source = "nicOrPassport", target = "niCorPassportDto")
    ResponseDto guideToResponseDto(@Valid Guide guide);

    NICorPassportDto nicToNicDto(NICorPassport niCorPassport);
}
