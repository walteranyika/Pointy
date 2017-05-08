package com.walter.pointend;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class CustomAdapter extends BaseAdapter {
    Context mContext;
    ArrayList<Item> data;//modify here
    public CustomAdapter(Context context, ArrayList<Item> data) //modify here
    {
        this.mContext = context;
        this.data = data;
    }
    @Override
    public int getCount() {
        return data.size();// # of items in your arraylist
    }
    @Override
    public Object getItem(int position) {
        return data.get(position);// get the actual movie
    }
    @Override
    public long getItemId(int id) {
        return id;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null) {
            LayoutInflater inflater = ((Activity) mContext).getLayoutInflater();
            convertView = inflater.inflate(R.layout.list_item_layout, parent,false);//modify here
            viewHolder = new ViewHolder();
            viewHolder.textViewNames = (TextView) convertView.findViewById(R.id.tvListNames);//modify here
            viewHolder.textViewStreet = (TextView) convertView.findViewById(R.id.tvListStreet);//modify here
            viewHolder.textViewDate = (TextView) convertView.findViewById(R.id.tvListDate);//modify here
            viewHolder.textViewTime = (TextView) convertView.findViewById(R.id.tvListTime);//modify here

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        Item item = data.get(position);//modify here
        viewHolder.textViewStreet.setText(item.address);//modify here
        viewHolder.textViewNames.setText(item.names);//modify here
        viewHolder.textViewTime.setText(item.time);//modify here
        viewHolder.textViewDate.setText(item.date);//modify here
        return convertView;

    }
    static class ViewHolder {
        TextView textViewNames;//modify here
        TextView textViewStreet;//modify here
        TextView textViewDate;//modify here
        TextView textViewTime;//modify here

    }

}