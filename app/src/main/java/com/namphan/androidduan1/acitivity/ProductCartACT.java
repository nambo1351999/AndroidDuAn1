package com.namphan.androidduan1.acitivity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

import com.namphan.androidduan1.R;
import com.namphan.androidduan1.database.TheLoaiDAO;
import com.namphan.androidduan1.database.UserDao;

public class ProductCartACT extends AppCompatActivity {
    TextView tvName, tvGia;
     TheLoaiDAO theLoaiDAO;
    String name,gia;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_cart_act);
        tvName =  findViewById(R.id.tvName);
        tvGia =  findViewById(R.id.tvGia);
        theLoaiDAO = new TheLoaiDAO(this);
        Intent in = getIntent();
        Bundle b = in.getExtras();
        name= b.getString("Name");
        gia=b.getString("Gia");

        tvName.setText(name);
        tvGia.setText(gia);
    }
}
