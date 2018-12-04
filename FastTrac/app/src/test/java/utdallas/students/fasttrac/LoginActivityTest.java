package utdallas.students.fasttrac;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class LoginActivityTest {

    LoginActivity uLoginAcvity;
    DatabaseHelper db;
    SQLiteDatabase data;

    @Before
    public void setUp() throws Exception {
        uLoginAcvity = new LoginActivity();
        Context context = uLoginAcvity.getApplicationContext();
        db = DatabaseHelper.getInstance(context);
    }

    @Test
    public void test(){
        db.addUser(new User("hello", "123456", "hello", "goodbye", "hello@utdallas.edu", 0));
        assertEquals(true, db.validCredentials("hello", "123456"));
    }
}

