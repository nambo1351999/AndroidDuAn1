package com.namphan.androidduan1.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper2 extends SQLiteOpenHelper {
    public static final String DATA_NAME="dbOderFood2.db";
    public static final int VERSION=1;

    public DatabaseHelper2(Context context) {

        super(context, DATA_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL(MapDao.SQL_MAP);





    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("Drop table if exists " + MapDao.TABLE_NAME1);

        onCreate(db);

    }
}
