package com.example.android.myprivatecontacts;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.widget.ListView;


public class DataListActivity extends ActionBarActivity {

    ListView listView;
    SQLiteDatabase sqLiteDatabase;
    UserDbHelper userDbHelper;
    Cursor cursor;
    ListDataAdapter listDataAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.data_list_layout);

        listView = (ListView) findViewById(R.id.list_view);

        listDataAdapter = new ListDataAdapter(getApplicationContext(), R.layout.row_layout);
        listView.setAdapter(listDataAdapter);


        //Initialize UserDBHelper and SQLiteDB

        userDbHelper = new UserDbHelper(getApplicationContext());
        sqLiteDatabase = userDbHelper.getReadableDatabase();
        cursor = userDbHelper.getInformation(sqLiteDatabase);

        if(cursor.moveToFirst()){

            do{

                String name, mob, email;
                name = cursor.getString(0);
                mob = cursor.getString(1);
                email = cursor.getString(2);

                DataProvider dataProvider = new DataProvider(name, mob, email);
                listDataAdapter.add(dataProvider);

            }

            while(cursor.moveToNext());
        }



    }



}
