package com.example.attendance_app.repository;

import com.example.attendance_app.model.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepo extends JpaRepository<UserModel, Integer> {

    @Query(value = "SELECT * FROM attendance_app.user_model where user_name = :userName", nativeQuery = true)
    UserModel findByUserName(String userName);

    @Query(value = "SELECT * FROM attendance_app.user_model where user_name = :userName and password = :password", nativeQuery = true)
    UserModel findByUserNameAndPassword(String userName, String password);

    @Override
    List<UserModel> findAll();
}
