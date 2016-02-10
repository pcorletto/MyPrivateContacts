package com.example.android.myprivatecontacts;

import android.app.Activity;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;


public class NewContactActivity extends Activity {

    private EditText contactNameEditText;
    private EditText contactMobileEditText;
    private EditText contactEmailEditText;

    private Context context;
    private UserDbHelper userDbHelper;
    private SQLiteDatabase sqLiteDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_contact);

        contactNameEditText = (EditText) findViewById(R.id.contact_name_edit_text);
        contactMobileEditText = (EditText) findViewById(R.id.contact_mobile_edit_text);
        contactEmailEditText = (EditText) findViewById(R.id.contact_email_edit_text);

    }


    public void addContact(View view) {

        context=this;

        String contactName = contactNameEditText.getText().toString();
        String contactMobile = contactMobileEditText.getText().toString();
        String contactEmail = contactEmailEditText.getText().toString();

        // Perform DB insertion

        // Initialize UserDbHelper and SQLiteDatabase objects

        userDbHelper = new UserDbHelper(context);
        sqLiteDatabase = userDbHelper.getWritableDatabase();
        userDbHelper.addInformation(contactName, contactMobile, contactEmail, sqLiteDatabase);

        Toast.makeText(getBaseContext(), "Data Saved.", Toast.LENGTH_LONG).show();

        userDbHelper.close();

    }


}






