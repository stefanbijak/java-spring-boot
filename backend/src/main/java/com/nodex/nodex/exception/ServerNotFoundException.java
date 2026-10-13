package com.nodex.nodex.exception;

public class ServerNotFoundException extends RuntimeException{
    public ServerNotFoundException(Integer id){
        super("Server with ID: " + id + " not found!");
    }
}
