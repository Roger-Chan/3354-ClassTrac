package utdallas.students.fasttrac;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class CoursesDatabase extends SQLiteOpenHelper {
    private static CoursesDatabase cd = null;

    public int ERROR = -1;
    public static final int DATABASE_VERSION = 2;
    public static final String DATABASE_NAME = "course_manager.db";
    public static final String TABLE_NAME = "COURSES";
    public static final String KEY_ID = "ID";           //column index 0
    public static final String KEY_NAME= "COURSE_NAME";            //column index 1
    public static final String KEY_CODE = "COURSE_CODE";         //coulum index 2
    public static final String KEY_HOUR = "COURSE_HOUR";           //column index 3
    public static final String KEY_MINUTE = "COURSE_MINUTE";           //column index 4
    public static final String KEY_INSTRUCTOR = "COURSE_INSTRUCTOR";                 //column index 5

    private CoursesDatabase(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        deleteAll();
        addCourse(new Course("SE 3354", "Software Engineering", "12345", 10, 30, "Dr. WEI"));
        addCourse(new Course("CE 2337", "CompSci 2", "13346", 11, 0, "jason smith"));
    }

    public static CoursesDatabase getInstance(Context context){
        if (cd == null){
            cd = new CoursesDatabase(context);
        }
        return cd;
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        final String SQL_CREATE_USER_TABLE = "CREATE TABLE " + TABLE_NAME +
                "(" + KEY_ID    +   " TEXT NOT NULL, "  +   // column 0
                KEY_NAME        +   " TEXT NOT NULL, "  +
                KEY_CODE        +   " TEXT NOT NULL, "  +
                KEY_HOUR        +   " INT, "            +
                KEY_MINUTE      +   " INT, "            +
                KEY_INSTRUCTOR  +   " TEXT);"
                ;
        db.execSQL(SQL_CREATE_USER_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public void addCourse(Course course){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_ID, course.getId());
        values.put(KEY_NAME , course.getName());
        values.put(KEY_CODE, course.getCode());
        values.put(KEY_HOUR , course.getHour());
        values.put(KEY_MINUTE, course.getMinute());
        values.put(KEY_INSTRUCTOR, course.getInstructor());

        // if error then returns -1
        long result = db.insert(TABLE_NAME, null, values);
    }

    public Course findCourse(String code) {
        SQLiteDatabase db = this.getWritableDatabase();

        // query to find the username
        Cursor cursor = db.rawQuery("select * from " + TABLE_NAME, null);

        while (cursor.moveToNext()) {
            if (cursor.getString(2).equals(code)){
                String id = cursor.getString(0);
                String name = cursor.getString(1);
                int hour = cursor.getInt(2);
                int minute = cursor.getInt(3);
                String instructor = cursor.getString(4);

                return new Course(id, name, code, hour, minute, instructor);
            }
        }

        return null;
    }

    public void deleteAll(){
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DELETE FROM " + TABLE_NAME);
    }

    public ArrayList<String> getProfessorCourses(String firstName, String lastName){
        SQLiteDatabase db = this.getWritableDatabase();
        ArrayList<String> names = new ArrayList<String>();

        // search for the student and get all the codes they have
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_NAME + " WHERE COURSE_INSTRUCTOR = ?",new String[] {firstName + " " + lastName});

        while(cursor.moveToNext()){
            names.add(cursor.getString(1));
        }

        return names;
    }
}
