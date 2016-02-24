package com.example.android.myprivatecontacts;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by hernandez on 2/6/2016.
 */
public class UserDbHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "USERINFO.DB";
    private static final int DATABASE_VERSION = 1;

    //QUERY. It specifies the table name and the columns along with their data types
    // Be careful to include the space after CREATE TABLE and before TEXT. The brackets { and } open
    // and close the columns.

    private static final String CREATE_QUERY = "CREATE TABLE " + UserContact.NewUserInfo.TABLE_NAME +
            "(" + UserContact.NewUserInfo.USER_NAME + " TEXT," + UserContact.NewUserInfo.USER_MOB +
            " TEXT," + UserContact.NewUserInfo.USER_EMAIL + " TEXT);";

    // Constructor:

    public UserDbHelper(Context context){

        super(context, DATABASE_NAME, null, DATABASE_VERSION);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        // Create the table using the QUERY
        db.execSQL(CREATE_QUERY);
        Log.e("DATABASE OPERATIONS", "Table created...");

    }

    // Added my own method to insert rows of data into the database table

    public void addInformation(String name, String mob, String email, SQLiteDatabase db){

        // Key-value maps next, are used to do the insertions

        ContentValues contentValues = new ContentValues();
        contentValues.put(UserContact.NewUserInfo.USER_NAME, name);
        contentValues.put(UserContact.NewUserInfo.USER_MOB, mob);
        contentValues.put(UserContact.NewUserInfo.USER_EMAIL, email);

        // Next, save all the information into the database

        db.insert(UserContact.NewUserInfo.TABLE_NAME, null, contentValues);

        Log.e("DATABASE OPERATIONS", "One row is inserted ...");


    }

    public Cursor getInformation(SQLiteDatabase db){

        // The return type of Object is "Cursor"
        Cursor cursor;

        // Create projections, or the needed column names
        String[] projections = {UserContact.NewUserInfo.USER_NAME, UserContact.NewUserInfo.USER_MOB,
             UserContact.NewUserInfo.USER_EMAIL};

        // We only need the table name and projection parameters. No conditions will be specified,
        // so, we will pass in null for the last five parameters.

        cursor = db.query(UserContact.NewUserInfo.TABLE_NAME, projections, null, null, null, null, null);

        return cursor;

    }


    // The next method is to retrieve a particular row from the database, for the SEARCH function

    public Cursor getContact(String user_name, SQLiteDatabase sqLiteDatabase){

        // Get the projections, so that we can get the mobile number and e-mail corresponding
        // to a name.

        String[] projections = {UserContact.NewUserInfo.USER_MOB, UserContact.NewUserInfo.USER_EMAIL};

        // Now, define the condition, the "WHERE" clause

        String selection = UserContact.NewUserInfo.USER_NAME + " LIKE ?";

        String[] selection_args = {user_name};

        // In the next statement, the parameters are: first, the table name; second, the projections;
        //third, the selection arguments; and the last three, make them null.

        Cursor cursor = sqLiteDatabase.query(UserContact.NewUserInfo.TABLE_NAME, projections, selection,
                selection_args, null, null, null);
        return cursor;
    }

    public void deleteInformation(String user_name, SQLiteDatabase sqLiteDatabase){

        String selection = UserContact.NewUserInfo.USER_NAME + " LIKE ?";

        String[] selection_args = {user_name};

        sqLiteDatabase.delete(UserContact.NewUserInfo.TABLE_NAME, selection, selection_args);


    }

    public int updateInformation(String old_name, String new_name, String new_mobile,
                                  String new_email, SQLiteDatabase sqLiteDatabase){

        ContentValues contentValues = new ContentValues();
        contentValues.put(UserContact.NewUserInfo.USER_NAME, new_name);
        contentValues.put(UserContact.NewUserInfo.USER_MOB, new_mobile);
        contentValues.put(UserContact.NewUserInfo.USER_EMAIL, new_email);

        // Provide a condition or selection

        String selection = UserContact.NewUserInfo.USER_NAME + " LIKE ?";

        String[] selection_args = {old_name};

        int count = sqLiteDatabase.update(UserContact.NewUserInfo.TABLE_NAME, contentValues,
                selection, selection_args);

        return count;
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
