package com.namphan.androidduan1.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;


import com.namphan.androidduan1.model.Maps;

import java.util.ArrayList;
import java.util.List;

public class MapDao {
    private SQLiteDatabase db;
    private DatabaseHelper dbHelper;
    public static final String TABLE_NAME1="Map";
    public static final String SQL_MAP = "CREATE TABLE Maps (longtitui double primary key ,latitui double );";
    public static final String TAG="MapsDao";
    public MapDao(Context context){
        dbHelper= new DatabaseHelper(context);
        db = dbHelper.getWritableDatabase();
    }



    public int inserMap(Maps maps){

        ContentValues values = new ContentValues();
        values.put("longitui",maps.getLongtitui());
        values.put("latitui",maps.getLatitui());


               return 1;     }
    //getA
    public List<Maps> getAllMaps()  {
        db = dbHelper.getReadableDatabase();
        List<Maps> dsMaps=new ArrayList<>();
        Cursor cursor =db.query(TABLE_NAME1,null,null,null,null,null,null);
        cursor.moveToFirst();
        while (cursor.isAfterLast()==false){
            Maps maps=new Maps();
            maps.setLongtitui(Double.parseDouble(cursor.getString(0)));
            maps.setLatitui(Double.parseDouble(cursor.getString(1)));

            dsMaps.add(maps);
            cursor.moveToNext();
        }
        cursor.close();


        Log.e("abc",dsMaps.toString());
        return dsMaps;

    }

    public int deleteMapsID(String longtitui){
        int result = db.delete(TABLE_NAME1,"longtitui=?",new String[]{longtitui});
        if (result == 0)
            return -1;
        return 1;
    }
    public int updateMaps(String fistname, String phone){
        ContentValues values = new ContentValues();
        values.put("phone",phone);
        values.put("fistname",fistname);
        int result = db.update(TABLE_NAME1,values,"fistname=?", new String[]{fistname});
        if (result == 0){
            return -1;
        }         return 1;
    }






}
