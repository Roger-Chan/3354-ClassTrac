package com.example.hicks.attendence_sheet;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "user_manager.db";
    public static final String TABLE_NAME = "STUDENTS";
    public static final String KEY_USERNAME = "USERNAME";
    public static final String KEY_PASSWRD = "PASSWORD";
    public static final String KEY_FIRSTNAME = "FIRSTNAME";
    public static final String KEY_LASTNAME = "LASTNAME";

    public DatabaseHelper(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);

        deleteAll();
        addStudent(new Student("mgh160030","password","matthew","hicks"));
        addStudent(new Student("mgh160031", "pass", "kevin", "smith"));
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        final String SQL_CREATE_USER_TABLE = "CREATE TABLE " + TABLE_NAME +
                "(" + KEY_USERNAME + " TEXT NOT NULL, " +
                KEY_PASSWRD + " TEXT NOT NULL, " +
                KEY_FIRSTNAME + " TEXT NOT NULL, " +
                KEY_LASTNAME+ " TEXT);";
        db.execSQL(SQL_CREATE_USER_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public void addStudent(Student student){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_USERNAME, student.getUsername());
        values.put(KEY_PASSWRD , student.getPasswrd());
        values.put(KEY_FIRSTNAME, student.getFirst_name());
        values.put(KEY_LASTNAME , student.getLast_name());

        // if error then returns -1
        long result = db.insert(TABLE_NAME, null, values);
    }

    /*
    public Cursor getAllData(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("SELECT * FROM " + TABLE_NAME, null);
        return res;
    }
    */

    public boolean validCredentials(String username, String passwrd){
        SQLiteDatabase db = this.getWritableDatabase();

        // query to find the username
        Cursor cursor = db.rawQuery("select * from " + TABLE_NAME, null);

        while(cursor.moveToNext()){
            System.out.println(cursor.getString(0) + " " + cursor.getString(1));
            if(cursor.getString(0).equalsIgnoreCase(username) && cursor.getString(1).equalsIgnoreCase(passwrd)){
                return true;
            }
        }

        return false;
    }

    public void deleteAll(){
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DELETE FROM " + TABLE_NAME);
    }
}
