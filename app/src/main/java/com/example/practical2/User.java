package com.example.practical2;

public class User {
    public String name;
    public String description;
    public int id;
    public boolean followed;


    //    Constructor
    public User (String userName, String userDesc, int userId, boolean userFollow){
        name = userName;
        description = userDesc;
        id = userId;
        followed = userFollow;
    }
}
