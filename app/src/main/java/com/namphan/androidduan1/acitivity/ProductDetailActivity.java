package com.namphan.androidduan1.acitivity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.namphan.androidduan1.R;
import com.namphan.androidduan1.database.TheLoaiDAO;
import com.namphan.androidduan1.model.TheLoai;

public class ProductDetailActivity extends AppCompatActivity {
    Button btnThanhToan;
    TextView textView;
    TheLoaiDAO theLoaiDAO;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sproduct);
        setTitle("Thanh Toan");
        btnThanhToan=findViewById(R.id.btnThanhToan);
        theLoaiDAO=new TheLoaiDAO(this);
        textView=findViewById(R.id.tvtong);
        btnThanhToan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addThanh();
            }
        });





    }

    private void addThanh() {
        textView.setText(theLoaiDAO.thanhtoan()+"$");

    }

}
