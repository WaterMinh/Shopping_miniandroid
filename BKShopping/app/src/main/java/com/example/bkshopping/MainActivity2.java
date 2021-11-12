package com.example.bkshopping;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class MainActivity2 extends AppCompatActivity {
    List<Product> mLst;
    AdapterProduct mAdapter;
    ListView lstView;
    int currentID;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        try {
            loadProduct();
        } catch (ParseException e) {
            e.printStackTrace();
        }

        //registerForContextMenu(lstView);

        //Lấy dữ liệu đổ lên form

        lstView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Product product = mLst.get(position);
                currentID = product.getId();
                ((EditText)findViewById(R.id.editTenmonhang)).setText(product.getProductName() + "");
                ((EditText)findViewById(R.id.editSoluong)).setText(product.getQuantity() + "");
                ((EditText) findViewById(R.id.editGiatien)).setText(product.getPrice() + "");
                ((EditText) findViewById(R.id.pickNgayMua)).setText(DateUtility.dateToString(product.getPurchase_date()));
                ((Button)findViewById(R.id.btnAdd)).setEnabled(false);
                ((Button)findViewById(R.id.btnDelete)).setEnabled(true);
                ((Button)findViewById(R.id.btnUpdate)).setEnabled(true);
            }
        });

    }

    //Đổ dữ liệu từ database lên listview
    private void loadProduct() throws ParseException {
        IProductDAO dao = new ImplProductDAO(this);
        mLst = dao.selectAll();

        mAdapter = new AdapterProduct(this,R.layout.item_order,mLst);

        lstView = findViewById(R.id.listProduct);
        lstView.setAdapter(mAdapter);


    }

    // Pick date cho form
    public void openDatePicker(View view) {
        EditText ngayMua = findViewById(R.id.pickNgayMua);

        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        final int day = calendar.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog.OnDateSetListener listener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                Calendar calen = Calendar.getInstance();
                calen.set(year,month, dayOfMonth);
                SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
                ngayMua.setText(format.format(calen.getTime()));


            }
        };

        DatePickerDialog datePickerDialog = new DatePickerDialog(this, listener, year, month, day);
        datePickerDialog.show();

    }


    @Override
    protected void onResume() {
        super.onResume();
        try {
            loadProduct();
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }


    public void insert(View view) throws ParseException {
        // Lấy dữ liệu lên form
        String productName = ((EditText) findViewById(R.id.editTenmonhang)).getText().toString();
        int quantity = Integer.parseInt(((EditText) findViewById(R.id.editSoluong)).getText().toString());
        float price = Float.parseFloat(((EditText) findViewById(R.id.editGiatien)).getText().toString());
        String strPurchase_date = ((EditText)findViewById(R.id.pickNgayMua)).getText().toString();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date purchase_date = dateFormat.parse(strPurchase_date);



        // Khởi tạo đối tượng Product
        Product product = new Product(productName, quantity, price,purchase_date);


        // Đối tượng DAO
        IProductDAO dao = new ImplProductDAO(this);
        boolean isOk = dao.insert(product);

        if (isOk) {
            Toast.makeText(this, "Thêm mới sản phẩm thành công!", Toast.LENGTH_SHORT).show();
            loadProduct();
        } else {
            Toast.makeText(this, "Thêm mới sản phẩm thất bại!", Toast.LENGTH_SHORT).show();
        }

    }




    public void delete(View view) {
        IProductDAO dao = new ImplProductDAO(this);
        boolean isOk = dao.delete(currentID);
        if (isOk){
            Toast.makeText(this, "Xóa sản phẩm thành công!", Toast.LENGTH_SHORT).show();
            ((EditText) findViewById(R.id.editTenmonhang)).setText("");
            ((EditText) findViewById(R.id.editSoluong)).setText("");
            ((EditText) findViewById(R.id.editGiatien)).setText("");
            ((EditText) findViewById(R.id.pickNgayMua)).setText("");
            mAdapter.notifyDataSetChanged();
            onResume();

        }else {
            Toast.makeText(this, "Xóa sản phẩm thất bại!", Toast.LENGTH_SHORT).show();
        }
    }


    public void update(View view) throws ParseException {
        // Lấy dữ liệu lên form
        String productName = ((EditText) findViewById(R.id.editTenmonhang)).getText().toString();
        int quantity = Integer.parseInt(((EditText) findViewById(R.id.editSoluong)).getText().toString());
        float price = Float.parseFloat(((EditText) findViewById(R.id.editGiatien)).getText().toString());
        String strPurchase_date = ((EditText)findViewById(R.id.pickNgayMua)).getText().toString();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date purchase_date = dateFormat.parse(strPurchase_date);

        // Khởi tạo đối tượng Product
        Product product = new Product(currentID, productName, quantity, price, purchase_date);

        // Đối tượng DAO
        IProductDAO dao = new ImplProductDAO(this);
        boolean isOk = dao.update(product);

        if (isOk) {
            Toast.makeText(this, "Cập nhật thành công", Toast.LENGTH_SHORT).show();
            findViewById(R.id.btnAdd).setEnabled(true);
            findViewById(R.id.btnUpdate).setEnabled(false);
            findViewById(R.id.btnDelete).setEnabled(false);
            ((EditText) findViewById(R.id.editTenmonhang)).setText("");
            ((EditText) findViewById(R.id.editSoluong)).setText("");
            ((EditText) findViewById(R.id.editGiatien)).setText("");
            ((EditText) findViewById(R.id.pickNgayMua)).setText("");
            loadProduct();

        } else {
            Toast.makeText(this, "Cập nhật thất bại", Toast.LENGTH_SHORT).show();
        }


    }
}