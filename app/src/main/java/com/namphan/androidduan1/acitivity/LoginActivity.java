package com.namphan.androidduan1.acitivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.namphan.androidduan1.MenuACT;
import com.namphan.androidduan1.R;
import com.namphan.androidduan1.database.UserDao;


public class LoginActivity extends AppCompatActivity {
    Button btnLogin;
    EditText edPass,edEmail;
    UserDao userDao;
    CheckBox chkRememberPass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
       inti();
        userDao=new UserDao(LoginActivity.this);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loginUser();
            }
        });
    }



    public void loginUser(){
        String email=edEmail.getText().toString();
        String pass=edPass.getText().toString();

        if (userDao.checkLogin(email,pass)>0) {
            Toast.makeText(getApplicationContext(), R.string.login_mani, Toast.LENGTH_SHORT).show();
            Intent intent =new Intent(LoginActivity.this, MenuACT.class);
            startActivity(intent);
            rememberUser(email,pass,chkRememberPass.isChecked());
            finish();
            // tên đăng nhập admin
        } else  if(email.equalsIgnoreCase("admin")&&pass.equalsIgnoreCase("admin")){
            Intent intent =new Intent(LoginActivity.this, MenuACT.class);
            startActivity(intent);
            rememberUser(email,pass,chkRememberPass.isChecked());
        }else{
            Toast.makeText(getApplicationContext(), R.string.login_check, Toast.LENGTH_SHORT).show();
        }

    }
    private void inti() {
        btnLogin=findViewById(R.id.Login);
        edEmail=findViewById(R.id.edEmail);
        edPass=findViewById(R.id.edPass12);
        chkRememberPass =  findViewById(R.id.chkRememberPass);
    }
    public void rememberUser(String u, String p, boolean status){
        SharedPreferences pref = getSharedPreferences("USER_FILE",MODE_PRIVATE);
        SharedPreferences.Editor edit = pref.edit();
        if (!status){

            edit.clear();
        }else {
            //luu du lieu
            edit.putString("USERNAME",u);
            edit.putString("PASSWORD",p);
            edit.putBoolean("REMEMBER",status);
        }
        edit.commit();


    }


}
