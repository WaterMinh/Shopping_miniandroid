package com.example.bkshopping;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ImplProductDAO implements IProductDAO{
    private Context mCtx;


    public ImplProductDAO(Context mCtx) {
        this.mCtx =mCtx;
    }

    @Override
    public List<Product> selectAll() throws ParseException {
        DatabaseHelper helper = new DatabaseHelper(mCtx);
        SQLiteDatabase db = helper.getWritableDatabase();

        String sql = "SELECT * FROM Product";
        Cursor c  = db.rawQuery(sql,null);

        List<Product> lst = new ArrayList<>();

        while (c.moveToNext()){
            int id = c.getInt(c.getColumnIndex("id"));
            String productName  = c.getString(c.getColumnIndex("productName"));
            int quantity = c.getInt(c.getColumnIndex("quantity"));
            float price = c.getFloat(c.getColumnIndex("price"));
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            Date purchase_date = dateFormat.parse(c.getString(c.getColumnIndex("purchase_date")));

            Product pr = new Product(id,productName,quantity,price,purchase_date);
            lst.add(pr);

        }

        return lst;
    }

    @Override
    public boolean insert(Product product) {
        DatabaseHelper helper = new DatabaseHelper(mCtx);
        SQLiteDatabase db = helper.getWritableDatabase();

        ContentValues content = new ContentValues();
        content.put("productName",product.getProductName());
        content.put("quantity",product.getQuantity());
        content.put("price",product.getPrice());
        content.put("quantity",product.getQuantity());

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        content.put("purchase_date",dateFormat.format(product.getPurchase_date()));

        long newID = db.insert("Product",null, content);
        if (newID >0){
            return true;
        }
        return false;
    }

    @Override
    public boolean update(Product product) {
        DatabaseHelper helper = new DatabaseHelper(mCtx);
        SQLiteDatabase db = helper.getWritableDatabase();

        ContentValues content = new ContentValues();
        content.put("productName",product.getProductName());
        content.put("quantity",product.getQuantity());
        content.put("price",product.getPrice());
        content.put("quantity",product.getQuantity());

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        content.put("purchase_date",dateFormat.format(product.getPurchase_date()));

        int row = db.update("Product",content, "id = ?",new String[] {String.valueOf(product.getId())});
        if (row > 0){
            return true;
        }
        return false;
    }

    @Override
    public boolean delete(int id) {
        DatabaseHelper helper = new DatabaseHelper(mCtx);
        SQLiteDatabase db = helper.getWritableDatabase();

        int row = db.delete("Product", "id = ?", new String[] {String.valueOf(id)});
        if (row > 0){
            return true;
        }
        return false;
    }

    @Override
    public Product selectById(int idOrder) throws ParseException {
        DatabaseHelper helper = new DatabaseHelper(mCtx);
        SQLiteDatabase db = helper.getWritableDatabase();

        String sql = "SELECT * FROM Product WHERE id= ?";

        Cursor c = db.rawQuery(sql, new String[]{String.valueOf(idOrder)});
        while (c.moveToNext()) {

            int id = c.getInt(c.getColumnIndex("id"));
            String productName = c.getString(c.getColumnIndex("productName"));
            int quantity = c.getInt(c.getColumnIndex("quantity"));
            float price = c.getFloat(c.getColumnIndex("price"));
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            Date purchase_date = dateFormat.parse(c.getString(c.getColumnIndex("departure_date")));

            Product pr = new Product(id, productName, quantity, price, purchase_date);
            return pr;
        }
        return null;
    }
}
