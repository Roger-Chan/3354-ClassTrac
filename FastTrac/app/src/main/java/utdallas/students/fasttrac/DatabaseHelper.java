package utdallas.students.fasttrac;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/*
    if you want to update the table and it gives an error like:
        - column [BLANK] is not found or
        - database table [BLANK] is not found
    when you add a new column or change a column name, just change the version number and it should work
 */


public class DatabaseHelper extends SQLiteOpenHelper {
    public int ERROR = -1;
    public static final int DATABASE_VERSION = 2;
    public static final String DATABASE_NAME = "user_manager.db";
    public static final String TABLE_NAME = "USERS";
    public static final String KEY_USERNAME = "USERNAME";           //column index 0
    public static final String KEY_PASSWRD = "PASSWORD";            //column index 1
    public static final String KEY_FIRSTNAME = "FIRSTNAME";         //coulum index 2
    public static final String KEY_LASTNAME = "LASTNAME";           //column index 3
    public static final String KEY_EMAIL = "EMAIL";                 //column index 4
    public static final String KEY_AUTHORIZATION = "AUTHORIZATION"; //column index 5

    public DatabaseHelper(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        deleteAll();
        addUser(new Student("mgh160030","password","matthew","hicks", "mgh160030@utdallas.fuckyou"));
        addUser(new Student("mgh160031", "pass", "kevin", "smith", "hellobitch@utdallas.fuckyou"));
        addUser(new Professor("professor1", "password", "Professor", "buttlicker", "professorbuttlicker347@utdallas.fuckyou.com"));
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        final String SQL_CREATE_USER_TABLE = "CREATE TABLE " + TABLE_NAME +
                "(" + KEY_USERNAME  +   " TEXT NOT NULL, "  +
                KEY_PASSWRD         +   " TEXT NOT NULL, "  +
                KEY_FIRSTNAME       +   " TEXT NOT NULL, "  +
                KEY_LASTNAME        +   " TEXT NOT NULL, "  +
                KEY_EMAIL           +   " TEXT, "           +
                KEY_AUTHORIZATION   +   " INT);";
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

        // if error then returns -1
        long result = db.insert(TABLE_NAME, null, values);
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
}
