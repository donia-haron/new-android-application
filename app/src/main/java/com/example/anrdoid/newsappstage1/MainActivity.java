package com.example.anrdoid.newsappstage1;

import android.app.LoaderManager;
import android.app.LoaderManager.LoaderCallbacks;
import android.content.Context;
import android.content.Intent;
import android.content.Loader;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements LoaderCallbacks<List<News>> {
    NewsAdapter Adapter;
     TextView mNo;
    ProgressBar loadingbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ListView news_List = findViewById(R.id.list);
        mNo = findViewById(R.id.nooo);
        Adapter = new NewsAdapter(this, new ArrayList<News>());
        loadingbar = findViewById(R.id.bar);
        news_List.setEmptyView(mNo);
        news_List.setAdapter(Adapter);

        ConnectivityManager connectMgr = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = connectMgr.getActiveNetworkInfo();



        if (netInfo != null && netInfo.isConnected()) {
            LoaderManager lmg = getLoaderManager();
            lmg.initLoader(0, null, this);
        } else {
            loadingbar.setVisibility(View.GONE);
            mNo.setText(R.string.interneterror);
        }

        news_List.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                News currentNews = Adapter.getItem(position);
                Uri Uri1 = Uri.parse(currentNews.getUrl());
                Intent webIntent = new Intent(Intent.ACTION_VIEW, Uri1);
                startActivity(webIntent);
            }
        });



    }

    @Override
    public Loader<List<News>> onCreateLoader(int id, Bundle args) {
        return new Newsload(this);
    }

    @Override
    public void onLoadFinished(Loader<List<News>> loading, List<News> neww) {

        loadingbar.setVisibility(View.GONE);
        mNo.setText(R.string.contneterror);
        Adapter.clear();

        if (neww != null && !neww.isEmpty()) {
            Adapter.addAll(neww);
        }
    }

    @Override
    public void onLoaderReset(Loader<List<News>> loader) {
        Adapter.clear();
    }
}
