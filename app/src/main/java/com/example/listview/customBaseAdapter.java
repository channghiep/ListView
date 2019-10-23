package com.example.listview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class customBaseAdapter extends BaseAdapter {
    private Context context; //context
    private ArrayList<redditItem> redditItems; //data source of the list adapter

    //public constructor
    public customBaseAdapter(Context context, ArrayList<redditItem> redditItems) {
        this.context = context;
        this.redditItems = redditItems;
    }
    @Override
    public int getCount() {
        return redditItems.size();//returns total of items in the list
    }

    @Override
    public Object getItem(int position) {
        return redditItems.get(position); //returns list item at the specified position
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // inflate the layout for each list row
        if (convertView == null) {
            convertView = LayoutInflater.from(context).
                    inflate(R.layout.layout_reddit_list, parent, false);
        }

        // get current item to be displayed
        redditItem currentItem = (redditItem) getItem(position);

        // get the TextView for item name and item description
        TextView textViewItemName = (TextView)
                convertView.findViewById(R.id.item_name);

        //sets the text for item name and item description from the current item object
        textViewItemName.setText(currentItem.getTitle());


        // returns the view for the current row
        return convertView;
    }
}
