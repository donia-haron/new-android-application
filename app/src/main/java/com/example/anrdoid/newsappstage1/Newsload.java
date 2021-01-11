package com.example.anrdoid.newsappstage1;

import android.content.AsyncTaskLoader;
import android.content.Context;

import java.util.List;

public class Newsload extends AsyncTaskLoader<List<News>> {
    private static String URL =
            "http://content.guardianapis.com/search?show-tags=contributor&api-key=test";

    public Newsload(Context context) {
        super(context);
    }

    @Override
    protected void onStartLoading() {
        forceLoad();
    }

    @Override
    public List<News> loadInBackground() {
        if (URL == null) {
            return null;
        }

        List<News> newsList = QueryUtils.fetchNews(URL);
        return newsList;
    }
}
