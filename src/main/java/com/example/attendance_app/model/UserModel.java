package com.example.attendance_app.model;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Getter
@Setter
@ToString
@NoArgsConstructor
@Entity
public class UserModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String userName;
    private String emailID;
    private String password;
    private String phoneNo;

    private boolean isLogin = false;
    private String role = "User";

    public UserModel(String userName, String emailID, String password, String phoneNo) {
        this.userName = userName;
        this.emailID = emailID;
        this.password = password;
        this.phoneNo = phoneNo;
    }
}
