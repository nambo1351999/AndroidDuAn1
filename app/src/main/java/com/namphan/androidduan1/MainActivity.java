package com.namphan.androidduan1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.namphan.androidduan1.acitivity.LoginActivity;
import com.namphan.androidduan1.database.UserDao;
import com.namphan.androidduan1.model.User;

public class MainActivity extends AppCompatActivity {
    Button btnLogin,btnSgin;
    EditText edLastName,edFistName,edEmail,edPhone,edPassword;
    UserDao userDao;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        inti();

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1=new Intent(MainActivity.this, LoginActivity.class);
                startActivity(intent1);
            }
        });
        btnSgin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addUser();

            }
        });


    }

    private void inti() {
        btnLogin=findViewById(R.id.btnLogin);
        btnSgin=findViewById(R.id.btnSignup);
        edEmail=findViewById(R.id.editEmail);
        edFistName=findViewById(R.id.editFname);
        edLastName=findViewById(R.id.editLname);
        edPassword=findViewById(R.id.editPass);
        edPhone=findViewById(R.id.editPhone);
    }

    public void addUser() {
        userDao=new UserDao(MainActivity.this);
        try {
            if (checkFrom()>0) {
                User user = new User(edEmail.getText().toString(), edFistName.getText().toString(), edLastName.getText().toString(), edPassword.getText().toString(), edPhone.getText().toString());
                if (userDao.inserUser(user) > 0) {
                    Toast.makeText(getApplicationContext(), R.string.main, Toast.LENGTH_SHORT).show();
                    Intent intent =new Intent(MainActivity.this, LoginActivity.class);
                    startActivity(intent);
                } else {
                    Toast.makeText(getApplicationContext(), R.string.main2, Toast.LENGTH_SHORT).show();
                }
            }
        }catch (Exception ex){
            Log.e("Error", ex.toString());

        }



    }


    public int checkFrom() {
        int check=1;
        String fname=edFistName.getText().toString();
        String lname=edLastName.getText().toString();
        String pass=edPassword.getText().toString();
        String email=edEmail.getText().toString();
        String phone=edPhone.getText().toString();

        String regexStr = "^[0-9]$";
        if(fname.isEmpty()||lname.isEmpty()||pass.isEmpty()||email.isEmpty()||phone.isEmpty()){
            Toast.makeText(getApplicationContext(), "Please enter full information", Toast.LENGTH_SHORT).show();
            check=-1;
        }
        else if (!Patterns.EMAIL_ADDRESS.matcher(edEmail.getText().toString()).matches()) {
            Toast.makeText(getApplicationContext(), "Please enter a Valid E-Mail Address!", Toast.LENGTH_SHORT).show();
            check=-1;

        }else if(phone.length()<10||phone.length()>12){
            Toast.makeText(getApplicationContext(),"Please enter "+"\n"+" valid phone number",Toast.LENGTH_SHORT).show();
            check=-1;

        }else if(pass.length()<6||pass.length()>20){
            Toast.makeText(getApplicationContext(), "erre pass", Toast.LENGTH_SHORT).show();
            check=-1;
        }

        return check;








    }
}
