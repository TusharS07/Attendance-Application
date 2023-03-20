package com.example.attendance_app.dto;

import lombok.Data;
import org.springframework.stereotype.Component;

@Data
@Component
public class LoginDTO {
    private String userName;
    private String password;
}
