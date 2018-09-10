package com.example.abdelrahman.virtualbank;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class New_Account extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new__account);
    }

    public boolean CheckPwd(){

        EditText TxtPwd = (EditText) findViewById(R.id.TxtPwd);
        EditText TxtRePwd = (EditText) findViewById(R.id.TxtRePwd);

        if(TxtPwd.getText().toString().equals(TxtRePwd.getText().toString())) return true;
        else return false;

    }

    public void AddNewAccount(View view) {

        if(CheckPwd()){
            EditText TxtName = (EditText) findViewById(R.id.TxtName);
            EditText TxtUN = (EditText) findViewById(R.id.TxtUN);
            EditText TxtPhone = (EditText) findViewById(R.id.TxtPhone);
            EditText TxtMail = (EditText) findViewById(R.id.TxtMail);
            EditText TxtPwd = (EditText) findViewById(R.id.TxtPwd);

            DataBase DBase = new DataBase(this);
            DBase.AddNewAccount(TxtUN.getText().toString(),TxtName.getText().toString(),TxtPhone.getText().toString(),TxtMail.getText().toString(),TxtPwd.getText().toString());
            Toast.makeText(this,"Account Created Successfully!",Toast.LENGTH_LONG).show();
            Intent LoginIntt = new Intent(this,Login.class);
            startActivity(LoginIntt);
        }else {
            Toast.makeText(this,"Password isn't correct",Toast.LENGTH_LONG).show();
        }
    }
}
