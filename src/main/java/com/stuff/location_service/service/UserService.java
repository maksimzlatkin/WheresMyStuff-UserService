package com.stuff.location_service.service;

import com.stuff.location_service.dto.UserCreateRequest;
import com.stuff.location_service.dto.UserDto;
import com.stuff.location_service.mapper.UserMapper;
import com.stuff.location_service.model.User;
import com.stuff.location_service.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.stuff.exception.EntityNotFoundException;

import java.util.*;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    UserMapper mapper = new UserMapper();

    public void createUser(UserCreateRequest householdCreateRequest){
        userRepository.save(mapper.toUser(householdCreateRequest, UUID.randomUUID().toString()));
    }

    public List<UserDto> getUsers(){
        List<UserDto> users = new ArrayList<UserDto>();
        for (User user : userRepository.findAll()) {
            users.add(mapper.toUserDto(user));
        }
        return users;
    }

    public UserDto getUser(String id){
        User user = getUserOrThrowException(id);
        return mapper.toUserDto(user);
    }

    public UserDto getUserFromEmail(String email){
        User user = getUserFromEmailOrThrowException(email);
        return mapper.toUserDto(user);
    }

    public void deleteUser(String id){
        User user = getUserOrThrowException(id);
        userRepository.delete(user);
    }

    public void updateUserField(String id, UserCreateRequest userCreateRequest) {
        User user = getUserOrThrowException(id);
        user.setName(userCreateRequest.getName());
        user.setEmail(user.getEmail());
        userRepository.save(user);
    }

    private User getUserOrThrowException(String id){
        Optional<User> user = userRepository.findById(id);
        if (user.isEmpty()) {
            throw new EntityNotFoundException(String.format("User with id='%s' not found", id));
        }
        return user.get();
    }

    private User getUserFromEmailOrThrowException(String email){
        User user = userRepository.findByEmail(email);
        if (user == null) {
            throw new EntityNotFoundException(String.format("User with email='%s' not found", email));
        }
        return user;
    }
}
