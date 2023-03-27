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

    private long timeDuration;

    @ManyToOne()
    @JoinColumn(name = "UserID")
    private UserModel user;


    public AttendanceReport(LocalDate date, UserModel user) {
        this.date = date;
        this.user = user;
    }
}
