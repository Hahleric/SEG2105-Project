package com.example.login2;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class DatabaseHelper extends SQLiteOpenHelper {
    public DatabaseHelper(@Nullable Context context) {
        super(context, "Login.db", null, 2);

    }
    public DatabaseHelper(@Nullable Context context, int version){
        super(context,"Login.db",null,version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table user(email text primary key,password text,identity text,name text)");


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists user");

    }

    public boolean insert(String email, String password, String identity, String name) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("email", email);
        contentValues.put("password", password);
        contentValues.put("identity", identity);
        contentValues.put("name", name);

        long ins = db.insert("user", null, contentValues);
        return ins != -1;

    }
    public boolean insert(String address, ArrayList<String> profileContent, ArrayList<String> serviceContent, String identity){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("email", String.valueOf(profileContent));
        contentValues.put("password", String.valueOf(serviceContent));
        contentValues.put("identity",identity);
        contentValues.put("name",address);
        long ins = db.insert("user",null,contentValues);
        return ins != -1;
    }

    public void delete(String email) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete("user",  "email" + "=?", new String[]{email});
    }

    public boolean chkemail(String email) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("Select * from user where email = ?", new String[]{email});
        if (cursor.getCount() > 0)
            return false;
        else
            return true;
    }

    public boolean chkemailpassword(String email, String password) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("Select * from user where email = ? and password = ?", new String[]{email, password});
        return cursor.getCount() > 0;
    }

    public boolean chkemailidentity(String email, String identity) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("Select * from user where email = ? and identity = ?", new String[]{email, identity});
        return cursor.getCount() > 0;
    }

    public String getName(){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query("user",null,null,null,null,null,null);
        cursor.moveToFirst();
        for(int i = 0;i<cursor.getCount()-1;i++){cursor.moveToNext();}
        String name = cursor.getString(3);
        cursor.close();
        return name;
    }
    public String getIdentity(){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query("user",null,null,null,null,null,null);
        cursor.moveToFirst();
        for(int i = 0;i<cursor.getCount()-1;i++){cursor.moveToNext();}
        String identity = cursor.getString(2);
        cursor.close();
        return identity;

    }

    public String getUser(String username){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "Select * FROM "+ "user" +" WHERE " +"email"+" = \"" +username+"\"";
        Cursor cursor = db.rawQuery(query, null);
        String name;
        if(cursor.moveToFirst()){
        name = cursor.getString(3);
        }else{name = null;}
        db.close();
        return name;
    }

    public String getLogIdentity(String username){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = " Select * FROM "+ " user" +" WHERE " +"email"+" = \"" +username+"\"";
        Cursor cursor = db.rawQuery(query, null);
        String identity;
        if(cursor.moveToFirst()){
            identity = cursor.getString(2);
        }else{identity = null;}
        db.close();
        return identity;
    }

}
