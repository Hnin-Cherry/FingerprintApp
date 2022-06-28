package com.hnincherry.fingerprintapp;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class ViewActivity extends AppCompatActivity{

    DatabaseOpenHelper dbHelper;

    ListView list_view;
    int memoId;

    private ArrayList<String> arraynote = new ArrayList<>();
    private ArrayList<String> arraydate = new ArrayList<>();
    private ArrayList<Integer> arrayid = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view);

        dbHelper = new DatabaseOpenHelper(this);
        list_view = findViewById(R.id.list_view);

        registerForContextMenu(list_view);
        generateListViewData();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == R.id.add) {
            Intent intent = new Intent(this,AddActivity.class);
            startActivity(intent);

        }
        return true;
    }

    public void generateListViewData() {
        try{

            Cursor c = dbHelper.getAllData();
            arraynote.clear();
            arraydate.clear();
            arrayid.clear();

            if (c.moveToFirst()){
                do {
                    arrayid.add(c.getInt(0));
                    arraynote.add(c.getString(1));
                    arraydate.add(c.getString(2));

                }while (c.moveToNext());
            }


            CustomListviewAdapter customListViewAdapter = new CustomListviewAdapter(this.getApplicationContext(),arraynote,arraydate);
            list_view.setAdapter(customListViewAdapter);
            c.close();

        }catch (Exception e){
            e.printStackTrace();
            Toast.makeText(getApplicationContext(),e.getMessage(),Toast.LENGTH_SHORT).show();
        }
    }



    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        menu.setHeaderTitle("Select the Action:");
        menu.add(0,v.getId(),0,"Update");
        menu.add(0,v.getId(),0,"Delete");

    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        if(item.getTitle() == "Update") {

            AdapterView.AdapterContextMenuInfo adapterContextMenuInfo =
                    (AdapterView.AdapterContextMenuInfo)item.getMenuInfo();
            int position = adapterContextMenuInfo.position;

            memoId = arrayid.get(position);
            String memoNote = arraynote.get(position);

            //Toast.makeText(this,"Memoid " + memoId + "MemoNote " + memoNote,Toast.LENGTH_SHORT).show();

            Intent intent = new Intent(this.getApplicationContext(),UpdateActivity.class);
            intent.putExtra("memoid",memoId);
            intent.putExtra("memonote",memoNote);
            startActivity(intent);

        }else if(item.getTitle() == "Delete") {

            AdapterView.AdapterContextMenuInfo adapterContextMenuInfo =
                    (AdapterView.AdapterContextMenuInfo)item.getMenuInfo();
            int position = adapterContextMenuInfo.position;

            memoId = arrayid.get(position);
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setCancelable(false);
            builder.setTitle("DELETE");
            builder.setMessage("Are you sure you want to delete");
            builder.setPositiveButton("OK!!!", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    //Toast.makeText(getApplicationContext(),"Ok Click",Toast.LENGTH_SHORT).show();
                    int result = dbHelper.deleteData(String.valueOf(memoId));
                    if(result > 0) {

                        generateListViewData();
                    }else {
                        Toast.makeText(getApplicationContext(), "Delete Fail" , Toast.LENGTH_SHORT).show();
                    }

                    //Toast.makeText(getApplicationContext(),"MemoId "+ memoId,Toast.LENGTH_SHORT).show();
                }
            }).setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    //Toast.makeText(getApplicationContext(),"Cancel Click",Toast.LENGTH_SHORT).show();

                }
            });
            builder.create().show();
        }
        return true;
    }

}
