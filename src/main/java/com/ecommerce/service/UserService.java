package com.ecommerce.service;

import com.ecommerce.dtos.UserDto;

import java.util.List;

public interface UserService {

    //create
    UserDto createUser(UserDto userDto);

    //update
    UserDto updateUser(UserDto userDto, String UserId);

    //Delete
     void deleteUser (String userId);

     //get all user
    List<UserDto> getAllUser();

    //get single user
    UserDto getUserById(String userId);

    //get user by email
    UserDto getUserByEmail(String email);

    //Search User
    List<UserDto> searchUser(String keyword);

}
