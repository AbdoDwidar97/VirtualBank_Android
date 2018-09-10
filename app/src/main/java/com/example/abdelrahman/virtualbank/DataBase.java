package com.example.abdelrahman.virtualbank;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

/**
 * Created by abdelrahman on 25/01/18.
 */

public class DataBase extends SQLiteOpenHelper {

    static final String DB = "EVBank.db";
    static final int Vers = 1;

    public DataBase(Context context) {
        super(context, DB, null, Vers);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table IF NOT EXISTS Customer (ID INTEGER PRIMARY KEY,UN TEXT, Name TEXT , Phone TEXT , Email TEXT , Pwd TEXT , Balance NUMERIC default 0)");
        db.execSQL("create table IF NOT EXISTS Deposits (Dep_ID INTEGER PRIMARY KEY, Cust_UN TEXT , Amount NUMERIC,foreign key(Cust_UN) references Customer(UN))");
        db.execSQL("create table IF NOT EXISTS WithDrwals (With_ID INTEGER PRIMARY KEY,Cust_UN TEXT , Amount NUMERIC,foreign key(Cust_UN) references Customer(UN))");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("Drop table IF EXISTS Customer");
        db.execSQL("Drop table IF EXISTS Deposits");
        db.execSQL("Drop table IF EXISTS WithDrwals");
        onCreate(db);
    }

    public void AddNewAccount(String un,String name, String phone, String Email, String Pwd){

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("Name",name);
        cv.put("UN",un);
        cv.put("Phone",phone);
        cv.put("Email",Email);
        cv.put("Pwd",Pwd);
        db.insert("Customer",null,cv);
    }

    public boolean Login(String UN, String Pwd){
        SQLiteDatabase db = this.getReadableDatabase();
        String Query = "select UN,Pwd from Customer where UN = '" + UN + "' and Pwd = '" + Pwd + "'";
        Cursor cur = db.rawQuery(Query,null);
        cur.moveToFirst();
        if(cur.getCount() != 0) return true;
        else return false;
    }

    public ArrayList<String> GetDeposits(String UN){

        ArrayList<String> deps = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        String Query = "select * from Deposits where Cust_UN = '" + UN + "'";
        Cursor cur = db.rawQuery(Query,null);
        cur.moveToFirst();
        if(cur.getCount() == 0) return null;
        while (!cur.isAfterLast()) {
            deps.add("Operation " + (deps.size()+1) + " : " + cur.getString(cur.getColumnIndex("Amount")));
            cur.moveToNext();
        }
        return deps;
    }

    public ArrayList<String> GetWithdrawals(String UN){

        ArrayList<String> wths = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        String Query = "select * from WithDrwals where Cust_UN = '" + UN + "'";
        Cursor cur = db.rawQuery(Query,null);
        cur.moveToFirst();
        if(cur.getCount() == 0) return null;
        while (!cur.isAfterLast()) {
            wths.add("Operation " + (wths.size()+1) + " : " + cur.getString(cur.getColumnIndex("Amount")));
            cur.moveToNext();
        }
        return wths;
    }

    public Double GetBalance(String UN){
        SQLiteDatabase db = this.getReadableDatabase();
        String Query = "select Balance from Customer where UN = '" + UN + "'";
        Cursor cur = db.rawQuery(Query,null);
        cur.moveToFirst();
        Double Blnc = cur.getDouble(cur.getColumnIndex("Balance"));
        return Blnc;
    }

    public void UpdateCustomerBalance(Double Blnc , String UN){
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("Update Customer set Balance = " + Blnc + " where UN = '" + UN + "'");
    }

    public void AddNewDeposit(Double Amount , String UN){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("Cust_UN",UN);
        cv.put("Amount",Amount);
        db.insert("Deposits",null,cv);
    }

    public void AddNewWithdrawal(Double Amount , String UN){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("Cust_UN",UN);
        cv.put("Amount",Amount);
        db.insert("WithDrwals",null,cv);
    }


    public AccInfo GetInfo(String UN){
        SQLiteDatabase db = this.getReadableDatabase();
        String Query = "select * from Customer where UN = '" + UN + "'";
        Cursor cur = db.rawQuery(Query,null);
        cur.moveToFirst();
        String name = cur.getString(cur.getColumnIndex("Name"));
        String phone = cur.getString(cur.getColumnIndex("Phone"));
        String Email = cur.getString(cur.getColumnIndex("Email"));
        String pwd = cur.getString(cur.getColumnIndex("Pwd"));
        AccInfo NewCust = new AccInfo(name,phone,Email,pwd);
        return NewCust;
    }

    public void UpdateAccount(AccInfo cust , String UN){
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("Update Customer set Name = '" + cust.getName() + "' , Phone = '" + cust.getPhone() + "', Email = '" + cust.getEMail() + "',Pwd = '" + cust.getPwd() + "' where UN = '" + UN + "'");
    }

    public void DeleteAccount(String UN){
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("delete from Customer where UN = '" + UN + "'");
    }
}
