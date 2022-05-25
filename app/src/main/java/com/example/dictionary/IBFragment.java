package com.example.dictionary;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.dictionary.DB.DBHelper;
import com.example.dictionary.adapter.RecycleViewKKBAIdapter;
import com.example.dictionary.adapter.RecycleViewKKBAdapter;
import com.example.dictionary.model.DictionaryProvider;

import org.w3c.dom.Text;

import java.util.ArrayList;


public class IBFragment extends Fragment {

    SQLiteDatabase sqLiteDatabase;
    DBHelper dbHelper;
    RecycleViewKKBAIdapter recycleViewKKBAIdapter;
    Cursor cursor;

    ArrayList<DictionaryProvider> data = new ArrayList<>();
    DictionaryProvider dictionaryProvider;
    ListView listView;

    EditText search;
    TextView textView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        ViewGroup root = (ViewGroup) inflater.inflate(R.layout.fragment_i_b, null);
        listView = (ListView) root.findViewById(R.id.receyler2);
        dbHelper = new DBHelper(getContext());

        search = (EditText) root.findViewById(R.id.search_text_ib);
        textView = (TextView) root.findViewById(R.id.textView1);

        search.addTextChangedListener(new TextWatcher() {
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
//        cursor = sqLiteDatabase.rawQuery("select * from tbl_kosa_kata  where Kosa_kata_bagai like `%"+text+"%` order by Kosa_kata_indonesia asc", null);
        cursor = sqLiteDatabase.rawQuery("select * from tbl_kosa_kata where Kosa_kata_indonesia like \"%" +text+ "%\" order by Kosa_kata_indonesia asc", null);

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

        recycleViewKKBAIdapter = new RecycleViewKKBAIdapter(getContext(), data);
        listView.setAdapter(recycleViewKKBAIdapter);
        cursor.close();
    }

    private void TampilkanData(){
        sqLiteDatabase = dbHelper.getWritableDatabase();
        cursor = sqLiteDatabase.rawQuery("select * from tbl_kosa_kata order by Kosa_kata_indonesia asc", null);

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

        recycleViewKKBAIdapter = new RecycleViewKKBAIdapter(getContext(), data);
        listView.setAdapter(recycleViewKKBAIdapter);
        cursor.close();
    }
}