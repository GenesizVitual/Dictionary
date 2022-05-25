package com.example.dictionary.DB;

import android.content.ContentValues;
import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

import com.example.dictionary.utils.Message;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.InputStream;
import java.io.InputStreamReader;

public class DBHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "dictionary.db";
    private static final String TABLE_NAME = "tbl_kosa_kata";
    public final String UID = "_id";
    public final String FIELD_KOSA_KATA_BAGAI = "Kosa_kata_bagai";
    public final String FIELD_KOSA_KATA_INDONESIA = "Kosa_kata_indonesia";
    public final String FIELD_TERJEMAHAN = "TERJEMAHAN";
    private static final int DATABASE_VERSION = 1;
    private Context context;

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
        Message.message(context, "Database Sedang dibuat");
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        try {
            sqLiteDatabase.execSQL("create table " + TABLE_NAME + " (" + UID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + FIELD_KOSA_KATA_BAGAI + " " +
                    "varchar(255) default null," + FIELD_KOSA_KATA_INDONESIA + " varchar(255) default null, " + FIELD_TERJEMAHAN + " TEXT default null)");
            Message.message(context, "Data kamus sedang diprosessss");
        } catch (SQLException e) {
            e.printStackTrace();
            Message.message(context, "" + e);
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        try {
            Message.message(context, "Data kamus sedang diload");
            sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
            onCreate(sqLiteDatabase);
        } catch (SQLException e) {
            Message.message(context, "" + e);
        }
    }

    public void deleteDictionary(){
       try{
           SQLiteDatabase db = this.getWritableDatabase();
           db.execSQL("DELETE FROM " + TABLE_NAME);
           db.close();
       }catch (SQLException e){
           e.printStackTrace();
       }
    }

    public void ImportData(InputStream file) {
        //Import data kosa_kata_csv ke sqlite
        try {
//            FileReader fis = new FileReader(directory);

            BufferedReader buffer = new BufferedReader(new InputStreamReader(file));
            String line = "";
            SQLiteDatabase db = this.getWritableDatabase();
            ContentValues values = new ContentValues();
            db.beginTransaction();
            while ((line = buffer.readLine()) != null) {
                String[] str = line.split(";");
                values.put(FIELD_KOSA_KATA_BAGAI, str[0]);
                values.put(FIELD_KOSA_KATA_INDONESIA, str[1]);
                values.put(FIELD_TERJEMAHAN, str[2]);
                db.insert(TABLE_NAME, null, values);
            }
            db.setTransactionSuccessful();
            db.endTransaction();
            Message.message(context, "Data selesai diload");
            Log.d("Progress", "Sedang diload");
        } catch (Exception e) {
            Message.message(context, "" + e);
            e.printStackTrace();
        }
    }

}
