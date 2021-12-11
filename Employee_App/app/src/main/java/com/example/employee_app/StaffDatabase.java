package com.example.employee_app;

import androidx.room.Database;
import androidx.room.RoomDatabase;

//This class is used to create the database by utilizing Androidx and RoomDatabase
@Database(entities = {Staff.class},version = 1, exportSchema = false)
public abstract class StaffDatabase extends RoomDatabase {

    public abstract StaffDAO staffDAO();
}
