package com.example.projetofinal.exception.handler;

import java.io.Serializable;

public class RestErrorMessageDTO implements Serializable {
    private final String message;

    public RestErrorMessageDTO(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
