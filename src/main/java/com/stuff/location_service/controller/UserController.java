package com.stuff.location_service.controller;

import com.stuff.location_service.dto.UserCreateRequest;
import com.stuff.location_service.dto.UserDto;
import com.stuff.location_service.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping({"api/users"})
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void CreateUser(@RequestBody UserCreateRequest userCreateRequest){
        userService.createUser(userCreateRequest);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<UserDto> GetHouseholds(){
        return userService.getUsers();
    }

    @GetMapping({"/{id}"})
    @ResponseStatus(HttpStatus.OK)
    public UserDto GetUser(@PathVariable String id){
        UserDto userDto = userService.getUser(id);
        return userDto;
    }

    @GetMapping({"/"})
    @ResponseStatus(HttpStatus.OK)
    public UserDto GetUserFromEmail(@RequestParam(value = "email", defaultValue = "") String email) {
        return userService.getUserFromEmail(email);
    }

    @DeleteMapping({"/{id}"})
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void DeleteUser(@PathVariable String id){
        userService.deleteUser(id);
    }

//    @PutMapping({"/{id}"})
//    @ResponseStatus(HttpStatus.CREATED)
//    public void UpdateUser(@PathVariable String id, @RequestBody UserCreateRequest userCreateRequest){
//        userService.CreateUser(id, userCreateRequest);
//    }

    @PutMapping({"/{id}"})
    @ResponseStatus(HttpStatus.OK)
    public void UpdateField(@PathVariable String id, @RequestBody UserCreateRequest userCreateRequest){
        userService.updateUserField(id, userCreateRequest);
    }
}