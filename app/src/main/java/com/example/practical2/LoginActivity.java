package com.example.practical2;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class LoginActivity extends AppCompatActivity {
    ArrayList<UserAccount> userLoginList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_activity);

        //Log in button
        Button logInBtn = findViewById(R.id.login_btn);
        readData();


        logInBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText userName = findViewById(R.id.username_inputField);
                EditText password = findViewById(R.id.input_passwordField);
                UserAccount userAcc = new UserAccount(userName.getText().toString(), password.getText().toString());
                System.out.println(validateLogIn(userAcc) + "Validation");
                if (validateLogIn(userAcc)){
                    startActivity(new Intent(LoginActivity.this, ListActivity.class));
                }
                else{
                    System.out.println("Invalid login credentials");
                }

            }
        });
    }

    public void readData(){
        DatabaseReference fireDB = FirebaseDatabase.getInstance("https://mad-practical-6-7e611-default-rtdb.asia-southeast1.firebasedatabase.app").getReference().child("Users");

        // Read from the database
        fireDB.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                userLoginList.clear();
                for (DataSnapshot userAccount : dataSnapshot.getChildren()){
                    UserAccount userAcc = userAccount.getValue(UserAccount.class);
                    userLoginList.add(userAcc);
                }
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value;
                System.out.println("Failed to read value.");
            }
        });
    }

    public boolean validateLogIn(UserAccount userAcc){
        for (int i = 0; i < userLoginList.size(); i ++){
            System.out.println(userAcc.username + " " + userAcc.password + "keyed in details");
            System.out.println(userLoginList.get(0).username + " " + userLoginList.get(0).password + "keyed in details");
            if ((userAcc.username.equals(userLoginList.get(i).username)) && (userAcc.password.equals(userLoginList.get(i).password))){
                return true;
            }
        }
        return false;
    }
}
