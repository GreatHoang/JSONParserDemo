package com.example.greathoang.jsonparserdemo;

import android.annotation.SuppressLint;
import android.util.Log;

import org.json.JSONObject;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by GreatHoang on 4/29/2016.
 */
public class MyHandle {

    private String country = "country";
    private String regionName = "regionName";
    private String providers = "isp";
    private String latitude = "lat";
    private String longitude = "lon";
    private String publicIPAddress = "query";
    private String urlApi = "http://ip-api.com/json";
    public volatile boolean parsingComplete = true;

    public MyHandle(){
    }

    public String getCountry() {
        return country;
    }

    public String getRegionName() {
        return regionName;
    }

    public String getLatitude() {
        return latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public String getProviders() {
        return providers;
    }

    public String getPublicIPAddress() {
        return publicIPAddress;
    }

    @SuppressLint("NewApi")
    public void readAndParseJSON(String in) {
        try {
            JSONObject reader = new JSONObject(in);
            Log.d("MyHandle", reader.toString());
            country = reader.getString(country);
            regionName = reader.getString(regionName);
            latitude = reader.getString(latitude);
            longitude = reader.getString(longitude);
            providers = reader.getString(providers);
            publicIPAddress = reader.getString(publicIPAddress);
            parsingComplete = false;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void fetchJSON(){
        Thread thread = new Thread(new Runnable(){
            @Override
            public void run() {
                try {
                    URL url = new URL(urlApi);
                    HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                    conn.setReadTimeout(30000 /* milliseconds */);
                    conn.setConnectTimeout(30000 /* milliseconds */);
                    conn.setRequestMethod("GET");
                    conn.setDoInput(true);
                    // Starts the query
                    conn. connect();
                    InputStream stream = conn.getInputStream();
                    String data = convertStreamToString(stream);
                    readAndParseJSON(data);
                    stream.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        thread.start();
    }
    static String convertStreamToString(java.io.InputStream is) {
        java.util.Scanner s = new java.util.Scanner(is).useDelimiter("\\A");
        return s.hasNext() ? s.next() : "";
    }
}
