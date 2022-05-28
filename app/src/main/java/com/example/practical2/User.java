package com.example.practical2;

public class User {
    public String name;
    public String description;
    public int id;
    public boolean followed;


    //    Constructor
    public User(){}

    public User (String userName, String userDesc, int userId, boolean userFollow){
        name = userName;
        description = userDesc;
        id = userId;
        followed = userFollow;
    }

    //Return user name
    public String getUserName(){return this.name;}
    //Return user description
    public String getUserDesc(){return this.description;}
    //Return user ID
    public int getUserID(){return this.id;}
    //Return user followed status
    public boolean getUserFollowed(){return this.followed;}

    //set user ID
    public void setUserID(int id){this.id = id;}
    //set user name
    public void setUserName(String name){this.name = name;}
    //set user description
    public void setUserDesc(String description){this.description = description;}
    //set user follow status
    public void setUserFollowed(boolean followed){this.followed = followed;}
}
