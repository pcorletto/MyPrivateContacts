package com.example.android.myprivatecontacts;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


public class UpdateContactActivity extends ActionBarActivity {

    private EditText search_name, name_edit_text, mob_edit_text, email_edit_text;
    private Button searchButton, updateButton;
    private TextView titleText;

    private String searchName, newName, newMobile, newEmail;

    private UserDbHelper userDbHelper;

    private SQLiteDatabase sqLiteDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_contact);

        search_name = (EditText) findViewById(R.id.search_name);
        name_edit_text = (EditText) findViewById(R.id.name_edit_text);
        mob_edit_text = (EditText) findViewById(R.id.mob_edit_text);
        email_edit_text = (EditText) findViewById(R.id.email_edit_text);
        searchButton = (Button) findViewById(R.id.search_name_btn);
        updateButton = (Button) findViewById(R.id.update_contact_btn);
        titleText = (TextView) findViewById(R.id.title_text);

        search_name.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                InputMethodManager imm =(InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
                if(imm != null) {
                    imm.showSoftInput(search_name, 0);
                }
                search_name.requestFocus();
                search_name.setText("");
                return true;
            }
        });

        name_edit_text.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                InputMethodManager imm =(InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
                if(imm != null) {
                    imm.showSoftInput(name_edit_text, 0);
                }
                name_edit_text.requestFocus();
                name_edit_text.setText("");
                return true;
            }
        });

        mob_edit_text.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                InputMethodManager imm =(InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
                if(imm != null) {
                    imm.showSoftInput(mob_edit_text, 0);
                }
                mob_edit_text.requestFocus();
                mob_edit_text.setText("");
                return true;
            }
        });

        email_edit_text.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                InputMethodManager imm =(InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
                if(imm != null) {
                    imm.showSoftInput(email_edit_text, 0);
                }
                email_edit_text.requestFocus();
                email_edit_text.setText("");
                return true;
            }
        });

    }

    public void searchContact(View view){

        searchName = search_name.getText().toString();
        userDbHelper = new UserDbHelper(getApplicationContext());
        sqLiteDatabase = userDbHelper.getReadableDatabase();
        Cursor cursor = userDbHelper.getContact(searchName, sqLiteDatabase);

        if(cursor.moveToFirst()){
            newMobile = cursor.getString(0);
            newEmail = cursor.getString(1);
            newName = searchName;
            name_edit_text.setText(newName);
            mob_edit_text.setText(newMobile);
            email_edit_text.setText(newEmail);
            name_edit_text.setVisibility(View.VISIBLE);
            mob_edit_text.setVisibility(View.VISIBLE);
            email_edit_text.setVisibility(View.VISIBLE);
            updateButton.setVisibility(View.VISIBLE);
            titleText.setVisibility(View.VISIBLE);
        }

    }

    public void updateContact(View view){

        userDbHelper = new UserDbHelper(getApplicationContext());
        sqLiteDatabase = userDbHelper.getWritableDatabase();

        String name, mobile, email;

        name = name_edit_text.getText().toString();
        mobile = mob_edit_text.getText().toString();
        email = email_edit_text.getText().toString();

        int count = userDbHelper.updateInformation(searchName, name, mobile, email,
                sqLiteDatabase);
        Toast.makeText(getApplicationContext(), count + " contact(s) updated", Toast.LENGTH_LONG).show();
        finish();

    }



}
