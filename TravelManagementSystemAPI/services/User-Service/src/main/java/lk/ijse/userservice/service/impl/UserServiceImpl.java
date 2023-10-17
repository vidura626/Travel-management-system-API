package lk.ijse.userservice.service.impl;

import lk.ijse.userservice.dto.RequestDto;
import lk.ijse.userservice.dto.ResponseDto;
import lk.ijse.userservice.entity.User;
import lk.ijse.userservice.exception.AlreadyExistsException;
import lk.ijse.userservice.exception.NotFoundException;
import lk.ijse.userservice.repository.UserRepository;
import lk.ijse.userservice.service.UserService;
import lk.ijse.userservice.util.constants.Role;
import lk.ijse.userservice.util.convertor.RequestMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final RequestMapper requestMapper = RequestMapper.INSTANCE;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void registerUser(RequestDto userDto) {
        if (findUserByUsername(userDto.getUsername()) != null)
            throw new AlreadyExistsException("Username already exists");
//        userRepository.save(requestMapper.userDtoToUser(userDto));
    }

    @Override
    public void updateUser(RequestDto user) {
        if (findUserByUsername(user.getUsername()) == null)
            throw new NotFoundException("User not found");
//        userRepository.save(requestMapper.userDtoToUser(user));
    }

    @Override
    public void deleteUser(String username) {
        ResponseDto user = findUserByUsername(username);
        if (user == null)
            throw new NotFoundException("User not found");
//        userRepository.delete(userMapper.userDtoToUser(user));
    }

    @Override
    public ResponseDto findUserByUsername(String username) {
//        return requestMapper.userToUserDto(userRepository.findUserByUsername(username));
        return null;
    }

    @Override
    public ResponseDto findUserByEmail(String email) {
//        return requestMapper.userToUserDto(userRepository.findUserByEmail(email));
        return null;
    }

    @Override
    public List<ResponseDto> findAllUsers() {
//        return userMapper.userListToUserDtoList(userRepository.findAll());
        return null;
    }

    @Override
    public List<ResponseDto> findUserByRole(Role role) {
        return null;
//        return userMapper.userListToUserDtoList(userRepository.findUsersByRole(role));

    }

    @Override
    public void changePassword(String username, String password) {
        User user = userRepository.findUserByUsername(username);
        if (user == null)
            throw new NotFoundException("User not found");
        user.setPassword(password);
//        userRepository.save(userMapper.userDtoToUser(user));
    }

    @Override
    public void changeEmail(String username, String email) {
        User user = userRepository.findUserByUsername(username);
        if (user == null)
            throw new NotFoundException("User not found");
        user.setEmail(email);
//        userRepository.save(userMapper.userDtoToUser(user));
    }

    @Override
    public void changeContact(String username, String contact) {
        User user = userRepository.findUserByUsername(username);
        if (user == null)
            throw new NotFoundException("User not found");
        user.setContact(contact);
//        userRepository.save(userMapper.userDtoToUser(user));
    }
}
