package com.example.employee_app;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.AsyncTask;
import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

public class ViewActivity extends AppCompatActivity {

    //initializing variables
    RecyclerView staffrecyclerview;
    RecyclerView.LayoutManager layoutManager;
    ArrayList<Staff> staffArrayList;
    CustomAdapter customAdapter;

    //function to see data in recyclerview
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view);

        staffrecyclerview=(RecyclerView)findViewById(R.id.staffrecyclerview);
        layoutManager=new LinearLayoutManager(getApplicationContext());
        staffrecyclerview.setLayoutManager(layoutManager);

        new LoadDataTask().execute();

    }

    //runs the instruction in the background and then synchronize again with main thread
    class LoadDataTask extends AsyncTask<Void,Void,Void>
    {
        StaffRepository staffRepository;
        List<Staff> staffList;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            staffRepository=new StaffRepository(getApplicationContext());
        }

        @Override
        protected Void doInBackground(Void... voids) {
            staffList=staffRepository.getStaff();
            staffArrayList=new ArrayList<>();

            for(int i=0;i<staffList.size();i++)
            {
                staffArrayList.add(staffList.get(i));
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            customAdapter=new CustomAdapter(staffArrayList, ViewActivity.this);
            staffrecyclerview.setAdapter(customAdapter);
        }
    }


}