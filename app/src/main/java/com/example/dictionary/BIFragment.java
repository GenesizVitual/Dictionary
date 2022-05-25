package com.example.dictionary;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.example.dictionary.DB.DBHelper;
import com.example.dictionary.adapter.RecycleViewKKBAdapter;
import com.example.dictionary.model.DictionaryProvider;

import org.w3c.dom.Text;

import java.util.ArrayList;


public class BIFragment extends Fragment {

    SQLiteDatabase sqLiteDatabase;
    DBHelper dbHelper;
    RecycleViewKKBAdapter recycleViewKKBAdapter;

    Cursor cursor;

    ArrayList<DictionaryProvider> data = new ArrayList<>();
    DictionaryProvider dictionaryProvider;
    ListView listView;
    EditText search_text_bi;
    TextView kontent_text1;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        ViewGroup root = (ViewGroup) inflater.inflate(R.layout.fragment_b_i, null);
        listView = (ListView) root.findViewById(R.id.receyler);
        search_text_bi = (EditText) root.findViewById(R.id.search_text_bi);
        kontent_text1 = (TextView) root.findViewById(R.id.kontent_text1);
        dbHelper = new DBHelper(getContext());

        search_text_bi.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
               if(!charSequence.equals("")){
                   CariKata(charSequence.toString());
               }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        TampilkanData();
        return root;
    }

    private void CariKata(String text){
        sqLiteDatabase = dbHelper.getWritableDatabase();
        cursor = sqLiteDatabase.rawQuery("select * from tbl_kosa_kata where Kosa_kata_bagai like \"%" +text+ "%\" order by Kosa_kata_bagai asc", null);

        data.clear();
        if(cursor.moveToFirst()){
            do {
                dictionaryProvider = new DictionaryProvider(
                        cursor.getString(cursor.getColumnIndex(dbHelper.FIELD_KOSA_KATA_BAGAI)),
                        cursor.getString(cursor.getColumnIndex(dbHelper.FIELD_KOSA_KATA_INDONESIA)),
                        cursor.getString(cursor.getColumnIndex(dbHelper.FIELD_TERJEMAHAN))
                );

                data.add(dictionaryProvider);
            }while (cursor.moveToNext());
        }

        recycleViewKKBAdapter = new RecycleViewKKBAdapter(getContext(), data);
        listView.setAdapter(recycleViewKKBAdapter);
        cursor.close();
    }

    private void TampilkanData(){
        sqLiteDatabase = dbHelper.getWritableDatabase();
        cursor = sqLiteDatabase.rawQuery("select * from tbl_kosa_kata order by Kosa_kata_bagai asc", null);

        data.clear();
        if(cursor.moveToFirst()){
            do {
                dictionaryProvider = new DictionaryProvider(
                        cursor.getString(cursor.getColumnIndex(dbHelper.FIELD_KOSA_KATA_BAGAI)),
                        cursor.getString(cursor.getColumnIndex(dbHelper.FIELD_KOSA_KATA_INDONESIA)),
                        cursor.getString(cursor.getColumnIndex(dbHelper.FIELD_TERJEMAHAN))
                );

                data.add(dictionaryProvider);
            }while (cursor.moveToNext());
        }

        recycleViewKKBAdapter = new RecycleViewKKBAdapter(getContext(), data);
        listView.setAdapter(recycleViewKKBAdapter);
        cursor.close();
    }
}