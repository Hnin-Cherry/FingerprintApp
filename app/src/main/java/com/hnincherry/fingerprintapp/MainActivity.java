package com.hnincherry.fingerprintapp;

import android.Manifest;
import android.app.KeyguardManager;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.hardware.fingerprint.FingerprintManager;
import android.os.Build;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView txt_view,txt_finger;
    private ImageView img;

    private FingerprintManager fingerprintManager;
    private KeyguardManager keyguardManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txt_view = findViewById(R.id.txt_view);
        txt_finger = findViewById(R.id.txt_finger);
        img = findViewById(R.id.img);

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            fingerprintManager = (FingerprintManager)getSystemService(FINGERPRINT_SERVICE);
            keyguardManager = (KeyguardManager)getSystemService(KEYGUARD_SERVICE);

            if(!fingerprintManager.isHardwareDetected()) {
                txt_finger.setText("Fingerprint scannar not detected in Device");
            }else if(ContextCompat.checkSelfPermission(this,Manifest.permission.USE_FINGERPRINT) != PackageManager.PERMISSION_GRANTED) {
                txt_finger.setText("Permission not granted to use fingerpsint Scanner");
            }else if(!keyguardManager.isKeyguardSecure()) {
                txt_finger.setText("Add lock to your Phone in Settings");
            }else if(!fingerprintManager.hasEnrolledFingerprints()) {
                txt_finger.setText("New should add atleast 1 Fingerprint to use this Feature");
            }else {
                txt_finger.setText("Please your Finger on Scanner to Access the App");

                FingerprintHandler fingerprintHandler = new FingerprintHandler(this);
                fingerprintHandler.startAuth(fingerprintManager,null);

            }
        }

    }
}
