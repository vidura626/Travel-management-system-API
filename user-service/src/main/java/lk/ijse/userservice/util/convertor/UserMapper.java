package lk.ijse.userservice.util.convertor;

import lk.ijse.userservice.dto.UserDto;
import lk.ijse.userservice.dto.request.RequestDto;
import lk.ijse.userservice.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
@Component
public interface UserMapper {
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    UserDto requestDtoToUserDto(RequestDto requestDto);

    User userDtoToUser(UserDto userDto);

    UserDto userToUserDto(User user);

    List<UserDto> userListToUserDtoList(List<User> users);
}
