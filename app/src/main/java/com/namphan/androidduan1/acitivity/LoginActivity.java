package com.namphan.androidduan1.acitivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Toast;

import com.namphan.androidduan1.MenuACT;
import com.namphan.androidduan1.R;
import com.namphan.androidduan1.database.UserDao;


public class LoginActivity extends AppCompatActivity implements TextWatcher, CompoundButton.OnCheckedChangeListener {
    Button btnLogin;
    EditText edPass,edEmail;
    UserDao userDao;
    private CheckBox rem_userpass;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    private static final String PREF_NAME = "prefs";
    private static final String KEY_REMEMBER = "remember";
    private static final String KEY_USERNAME = "username";
    private static final String KEY_PASS = "password";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
       inti();
       remUserPass();
        userDao=new UserDao(LoginActivity.this);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loginUser();
            }
        });
    }

    private void remUserPass() {

        sharedPreferences = getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
        if(sharedPreferences.getBoolean(KEY_REMEMBER, false))
            rem_userpass.setChecked(true);
        else
            rem_userpass.setChecked(false);

        edEmail.setText(sharedPreferences.getString(KEY_USERNAME,""));
        edPass.setText(sharedPreferences.getString(KEY_PASS,""));

        edEmail.addTextChangedListener(this);
        edPass.addTextChangedListener(this);
        rem_userpass.setOnCheckedChangeListener(this);

    }


    public void loginUser(){
        String email=edEmail.getText().toString();
        String pass=edPass.getText().toString();

        if (userDao.checkLogin(email,pass)>0) {
            Toast.makeText(getApplicationContext(), R.string.login_mani, Toast.LENGTH_SHORT).show();
            Intent intent =new Intent(LoginActivity.this, MenuACT.class);
            startActivity(intent);

            finish();
            // tên đăng nhập admin
        } else  if(email.equalsIgnoreCase("admin")&&pass.equalsIgnoreCase("admin")){
            Intent intent =new Intent(LoginActivity.this, MenuACT.class);
            startActivity(intent);

        }else{
            Toast.makeText(getApplicationContext(), R.string.login_check, Toast.LENGTH_SHORT).show();
        }

    }
    private void inti() {
        btnLogin=findViewById(R.id.Login);
        edEmail=findViewById(R.id.edEmail);
        edPass=findViewById(R.id.edPass12);
        rem_userpass =  findViewById(R.id.chkRememberPass);
    }


    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {

    }

    @Override
    public void afterTextChanged(Editable s) {

    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        managePrefs();
    }
    private void managePrefs(){
        if(rem_userpass.isChecked()){
            editor.putString(KEY_USERNAME, edEmail.getText().toString().trim());
            editor.putString(KEY_PASS, edPass.getText().toString().trim());
            editor.putBoolean(KEY_REMEMBER, true);
            editor.apply();
        }else{
            editor.putBoolean(KEY_REMEMBER, false);
            editor.remove(KEY_PASS);//editor.putString(KEY_PASS,"");
            editor.remove(KEY_USERNAME);//editor.putString(KEY_USERNAME, "");
            editor.apply();
        }
    }
}
