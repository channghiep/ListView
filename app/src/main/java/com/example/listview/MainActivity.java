package com.example.listview;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    ArrayList<redditItem> redditList;
//    List<redditItem> redditTitle = new ArrayList<redditItem>();

    String url ="https://www.reddit.com/.json";
    private customBaseAdapter customBaseAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);




        fetchData();






    }
    private void fetchData(){
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.e("test", response.toString());
                        try {
                            JSONObject obj = new JSONObject(response);
                            Log.e("Test", obj.getJSONObject("data").getJSONArray("children").getJSONObject(1).getJSONObject("data").getString("title"));

                            JSONArray redditResArray = obj.getJSONObject("data").getJSONArray("children");
                            redditList = new ArrayList<redditItem>();

                            for (int i = 0; i < redditResArray.length(); i++) {

                                redditItem tempItem = new redditItem();

                                JSONObject resOb = redditResArray.getJSONObject(i).getJSONObject("data");
                                tempItem.title = resOb.getString("title");
                                Log.e("test1.1", tempItem.title);
                                redditList.add(tempItem);
                            }

                            setupAdapter();
                            Log.e("test2", redditList.toString());


                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                        Log.e("test 4", redditList.toString());


                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }

        });

//        ArrayAdapter<redditItem> adapter = new ArrayAdapter<redditItem>(
//                this, android.R.layout.simple_list_item_1, redditList
//        );

//
//        listView.setAdapter(adapter);
        RequestQueue queue = Volley.newRequestQueue(this);
        queue.add(stringRequest);
    }
    private void setupAdapter(){
//        ArrayAdapter<redditItem> adapter = new ArrayAdapter<redditItem>(
//                this, android.R.layout.simple_list_item_1, redditList);
//        listView.setAdapter(adapter);
        customBaseAdapter adapter = new customBaseAdapter(this, redditList);
        ListView listView = findViewById(android.R.id.list);
        listView.setAdapter(adapter);
    }
}
