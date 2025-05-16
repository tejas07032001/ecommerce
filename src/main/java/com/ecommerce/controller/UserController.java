package com.ecommerce.controller;

import com.ecommerce.dtos.UserDto;
import com.ecommerce.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

}

