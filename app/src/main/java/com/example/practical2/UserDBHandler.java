package com.example.practical2;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class UserDBHandler extends SQLiteOpenHelper {

    public static final String TABLE_USERS = "Users";
    public static final String COLUMN_ID = "UserID";
    public static final String COLUMN_USERNAME = "Username";
    public static final String COLUMN_DESCRIPTION = "UserDesc";
    public static final String COLUMN_FOLLOWED = "UserFollowed";

    public UserDBHandler(Context context, String name, SQLiteDatabase.CursorFactory factory, int version){
        super(context, "Users.db", factory, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_USER_TABLE = "CREATE TABLE " + TABLE_USERS + " (" +
                COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                COLUMN_USERNAME+ " TEXT," +
                COLUMN_DESCRIPTION + " TEXT," +
                COLUMN_FOLLOWED + " BOOLEAN)";
        db.execSQL(CREATE_USER_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USERS);
        onCreate(db);
    }

    public void addUser(User user){
        SQLiteDatabase db = this.getWritableDatabase();

        String query = "INSERT INTO " + TABLE_USERS
                + "(" + COLUMN_USERNAME + ","
                + COLUMN_DESCRIPTION + ","
                + COLUMN_FOLLOWED + ")"
                + " VALUES(\"" + user.getUserName() + "\"," + "\"" + user.getUserDesc() + "\"," + "" + user.getUserFollowed() + ")";

        db.execSQL(query);
        db.close();
        System.out.println("Called");
    }

    public User findUser(User user){
        //Find user from database
        String query = "SELECT * FROM " + TABLE_USERS + " WHERE" + COLUMN_USERNAME + " = " + "\"" + user.name + "\"";

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);

        User selectedUser = new User();
        if (cursor.moveToFirst()){
            selectedUser.setUserID(Integer.parseInt(cursor.getString(0)));
            selectedUser.setUserName(cursor.getString(1));
            selectedUser.setUserDesc(cursor.getString(2));
            selectedUser.setUserFollowed(Boolean.parseBoolean(cursor.getString(3)));
            cursor.close();
        }
        else{selectedUser = null;}
        db.close();
        return selectedUser;
    }

    public void updateUser(User user){
        System.out.println(user.getUserFollowed());
        int followValue = user.getUserFollowed() ? 1:0;
        System.out.println(followValue);
        String query = "UPDATE " + TABLE_USERS + " SET " + COLUMN_FOLLOWED + " = " + followValue
                + " WHERE " + COLUMN_USERNAME + " = \"" + user.getUserName() + "\"";
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL(query);
    }

    public ArrayList<User> getUsers(){
        ArrayList<User> userList = new ArrayList<>();
        String query = "SELECT * FROM " + TABLE_USERS;

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        if (cursor.getCount() == 0 ){return userList;}
        while(cursor.moveToNext()){
            User newUser = new User();
            newUser.setUserID(cursor.getInt(0));
            newUser.setUserName(cursor.getString(1));
            newUser.setUserDesc(cursor.getString(2));
            int booleanValue = cursor.getInt(3);
            if (booleanValue == 1){
                newUser.setUserFollowed(true);
                userList.add(newUser);
            }
            else if (booleanValue == 0){
                newUser.setUserFollowed(false);
                userList.add(newUser);
            }
        }
        return userList;
    }
}
