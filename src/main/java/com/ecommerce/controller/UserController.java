package com.ecommerce.controller;

import com.ecommerce.dtos.UserDto;
import com.ecommerce.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    //Create
    @PostMapping
    public ResponseEntity<UserDto> createUser (@RequestBody UserDto userDto){

        UserDto userDto1 = userService.createUser(userDto);

        return new ResponseEntity<>(userDto1, HttpStatus.CREATED);
    }

    //update
    @PutMapping("/{userId}")
    public ResponseEntity<UserDto> updateUser(  @PathVariable("userId") String userId,
                                                @RequestBody UserDto userDto){

        UserDto updateduserDto1 = userService.updateUser(userDto, userId);

        return new ResponseEntity<>(updateduserDto1,HttpStatus.OK);
    }

    //delete
    @DeleteMapping("/{userId}")
    public ResponseEntity<String> deleteUser (@PathVariable String userId){          // never send data in String .. normal String not a json data
                                                                                     //    so we need to create message custome (may be in json)

        userService.deleteUser(userId);

        return new ResponseEntity<>("user delete successfully",HttpStatus.OK);
    }

    //getALL
    @GetMapping()
    public ResponseEntity<List<UserDto>> getALLUsers(){
        List<UserDto> allUser = userService.getAllUser();

        return new ResponseEntity<>(allUser,HttpStatus.OK);

    }


    //get single
    @GetMapping("/{userId}")
    public ResponseEntity<UserDto> getUser( @PathVariable String userId ){

        UserDto userById = userService.getUserById(userId);

        return new ResponseEntity<>(userById,HttpStatus.OK);
    }

    //get By Email
    @GetMapping("/email/{email}")
    public ResponseEntity<UserDto> getUserByEmail(@PathVariable String email){

        UserDto userByEmail = userService.getUserByEmail(email);

        return new ResponseEntity<>(userByEmail,HttpStatus.OK);
    }

    //search user

    @GetMapping("/search/{keywords}")
    public ResponseEntity<List<UserDto>> searchUser(@PathVariable String keywords){

        List<UserDto> userDtos = userService.searchUser(keywords);

        return new ResponseEntity<>(userDtos,HttpStatus.OK);

    }

}

