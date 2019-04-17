package com.namphan.androidduan1.acitivity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.namphan.androidduan1.MapsActivity;
import com.namphan.androidduan1.R;
import com.namphan.androidduan1.adapter.ProductAdapter;
import com.namphan.androidduan1.database.TheLoaiDAO;
import com.namphan.androidduan1.model.TheLoai;

import java.util.ArrayList;
import java.util.List;

public class ListProductActivity extends AppCompatActivity  {
    public static List<TheLoai> dsTheLoai = new ArrayList<>();
    ListView lvTheLoai;
    ProductAdapter adapter = null;

    TheLoaiDAO theLoaiDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_product);
        setTitle("Product");

        lvTheLoai = (ListView) findViewById(R.id.lvTheLoai);
        registerForContextMenu(lvTheLoai);
        theLoaiDAO = new TheLoaiDAO(ListProductActivity.this);
        dsTheLoai = theLoaiDAO.getAllTheLoai();
        adapter = new ProductAdapter(this, dsTheLoai);
        lvTheLoai.setAdapter(adapter);


        lvTheLoai.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(ListProductActivity.this, ProductDetailActivity.class);
                Bundle b = new Bundle();
                b.putString("MaMon", dsTheLoai.get(position).getMaMon());
                b.putString("TenMon", dsTheLoai.get(position).getTenMon());
                b.putString("Gia", String.valueOf(dsTheLoai.get(position).getGia()));
                b.putString("MoTa", dsTheLoai.get(position).getMoTa());

                intent.putExtras(b);
                startActivity(intent);

            }
        });
        lvTheLoai.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                return false;
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_theloai, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.addThem:
                Intent intent = new Intent(ListProductActivity.this, ProductActivity.class);
                startActivity(intent);
                return (true);
        }


        return super.onOptionsItemSelected( item );

    }

    @Override
    protected void onResume() {
        super.onResume();
        dsTheLoai.clear();
        dsTheLoai = theLoaiDAO.getAllTheLoai();
        adapter.changeDataset(theLoaiDAO.getAllTheLoai());
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_context, menu);
        menu.setHeaderTitle("Chọn ");
    }
    @Override
    public boolean onContextItemSelected(MenuItem item) {
        switch(item.getItemId()) {
            case R.id.menu_ctx_edit:
                Intent intent1 = new Intent(ListProductActivity.this,EditProductACT.class);
                startActivity(intent1);
                return(true);
            case R.id.menu_ctx_del:
                Intent intent2 = new Intent(this,EditProductACT.class);
                startActivity(intent2);
                return(true);


        }
        return super.onContextItemSelected(item);

    }






}
