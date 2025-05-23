package com.ecommerce.service.impl;

import com.ecommerce.dtos.PageableResponse;
import com.ecommerce.dtos.UserDto;
import com.ecommerce.entities.User;
import com.ecommerce.helper.Helper;
import com.ecommerce.repository.UserRepository;
import com.ecommerce.service.UserService;
import lombok.Setter;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;


@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ModelMapper mapper;     // we taking model mapper class with map method  to convert entity to dto and dto to entity

    @Value("${user.profile.image.path}")        //we are using these for delete image when we delete user
    private String imagePath;                   // it get path of image

    private Logger logger= LoggerFactory.getLogger(UserServiceImpl.class);

    @Override
    public UserDto createUser(UserDto userDto) {

        // we did not declared auto generated id so
        // we generate unique id in String formate
        String userId = UUID.randomUUID().toString();
        userDto.setUserId(userId);



        User user= dtoToEntity(userDto);             //This method convert DTO to Entity because we work on entity bt we transfer value from dto so first we need to convert it into entity
                                                    // we can use local mapper class for it and we do it manually also
        User savedUser = userRepository.save(user);

        UserDto newDto = entityToDto(savedUser);// This method convert entity to Dto because we need entity to work bt after working we need to pass the data and we dto class for that

        return newDto;
    }


    @Override
    public UserDto updateUser(UserDto userDto, String UserId) {
        User user = userRepository.findById(UserId).orElseThrow(() -> new RuntimeException("User not found exception"));

        user.setName(userDto.getName());
        user.setAbout(userDto.getAbout());
        user.setGender(userDto.getGender());
        user.setPassword(userDto.getPassword());
        user.setImage(userDto.getImage());

        User updatedUser = userRepository.save(user);
        UserDto updatedDto = entityToDto(updatedUser);

        return updatedDto;
    }

    @Override
    public void deleteUser(String userId) {
        //getting user to delete step1
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("not user find"));

        //when we delete the user then we want to delete user image as well that we stored in the image folder
        //so the following operation is usefull for that

        //delete user profile image
        //images/user/abc.png           get full image path
        String fullPath = imagePath + user.getImage();

        try {
            Path path = Paths.get(fullPath);
            Files.delete(path);
        }catch (NoSuchFileException ex){
            logger.info("user image not found in folder");
            ex.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();

        }

        // delete user step2
        userRepository.delete(user);
    }

    @Override
    public PageableResponse<UserDto> getAllUser(int pageNumber , int pageSize, String sortBy, String sortDir) {

       // Sort sort = Sort.by(sortBy);//these declared after pagination for  sorting

        Sort sort=(sortDir.equalsIgnoreCase("desc")) ? (Sort.by(sortBy).descending()):(Sort.by(sortBy).ascending());
                    //These declared for checking condition for ascending or decending

        //pageNumber default start from zero
        Pageable pageable= PageRequest.of(pageNumber,pageSize,sort);                 //added after we want data in pagination then we add sort also

        Page<User> page = userRepository.findAll(pageable);

        PageableResponse<UserDto> response = Helper.getPaegebleResponse(page, UserDto.class);

        return response;
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







    private User dtoToEntity(UserDto userDto) {     // after taking modelmapper we comment out written to code to convert  dto to enntity


 //       User user = User.builder()
//                .userId(userDto.getUserId())
//                .name(userDto.getName())
//                .email(userDto.getEmail())
//                .password(userDto.getPassword())
//                .about(userDto.getAbout())
//                .gender(userDto.getGender())
//                .image(userDto.getImage())
//                .build();
//        return user;

        return mapper.map(userDto,User.class);           //return it after we declared mapper class
    }



    private UserDto entityToDto(User savedUser) {           // after taking modelmapper we comment out written to code to convert entity to dto

//        UserDto userDto = UserDto.builder()
//                .userId(savedUser.getUserId())
//                .name(savedUser.getName())
//                .email(savedUser.getEmail())
//                .password(savedUser.getPassword())
//                .about(savedUser.getAbout())
//                .gender(savedUser.getGender())
//                .image(savedUser.getImage())
//                .build();
//        return userDto;

         return mapper.map(savedUser,UserDto.class);        //return it after we declared mapper class

    }


}