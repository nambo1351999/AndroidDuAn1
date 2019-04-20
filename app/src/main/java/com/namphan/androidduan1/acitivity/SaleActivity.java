package com.namphan.androidduan1.acitivity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.namphan.androidduan1.R;
import com.namphan.androidduan1.model.Sale;

import java.util.List;

public class SaleActivity extends AppCompatActivity {
    EditText nhapCode;
    Button btnNhap;
    String nhap;
    Sale sale;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sale);
        btnNhap=findViewById(R.id.ok);
        nhapCode=findViewById(R.id.NhapCode);
        nhap=btnNhap.getText().toString();



    }
}
