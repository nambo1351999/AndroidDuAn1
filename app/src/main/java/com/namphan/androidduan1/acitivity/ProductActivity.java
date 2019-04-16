package com.namphan.androidduan1.acitivity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.namphan.androidduan1.R;
import com.namphan.androidduan1.database.TheLoaiDAO;
import com.namphan.androidduan1.model.TheLoai;

public class ProductActivity extends AppCompatActivity {
    Button btnThemMon;
    TheLoaiDAO theLoaiDAO;
    EditText edMaMon,edTenMon,edGia,edMoTa;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product);
        inti();
    }



    public void addMaTheLoai(View view) {
            theLoaiDAO=new TheLoaiDAO(ProductActivity.this);
            TheLoai theLoai=new TheLoai(edMaMon.getText().toString(),edTenMon.getText().toString(),edMoTa.getText().toString(),Integer.parseInt(edGia.getText().toString()));
            try {
                if (validateForm()>0){
                    if (theLoaiDAO.inserTheLoai(theLoai) > 0) {
                        Toast.makeText(getApplicationContext(), R.string.add_product, Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(getApplicationContext(), R.string.delteproduct, Toast.LENGTH_SHORT).show();
                    }
                }
            } catch (Exception ex) {
                Log.e("Error", ex.toString());
            }
        }
        public int validateForm() {

            int check = 1;
            if (edMaMon.getText().length() == 0 || edTenMon.getText().length() == 0 || edMoTa.getText().length() == 0 || edGia.getText().length() == 0) {
                Toast.makeText(getApplicationContext(),  R.string.from_check, Toast.LENGTH_SHORT).show();
                check = -1;
            } else {


            }
            return check;

        }

    public void Huy(View view) {
        theLoaiDAO=new TheLoaiDAO(ProductActivity.this);
        edTenMon.setText("");
        edMaMon.setText("");
        edMoTa.setText("");
        edGia.setText("");

    }

    public void Show(View view) {
        Intent intent=new Intent(this,ListProductActivity.class);
        startActivity(intent);
    }
    private void inti() {

        btnThemMon = findViewById(R.id.btnAddTheLoai);
        edMaMon = findViewById(R.id.edMaMon);
        edTenMon = findViewById(R.id.edTenMon);
        edGia = findViewById(R.id.edGia);
        edMoTa = findViewById(R.id.edMoTa);
    }
}
