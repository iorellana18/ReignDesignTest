package com.example.ianorellana.reigndesigntest;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * Created by ianorellana on 18-12-17.
 */

public class listadapter extends BaseAdapter {

    Context context;
    LayoutInflater inflater;
    ArrayList<Objeto> objetos;
    TextView title;
    TextView date;

    public listadapter(Context context, ArrayList<Objeto> objetos) {
        this.context = context;
        this.objetos = objetos;
    }


    @Override
    public int getCount() {
        return objetos.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    public View getView(final int position, View convertView, ViewGroup parent) {
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        final View itemView = inflater.inflate(R.layout.list_style, parent, false);

        title = itemView.findViewById(R.id.title);
        date = itemView.findViewById(R.id.date);

        title.setText(objetos.get(position).getStory_title());
        date.setText(objetos.get(position).getAuthor()+" - "+objetos.get(position).getCreated_at());


        return itemView;
    }
}
