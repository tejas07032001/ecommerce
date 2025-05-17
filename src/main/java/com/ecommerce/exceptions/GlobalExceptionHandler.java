package com.ecommerce.exceptions;

import com.ecommerce.dtos.ApiResponseMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.List;
import java.util.Map;

public class GlobalExceptionHandler {
    private Logger logger= LoggerFactory.getLogger(GlobalExceptionHandler.class);


//    //handler resource not found exception
//    @ExceptionHandler(ResourceNotFoundException.class)
//    public ResponseEntity<ApiResponseMessage> resourceNotFoundExceptionHandler(ResourceNotFoundException ex){
//
//        logger.info("Exception handler invoked");
//
//        ApiResponseMessage response = ApiResponseMessage.builder().message(ex.getMessage()).status(HttpStatus.NOT_FOUND).success(true).build();
//
//        return new ResponseEntity(response,HttpStatus.NOT_FOUND);
//    }
//
//
//    //Method Argument Notvalid Exception
//    public ResponseEntity<Map<String,Object>> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex){
//        List<ObjectError> allErrors = ex.getBindingResult().getAllErrors();
//        return null;
//    }



    // Handle BAD API REQUEST EXCEPTION

    @ExceptionHandler(BadApiRequest.class)
    public ResponseEntity<ApiResponseMessage> handleBadApiRequest(BadApiRequest ex){

        logger.info("Exception handler invoked");

        ApiResponseMessage response = ApiResponseMessage.builder().msg(ex.getMessage()).status(HttpStatus.BAD_REQUEST).success(false).build();

        return new ResponseEntity(response,HttpStatus.BAD_REQUEST);
    }

}
