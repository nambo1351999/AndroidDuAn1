package com.namphan.androidduan1.acitivity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.namphan.androidduan1.R;
import com.namphan.androidduan1.database.CartDao;
import com.namphan.androidduan1.database.TheLoaiDAO;
import com.namphan.androidduan1.model.TheLoai;

public class EditProductACT extends AppCompatActivity {
   Button btnThemProduct;
    TheLoaiDAO theLoaiDAO;
    TextView edMaMon,edTenMon,edGia,edMoTa;
    String MaMon1,TenMon1,Gia1,MoTa1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_product_act);
        setTitle("CHI TIẾT THỂ LOẠI");
        btnThemProduct=findViewById(R.id.btnAddTheLoai);
        edMaMon=findViewById(R.id.edMaTheLoai);
        edTenMon=findViewById(R.id.edTenTheLoai);
        edGia=findViewById(R.id.edViTri);
        edMoTa=findViewById(R.id.edMoTa);
        theLoaiDAO=new TheLoaiDAO(this);



    }

    public void addSaveProduct(View view) {
        TheLoai theLoai=new TheLoai();
        if (theLoaiDAO.updateTheLoai(theLoai) > 0) {
            Toast.makeText(getApplicationContext(), R.string.udap_user, Toast.LENGTH_SHORT).show();

    }else {
        Toast.makeText(this, "Error", Toast.LENGTH_SHORT).show();
    }
    }

    public void Huy(View view) {
        edMaMon.setText("");
        edTenMon.setText("");
        edGia.setText("");
        edMoTa.setText("");
    }

    public void Show(View view) {
        Intent intent=new Intent(this,ListProductActivity.class);
        startActivity(intent);
    }
}
