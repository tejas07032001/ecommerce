package com.ecommerce.dtos;

import jakarta.persistence.Column;
import lombok.*;

//These layer is  use to transfer data (data transfer object )




@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserDto {

    private String userId;


    private String name;

    private String email;

    private String password;

    private String gender;

    private String about;

    private String image;

}
