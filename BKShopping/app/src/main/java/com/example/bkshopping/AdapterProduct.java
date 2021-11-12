package com.example.bkshopping;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class AdapterProduct extends ArrayAdapter<Product> {
    private Context mCtx;
    private int mLayout;
    private List<Product> mLst;

    public AdapterProduct( Context context, int resource, List<Product> objects) {
        super(context, resource, objects);
        this.mCtx =context;
        this.mLayout =resource;
        this.mLst = objects;
    }


    @Override
    public View getView(int position,  View convertView,  ViewGroup parent) {
        View item =convertView;
        if (item == null){
            item = LayoutInflater.from(mCtx).inflate(mLayout, null);

        }
        Product pr = mLst.get(position);

        ((TextView) item.findViewById(R.id.itemTensp)).setText(String.valueOf(pr.getProductName()));
        ((TextView) item.findViewById(R.id.itemGia)).setText(String.valueOf(pr.getPrice()));
        ((TextView) item.findViewById(R.id.itemSoluong)).setText(String.valueOf(pr.getQuantity()));
        ((TextView) item.findViewById(R.id.itemTongtien)).setText(String.valueOf(pr.getQuantity() * pr.getPrice() ));
        ((TextView) item.findViewById(R.id.itemNgaydat)).setText(DateUtility.dateToString(pr.getPurchase_date()));


        return item;
    }
}
