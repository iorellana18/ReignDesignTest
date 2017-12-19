package com.example.ianorellana.reigndesigntest;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.daimajia.swipe.SwipeLayout;

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
    TextView delete;
    String titulo;

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

    public View getView(final int position, final View convertView, ViewGroup parent) {
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        final View itemView = inflater.inflate(R.layout.swipetest, parent, false);

        SwipeLayout swipeLayout =  (SwipeLayout)itemView.findViewById(R.id.swipet);
        delete = itemView.findViewById(R.id.textDelete);

        swipeLayout.setShowMode(SwipeLayout.ShowMode.LayDown);
        swipeLayout.addDrag(SwipeLayout.DragEdge.Left, itemView.findViewById(R.id.bottom_wrapper));

        swipeLayout.addSwipeListener(new SwipeLayout.SwipeListener() {
            @Override
            public void onClose(SwipeLayout layout) {
            }

            @Override
            public void onUpdate(SwipeLayout layout, int leftOffset, int topOffset) {
            }

            @Override
            public void onStartOpen(SwipeLayout layout) {

            }

            @Override
            public void onOpen(SwipeLayout layout) {
            }

            @Override
            public void onStartClose(SwipeLayout layout) {

            }

            @Override
            public void onHandRelease(SwipeLayout layout, float xvel, float yvel) {
            }
        });

        title = itemView.findViewById(R.id.title);
        date = itemView.findViewById(R.id.date);


        if(objetos.get(position).getStory_title().equals("null")){
            titulo = objetos.get(position).getTitle();
        }else{
            titulo = objetos.get(position).getStory_title();
        }
        title.setText(titulo);

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context,"Delete "+titulo,Toast.LENGTH_LONG).show();
            }
        });

        date.setText(objetos.get(position).getAuthor()+" - "+objetos.get(position).getCreated_at());


        return itemView;
    }
}
