package lk.ijse.userservice.service.impl;

import lk.ijse.userservice.dto.RequestDto;
import lk.ijse.userservice.dto.ResponseDto;
import lk.ijse.userservice.entity.User;
import lk.ijse.userservice.exception.AlreadyExistsException;
import lk.ijse.userservice.exception.AlreadyInTravelException;
import lk.ijse.userservice.exception.NotFoundException;
import lk.ijse.userservice.repository.UserRepository;
import lk.ijse.userservice.service.UserService;
import lk.ijse.userservice.util.constants.Role;
import lk.ijse.userservice.util.constants.TravelStatus;
import lk.ijse.userservice.util.mappers.RequestMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;
import java.util.Map;

import static java.util.stream.Collectors.toList;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final RequestMapper requestMapper;
    private final WebClient.Builder webClient;
    private PasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, RequestMapper requestMapper, WebClient.Builder webClient, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.requestMapper = requestMapper;
        this.webClient = webClient;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void registerUser(RequestDto requestDto) {
        if (requestDto.getPassword() == null)
            throw new RuntimeException("Password cannot be null");
        else if (userRepository.findUserByUsername(requestDto.getUsername()) != null)
            throw new AlreadyExistsException("Username already exists. Username : " + requestDto.getUsername());
        else {
            requestDto.setPassword(passwordEncoder.encode(requestDto.getPassword()));
            userRepository.save(requestMapper.requestDtoToUser(requestDto));
        }
    }

    @Override
    public Long updateUser(RequestDto requestUserUpdateDetails) {
        User dbUser = userRepository.findUserByUsername(requestUserUpdateDetails.getUsername());
        if (dbUser == null) {
            throw new NotFoundException("User not found. Username : " + requestUserUpdateDetails.getUsername());
        } else {
            WebClient webFluxClient = webClient.build();

            Map hasActiveTravels = webFluxClient.get().uri("lb://travel-service/api/travels/hasActiveTravels",
                            uriBuilder -> uriBuilder.queryParam("userId", requestUserUpdateDetails.getUserId()).build())
                    .retrieve()
                    .bodyToMono(Map.class)
                    .doOnError(throwable -> {
                        throw new RuntimeException("Unknown error at webflux call with travel-service/api/travels/hasActiveTravels Username : " + requestUserUpdateDetails.getUsername());
                    })
                    .block();

            if (!Boolean.TRUE.equals(hasActiveTravels.get(TravelStatus.ACTIVE_TRAVEL))) {
//                Only can update email and contact details within 48 hours after booking a package
                if (requestUserUpdateDetails.getPassword() == null) {
                    requestUserUpdateDetails.setPassword(dbUser.getPassword());
                } else {
                    requestUserUpdateDetails.setPassword(passwordEncoder.encode(requestUserUpdateDetails.getPassword()));
                }
                userRepository.save(requestMapper.requestDtoToUser(requestUserUpdateDetails));
                return requestUserUpdateDetails.getUserId();
            } else if (!Boolean.TRUE.equals(hasActiveTravels.get(TravelStatus.BOOKING_TRAVEL))) {
                checkUpdateInformation(requestUserUpdateDetails, dbUser, TravelStatus.ACTIVE_TRAVEL);
                userRepository.save(requestMapper.requestDtoToUser(requestUserUpdateDetails));
            } else {
                checkUpdateInformation(requestUserUpdateDetails, dbUser, TravelStatus.BOOKING_TRAVEL);
                userRepository.save(requestMapper.requestDtoToUser(requestUserUpdateDetails));
            }
        }

        return requestUserUpdateDetails.getUserId();
    }

    private void checkUpdateInformation(RequestDto user, User userByUsername, TravelStatus status) {

        switch (status) {
            case ACTIVE_TRAVEL:
                if (userByUsername.getNicOrPassport().getId() != user.getNicOrPassport())
                    throw new AlreadyInTravelException("Already in a travel.");
                if (userByUsername.getAddress() != user.getAddress())
                    throw new AlreadyInTravelException("Already in a travel.");
                if (userByUsername.getName() != user.getName())
                    throw new AlreadyInTravelException("Already in a travel.");
                if (userByUsername.getContact() != user.getContact())
                    throw new AlreadyInTravelException("Already in a travel.");
                break;
            case BOOKING_TRAVEL:
                if (userByUsername.getNicOrPassport().getId() != user.getNicOrPassport())
                    throw new AlreadyInTravelException("Already in a travel.");
                if (userByUsername.getName() != user.getName())
                    throw new AlreadyInTravelException("Already in a travel.");
                break;
            default:
                throw new RuntimeException("Unknown error. Username : " + user.getUsername());
        }

    }

    @Override
    public ResponseDto deleteUser(String username) {
        User user = userRepository.findUserByUsername(username);
        if (user == null) {
            throw new NotFoundException("User not found. Username : " + username);
        } else {

            Boolean isDeleted = webClient.build().delete()
                    .uri("lb://travel-service/api/travels/deleteByUserId/" + user.getUserId())
                    .retrieve()
                    .bodyToMono(Boolean.class)
                    .block();

            if (Boolean.TRUE.equals(isDeleted)) {
                userRepository.delete(user);
            } else throw new RuntimeException("Unknown error. Username : " + username);

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

    @Override
    public ResponseDto findUserByUserId(long userID) {
        User user = userRepository.findUserByUserId(userID);
        if (user == null) {
            throw new NotFoundException("User not found. UserId : " + userID);
        } else {
            return requestMapper.userToResponseDto(user);
        }
    }

    @Override
    public void deleteUser(long userId) {
        userRepository.deleteById(String.valueOf(userId));
    }
}
