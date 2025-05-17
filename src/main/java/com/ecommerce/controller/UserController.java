package com.ecommerce.controller;

import com.ecommerce.dtos.ApiResponseMessage;
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
    public ResponseEntity<ApiResponseMessage> deleteUser (@PathVariable String userId){          // never send data in String .. normal String not a json data
                                                                                     //    so we need to create message custome (may be in json)
                                                                                     //By using these APiResponseMessage class (before use use String in it
        userService.deleteUser(userId);
        ApiResponseMessage message = ApiResponseMessage.builder().msg("not working").success(true).status(HttpStatus.OK).build();//newly added after api class

        return new ResponseEntity<>(message,HttpStatus.OK);
    }

    //getALL
    @GetMapping()
    public ResponseEntity<List<UserDto>> getALLUsers(
                                        @RequestParam(value = "pageNumber",defaultValue = "0", required = false) int pageNumber ,           //We declared it later to make pagination
                                        @RequestParam(value = "pageSize",defaultValue = "10",required = false) int pageSize
                        )
    {
        List<UserDto> allUser = userService.getAllUser(pageNumber,pageSize);

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

