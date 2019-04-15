package com.namphan.androidduan1.database;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;


import com.namphan.androidduan1.model.User;

import java.util.ArrayList;
import java.util.List;

public class UserDao {
    private SQLiteDatabase db;
    private DatabaseHelper dbHelper;
    public static final String TABLE_NAME="User";
    public static final String SQL_USER = "CREATE TABLE User (email text primary key ,fistname text ,lastname text ,password text ,phone text);";
    public static final String TAG="UserDao";
    public UserDao(Context context){
        dbHelper= new DatabaseHelper(context);
        db = dbHelper.getWritableDatabase();
    }



    public int inserUser(User user){

        ContentValues values = new ContentValues();
        values.put("fistname",user.getFistname());
        values.put("lastname",user.getLastname());
        values.put("email",user.getEmail());
        values.put("password",user.getPassword());
        values.put("phone", user.getPhone());
        try {
            if(db.insert(TABLE_NAME,null,values)== -1){
                Log.e(TAG,"Loi sssssss");
                return -1;             }
        }catch (Exception ex){
            Log.e(TAG,ex.toString());
        }         return 1;     }
    //getA
    public List<User> getAllUser(){
        db = dbHelper.getReadableDatabase();
        List<User> dsUser=new ArrayList<>();
        Cursor cursor =db.query(TABLE_NAME,null,null,null,null,null,null);
            cursor.moveToFirst();
            while (cursor.isAfterLast()==false){
                User user=new User();
                user.setFistname(cursor.getString(0));
                user.setLastname(cursor.getString(1));
                user.setEmail(cursor.getString(2));
                user.setPassword(cursor.getString(3));
                user.setPhone(cursor.getString(4));
                dsUser.add(user);
                cursor.moveToNext();
        }
        cursor.close();


        Log.e("abc",dsUser.toString());
        return dsUser;

    }
    public int checkLogin(String email,String password){
        int restul=db.delete(TABLE_NAME,"email =? AND password =?",new String[]{email,password});
            if (restul==0)
                return -1;

            return 1;

    }
    //delete
    public int deleteNguoiDungByID(String email){
        int result = db.delete(TABLE_NAME,"email=?",new String[]{email});
        if (result == 0)
            return -1;
        return 1;
    }
    public int updateInfoNguoiDung(String fistname, String phone){
        ContentValues values = new ContentValues();
        values.put("phone",phone);
        values.put("fistname",fistname);
        int result = db.update(TABLE_NAME,values,"fistname=?", new String[]{fistname});
        if (result == 0){
            return -1;
        }         return 1;
    }





}
