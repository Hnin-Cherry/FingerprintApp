package com.hnincherry.fingerprintapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class UpdateActivity extends AppCompatActivity implements View.OnClickListener {

    DatabaseOpenHelper myDb;
    EditText update_note;
    Button btn_update,btn_back;
    int memoId;
    String memoNote;


    Memo memo = new Memo();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);

        myDb = new DatabaseOpenHelper(this);
        update_note = findViewById(R.id.update_note);
        btn_update = findViewById(R.id.btn_update);
        btn_back = findViewById(R.id.btn_back);

        memoId = getIntent().getExtras().getInt("memoid");
        memoNote = getIntent().getExtras().getString("memonote");

        update_note.setText(memoNote);
        btn_update.setOnClickListener(this);
        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),ViewActivity.class);
                startActivity(intent);
            }
        });

    }


    @Override
    public void onClick(View v) {

        String memoNote = update_note.getText().toString();

        String date = memo.getDate();

        Boolean result = myDb.updateData(String.valueOf(memoId),memoNote,date);

        if(result == true) {
            Toast.makeText(this.getApplicationContext(),"Data Update Success",Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(this.getApplicationContext(),"Data Update Fail",Toast.LENGTH_SHORT).show();
        }
    }
}
