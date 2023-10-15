package lk.ijse.userservice.service.impl;

import lk.ijse.userservice.dto.UserDto;
import lk.ijse.userservice.exception.AlreadyExistsException;
import lk.ijse.userservice.exception.NotFoundException;
import lk.ijse.userservice.repository.UserRepository;
import lk.ijse.userservice.service.UserService;
import lk.ijse.userservice.util.constants.Role;
import lk.ijse.userservice.util.convertor.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    @Override
    public void registerUser(UserDto userDto) {
        if (findUserByUsername(userDto.getUsername()) != null)
            throw new AlreadyExistsException("Username already exists");
        userRepository.save(userMapper.userDtoToUser(userDto));
    }

    @Override
    public void updateUser(UserDto user) {
        if (findUserByUsername(user.getUsername()) == null)
            throw new NotFoundException("User not found");
        userRepository.save(userMapper.userDtoToUser(user));
    }

    @Override
    public void deleteUser(String username) {
        UserDto user = findUserByUsername(username);
        if (user == null)
            throw new NotFoundException("User not found");
        userRepository.delete(userMapper.userDtoToUser(user));
    }

    @Override
    public UserDto findUserByUsername(String username) {
        return userMapper.userToUserDto(userRepository.findUserByUsername(username));
    }

    @Override
    public UserDto findUserByEmail(String email) {
        return userMapper.userToUserDto(userRepository.findUserByEmail(email));
    }

    @Override
    public List<UserDto> findAllUsers() {
        return userMapper.userListToUserDtoList(userRepository.findAll());
    }

    @Override
    public List<UserDto> findUserByRole(Role role) {
        return userMapper.userListToUserDtoList(userRepository.findUsersByRole(role));
    }

    @Override
    public void changePassword(String username, String password) {
        UserDto user = findUserByUsername(username);
        if (user == null)
            throw new NotFoundException("User not found");
        user.setPassword(password);
        userRepository.save(userMapper.userDtoToUser(user));
    }

    @Override
    public void changeEmail(String username, String email) {
        UserDto user = findUserByUsername(username);
        if (user == null)
            throw new NotFoundException("User not found");
        user.setEmail(email);
        userRepository.save(userMapper.userDtoToUser(user));
    }

    @Override
    public void changeContact(String username, String contact) {
        UserDto user = findUserByUsername(username);
        if (user == null)
            throw new NotFoundException("User not found");
        user.setContact(contact);
        userRepository.save(userMapper.userDtoToUser(user));
    }
}
