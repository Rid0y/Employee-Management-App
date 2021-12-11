package com.example.employee_app;


import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;



//I am Using Room Database instead of SQLiteOpenHelper to issue queries to database.
@Entity
public class Staff {

    //initializing variables
    @PrimaryKey(autoGenerate = true)
    public int id;

    @ColumnInfo(name = "name")
    public String name;

    @ColumnInfo(name = "position")
    public String position;


    //Primary constructor overloading
    public Staff(String name, String position) {
        this.name = name;
        this.position = position;
    }

    //Secondary Constructor
    @Ignore
    public Staff(int id, String name, String position) {
        this.id = id;
        this.name = name;
        this.position = position;
    }


    //Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }
}
