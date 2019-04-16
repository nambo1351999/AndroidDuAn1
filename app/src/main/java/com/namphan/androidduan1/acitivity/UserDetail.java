package com.namphan.androidduan1.acitivity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Patterns;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.namphan.androidduan1.R;
import com.namphan.androidduan1.database.UserDao;


public class UserDetail extends AppCompatActivity {
    EditText edFistName, edPhone;
    UserDao userDao;
    String email,FistName,phone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_detail);
        edFistName = (EditText) findViewById(R.id.edFullName);
        edPhone = (EditText) findViewById(R.id.edPhone);
        userDao = new UserDao(this);
        Intent in = getIntent();
        Bundle b = in.getExtras();
        FistName = b.getString("FistName");
        phone=b.getString("Phone");

        edFistName.setText(FistName);
        edPhone.setText(phone);
    }

    public void updateUser(View view) {
        if (checkFrom()>0) {
            if (userDao.updateInfoNguoiDung(edFistName.getText().toString(), edPhone.getText().toString()) > 0) {
                Toast.makeText(getApplicationContext(), R.string.udap_user, Toast.LENGTH_SHORT).show();
            }
        }else {
            Toast.makeText(this, "Error", Toast.LENGTH_SHORT).show();
        }
    }

    public void Huy(View view) {
        Intent intent=new Intent(this,ListUserActivity.class);
        startActivity(intent);
    }
    public int checkFrom() {
        int check=1;

        String fname=edFistName.getText().toString();

        String phone=edPhone.getText().toString();

        String regexStr = "^[0-9]$";
        if(fname.isEmpty()||phone.isEmpty()){
            Toast.makeText(getApplicationContext(), "Please enter full information", Toast.LENGTH_SHORT).show();
            check=-1;
        }else if(phone.length()<10||phone.length()>12) {
            Toast.makeText(getApplicationContext(), "Please enter " + "\n" + " valid phone number", Toast.LENGTH_SHORT).show();
            check = -1;
        }


            return check;
        }

}
