package com.ecommerce.service.impl;

import com.ecommerce.dtos.UserDto;
import com.ecommerce.entities.User;
import com.ecommerce.repository.UserRepository;
import com.ecommerce.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDto createUser(UserDto userDto) {

        // we did not declared auto generated id so
        // we generate unique id in String formate
        String userId = UUID.randomUUID().toString();
        userDto.setUserId(userId);



        User user= DtoToEntity(userDto);             //This method convert DTO to Entity because we work on entity bt we transfer value from dto so first we need to convert it into entity
                                                    // we can use local mapper class for it and we do it manually also
        User savedUser = userRepository.save(user);

       UserDto newDto = EntityToDto(savedUser);  // This method convert entity to Dto because we need entity to work bt after working we need to pass the data and we dto class for that

        return newDto;
    }


    @Override
    public UserDto updateUser(UserDto userDto, String UserId) {
        return null;
    }

    @Override
    public void deleteUser(String userId) {

    }

    @Override
    public List<UserDto> getAllUser() {
        return null;
    }

    @Override
    public UserDto getUserById(String userId) {
        return null;
    }

    @Override
    public UserDto getUserByEmail(String email) {
        return null;
    }

    @Override
    public List<UserDto> searchUser(String keyword) {
        return null;
    }







    private User DtoToEntity(UserDto userDto) {

        User user = User.builder()
                .userId(userDto.getUserId())
                .name(userDto.getName())
                .email(userDto.getEmail())
                .password(userDto.getPassword())
                .about(userDto.getAbout())
                .gender(userDto.getGender())
                .image(userDto.getImage())
                .build();
        return user;
    }



    private UserDto EntityToDto(User savedUser) {

        UserDto userDto = UserDto.builder()
                .userId(savedUser.getUserId())
                .name(savedUser.getName())
                .email(savedUser.getEmail())
                .password(savedUser.getPassword())
                .about(savedUser.getAbout())
                .gender(savedUser.getGender())
                .image(savedUser.getImage())
                .build();
        return userDto;

    }


}