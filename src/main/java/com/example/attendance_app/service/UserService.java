package com.example.attendance_app.service;


import com.example.attendance_app.dto.LoginDTO;
import com.example.attendance_app.dto.RegisterDTO;
import com.example.attendance_app.exception.AtttendenceAppException;
import com.example.attendance_app.model.AttendanceReport;
import com.example.attendance_app.model.UserModel;
import com.example.attendance_app.repository.AttendanceReportRepo;
import com.example.attendance_app.repository.UserRepo;
import com.example.attendance_app.utility.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Collections;
import java.util.List;

@Service
public class UserService implements IuserService{

    @Autowired
    UserRepo userRepo;

    @Autowired
    AttendanceReportRepo attendancRepo;

    @Autowired
    JwtUtils jwtUtils;

    @Override
    public String registerUser(RegisterDTO registerDTO) {
        if (userRepo.findByUserName(registerDTO.getUserName()) == null) {
            UserModel registerUser = new UserModel(registerDTO.getUserName(), registerDTO.getEmailID(), registerDTO.getPassword(), registerDTO.getPhoneNo());
            userRepo.save(registerUser);
            return "User Register Successful";
        }
        throw new AtttendenceAppException("User Already Exist");
    }

    @Override
    public String login(LoginDTO loginDTO) {
        UserModel userModel = userRepo.findByUserName(loginDTO.getUserName());
        if (userModel != null) {
            if (userModel.getPassword().equals(loginDTO.getPassword())) {
                String token = jwtUtils.generateToken(loginDTO);
                userModel.setLogin(true);
                userModel.setId(userModel.getId());
                userRepo.save(userModel);
                return token;
            }
            throw new AtttendenceAppException("please check Your Password");
        }
        throw new AtttendenceAppException("Check Your UserName");
    }

    @Override
    public AttendanceReport signIN(String token) {
        LoginDTO loginDTO = jwtUtils.decodeToken(token);
        UserModel user = userRepo.findByUserNameAndPassword(loginDTO.getUserName(), loginDTO.getPassword());
        if (user.isLogin()) {
            if (attendancRepo.findByUserID(user.getId()) == null) {
                AttendanceReport attendanceReport = new AttendanceReport(LocalDate.now(), user.getId());
                attendanceReport.setSignINTime(LocalTime.now());
                attendanceReport.setSignIN(true);
                return attendancRepo.save(attendanceReport);
            }
            throw new AtttendenceAppException("User Already SignedIn");
        }
        throw new AtttendenceAppException("Invalid User");
    }

    @Override
    public AttendanceReport signOut(String token) {
        LoginDTO loginDTO = jwtUtils.decodeToken(token);
        UserModel user = userRepo.findByUserNameAndPassword(loginDTO.getUserName(), loginDTO.getPassword());
        if (user.isLogin()) {
            AttendanceReport attendanceReport = attendancRepo.findByUserID(user.getId());
            if (attendanceReport != null) {
                attendanceReport.setSignOut(LocalTime.now());
                attendanceReport.setSignIN(false);
                return attendancRepo.save(attendanceReport);
            }
            throw new AtttendenceAppException("Please SignedIn First");
        }
        throw new AtttendenceAppException("Invalid User");
    }

    @Override
    public AttendanceReport getAttendenceReport(String token) {
        LoginDTO loginDTO = jwtUtils.decodeToken(token);
        UserModel user = userRepo.findByUserNameAndPassword(loginDTO.getUserName(), loginDTO.getPassword());
        if (user.isLogin()) {
            return attendancRepo.findByUserID(user.getId());
        }
        throw new AtttendenceAppException("Invalid User");
    }


}