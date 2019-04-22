package com.namphan.androidduan1;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.namphan.androidduan1.acitivity.IntroductACT;
import com.namphan.androidduan1.acitivity.ListCartACT;
import com.namphan.androidduan1.acitivity.ListMapsACT;
import com.namphan.androidduan1.acitivity.ListProductActivity;
import com.namphan.androidduan1.acitivity.ListUserActivity;
import com.namphan.androidduan1.acitivity.LoginActivity;
import com.namphan.androidduan1.database.TheLoaiDAO;

public class MenuACT extends AppCompatActivity {
    private TextView tvTong1;
    private TheLoaiDAO theLoaiDAO;
    ImageView btnChon,imgPhone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_act);
        tvTong1=findViewById(R.id.count);
        theLoaiDAO=new TheLoaiDAO(this);
        btnChon=findViewById(R.id.icon_badge);
        btnChon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tvTong1.setText(theLoaiDAO.getSoHang()+"");
            }
        });
        imgPhone=findViewById(R.id.phone);
        imgPhone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:0987654321"));
                startActivity(intent);
            }
        });





    }
    public void viewNguoiDung(View view) {
        Intent intent =new Intent(MenuACT.this, ListUserActivity.class);
        startActivity(intent);
    }

    public void viewCart(View view) {
        Intent intent =new Intent(MenuACT.this, ListCartACT.class);
        startActivity(intent);
    }

    public void viewGoogle(View view) {
        Intent intent1=new Intent(MenuACT.this, ListMapsACT.class);
        startActivity(intent1);
    }



    public void ViewGioiThieu(View view) {
        Intent intent=new Intent(this, IntroductACT.class);
        startActivity(intent);
    }

    public void ViewProduct(View view) {
        Intent intent1=new Intent(MenuACT.this, ListProductActivity.class);
        startActivity(intent1);
    }
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_context, menu);
        menu.setHeaderTitle("Chọn ");
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_login, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {

            Intent intent = new Intent(this, LoginActivity.class);
            startActivity(intent);
            return true;
        }
        if (id == R.id.action_exit) {

            startActivity(new Intent(getApplicationContext(), ListProductActivity.class));
            Intent startMain = new Intent(Intent.ACTION_MAIN);
            startMain.addCategory(Intent.CATEGORY_HOME);
            startActivity(startMain);
            finish();
            return true;
        }
        if (id == R.id.action_Listmap) {

          Intent intent=new Intent(this,ListMapsACT.class);
          startActivity(intent);
        }
        return super.onOptionsItemSelected( item );

    }

    public void viewHoaDoan(View view) {
        Intent intent1=new Intent(MenuACT.this, NotesListActivity.class);
        startActivity(intent1);


    }
}
