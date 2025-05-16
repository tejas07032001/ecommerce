package com.ecommerce.service.impl;

import com.ecommerce.dtos.UserDto;
import com.ecommerce.entities.User;
import com.ecommerce.repository.UserRepository;
import com.ecommerce.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

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



        User user= dtoToEntity(userDto);             //This method convert DTO to Entity because we work on entity bt we transfer value from dto so first we need to convert it into entity
                                                    // we can use local mapper class for it and we do it manually also
        User savedUser = userRepository.save(user);

       UserDto newDto = entityToDto(savedUser);  // This method convert entity to Dto because we need entity to work bt after working we need to pass the data and we dto class for that

        return newDto;
    }


    @Override
    public UserDto updateUser(UserDto userDto, String UserId) {
        return null;
    }

    @Override
    public void deleteUser(String userId) {

        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("not user find"));
            userRepository.delete(user);
    }

    @Override
    public List<UserDto> getAllUser() {

        List<User> all = userRepository.findAll();
        List<UserDto> collect = all.stream().map(user -> entityToDto(user)).collect(Collectors.toList());


        return collect;
    }

    @Override
    public UserDto getUserById(String userId) {

        User id = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("No user with this id"));

        return entityToDto(id);
    }

    @Override
    public UserDto getUserByEmail(String email) {

        User user = userRepository.findByEmail(email).orElseThrow(() -> new RuntimeException("No user found with email"));

        return entityToDto(user);
    }

    @Override
    public List<UserDto> searchUser(String keyword) {

        Optional<User> containing = userRepository.findByNameContaining(keyword);
        List<UserDto> dtoList = containing.stream().map(user -> entityToDto(user)).collect(Collectors.toList());


        return dtoList;
    }







    private User dtoToEntity(UserDto userDto) {

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



    private UserDto entityToDto(User savedUser) {

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