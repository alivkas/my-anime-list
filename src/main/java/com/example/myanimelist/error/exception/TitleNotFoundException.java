package com.example.myanimelist.error.exception;

public class TitleNotFoundException extends RuntimeException {

    public final String CODE = "400";

    public TitleNotFoundException(String name) {
        super("Title " + name + " not found");
    }
}
