package com.example.abdelrahman.virtualbank;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class Main extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void GoSignOut(View view) {
        Intent LogIntt = new Intent(this,Login.class);
        Cust_Info.setUN("");
        startActivity(LogIntt);
    }

    public void GoDeposits(View view) {
        Intent LogIntt = new Intent(this,Deposits.class);
        startActivity(LogIntt);
    }

    public void GoWithdrawals(View view) {
        Intent LogIntt = new Intent(this,Withdrawals.class);
        startActivity(LogIntt);
    }

    public void GoInfo(View view) {
        Intent LogIntt = new Intent(this,Info.class);
        startActivity(LogIntt);
    }
}
