package com.example.attendance_app.exception;

public class AtttendenceAppException extends RuntimeException{
    String message;

    public AtttendenceAppException(String message) {
        this.message = message;
    }

    public AtttendenceAppException(){

    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
