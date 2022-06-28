package com.hnincherry.fingerprintapp;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Date;

public class AddActivity extends AppCompatActivity {

    DatabaseOpenHelper myDb;
    EditText add_note;
    private Memo memo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        myDb = new DatabaseOpenHelper(this);
        add_note = findViewById(R.id.add_note);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.save_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()) {
            case R.id.save:

                String note = add_note.getText().toString();

                if (memo == null){
                    if(note.isEmpty() || note.trim().isEmpty()) {
                        add_note.setError("Please write some text....");
                    }
                    else {
                        //Add new memo
                        Memo temp = new Memo();
                        // memo.setText(note);
                        Boolean result = myDb.insertData(note, temp);
                        if (result == true) {
                            Toast.makeText(this, "Save Success", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(this, "Save Fail", Toast.LENGTH_SHORT).show();
                        }

                    }
                }

                else {
                    //update the memo
                    memo.setText(note);
                    //myDb.updateData(memo);
                }
                return true;

            case R.id.reply:

                Intent intent = new Intent(this,ViewActivity.class);
                startActivity(intent);
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }

    }
}
