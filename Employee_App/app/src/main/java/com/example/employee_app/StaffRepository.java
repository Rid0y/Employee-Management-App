package com.example.employee_app;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;
import androidx.room.Room;
import java.util.List;

//This is used to handle all the operations of CRUD

public class StaffRepository {

    //initializing varibales
    private String DB_NAME="staffdb";
    private StaffDatabase staffDatabase;
    Context context;


    //constructor
    public StaffRepository(Context context){
        this.context = context;
        staffDatabase = Room.databaseBuilder(context, StaffDatabase.class, DB_NAME).build();
    }

    //inserting data function
    public void InsertTask(final Staff staff){
        new AsyncTask<Void,Void,Void>(){

            @Override
            protected Void doInBackground(Void... voids) {
                staffDatabase.staffDAO().insertTask(staff);
                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);
                Toast.makeText(context,staff.name+" is inserted",Toast.LENGTH_SHORT).show();
            }
        }.execute();
    }
    //end insert



    //getting data fucntion
    public List<Staff> getStaff(){

        List<Staff> staffList=staffDatabase.staffDAO().getAll();
        return staffList;
    }
    //end get



    //updating data function
    public void UpdateTask(final Staff staff){
        new AsyncTask<Void,Void,Void>(){

            @Override
            protected Void doInBackground(Void... voids) {
                staffDatabase.staffDAO().updateTask(staff);
                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);
                Toast.makeText(context,staff.name+" is updated",Toast.LENGTH_SHORT).show();
            }
        }.execute();
    }
    //end update

    //deleting data function
    public void DeleteTask(final Staff staff){
        new AsyncTask<Void,Void,Void>(){

            @Override
            protected Void doInBackground(Void... voids) {
                staffDatabase.staffDAO().deleteTask(staff);
                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);
                Toast.makeText(context,staff.name+" is deleted",Toast.LENGTH_SHORT).show();
            }
        }.execute();
    }
    //end delete
}
