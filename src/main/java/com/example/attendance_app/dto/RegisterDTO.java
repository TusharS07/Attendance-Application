package com.example.attendance_app.dto;

import lombok.Data;

@Data
public class RegisterDTO {
    private String userName;
    private String emailID;
    private String password;
    private String phoneNo;
}
