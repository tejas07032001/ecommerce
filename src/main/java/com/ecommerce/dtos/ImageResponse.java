package com.ecommerce.dtos;

import lombok.*;
import org.springframework.http.HttpStatus;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ImageResponse {

    private String imageName;
    private boolean success;
    private HttpStatus status;
}
