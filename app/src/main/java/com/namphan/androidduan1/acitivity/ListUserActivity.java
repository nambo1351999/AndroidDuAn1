package com.namphan.androidduan1.acitivity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;


import com.namphan.androidduan1.R;
import com.namphan.androidduan1.adapter.UserAdapter;
import com.namphan.androidduan1.database.UserDao;
import com.namphan.androidduan1.model.User;

import java.util.ArrayList;
import java.util.List;


public class ListUserActivity extends AppCompatActivity {
    public static List<User> dsUser = new ArrayList<>();
    ListView lvUser;
    UserAdapter adapter = null;
    UserDao userDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_user);
        lvUser =  findViewById(R.id.lvUser);
        userDao = new UserDao(ListUserActivity.this);
        dsUser = userDao.getAllUser();
        adapter = new UserAdapter(ListUserActivity.this, dsUser);
        lvUser.setAdapter(adapter);
        lvUser.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(ListUserActivity.this,UserDetail.class);

                Bundle b = new Bundle();
                b.putString("FistName", dsUser.get(position).getFistname());
                b.putString("Phone", dsUser.get(position).getPhone());

                intent.putExtras(b);
                startActivity(intent);
            }
        });
        lvUser.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                return false;
            }
        });


    }
    @Override
    protected void onResume() {
        super.onResume();
        dsUser.clear();
        dsUser = userDao.getAllUser();
        adapter.changeDataset(userDao.getAllUser());
    }
}
