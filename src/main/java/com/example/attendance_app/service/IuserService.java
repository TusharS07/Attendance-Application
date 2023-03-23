package com.example.attendance_app.service;

import com.example.attendance_app.dto.LoginDTO;
import com.example.attendance_app.dto.RegisterDTO;
import com.example.attendance_app.model.AttendanceReport;

import java.time.LocalTime;
import java.util.List;

public interface IuserService {

    String registerUser(RegisterDTO registerDTO);

    String logOut(String token);

    String login(LoginDTO loginDTO);

    AttendanceReport signIN(String token);

    AttendanceReport signOut(String token);

    List<AttendanceReport> getAttendenceReport(String token);
}
