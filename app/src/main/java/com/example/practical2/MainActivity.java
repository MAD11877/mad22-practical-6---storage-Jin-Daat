package com.example.practical2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.time.Duration;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent receivingEnd = getIntent();
        String newName = receivingEnd.getStringExtra("newName");
        String newDesc = receivingEnd.getStringExtra("newDesc");
        boolean followStatus = receivingEnd.getBooleanExtra("followStatus", false);

        //Create a user obj from retrieved user
        User newUser = new User(newName, newDesc, 0, followStatus);

        //Set User name
        TextView userName = (TextView)findViewById(R.id.userName);
        userName.setText(newName);

        //Set User description
        TextView userDesc = (TextView)findViewById(R.id.userDesc);
        userDesc.setText(newDesc);

        Button followBtn = findViewById(R.id.follow);
        Button messageBtn = findViewById(R.id.message);

        followBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Button clickFollowBtn = findViewById(R.id.follow);

                if (!newUser.followed){
                    clickFollowBtn.setText("Unfollow");
                    newUser.followed = true;
                    Toast.makeText(getApplicationContext(), "Followed", Toast.LENGTH_SHORT).show();
                }
                else{
                    clickFollowBtn.setText("Follow");
                    newUser.followed = false;
                    Toast.makeText(getApplicationContext(), "Unfollowed", Toast.LENGTH_SHORT).show();
                }
            }

        });

        messageBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Button clickMessageBtn = findViewById(R.id.follow);
                Intent activityName = new Intent(MainActivity.this, MessageGroup.class);
                startActivity(activityName);
            }

        });


    }

}