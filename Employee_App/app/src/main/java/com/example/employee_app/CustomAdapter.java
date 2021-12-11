package com.example.employee_app;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Random;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.MyViewHolder> {
    ArrayList<Staff> staffArrayList;
    Context context;

    public CustomAdapter(ArrayList<Staff> staffArrayList, Context context) {
        this.staffArrayList = staffArrayList;
        this.context = context;
    }

    //implementing methods
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        //calling objects
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.entity_staff,parent,false);
        MyViewHolder myViewHolder= new MyViewHolder(view);

        return myViewHolder;
    }


    @SuppressLint("RecyclerView")
    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        //Shows first letter of name
        holder.tv_name.setText(staffArrayList.get(position).getName()+"");
        holder.tv_position.setText(staffArrayList.get(position).getPosition()+"");
        holder.tv_title.setText(staffArrayList.get(position).getName().toUpperCase().charAt(0)+"");

        //Setting Lights in background
        Random random=new Random();
        int red= random.nextInt(133)+134;
        int green= random.nextInt(133)+134;
        int blue= random.nextInt(133)+134;


        holder.tv_title.setBackgroundColor(Color.rgb(red, green, blue));

        //Calling Update
        holder.img_update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                update_dialog(position);
            }
        });

        //Calling Delete
        holder.img_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                delete_dialog(position);
            }
        });

    }

    @Override
    public int getItemCount() {
        return staffArrayList.size();
    }
    //end methods

    //Creating View  (holds data for recycler view, 5 rows in database = 5 holders)
    public static class MyViewHolder extends RecyclerView.ViewHolder{

        TextView tv_title;
        TextView tv_name;
        TextView tv_position;
        ImageView img_update;
        ImageView img_delete;


        //Constructor for view holder
        public MyViewHolder(View itemview){

            super(itemview);

            this.tv_title=(TextView)itemview.findViewById(R.id.tv_title);
            this.tv_position=(TextView)itemview.findViewById(R.id.tv_position);
            this.tv_name=(TextView)itemview.findViewById(R.id.tv_name);
            this.img_update=(ImageView) itemview.findViewById(R.id.img_update);
            this.img_delete=(ImageView) itemview.findViewById(R.id.img_delete);



        }

    }//end View


    //Creating Update
    public void update_dialog(int position_of_update){

        AlertDialog.Builder builder=new AlertDialog.Builder(context);
        builder.setTitle("Update");

        LayoutInflater li=LayoutInflater.from(context);
        View view_update=li.inflate(R.layout.update_staff, null);
        builder.setView(view_update);

        EditText edt_name=(EditText)view_update.findViewById(R.id.edt_name);
        EditText edt_position=(EditText)view_update.findViewById(R.id.edt_position);

        edt_name.setText(staffArrayList.get(position_of_update).getName()+"");
        edt_position.setText(staffArrayList.get(position_of_update).getPosition()+"");


        builder.setPositiveButton("Update", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                if(edt_name.getText().toString().trim().isEmpty() ||
                        edt_position.getText().toString().trim().isEmpty()){

                    Toast.makeText(context,"Enter Value", Toast.LENGTH_SHORT).show();
                }
                else{
                    //updating in database

                    StaffRepository staffRepository=new StaffRepository(context);
                    String updated_name=edt_name.getText().toString().trim();
                    String updated_position=edt_position.getText().toString().trim();

                    Staff staff_update=new Staff(staffArrayList.get(position_of_update).getId(), updated_name, updated_position);
                    staffRepository.UpdateTask(staff_update);
                    //end database update


                    staffArrayList.get(position_of_update).setName(updated_name);
                    staffArrayList.get(position_of_update).setPosition(updated_position);


                    notifyDataSetChanged();
                    dialog.dismiss();
                }

            }
        });

        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        builder.show();


    }
    //End Update


    //Creating Delete
    public void delete_dialog(int position_of_delete){

        //Warning Message
        AlertDialog.Builder builder=new AlertDialog.Builder(context);
        builder.setTitle("Delete Employee");
        builder.setMessage("Confirm Deletion by pressing Yes");

        //User Input will delete employee
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                StaffRepository staffRepository=new StaffRepository(context);
                staffRepository.DeleteTask(staffArrayList.get(position_of_delete));

                staffArrayList.remove(position_of_delete);
                notifyDataSetChanged();
                dialog.dismiss();
            }
        });

        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        builder.show();
    }
}
