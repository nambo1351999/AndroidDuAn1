package com.namphan.androidduan1.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.namphan.androidduan1.model.Cart;
import com.namphan.androidduan1.model.TheLoai;

import java.util.ArrayList;
import java.util.List;

public class CartDao {
    private SQLiteDatabase db;
    private DatabaseHelper3 dbHelper3;
    public static final String TABLE_NAME = "Cart";
    public static final String SQL_CART ="CREATE TABLE Cart (mamon text primary key, tenmon text, mota text, gia int);";
    public static final String TAG = "CartDAO";
    public CartDao(Context context) {
        dbHelper3 = new DatabaseHelper3(context);
        db = dbHelper3.getWritableDatabase();
    }
    //insert
    public int inserCart(Cart cart){
        ContentValues values = new ContentValues();
        values.put("mamon",cart.getMaMon());
        values.put("tenmon",cart.getTenMon());
        values.put("mota",cart.getMoTa());
        values.put("gia",cart.getGia());
        try {
            if(db.insert(TABLE_NAME,null,values)== -1){
                return -1;
            }
        }catch (Exception ex){
            Log.e(TAG,ex.toString());
        }         return 1;
    }
    public List<Cart> getAllCart(){
        List<Cart> dsCart = new ArrayList<>();
        Cursor c =db.query(TABLE_NAME,null,null,null,null,null,null);
        c.moveToFirst();
        while (c.isAfterLast()==false){
            Cart ee = new Cart();
            ee.setMaMon(c.getString(0));
            ee.setTenMon(c.getString(1));
            ee.setMoTa(c.getString(2));
            ee.setGia(Integer.parseInt(c.getString(3)));
            dsCart.add(ee);
            Log.d("//=====",ee.toString());
            c.moveToNext();
        }
        c.close();
        return dsCart;
    }

    public int deleteTheLoaiByID(String mamon){
        int result = db.delete(TABLE_NAME,"mamon=?",new String[]{mamon});
        if (result == 0)
            return -1;
        return 1;
    }
    public int thanhtoan() {
        int gia = 0;
        db = dbHelper3.getWritableDatabase();
        String sql = "SELECT SUM(gia) from " + TABLE_NAME;
        Cursor cursor = db.rawQuery( sql, null );
        cursor.moveToFirst();
        while (cursor.isAfterLast() == false) {
            gia = cursor.getInt(0);
            cursor.moveToNext();
        }
        cursor.close();
        return gia;
    }
}
