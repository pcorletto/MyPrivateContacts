package com.example.android.myprivatecontacts;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;


public class SearchContactActivity extends ActionBarActivity {

    TextView display_email, display_mobile;
    EditText search_name_editText;
    UserDbHelper userDbHelper;
    SQLiteDatabase sqLiteDatabase;
    String search_name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_contact);
        display_email = (TextView) findViewById(R.id.display_email);
        display_mobile = (TextView) findViewById(R.id.display_mobile);
        search_name_editText = (EditText) findViewById(R.id.search_name);
        display_email.setVisibility(View.INVISIBLE);
        display_mobile.setVisibility(View.INVISIBLE);
    }

    public void searchContact(View view){

        search_name = search_name_editText.getText().toString();

        // Next, call the getContact method from the UserDbHelper

        // Initialize the userDbHelper object

        userDbHelper = new UserDbHelper(getApplicationContext());

        // Initialize the SQLiteDatabase object

        sqLiteDatabase = userDbHelper.getReadableDatabase();

        Cursor cursor = userDbHelper.getContact(search_name, sqLiteDatabase);

        // Now, get the information from the cursor object.

        if(cursor.moveToFirst()) {

            {
                String MOBILE = cursor.getString(0);
                String EMAIL = cursor.getString(1);
                display_mobile.setText(MOBILE);
                display_email.setText(EMAIL);

                // Make these two textviews visible

                display_mobile.setVisibility(View.VISIBLE);
                display_email.setVisibility(View.VISIBLE);
            }

        }



    }



}
