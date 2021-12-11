package com.example.employee_app;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;



public class InsertActivity extends AppCompatActivity {

    //initializing variables
    EditText edt_name, edt_position;
    Button btn_submit;

    String sname="", sposition="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert);

        //calling text boxes
        edt_name = (EditText)findViewById(R.id.edt_name);
        edt_position = (EditText)findViewById(R.id.edt_position);

        //calling button
        btn_submit = (Button) findViewById(R.id.btn_submit);


        //action listener to take in value and send it to database
        btn_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(edt_name.getText().toString().trim().isEmpty() ||
                        edt_position.getText().toString().trim().isEmpty()
                ){
                    Toast.makeText(getApplicationContext(),"Enter value", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    sname = edt_name.getText().toString().trim();
                    sposition= edt_position.getText().toString().trim();

                        Staff staff = new Staff(sname,sposition);
                        StaffRepository staffRepository = new StaffRepository(getApplicationContext());
                        staffRepository.InsertTask(staff);
                        finish();
                }

            }
        });




    }
}