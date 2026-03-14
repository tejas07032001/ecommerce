package com.ecommerce.controller;

import com.ecommerce.dtos.ApiResponseMessage;
import com.ecommerce.dtos.ImageResponse;
import com.ecommerce.dtos.PageableResponse;
import com.ecommerce.dtos.UserDto;
import com.ecommerce.service.FileService;
import com.ecommerce.service.UserService;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    //declare for adding the image later
    private FileService fileService;



    @Value("${user.profile.image.path}")
    private String imageUploadPath;            // where we upload our image thats path is stored in that varible that we use use in below filesss
                                               // And we get the value means the path from application.properties and store the image into these folder

   private Logger logger= LoggerFactory.getLogger(UserController.class);

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
    public ResponseEntity<PageableResponse<UserDto>> getALLUsers(
                        @RequestParam(value = "pageNumber",defaultValue = "0", required = false) int pageNumber ,           //We declared it later to make pagination
                        @RequestParam(value = "pageSize",defaultValue = "10",required = false) int pageSize,                 // these is also

                        @RequestParam(value = "sortBy",defaultValue = "name", required = false) String sortBy ,          //THESE IS DECLARED AFTER PAGINATION FOR SORTING
                        @RequestParam(value = "sortDir",defaultValue = "asc", required = false) String sortDir
                        )
    {
        PageableResponse<UserDto> allUser = userService.getAllUser(pageNumber, pageSize, sortBy, sortDir);

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






    ////////////////////////////
    //Upload User Image
    @PostMapping("image/{userId}")
    public ResponseEntity<ImageResponse> uploadUserImage(
                        @RequestParam("userImage")MultipartFile image, @PathVariable String userId ) throws IOException               //multipart part file is data type which is used to store file data
    {
        String imageName = fileService.uploadFile(image, imageUploadPath);

        //We need to update image name into userId profile so we written below line
        UserDto user = userService.getUserById(userId);
        user.setImage(imageName);
        UserDto userDto = userService.updateUser(user, userId);  //


        ImageResponse imageResponse= ImageResponse.builder().imageName(imageName).success(true).status(HttpStatus.CREATED).build();
        return new ResponseEntity<>(imageResponse,HttpStatus.CREATED);
    }

    //serve user image   ....... to get image
    @GetMapping("/image/{userId}")
    public void serveUserImage(@PathVariable String userId , HttpServletResponse response) throws IOException {

        UserDto user = userService.getUserById(userId);
        logger.info("user image name: {}",user.getImage());
        InputStream resource = fileService.geResource(imageUploadPath, user.getImage());
        response.setContentType(MediaType.IMAGE_JPEG_VALUE);
        StreamUtils.copy(resource,response.getOutputStream());

    }




}

