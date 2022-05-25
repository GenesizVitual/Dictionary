package com.example.dictionary.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.dictionary.R;
import com.example.dictionary.model.DictionaryProvider;

import java.util.ArrayList;

public class RecycleViewKKBAIdapter extends BaseAdapter {


    public Context context;
    public ArrayList<DictionaryProvider> dlist;

    public class Holder {
        TextView banggai;
        TextView indonesia;
        TextView translate;
    }

    public RecycleViewKKBAIdapter(Context context, ArrayList<DictionaryProvider> dlis) {
        this.dlist = dlis;
        this.context = context;
    }

    @Override
    public int getCount() {
        return dlist.size();
    }

    @Override
    public String getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        Holder holder;
        LayoutInflater inflater;
        if (view == null) {
            inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.view_kosa_kata_ib, null);

            holder = new Holder();
            holder.banggai = view.findViewById(R.id.kt_banggais);
            holder.indonesia = view.findViewById(R.id.kt_indos);
            holder.translate = view.findViewById(R.id.translates);

            view.setTag(holder);
        } else {
            holder = (Holder) view.getTag();
        }

        holder.banggai.setText(dlist.get(i).getKosa_kata_bagai());
        holder.indonesia.setText(dlist.get(i).getKosa_kata_indonesia());
//        holder.translate.setText(dlist.get(i).getTERJEMAHAN());

        return view;
    }
}
