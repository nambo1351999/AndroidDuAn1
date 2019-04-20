package com.namphan.androidduan1.acitivity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.namphan.androidduan1.MainActivity;
import com.namphan.androidduan1.R;
import com.namphan.androidduan1.database.CartDao;
import com.namphan.androidduan1.database.TheLoaiDAO;
import com.namphan.androidduan1.database.UserDao;
import com.namphan.androidduan1.model.Cart;
import com.namphan.androidduan1.model.TheLoai;
import com.namphan.androidduan1.model.User;
import com.nex3z.notificationbadge.NotificationBadge;

public class ProductDetailActivity extends AppCompatActivity {
    ImageView btnThemProduct;
    TheLoaiDAO theLoaiDAO;
    TextView edMaMon,edTenMon,edGia,edMoTa;
    String MaMon,TenMon,Gia,MoTa;
    CartDao cartDao;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sproduct);
        setTitle("Sản Phẩm");
        btnThemProduct=findViewById(R.id.btnAddTheLoai);
        edMaMon=findViewById(R.id.edMaTheLoai);
        edTenMon=findViewById(R.id.edTenTheLoai);
        edGia=findViewById(R.id.edViTri);
        edMoTa=findViewById(R.id.edMoTa);
        theLoaiDAO=new TheLoaiDAO(this);
        Intent in=getIntent();
        Bundle b=in.getExtras();
        MaMon=b.getString("MaMon");
        TenMon=b.getString("TenMon");
        Gia=b.getString("Gia");
        MoTa=b.getString("MoTa");
        edMaMon.setText(MaMon);
        edTenMon.setText(TenMon);
        edGia.setText(Gia);
        edMoTa.setText(MoTa);




    }


    public void addCart(View view) {
        cartDao=new CartDao(ProductDetailActivity.this);
        try {
                Cart cart = new Cart(edMaMon.getText().toString(), edTenMon.getText().toString(), edMoTa.getText().toString(),Integer.parseInt(edGia.getText().toString()));
                if (cartDao.inserCart(cart) > 0) {
                    Toast.makeText(getApplicationContext(), R.string.cart1, Toast.LENGTH_SHORT).show();


                } else {
                    Toast.makeText(getApplicationContext(), R.string.cart2, Toast.LENGTH_SHORT).show();

            }
        }catch (Exception ex){
            Log.e("Error", ex.toString());

        }


    }

    public void Show(View view) {
        Intent intent=new Intent(this,ListCartACT.class);
        startActivity(intent);
    }
}
