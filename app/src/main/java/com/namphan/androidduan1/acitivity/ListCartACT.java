package com.namphan.androidduan1.acitivity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.namphan.androidduan1.R;
import com.namphan.androidduan1.adapter.CartAdapter;
import com.namphan.androidduan1.adapter.ProductAdapter;
import com.namphan.androidduan1.database.CartDao;
import com.namphan.androidduan1.database.TheLoaiDAO;
import com.namphan.androidduan1.model.Cart;
import com.namphan.androidduan1.model.TheLoai;

import java.util.ArrayList;
import java.util.List;

public class ListCartACT extends AppCompatActivity {
    public static List<Cart> dsCart = new ArrayList<>();
    ListView lvCart;
   CartAdapter adapter = null;
   TextView tvTong;

    Button btnThanhToan;

   CartDao cartDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_cart);
        setTitle("Cart");

        lvCart = (ListView) findViewById(R.id.lvCart);
        registerForContextMenu(lvCart);
        cartDao= new CartDao(ListCartACT.this);
        dsCart = cartDao.getAllCart();
        adapter = new CartAdapter(this, dsCart);
        lvCart.setAdapter(adapter);
        btnThanhToan=findViewById(R.id.btnThanhToan);
        tvTong=findViewById(R.id.tvThanhToan);
        btnThanhToan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addThanhToan();
            }
        });




    }

    private void addThanhToan() {
        cartDao= new CartDao(ListCartACT.this);
        tvTong.setText( cartDao.thanhtoan()+" $" );


    }

    @Override
    protected void onResume() {
        super.onResume();
        dsCart.clear();
        dsCart = cartDao.getAllCart();
        adapter.changeDataset(cartDao.getAllCart());
    }



}
