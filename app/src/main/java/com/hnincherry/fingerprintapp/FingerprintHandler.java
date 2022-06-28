package com.hnincherry.fingerprintapp;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;

import android.content.Intent;
import android.hardware.fingerprint.FingerprintManager;
import android.os.Build;
import android.os.CancellationSignal;
import android.support.v4.content.ContextCompat;

import android.widget.TextView;
import android.widget.Toast;


@TargetApi(Build.VERSION_CODES.M)
public class FingerprintHandler extends FingerprintManager.AuthenticationCallback {

    private Context context;


    public FingerprintHandler(Context context) {
        this.context = context;
    }

    public void startAuth(FingerprintManager fingerprintManager,FingerprintManager.CryptoObject cryptoObject) {

        CancellationSignal cancellationSignal = new CancellationSignal();
        fingerprintManager.authenticate(cryptoObject,cancellationSignal,0,this,null);

    }

    @Override
    public void onAuthenticationError(int errorCode, CharSequence errString) {
        this.update("There was an Auth Error. " + errString, false);
    }

    @Override
    public void onAuthenticationFailed() {
        this.update("Please try Again. " ,false);
    }

    @Override
    public void onAuthenticationHelp(int helpCode, CharSequence helpString) {
        this.update("Error : " + helpString,false);
    }

    @Override
    public void onAuthenticationSucceeded(FingerprintManager.AuthenticationResult result) {
        this.update("You can now access this app. " ,true);


    }


    private void update(String s, boolean b) {
        TextView txt_finger = ((Activity)context).findViewById(R.id.txt_finger);

        txt_finger.setText(s);
        if(b == false) {
            txt_finger.setTextColor(ContextCompat.getColor(context,R.color.colorAccent));
        }else if(b == true){
            txt_finger.setTextColor(ContextCompat.getColor(context,R.color.colorPrimary));

            context.startActivity(new Intent(context,ViewActivity.class));
        }
    }

}