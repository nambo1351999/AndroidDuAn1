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
    private DatabaseHelper2 dbHelpers;
    public static final String TABLE_NAME1="Maps";
    public static final String SQL_MAP = "CREATE TABLE Maps (longtitui double primary key ,latitui double );";
    public static final String TAG="MapsDao";
    public MapDao(Context context){
        dbHelpers= new DatabaseHelper2(context);
        db = dbHelpers.getWritableDatabase();
    }



    public int inserMap(Maps maps){

        ContentValues values = new ContentValues();
        values.put("longtitui",maps.getLongtitui());
        values.put("latitui",maps.getLatitui());
        try {
            if(db.insert(TABLE_NAME1,null,values)== -1){
                return -1;
            }
        }catch (Exception ex){
            Log.e(TAG,ex.toString());
        }


        return 1;     }
    //getA
    public List<Maps> getAllMaps()  {
        db = dbHelpers.getReadableDatabase();
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

    public int deleteMapsID(Double longtitui){
        int result = db.delete(TABLE_NAME1,"longtitui=?",new String[]{longtitui+""});
        if (result == 0)
            return -1;
        return 1;
    }
    public int changeMaps(Maps maps){
        ContentValues values = new ContentValues();
        values.put("longtitui",maps.getLongtitui());
        values.put("latitui",maps.getLatitui());
        int result = db.update(TABLE_NAME1,values,"longtitui=?", new String[]{maps.getLongtitui()+""});
        if (result == 0){
            return -1;
        }
        return 1;
    }



}
