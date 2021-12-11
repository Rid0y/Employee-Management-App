package com.example.employee_app;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

//In this class I am using Data Access Objects to define my database interactions

import java.util.List;

@Dao
public interface StaffDAO {

    //creating task methods
    @Insert
    void insertTask(Staff staff);

    @Update
    void updateTask(Staff staff);

    @Delete
    void deleteTask(Staff staff);

    //query to view data in recyclerview
    @Query("select * from STAFF order by id asc")
    List<Staff> getAll();

}
