package com.example.attendance_app.controller;

import com.example.attendance_app.Response;
import com.example.attendance_app.dto.LoginDTO;
import com.example.attendance_app.dto.RegisterDTO;
import com.example.attendance_app.model.AttendanceReport;
import com.example.attendance_app.model.UserModel;
import com.example.attendance_app.service.IuserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/UserPage")
@CrossOrigin("*")
public class UserController {

    @Autowired
    IuserService iuserService;

    @PostMapping("/Register_New_User")
    public ResponseEntity<Response> registerNewUser(@RequestBody RegisterDTO registerDTO) {
        iuserService.registerUser(registerDTO);
        Response response = new Response(registerDTO, "User Registered Successful");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/Login")
    public ResponseEntity<Response> loginPage(@RequestBody LoginDTO loginDTO) {
        String token= iuserService.login(loginDTO);
        Response response = new Response(token, "Login Successful");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/Logout")
    public ResponseEntity<Response> logOutUser(@RequestParam String token) {
        iuserService.logOut(token);
        Response response = new Response("User Logout", "SuccessFully Logout");
        return  new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("SignIn_Attendence")
    public ResponseEntity<Response> SignInAttendence(@RequestParam String token) {
        AttendanceReport attendanceReport = iuserService.signIN(token);
        Response response = new Response(attendanceReport, "SignIn Successful");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("SignOUT_Attendence")
    public ResponseEntity<Response> SignOUTAttendence(@RequestParam String token) {
        AttendanceReport attendanceReport = iuserService.signOut(token);
        Response response = new Response(attendanceReport, "SignOut Successful");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("Attendence_Report")
    public ResponseEntity<Response> attendenceReport(@RequestParam String token) {
        List<AttendanceReport> attendanceReport = iuserService.getAttendenceReport(token);
        Response response = new Response(attendanceReport, "Attendance Report");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("Get_User_Data")
    public ResponseEntity<Response> getUserData(@RequestParam String token) {
        UserModel userModel = iuserService.fetchUserData(token);
        Response response = new Response(userModel, "UserData");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }


}
