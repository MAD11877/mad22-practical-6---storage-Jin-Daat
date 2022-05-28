package com.example.practical2;

import android.app.ListActivity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ListUserAdapter extends RecyclerView.Adapter<UserViewHolder> {
    ArrayList<User> userData;
    Context context;
    public ListUserAdapter(ArrayList<User> userData, Context context)
    {
        this.userData = userData;
        this.context = context;
    }

    @NonNull
    @Override
    public UserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View item = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_user, null, false);
        return new UserViewHolder(item);
    }

    @Override
    public void onBindViewHolder(@NonNull UserViewHolder holder, int position) {
        User user = userData.get(position);
        String newName = user.name;
        String newDesc = user.description;
        boolean followStatus = user.followed;


        if(!newName.substring(newName.length() - 1).equals("7")){
            holder.secondImage.setVisibility(View.GONE);
        }


        holder.name.setText(newName);
        holder.description.setText(newDesc);
        holder.profileImage.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(context);

                builder.setTitle("Profile");
                builder.setMessage(newName);
                builder.setCancelable(true);
                builder.setPositiveButton("View", new DialogInterface.OnClickListener(){
                	public void onClick(DialogInterface dialog, int id){
                	    Bundle extras = new Bundle();
                	    extras.putString("newName", newName);
                	    extras.putString("newDesc", newDesc);
                	    extras.putBoolean("followStatus", followStatus);

                        Intent activityName = new Intent(context, MainActivity.class);
                        activityName.putExtras(extras);
                        context.startActivity(activityName);

                    }
                });
                builder.setNegativeButton("Close", new DialogInterface.OnClickListener(){
                	public void onClick(DialogInterface dialog, int id){

                	}
                });
                AlertDialog alert = builder.create();
                alert.show();
            }
        });

    }

    @Override
    public int getItemCount() {
        return userData.size();
    }
}
