package com.LinkUp.response;

public class AuthResponse {
    private String token;
    private String message;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public AuthResponse()
    {

    }
    public AuthResponse(String message, String token) {
        this.message = message;
        this.token = token;
    }


}
