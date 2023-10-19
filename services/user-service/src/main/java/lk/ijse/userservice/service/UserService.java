package lk.ijse.userservice.service;

import lk.ijse.userservice.dto.RequestDto;
import lk.ijse.userservice.dto.ResponseDto;
import lk.ijse.userservice.exception.NotFoundException;
import lk.ijse.userservice.util.constants.Role;

import java.util.List;
import java.util.Locale;

public interface UserService {
    public void registerUser(RequestDto user);

    public Long updateUser(RequestDto user) throws NotFoundException;

    public void deleteUser(String username);

    public ResponseDto findUserByUsername(String username);

    public ResponseDto findUserByEmail(String email);

    public List<ResponseDto> findAllUsers();

    public List<ResponseDto> findUserByRole(Role role);

    public void changePassword(String username, String password);

    public void changeEmail(String username, String email);

    public void changeContact(String username, String contact);
}
