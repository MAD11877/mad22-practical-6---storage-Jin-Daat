package com.example.practical2;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class UserAccount {
    public String username;
    public String password;

    public UserAccount(){}

    public UserAccount(String username, String password){
        this.username = username;
        this.password = password;
    }
}


