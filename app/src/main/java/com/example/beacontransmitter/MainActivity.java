package com.example.beacontransmitter;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.bluetooth.le.AdvertiseCallback;
import android.bluetooth.le.AdvertiseSettings;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Toast;
import android.widget.ToggleButton;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import org.altbeacon.beacon.Beacon;

import org.altbeacon.beacon.BeaconParser;
import org.altbeacon.beacon.BeaconTransmitter;

import org.altbeacon.beacon.Region;
import org.altbeacon.beacon.startup.BootstrapNotifier;


import java.util.Arrays;

public class MainActivity extends AppCompatActivity implements BootstrapNotifier {
    private static final int REQUEST_ENABLE_BLUETOOTH = 1;
    BeaconTransmitter beaconTransmitter;
    Beacon beacon;
    BeaconParser beaconParser;

    private Button Red;
    private Button Orange;
    private Button Yellow;
    private Button Green;
    private Button Blue;
    private Button Indigo;
    private Button Purple;
    int major = 1;

    androidx.constraintlayout.widget.ConstraintLayout background;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViews();
        background = findViewById(R.id.back);

        ToggleButton toggleButton = (ToggleButton) findViewById(R.id.Toggle_Button);
        toggleButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                try
                {
                    trasmitClick(8);
                    if(isChecked==true)
                        Toast.makeText(MainActivity.this, "Twinkle on",Toast.LENGTH_SHORT).show();
                    else
                        Toast.makeText(MainActivity.this, "Twinkle off",Toast.LENGTH_SHORT).show();
                }
                catch(Exception e)
                {
                    Toast.makeText(MainActivity.this, ""+isChecked, Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    private void findViews() {
        //find button id
        Red = (Button)findViewById(R.id.Red);
        Orange = (Button)findViewById(R.id.Orange);
        Yellow = (Button)findViewById(R.id.Yellow);
        Green = (Button)findViewById(R.id.Green);
        Blue = (Button)findViewById(R.id.Blue);
        Indigo = (Button)findViewById(R.id.Indigo);
        Purple = (Button)findViewById(R.id.Purple);
    }

    /*-----------------------------------------RED-----------------------------------------*/
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public void buttononClickRed(View view){
//        if (isBluetoothEnabled)
//        {
        try
        {
            trasmitClick(1);
            Toast.makeText(this, R.string.buttonOnClickSuccess, Toast.LENGTH_SHORT).show();
        }
        catch(Exception e)
        {
            Toast.makeText(this, R.string.buttonOnClickFailure, Toast.LENGTH_LONG).show();
        }
//        background.setBackgroundColor(Color.parseColor("#FF0000"));
    }
    /*-----------------------------------------ORANGE-----------------------------------------*/
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public void buttononClickOrange(View view){
        try
        {
            trasmitClick(2);
            Toast.makeText(this, R.string.buttonOnClickSuccess, Toast.LENGTH_SHORT).show();
        }
        catch(Exception e)
        {
            Toast.makeText(this, R.string.buttonOnClickFailure, Toast.LENGTH_LONG).show();
        }
    }
    /*-----------------------------------------YELLOW-----------------------------------------*/
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public void buttononClickYellow(View view){
        try
        {
            trasmitClick(3);
            Toast.makeText(this, R.string.buttonOnClickSuccess, Toast.LENGTH_SHORT).show();
        }
        catch(Exception e)
        {
            Toast.makeText(this, R.string.buttonOnClickFailure, Toast.LENGTH_LONG).show();
        }
    }
    /*-----------------------------------------GREEN-----------------------------------------*/
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public void buttononClickGreen(View view){
        try
        {
            trasmitClick(4);
            Toast.makeText(this, R.string.buttonOnClickSuccess, Toast.LENGTH_SHORT).show();
        }
        catch(Exception e)
        {
            Toast.makeText(this, R.string.buttonOnClickFailure, Toast.LENGTH_LONG).show();
        }
    }
    /*-----------------------------------------BLUE-----------------------------------------*/
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public void buttononClickBlue(View view){
        try
        {
            trasmitClick(5);
            Toast.makeText(this, R.string.buttonOnClickSuccess, Toast.LENGTH_SHORT).show();
        }
        catch(Exception e)
        {
            Toast.makeText(this, R.string.buttonOnClickFailure, Toast.LENGTH_LONG).show();
        }
    }
    /*-----------------------------------------INDIGO-----------------------------------------*/
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public void buttononClickIndigo(View view){
        try
        {
            trasmitClick(6);
            Toast.makeText(this, R.string.buttonOnClickSuccess, Toast.LENGTH_SHORT).show();
        }
        catch(Exception e)
        {
            Toast.makeText(this, R.string.buttonOnClickFailure, Toast.LENGTH_LONG).show();
        }
    }
    /*-----------------------------------------PURPLE-----------------------------------------*/
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public void buttononClickPurple(View view){
        try
        {
            trasmitClick(7);
            Toast.makeText(this, R.string.buttonOnClickSuccess, Toast.LENGTH_SHORT).show();
        }
        catch(Exception e)
        {
            Toast.makeText(this, R.string.buttonOnClickFailure, Toast.LENGTH_LONG).show();
        }
    }

    /*-----------------------------------------transimitClick()-----------------------------------------*/
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public void trasmitClick(int minor) {
        if(major!=1)
            beaconTransmitter.stopAdvertising();
        beacon = new Beacon.Builder()
                .setId1("fda50693a4e24fb1afcfc6eb07647825")
                .setId2(String.valueOf(major++))
                .setId3(String.valueOf(minor))
                .setManufacturer(0x0118) // It is for AltBeacon.  Change this for other beacon layouts
                .setDataFields(Arrays.asList(new Long[] {0l})) // Remove this for beacon layouts without d: fields
                .build();

        // Change the layout below for other beacon types
        beaconParser = new BeaconParser()
                .setBeaconLayout("m:2-3=0215,i:4-19,i:20-21,i:22-23,p:24-24");

        beaconTransmitter = new BeaconTransmitter(getApplicationContext(), beaconParser);
        beaconTransmitter.startAdvertising(beacon);
    }

//    @Override
//    public void onBluetoothEnabled(boolean enable) {
//        if (enable) {
//            isBluetoothEnabled = true;
//        } else {
//            Intent enableBtIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
//            startActivityForResult(enableBtIntent, REQUEST_ENABLE_BLUETOOTH);
//        }
//    }

    @Override
    public void didEnterRegion(Region region) {

    }

    @Override
    public void didExitRegion(Region region) {

    }

    @Override
    public void didDetermineStateForRegion(int i, Region region) {

    }
}