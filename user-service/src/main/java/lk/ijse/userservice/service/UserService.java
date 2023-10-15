package lk.ijse.userservice.service;

import lk.ijse.userservice.dto.UserDto;
import lk.ijse.userservice.dto.request.RequestDto;
import lk.ijse.userservice.dto.response.ResponseDto;
import lk.ijse.userservice.exception.NotFoundException;
import lk.ijse.userservice.util.constants.Role;

import java.util.List;


public interface UserService {
    public void registerUser(UserDto user);

    public void updateUser(UserDto user) throws NotFoundException;

    public void deleteUser(String username);

    public UserDto findUserByUsername(String username);

    public UserDto findUserByEmail(String email);

    public List<UserDto> findAllUsers();

    public List<UserDto> findUserByRole(Role role);

    public void changePassword(String username, String password);

    public void changeEmail(String username, String email);

    public void changeContact(String username, String contact);
}
