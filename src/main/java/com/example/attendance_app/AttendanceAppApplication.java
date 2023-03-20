package com.example.attendance_app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;



@SpringBootApplication
@EnableWebMvc
public class AttendanceAppApplication {

    public static void main(String[] args) {
        SpringApplication.run(AttendanceAppApplication.class, args);
        System.out.println("Welcome to attendence app");
    }

}
