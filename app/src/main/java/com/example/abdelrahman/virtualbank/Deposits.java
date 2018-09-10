package com.example.abdelrahman.virtualbank;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class Deposits extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_deposits);


        //==============================================

        FormInit();

    }


    public void FormInit(){
        ListView LvDeps = (ListView) findViewById(R.id.LvDeps);
        TextView VwTxtShowBalance = (TextView) findViewById(R.id.VwTxtShowBalance);

        TextView VwTxtShowUserName = (TextView) findViewById(R.id.VwTxtShowUserName);
        VwTxtShowUserName.setText(Cust_Info.getUN());

        DataBase Dbase = new DataBase(this);

        Double blnc = Dbase.GetBalance(Cust_Info.getUN());
        VwTxtShowBalance.setText(blnc.toString());

        if(Dbase.GetDeposits(Cust_Info.getUN()) != null){
            ArrayList<String> dep = Dbase.GetDeposits(Cust_Info.getUN());
            if(dep.size() != 0)
                LvDeps.setAdapter(new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,dep));
        }
    }

    public void SaveDeposit(View view) {

        TextView VwTxtShowBalance = (TextView) findViewById(R.id.VwTxtShowBalance);
        EditText TxtAmount = (EditText) findViewById(R.id.TxtAmount);

        if(TxtAmount.getText().equals("")) return;

        Double NewBalance = Double.parseDouble(VwTxtShowBalance.getText().toString()) + Double.parseDouble(TxtAmount.getText().toString());

        DataBase dbase = new DataBase(this);
        dbase.UpdateCustomerBalance(NewBalance,Cust_Info.getUN());
        dbase.AddNewDeposit(Double.parseDouble(TxtAmount.getText().toString()),Cust_Info.getUN());
        Toast.makeText(this,"Operation Saved Successfully!",Toast.LENGTH_SHORT).show();
        Intent MIntt = new Intent(this,Main.class);
        startActivity(MIntt);
        FormInit();
    }

}
