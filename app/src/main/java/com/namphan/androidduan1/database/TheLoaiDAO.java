package com.namphan.androidduan1.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.namphan.androidduan1.model.TheLoai;

import java.util.ArrayList;
import java.util.List;

public class TheLoaiDAO {
    private SQLiteDatabase db;
    private DatabaseHelper1 dbHelper;
    public static final String TABLE_NAME = "TheLoai";
    public static final String SQL_THE_LOAI ="CREATE TABLE TheLoai (matheloai text primary key, tentheloai text, mota text, vitri int);";
    public static final String TAG = "TheLoaiDAO";
    public TheLoaiDAO(Context context) {
        dbHelper = new DatabaseHelper1(context);
        db = dbHelper.getWritableDatabase();
    }



    //insert
    public int inserTheLoai(TheLoai theLoai){
        ContentValues values = new ContentValues();
        values.put("matheloai",theLoai.getMaMon());
        values.put("tentheloai",theLoai.getTenMon());
        values.put("mota",theLoai.getMoTa());
        values.put("vitri",theLoai.getGia());
        try {
            if(db.insert(TABLE_NAME,null,values)== -1){
                return -1;
            }
        }catch (Exception ex){
            Log.e(TAG,ex.toString());
        }         return 1;
    }     //getAllTheLoai
    public List<TheLoai> getAllTheLoai(){
        List<TheLoai> dsTheLoai = new ArrayList<>();
        Cursor c =db.query(TABLE_NAME,null,null,null,null,null,null);
        c.moveToFirst();
        while (c.isAfterLast()==false){
            TheLoai ee = new TheLoai();
            ee.setMaMon(c.getString(0));
            ee.setTenMon(c.getString(1));
            ee.setMoTa(c.getString(2));
            ee.setGia(Integer.parseInt(c.getString(3)));
            dsTheLoai.add(ee);
            Log.d("//=====",ee.toString());
            c.moveToNext();
        }
        c.close();
        return dsTheLoai;
    }
    //update
    public int updateTheLoai(TheLoai theLoai){
        ContentValues values = new ContentValues();
        values.put("matheloai",theLoai.getMaMon());
        values.put("tentheloai",theLoai.getTenMon());
        values.put("mota",theLoai.getMoTa());
        values.put("vitri",theLoai.getGia());
        int result = db.update(TABLE_NAME,values,"matheloai=?", new String[]{theLoai.getMaMon()});
        if (result == 0){
            return -1;
        }         return 1;
    }
    //delete
    public int deleteTheLoaiByID(String matheloai){
        int result = db.delete(TABLE_NAME,"matheloai=?",new String[]{matheloai});
        if (result == 0)
            return -1;
        return 1;
    }
    public int getSoHang() {
        int vitri = 0;
        db = dbHelper.getWritableDatabase();
        String sql = "SELECT COUNT(vitri) from " + TABLE_NAME;
        Cursor cursor = db.rawQuery( sql, null );
        cursor.moveToFirst();
        while (cursor.isAfterLast() == false) {
            vitri = cursor.getInt( 0 );
            cursor.moveToNext();
        }
        cursor.close();
        return vitri;
    }



}
