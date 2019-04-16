package com.namphan.androidduan1.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper3 extends SQLiteOpenHelper {
    public static final String DATA_NAME="dbOderFood23.db";
    public static final int VERSION=1;

    public DatabaseHelper3(Context context) {

        super(context, DATA_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL(CartDao.SQL_CART);





    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("Drop table if exists " + CartDao.TABLE_NAME);

        onCreate(db);

    }
}
