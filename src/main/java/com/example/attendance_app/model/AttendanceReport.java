package com.example.attendance_app.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@Setter
@ToString
@NoArgsConstructor
@Entity
public class AttendanceReport {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private LocalDate date;

    private LocalTime signINTime;

    private LocalTime signOut;

    private boolean isSignIN = false;

    private int UserID;

    public AttendanceReport(LocalDate date, int userID) {
        this.date = date;
        UserID = userID;
    }
}
