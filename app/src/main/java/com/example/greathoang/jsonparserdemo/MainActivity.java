package com.example.greathoang.jsonparserdemo;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    private EditText country, regionName, latitude, longitude, providers, publicIPAddress;
    private MyHandle obj ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        country = (EditText) findViewById(R.id.editText1);
        regionName = (EditText) findViewById(R.id.editText2);
        latitude = (EditText) findViewById(R.id.editText3);
        longitude = (EditText) findViewById(R.id.editText4);
        providers = (EditText) findViewById(R.id.editText5);
        publicIPAddress = (EditText) findViewById(R.id.editText6);
    }

    public void open(View view){
        obj = new MyHandle();
        obj.fetchJSON();
        while(obj.parsingComplete);
        country.setText(obj.getCountry());
        regionName.setText(obj.getRegionName());
        latitude.setText(obj.getLatitude());
        longitude.setText(obj.getLongitude());
        providers.setText(obj.getProviders());
        publicIPAddress.setText(obj.getPublicIPAddress());
    }
}
