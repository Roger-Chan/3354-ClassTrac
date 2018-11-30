package utdallas.students.fasttrac;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import java.util.ArrayList;

/*
    if you want to update the table and it gives an error like:
        - column [BLANK] is not found or
        - database table [BLANK] is not found
    when you add a new column or change a column name, just change the version number and it should work
 */


public class DatabaseHelper extends SQLiteOpenHelper {
    //static global instance
    private static DatabaseHelper db = null;

    public static final int DATABASE_VERSION = 3;
    public static final String DATABASE_NAME = "user_manager.db";
    public static final String TABLE_NAME = "USERS";
    public static final String KEY_USERNAME = "USERNAME";           //column index 0
    public static final String KEY_PASSWRD = "PASSWORD";            //column index 1
    public static final String KEY_FIRSTNAME = "FIRSTNAME";         //column index 2
    public static final String KEY_LASTNAME = "LASTNAME";           //column index 3
    public static final String KEY_EMAIL = "EMAIL";                 //column index 4
    public static final String KEY_AUTHORIZATION = "AUTHORIZATION"; //column index 5

    public static final String CODE_COURSE_1 = "COURSE_1_CODE";
    public static final String CODE_COURSE_2 = "COURSE_2_CODE";
    public static final String CODE_COURSE_3 = "COURSE_3_CODE";
    public static final String CODE_COURSE_4 = "COURSE_4_CODE";
    public static final String CODE_COURSE_5 = "COURSE_5_CODE";


    private DatabaseHelper(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        deleteAll();
        addUser(new Student("username","password","matthew","hicks", "mgh160030@utdallas.edu"));
        addUser(new Student("mgh160031", "pass", "kevin", "smith", "smith1@utdallas.edu"));
        addUser(new Professor("professor1", "password", "jason", "smith", "jason.smith@utdallas.edu"));
    }

    public static DatabaseHelper getInstance(Context context){
        if (db == null)
        {
            db = new DatabaseHelper(context);
        }
        return db;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        final String SQL_CREATE_USER_TABLE = "CREATE TABLE IF NOT EXISTS " + TABLE_NAME +
                "(" +
                KEY_USERNAME        +   " TEXT NOT NULL, "  +   // column 0
                KEY_PASSWRD         +   " TEXT NOT NULL, "  +
                KEY_FIRSTNAME       +   " TEXT NOT NULL, "  +   // column 2
                KEY_LASTNAME        +   " TEXT NOT NULL, "  +
                KEY_EMAIL           +   " TEXT, "           +   // column 4
                KEY_AUTHORIZATION   +   " INT, "            +
                CODE_COURSE_1       +   " TEXT, "           +   // column 6
                CODE_COURSE_2       +   " TEXT, "           +
                CODE_COURSE_3       +   " TEXT, "           +   // column 8
                CODE_COURSE_4       +   " TEXT, "           +
                CODE_COURSE_5       +   " TEXT);";              // column 10
        db.execSQL(SQL_CREATE_USER_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public void addUser(User user){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_USERNAME, user.getUsername());
        values.put(KEY_PASSWRD , user.getPasswrd());
        values.put(KEY_FIRSTNAME, user.getFirst_name());
        values.put(KEY_LASTNAME , user.getLast_name());
        values.put(KEY_EMAIL, user.getEmail());
        values.put(KEY_AUTHORIZATION, user.getAuthorization());

        /*
        // see what courses they are already in
        for(int i =0; i < user.getCourse().size(); i++){
            values.put("CODE_COURSE_" + (i+1),user.getCourse().get(i).getCode());
        }

        // for all other courses enter null
        for(int i =0; i < (5 - user.getCourse().size()); i++){
            values.put("CODE_COURSE_" + (i + user.getCourse().size()), "NULL");
        }
       */

        values.put(CODE_COURSE_1, "NULL");
        values.put(CODE_COURSE_2, "NULL");
        values.put(CODE_COURSE_3, "NULL");
        values.put(CODE_COURSE_4, "NULL");
        values.put(CODE_COURSE_5, "NULL");

        // if error then returns -1
        db.insert(TABLE_NAME, null, values);


    }

    public User validCredentials(String username, String passwrd){
        SQLiteDatabase db = this.getWritableDatabase();

        // query to find the username
        Cursor cursor = db.rawQuery("select * from " + TABLE_NAME, null);

        while(cursor.moveToNext()){
            if(cursor.getString(0).equalsIgnoreCase(username) && cursor.getString(1).equalsIgnoreCase(passwrd)){
                String uname = cursor.getString(0);
                String passw = cursor.getString(1);
                String fname = cursor.getString(2);
                String lname = cursor.getString(3);
                String mail = cursor.getString(4);
                int auth = cursor.getInt(5);;

                if(auth == 0){
                    // its a student
                    return new Student(uname, passw, fname, lname, mail);
                }   else{
                    // its a professor
                    return new Professor(uname, passw, fname, lname, mail);
                }
            }
        }
        return null;
    }

    public void deleteAll(){
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DELETE FROM " + TABLE_NAME);
    }

    public int updateData(String UserBefore, String username, String pass, String firstname, String lastname, String email){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(KEY_USERNAME, username);
        contentValues.put(KEY_PASSWRD, pass);
        contentValues.put(KEY_FIRSTNAME, firstname);
        contentValues.put(KEY_LASTNAME, lastname);
        contentValues.put(KEY_EMAIL, email);

        return db.update(TABLE_NAME, contentValues, "USERNAME = ?", new String [] {UserBefore});
    }

    /*
    will improve update function in a little
     */
    public boolean addCourse(String username, String password, Course course){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_NAME + " WHERE USERNAME = ? AND PASSWORD = ?",new String[] {username,password});
        while(cursor.moveToNext()){
            for(int i = 6; i <= 10; i++){
                // also checks if all the courses are take up
                if(cursor.getString(i).contains("NULL")){
                    // add the course to this column
                    ContentValues contentValues = new ContentValues();
                    contentValues.put("COURSE_" + (i - 5) + "_CODE", course.getCode());
                    db.update(TABLE_NAME, contentValues, "USERNAME = ? AND PASSWORD = ?", new String[] {username, password});
                    return true;
                }   else if(cursor.getString(i).contains(course.getCode())){
                    // the user is already in the class
                    return false;
                }
            }
        }
        return false;
    }
    public void updateCourse(String username, Course course){
            SQLiteDatabase db = this.getWritableDatabase();
            Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_NAME + " WHERE USERNAME = ?",new String[] {username});
            while(cursor.moveToNext()){

            }
            //ContentValues contentValues = new ContentValues();

    }

    public ArrayList<String> getCodes(String username, String password){
        SQLiteDatabase db = this.getWritableDatabase();
        ArrayList<String> codes = new ArrayList<String>();

        // search for the student and get all the codes they have
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_NAME + " WHERE USERNAME = ? AND PASSWORD = ?",new String[] {username,password});
        while(cursor.moveToNext()){
            for(int i = 6; i <= 10; i++){
                if(cursor.getString(i) == "NULL"){
                    codes.add(null);
                }   else{
                    codes.add(cursor.getString(i));
                }
            }
        }

        // return all the codes we have
        return codes;
    }
}
