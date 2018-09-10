package com.example.abdelrahman.virtualbank;

import android.content.Intent;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class Info extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);

        FormInitilization();
    }

    public void FormInitilization(){

        EditText TxtName = (EditText) findViewById(R.id.TxtName);
        EditText TxtUN = (EditText) findViewById(R.id.TxtUN);
        EditText TxtPhone = (EditText) findViewById(R.id.TxtPhone);
        EditText TxtMail = (EditText) findViewById(R.id.TxtMail);

        DataBase dbase = new DataBase(this);
        AccInfo CustData = dbase.GetInfo(Cust_Info.getUN());

        TxtName.setText(CustData.getName());
        TxtUN.setText(Cust_Info.getUN());
        TxtPhone.setText(CustData.getPhone());
        TxtMail.setText(CustData.getEMail());
    }

    public boolean CheckPwd(){

        EditText TxtPwd = (EditText) findViewById(R.id.TxtPwd);
        EditText TxtRePwd = (EditText) findViewById(R.id.TxtRePwd);

        if(TxtPwd.getText().toString().equals(TxtRePwd.getText().toString())) return true;
        else return false;

    }

    public void UpdateInfo(View view) {

        if(CheckPwd()){
            EditText TxtName = (EditText) findViewById(R.id.TxtName);
            EditText TxtUN = (EditText) findViewById(R.id.TxtUN);
            EditText TxtPhone = (EditText) findViewById(R.id.TxtPhone);
            EditText TxtMail = (EditText) findViewById(R.id.TxtMail);
            EditText TxtPwd = (EditText) findViewById(R.id.TxtPwd);

            DataBase DBase = new DataBase(this);
            AccInfo UpCust = new AccInfo(TxtName.getText().toString(),TxtPhone.getText().toString(),TxtMail.getText().toString(),TxtPwd.getText().toString());
            DBase.UpdateAccount(UpCust,Cust_Info.getUN());
            Toast.makeText(this,"Info Updated Successfully!",Toast.LENGTH_LONG).show();
            Intent LoginIntt = new Intent(this,Login.class);
            startActivity(LoginIntt);
        }else {
            Toast.makeText(this,"Password isn't correct",Toast.LENGTH_LONG).show();
        }

    }

    public void DeleteAccount(View view) {

        DataBase DBase = new DataBase(this);
        DBase.DeleteAccount(Cust_Info.getUN());
        Toast.makeText(this,"Account Deleted!",Toast.LENGTH_LONG).show();
        Cust_Info.setUN("");
        Intent LoginIntt = new Intent(this,Login.class);
        startActivity(LoginIntt);
    }
}
