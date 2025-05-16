package com.ecommerce.dtos;
import lombok.*;
import org.springframework.http.HttpStatus;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class ApiResponseMessage {

    private String msg;
    private boolean success;
    private HttpStatus status;              //not compulsary depend on us

}


//This class is create to send message in json formate
// example we delete the message and we need to send message
//so it send in string formate thats why we need to send it in json
// and for that we created separate class for message
