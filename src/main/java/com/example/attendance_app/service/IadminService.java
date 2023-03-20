package com.example.attendance_app.service;

import com.example.attendance_app.dto.LoginDTO;
import com.example.attendance_app.model.AttendanceReport;
import com.example.attendance_app.model.UserModel;

import java.util.List;

public interface IadminService {
    String adminLogin(LoginDTO loginDTO);
    List<UserModel> getAllUserList(String token);

    AttendanceReport getAttendenceReport(String token, int userId);
}
