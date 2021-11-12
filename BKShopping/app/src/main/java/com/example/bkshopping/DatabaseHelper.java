package com.example.bkshopping;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static  final String DATABASE_NAME = "BKShopping.sqlite";
    private static final int DATABASE_VERSION = 1;
    private Context mCtx;

    public DatabaseHelper( Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.mCtx = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE Product (\n" +
                "    id            INTEGER       PRIMARY KEY AUTOINCREMENT,\n" +
                "    productName   VARCHAR (200) NOT NULL,\n" +
                "    quantity      INT           NOT NULL,\n" +
                "    price         Float        NOT NULL,\n" +
                "    purchase_date DATE      NOT NULL\n" +
                ");\n";
        db.execSQL(sql);

        sql = "INSERT INTO Product (productName, quantity, price, purchase_date) VALUES ('Bỉm Bobbi', 6, 900000, '2021-05-18')";
        db.execSQL(sql);
        sql = "INSERT INTO Product (productName, quantity, price, purchase_date) VALUES ('Sữa', 16, 10000000, '2021-05-18')";
        db.execSQL(sql);
        sql = "INSERT INTO Product (productName, quantity, price, purchase_date) VALUES ('Đồ chơi', 7, 780000, '2021-05-18')";
        db.execSQL(sql);



    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
