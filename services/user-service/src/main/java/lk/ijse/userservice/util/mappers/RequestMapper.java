package lk.ijse.userservice.util.mappers;

import lk.ijse.userservice.dto.RequestDto;
import lk.ijse.userservice.dto.ResponseDto;
import lk.ijse.userservice.entity.User;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;

@Mapper(componentModel = "spring")
public interface RequestMapper {
    RequestMapper INSTANCE = Mappers.getMapper(RequestMapper.class);


    @Mapping(source = "nicOrPassport", target = "nicOrPassport.id")
    @Mapping(source = "frontImg", target = "nicOrPassport.frontImg")
    @Mapping(source = "backImg", target = "nicOrPassport.backImg")
    User requestDtoToUser(RequestDto requestDto);

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
}
