package com.example.anrdoid.newsappstage1;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class NewsAdapter extends ArrayAdapter<News> {
    public NewsAdapter(Context context, ArrayList<News> news) {
        super(context, 0, news);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View listItem = convertView;
        if (listItem == null) {
            listItem = LayoutInflater.from(getContext()).inflate(R.layout.list_item, parent, false);
        }

        News cnd = getItem(position);
        TextView Titledata= (TextView) listItem.findViewById(R.id.title);
        String titlei = cnd.getTitle();
        Titledata.setText(titlei);

        TextView secdata = (TextView) listItem.findViewById(R.id.cat);
        String seci = cnd.getSection();
        secdata.setText(seci);

        TextView datedata = (TextView) listItem.findViewById(R.id.date);
        String datei = cnd.getDate();
        datedata.setText(datei);

        TextView autherdata = (TextView) listItem.findViewById(R.id.pillar);
        String authori = cnd.getpillar();
        autherdata.setText(authori);

        TextView typedata = (TextView) listItem.findViewById(R.id.type);
        String typei = cnd.getType();
        typedata.setText(typei);
        return listItem;
    }
}
