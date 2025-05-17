package com.ecommerce.exceptions;

public class BadApiRequest extends RuntimeException{

    public BadApiRequest(String message){
        super(message);
    }

    public BadApiRequest(){
        super("bad request");
    }


}
