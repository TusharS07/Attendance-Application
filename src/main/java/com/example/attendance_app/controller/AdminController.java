package com.example.attendance_app.controller;

import com.example.attendance_app.Response;
import com.example.attendance_app.dto.LoginDTO;
import com.example.attendance_app.model.AttendanceReport;
import com.example.attendance_app.model.UserModel;
import com.example.attendance_app.service.IadminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/AdminPage")
@CrossOrigin("*")
public class AdminController {

    @Autowired
    IadminService iadminService;
    @PostMapping("/Login")
    public ResponseEntity<Response> loginPage(@RequestBody LoginDTO loginDTO) {
        String token= iadminService.adminLogin(loginDTO);
        Response response = new Response(token, "Login Successful");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("ALl_Users")
    public ResponseEntity<Response> getallusersData(@RequestParam String token) {
        List<UserModel> allUserList = iadminService.getAllUserList(token);
        Response response = new Response(allUserList, allUserList.size()+" Users Data");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("UserAttendence_Report")
    public ResponseEntity<Response> getUsersAttendanceReport(@RequestParam String token, @RequestParam int userID) {
        AttendanceReport attendanceReports = iadminService.getAttendenceReport(token, userID);
        Response response = new Response(attendanceReports, "attendance Report of User "+userID);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}
