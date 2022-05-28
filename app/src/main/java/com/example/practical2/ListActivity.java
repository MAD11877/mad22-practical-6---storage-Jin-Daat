package com.example.practical2;

import static java.lang.Math.round;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteCursorDriver;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQuery;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ListActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        ArrayList<User> userList = new ArrayList<>();
        UserDBHandler dbHandler = new UserDBHandler(this, null, null, 1);
        //Populate the list from the database
        userList = dbHandler.getUsers();
        System.out.println("Code is runned");
        //If app is running for the first time, run this condition.
        if (userList.size() == 0){
            System.out.println("Condition met");

            for (int i = 1; i <= 20; i ++){
                long ranInt = (long) Math.floor(Math.random() * 9_000_000_000L) + 1_000_000_000L;
                long desc = (long) Math.floor(Math.random() * 9_000_000_000L) + 1_000_000_000L;
//                if (ranInt % 3 == 0 ){
//                    User newUser = new User(
//                            "" + "Name" + ranInt,
//                            "" + "Description " + desc,
//                            i,
//                            true
//                    );
//                    dbHandler.addUser(newUser);
//                }
                User newUser = new User(
                        "" + "Name" + ranInt,
                        "" + "Description " + desc,
                        i,
                        false
                );
                dbHandler.addUser(newUser);
                //Add items into the list activity list to populate recycler view
                userList.add(newUser);
            }
        }

        RecyclerView rv = findViewById(R.id.rv);
        ListUserAdapter adapter = new ListUserAdapter(userList, ListActivity.this);
        LinearLayoutManager  layout = new LinearLayoutManager(this);
        rv.setLayoutManager(layout);
        rv.setAdapter(adapter);



    }
}