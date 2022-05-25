package com.example.practical2;

import static java.lang.Math.round;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.content.Intent;
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
        for (int i = 1; i < 21; i ++){
            long ranInt = (long) Math.floor(Math.random() * 9_000_000_000L) + 1_000_000_000L;
            long desc = (long) Math.floor(Math.random() * 9_000_000_000L) + 1_000_000_000L;
            User newUser = new User(
                    "" + "Name" + ranInt,
                    "" + "Description " + desc,
                    i,
                    false
            );
            userList.add(newUser);
        }

        RecyclerView rv = findViewById(R.id.rv);
        ListUserAdapter adapter = new ListUserAdapter(userList, ListActivity.this);
        LinearLayoutManager  layout = new LinearLayoutManager(this);

        rv.setLayoutManager(layout);
        rv.setAdapter(adapter);



    }
}