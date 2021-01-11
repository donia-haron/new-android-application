package com.example.anrdoid.newsappstage1;

import android.text.TextUtils;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Labtop on 18/02/18.
 */

public class QueryUtils {
    private QueryUtils() {
    }

    public static List<News> fetchNews(String theUrl) {
        URL url = createtheUrl(theUrl);

        String jsonres = null;
        try {
            jsonres = makethehttpRequest(url);
        } catch (IOException e) {
            e.printStackTrace();
        }
        List<News> newsList = extractdatafromJson(jsonres);

        return newsList;
    }

    private static URL createtheUrl(String createUrl) {
        URL crturl = null;
        try {
            crturl = new URL(createUrl);
        } catch (MalformedURLException e) {

            e.printStackTrace();
        }
        return crturl;
    }


    private static String makethehttpRequest(URL requrl) throws IOException {
        String jsonRes = "";
        if (requrl == null) {
            return jsonRes;
        }
        HttpURLConnection urlCon = null;
        InputStream inputStr = null;
        try {
            urlCon = (HttpURLConnection) requrl.openConnection();
            urlCon.setReadTimeout(10000);
            urlCon.setConnectTimeout(15000);
            urlCon.setRequestMethod("GET");
            urlCon.connect();
            if (urlCon.getResponseCode() == 200) {
                inputStr = urlCon.getInputStream();
                jsonRes = readingFromStream(inputStr);
            } else {
                Log.d("Error>>>>>>>>>> ", String.valueOf(urlCon.getResponseCode()));
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (inputStr != null) {

                inputStr.close();
            }
            if (urlCon != null) {
                urlCon.disconnect();
            }

        }
        return jsonRes;
    }

    private static String readingFromStream(InputStream inputStr) throws IOException {
        StringBuilder outputstr = new StringBuilder();
        if (inputStr != null) {
            InputStreamReader inputStrRead = new InputStreamReader(inputStr, Charset.forName("UTF-8"));
            BufferedReader reader = new BufferedReader(inputStrRead);
            String ln = reader.readLine();
            while (ln != null) {
                outputstr.append(ln);
                ln = reader.readLine();
            }
        }
        return outputstr.toString();
    }

    private static List<News> extractdatafromJson(String newsdataJSON) {
        List<News> newsList = new ArrayList<>();
        try {
            JSONObject jsonob = new JSONObject(newsdataJSON);
            JSONObject responses = jsonob.getJSONObject("response");
            JSONArray resultsall = responses.getJSONArray("results");

            for (int i = 0; i < resultsall.length(); i++) {

                JSONObject currentindex = resultsall.getJSONObject(i);
                String iTitle = currentindex.getString("webTitle");
                String isecname= currentindex.getString("sectionName");
                String idate = currentindex.getString("webPublicationDate");
                String iurl = currentindex.getString("webUrl");
                String itype = currentindex.getString("type");
                String pillar = currentindex.getString("pillarName");

                News Inew = new News(iTitle, isecname, idate, iurl, pillar,itype);
                newsList.add(Inew);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return newsList;
    }
}
