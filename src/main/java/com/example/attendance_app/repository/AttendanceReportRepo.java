package com.example.attendance_app.repository;

import com.example.attendance_app.model.AttendanceReport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AttendanceReportRepo extends JpaRepository<AttendanceReport, Integer> {

    @Override
    List<AttendanceReport> findAll();



    @Query(value = "SELECT * FROM attendance_app.attendance_report where userid = :userId", nativeQuery = true)
    AttendanceReport findByUserID(int userId);
}
