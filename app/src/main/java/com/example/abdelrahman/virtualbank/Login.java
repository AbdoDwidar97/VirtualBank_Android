package com.example.abdelrahman.virtualbank;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class Login extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }

    public void GoToCreateForm(View view) {
        Intent intt = new Intent(this,New_Account.class);
        startActivity(intt);
    }

    public void GoLogin(View view) {

        EditText TxtUserID = (EditText) findViewById(R.id.TxtUserID);
        EditText TxtUserPwd = (EditText) findViewById(R.id.TxtUserPwd);
        DataBase Dbase = new DataBase(this);

        boolean chckr = Dbase.Login(TxtUserID.getText().toString(),TxtUserPwd.getText().toString());
        if(chckr){
            Cust_Info.setUN(TxtUserID.getText().toString());
            TxtUserID.setText("");
            TxtUserPwd.setText("");
            Intent intt = new Intent(this,Main.class);
            startActivity(intt);
        }else Toast.makeText(this,"Invalid user id or password",Toast.LENGTH_LONG).show();
    }
}
