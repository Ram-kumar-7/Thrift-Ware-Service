package com.ramTech.ThriftWare.Response;

public class AuthResponse {
    private boolean status;
    private String message;
    private String token;

    public boolean getStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public AuthResponse(boolean status, String message) {
        this.status = status;
        this.message = message;
    }

    public AuthResponse(boolean status, String message, String token) {
        this.status = status;
        this.message = message;
        this.token = token;
    }

}
