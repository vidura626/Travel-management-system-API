package lk.ijse.vehicleservice.service.impl;

import lk.ijse.vehicleservice.dto.RequestDto;
import lk.ijse.vehicleservice.dto.ResponseDto;
import lk.ijse.vehicleservice.exception.AlreadyExistsException;
import lk.ijse.vehicleservice.exception.NotFoundException;
import lk.ijse.vehicleservice.repository.UserRepository;
import lk.ijse.vehicleservice.service.VehicleService;
import lk.ijse.vehicleservice.util.constants.Role;
import lk.ijse.vehicleservice.util.mappers.RequestMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static java.util.stream.Collectors.toList;

@Service
public class VehicleServiceImpl implements VehicleService {
    private final UserRepository userRepository;
    @Autowired
    private final RequestMapper requestMapper;

    @Autowired
    public VehicleServiceImpl(UserRepository userRepository, RequestMapper requestMapper) {
        this.userRepository = userRepository;
        this.requestMapper = requestMapper;
    }

    @Override
    public void registerUser(RequestDto requestDto) {
        if (userRepository.findUserByUsername(requestDto.getUsername()) != null) {
            throw new AlreadyExistsException("Username already exists. Username : " + requestDto.getUsername());
        } else {
            userRepository.save(requestMapper.requestDtoToUser(requestDto));
        }
    }

    @Override
    public Long updateUser(RequestDto user) {
        User userByUsername = userRepository.findUserByUsername(user.getUsername());
        if (userByUsername == null) {
            throw new NotFoundException("User not found. Username : " + user.getUsername());
        } else {
            if (user.getPassword() != null) {
            } else {
                user.setPassword(userByUsername.getPassword());
            }
            userRepository.save(requestMapper.requestDtoToUser(user));
            return user.getUserId();
        }
    }

    @Override
    public ResponseDto deleteUser(String username) {
        User user = userRepository.findUserByUsername(username);
        if (user == null) {
            throw new NotFoundException("User not found. Username : " + username);
        } else {
            userRepository.delete(user);
            return requestMapper.userToResponseDto(user);
        }
    }

    @Override
    public ResponseDto findUserByUsername(String username) {
        User user = userRepository.findUserByUsername(username);
        if (user == null) {
            throw new NotFoundException("User not found. Username : " + username);
        } else {
            return requestMapper.userToResponseDto(userRepository.findUserByUsername(username));
        }
    }

    @Override
    public ResponseDto findUserByEmail(String email) {
        User user = userRepository.findUserByEmail(email);
        if (user == null) {
            throw new NotFoundException("User not found. Email : " + email);
        } else {
            return requestMapper.userToResponseDto(user);
        }
    }

    @Override
    public List<ResponseDto> findAllUsers() {
        return userRepository
                .findAll()
                .stream()
                .map(requestMapper::userToResponseDto)
                .collect(toList());
    }

    @Override
    public List<ResponseDto> findUserByRole(Role role) {
        return userRepository
                .findUsersByRole(role.toString())
                .stream()
                .map(requestMapper::userToResponseDto)
                .collect(toList());
    }
}
