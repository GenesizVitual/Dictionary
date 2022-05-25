package com.example.dictionary;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;

import com.example.dictionary.DB.DBHelper;

import java.io.InputStream;

public class MainActivity extends AppCompatActivity {

    private int waktu_loading=5000;
    DBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if(getSupportActionBar() !=null){
            getSupportActionBar().hide();
        }
        dbHelper = new DBHelper(this);
        dbHelper.deleteDictionary();
        SQLiteDatabase sqLiteDatabase = dbHelper.getWritableDatabase();
        InputStream file = getResources().openRawResource(R.raw.kosa_kata);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                //setelah loading maka akan langsung berpindah ke home activity
                Intent home = new Intent(MainActivity.this, Home.class);
                dbHelper.ImportData(file); // Import Data
                startActivity(home);
                finish();
            }
        }, waktu_loading);
    }
}