package com.example.attendance_app.service;

import com.example.attendance_app.dto.LoginDTO;
import com.example.attendance_app.exception.AtttendenceAppException;
import com.example.attendance_app.model.AttendanceReport;
import com.example.attendance_app.model.UserModel;
import com.example.attendance_app.repository.AttendanceReportRepo;
import com.example.attendance_app.repository.UserRepo;
import com.example.attendance_app.utility.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminService implements IadminService{

    @Autowired
    UserRepo userRepo;

    @Autowired
    AttendanceReportRepo attendancRepo;

    @Autowired
    JwtUtils jwtUtils;
    @Override
    public String adminLogin(LoginDTO loginDTO) {
        UserModel admin = userRepo.findByUserName(loginDTO.getUserName());
        if (admin != null) {
            if (admin.getPassword().equals(loginDTO.getPassword()) && admin.getRole().equals("Admin")) {
                String token = jwtUtils.generateToken(loginDTO);
                admin.setLogin(true);
                admin.setId(admin.getId());
                userRepo.save(admin);
                return token;
            }
            throw new AtttendenceAppException("please check Your Password");
        }
        throw new AtttendenceAppException("Check Your UserName");
    }

    @Override
    public List<UserModel> getAllUserList(String token) {
        LoginDTO loginDTO = jwtUtils.decodeToken(token);
        UserModel user = userRepo.findByUserNameAndPassword(loginDTO.getUserName(), loginDTO.getPassword());
        if (user.getRole().equals("Admin") && user.isLogin()) {
            List<UserModel> usersList = userRepo.findAll();
            return usersList;
        }
        throw new AtttendenceAppException("Please sign in your account as Admin");
    }

    @Override
    public String logOutAdmin(String token) {
        LoginDTO loginDTO = jwtUtils.decodeToken(token);
        UserModel user = userRepo.findByUserNameAndPassword(loginDTO.getUserName(), loginDTO.getPassword());
        if (user.getRole().equals("Admin") && user.isLogin()) {
            user.setLogin(false);
            userRepo.save(user);
            return "User Logout Successful";
        }
        throw new AtttendenceAppException("Invalid User");
    }

    @Override
    public List<AttendanceReport> getAttendenceReport(String token, int userId) {
        LoginDTO loginDTO = jwtUtils.decodeToken(token);
        UserModel user = userRepo.findByUserNameAndPassword(loginDTO.getUserName(), loginDTO.getPassword());
        if (user.getRole().equals("Admin") && user.isLogin()) {
            return attendancRepo.findAlByUserID(userId);
        }
        throw new AtttendenceAppException("Please sign in your account as Admin");
    }
}
