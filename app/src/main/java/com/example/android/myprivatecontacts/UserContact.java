package com.example.android.myprivatecontacts;

/**
 * Created by hernandez on 2/6/2016.
 */
public class UserContact {

    // Static inner class called NewUserInfo is to hold a table. Each table in a DB
    // needs to have its own inner static inner class, like this one.

    public static abstract class NewUserInfo {


        // Each of the following constants will represent a column in the table.
        public static final String USER_NAME = "user_name";

        public static final String USER_MOB = "user_mob";

        public static final String USER_EMAIL = "user_email";

        // The following constant will represent my table name.
        public static final String TABLE_NAME = "user_info";

        // Next, define the database operations. Use SQLite Open Helper Class







    }

}
