package com.hnincherry.fingerprintapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class CustomListviewAdapter extends BaseAdapter {

    private Context context;
    private ArrayList<String> note = new ArrayList<>();
    private ArrayList<String> date = new ArrayList<>();


    public CustomListviewAdapter(Context context, ArrayList<String> note,ArrayList<String> date) {
        this.context = context;
        this.note = note;
        this.date = date;

    }



    @Override
    public int getCount() {
        return note.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }


    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        convertView =  layoutInflater.inflate(R.layout.list_item,null);
        TextView list_note,list_date;
        list_note = convertView.findViewById(R.id.list_note);
        list_date = convertView.findViewById(R.id.list_date);

        list_note.setText(note.get(position));
        list_date.setText(date.get(position));

        return convertView;
    }
}
