package com.example.ianorellana.reigndesigntest;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by ianorellana on 18-12-17.
 */

public class main extends AppCompatActivity implements SwipeRefreshLayout.OnRefreshListener {
    ListView list;
    listadapter adapter;
    SwipeRefreshLayout swipeRefreshLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.listview);

        swipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipe_refresh_layout);


        swipeRefreshLayout.setOnRefreshListener(this);

        swipeRefreshLayout.post(new Runnable() {
                                    @Override
                                    public void run() {
                                        swipeRefreshLayout.setRefreshing(true);
                                        getData();

                                    }
                                }
        );


    }

    public void getData(){
        StringRequest stringRequest = new StringRequest(Request.Method.GET,
                "https://hn.algolia.com/api/v1/search_by_date?query=android",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        setList(response);
                        swipeRefreshLayout.setRefreshing(false);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getApplicationContext(),error.toString(),Toast.LENGTH_LONG).show();
                    }
                }) {
        };
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }

    private void setList(String response){
        list =findViewById(R.id.list);
        final ArrayList<Objeto> items = obtenerLista(response);
        adapter = new listadapter(getApplicationContext(),items);
        list.setAdapter(adapter);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if(items.get(position).getStory_url().equals("null") && items.get(position).getUrl().equals("null")){
                    Toast.makeText(getApplicationContext(),"Este artículo no posee url",Toast.LENGTH_LONG).show();
                }else if(items.get(position).getStory_url().equals("null")){
                    Intent intent = new Intent(getApplicationContext(), webView.class);
                    intent.putExtra("url", items.get(position).getUrl());
                    startActivity(intent);
                }else {
                    Intent intent = new Intent(getApplicationContext(), webView.class);
                    intent.putExtra("url", items.get(position).getStory_url());
                    startActivity(intent);
                }
            }
        });
    }

    public ArrayList<Objeto> obtenerLista(String response){
        ArrayList<Objeto> objetos = new ArrayList<>();
        try{
            JSONObject object = new JSONObject(response);
            JSONArray jsonArray = object.getJSONArray("hits");
            for(int i=0;i<jsonArray.length();i++){
                JSONObject item = jsonArray.getJSONObject(i);
                String story_title = item.getString("story_title");
                String title = item.getString("title");
                String author = item.getString("author");
                String created_at = item.getString("created_at");
                String story_url = item.getString("story_url");
                String url = item.getString("url");
                Objeto objeto = new Objeto(story_title,author,created_at,story_url,title,url);
                objetos.add(objeto);
            }
        }catch (JSONException e) {
            System.out.println(e);
        }
        return objetos;
    }

    @Override
    public void onRefresh() {
        getData();
    }

}