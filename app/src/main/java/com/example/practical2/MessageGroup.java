package com.example.practical2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MessageGroup extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message_group);

        Button groupOneBtn = (Button)findViewById(R.id.group1);
        Button groupTwoBtn = (Button)findViewById(R.id.group2);

        groupOneBtn.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                getSupportFragmentManager().beginTransaction().replace(R.id.frame1, GroupOneFragment.class, null).commit();
            }
        });

        groupTwoBtn.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                getSupportFragmentManager().beginTransaction().replace(R.id.frame1, GroupTwoFragment.class, null).commit();
            }
        });
    }
}