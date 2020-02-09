package com.farhanramzan.tutorlocating;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class SqliteHelper extends SQLiteOpenHelper {

    //DATABASE NAME
    public static final String DATABASE_NAME = "mobileApp";

    //DATABASE VERSION
    public static final int DATABASE_VERSION = 1;

    //TABLE NAME
    public static final String TABLE_USER_REGISTRATION = "registration";
    public static final String TABLE_USER_INFRO = "user_info";

    //TABLE USERS COLUMNS
    //ID COLUMN @primaryKey
    public static final String USER_ID = "id";

    //COLUMN user name
    public static final String KEY_USER_NAME = "username";

    //COLUMN password
    public static final String KEY_PASSWORD = "password";

    //COLUMN mobile number
    public static final String KEY_MOBILENO = "mobileno";

    //COLUMN address
    public static final String KEY_ADDRESS = "address";

    //COLUMN user role
    public static final String KEY_USER_ROLE = "userrole";

    //SQL for creating users table
    public static final String SQL_TABLE_USER_REGISTRATION = " CREATE TABLE " + TABLE_USER_REGISTRATION
            + " ( "
            + USER_ID + " INTEGER PRIMARY KEY, "
            + KEY_USER_NAME + " TEXT, "
            + KEY_PASSWORD + " TEXT, "
            + KEY_MOBILENO + " TEXT, "
            + KEY_ADDRESS + " TEXT, "
            + KEY_USER_ROLE + " TEXT "
            + " ) ";

    public SqliteHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        //Create Table when oncreate gets called
        sqLiteDatabase.execSQL(SQL_TABLE_USER_REGISTRATION);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        //drop table to create new one if database version updated
        sqLiteDatabase.execSQL(" DROP TABLE IF EXISTS " + TABLE_USER_REGISTRATION);
    }

    //using this method we can add users to user table
    public void addUser(User user) {

        //get writable database
        SQLiteDatabase db = this.getWritableDatabase();

        //create content values to insert
        ContentValues values = new ContentValues();

        //Put username in  @values
        values.put(KEY_USER_NAME, user.userName);

        //Put password in  @values
        values.put(KEY_PASSWORD, user.password);

        //Put mobile number in  @values
        values.put(KEY_MOBILENO, user.mobileno);

        //Put address in  @values
        values.put(KEY_ADDRESS, user.address);

        //Put role in  @values
        values.put(KEY_USER_ROLE, user.role);

        // insert row
        long todo_id = db.insert(TABLE_USER_REGISTRATION, null, values);
    }

    public User Authenticate(User user) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_USER_REGISTRATION,// Selecting Table
                new String[]{KEY_USER_NAME, KEY_PASSWORD},//Selecting columns want to query
                KEY_USER_NAME + "=?",
                new String[]{user.userName},//Where clause
                null, null, null);

        if (cursor != null && cursor.moveToFirst()&& cursor.getCount()>0) {
            //if cursor has value then in user database there is user associated with this given email
            User user1 = new User(cursor.getString(0), cursor.getString(1), cursor.getString(2), cursor.getString(3));

            //Match both passwords check they are same or not
            if (user.password.equalsIgnoreCase(user1.password)) {
                return user1;
            }
        }

        //if user password does not matches or there is no record with that email then return @false
        return null;
    }
    public boolean isUserExists(String email) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_USER_REGISTRATION,// Selecting Table
                new String[]{KEY_USER_NAME,KEY_PASSWORD},//Selecting columns want to query
                KEY_USER_NAME + "=?",
                new String[]{email},//Where clause
                null, null, null);

        if (cursor != null && cursor.moveToFirst()&& cursor.getCount()>0) {
            //if cursor has value then in user database there is user associated with this given email so return true
            return true;
        }

        //if email does not exist return false
        return false;
    }
}
