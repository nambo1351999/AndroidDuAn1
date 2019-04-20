package com.namphan.androidduan1.acitivity;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

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
    private Dialog dialog;

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
                showAlertDialog();
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
    public void showAlertDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Hóa đơn");
        builder.setMessage("Hóa đơn của bạn hết :"+tvTong.getText());



        builder.setCancelable(false);
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Toast.makeText(ListCartACT.this, "Bạn hãy chọn địa chỉ", Toast.LENGTH_SHORT).show();
                Intent intent=new Intent(ListCartACT.this,ListMapsACT.class);
                startActivity(intent);
            }
        });
        builder.setNegativeButton("Cannel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();

    }



}
