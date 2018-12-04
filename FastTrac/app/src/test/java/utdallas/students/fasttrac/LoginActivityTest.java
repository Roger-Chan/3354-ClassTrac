package utdallas.students.fasttrac;

import android.content.Context;
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

    @Before
    public void setUp() throws Exception {
        uLoginAcvity = new LoginActivity();
        Context context = uLoginAcvity.getApplicationContext();
        db = new DatabaseHelper(context, DatabaseHelper.class).build()
    }
    @Test
    public void test(){
        db.addUser(new User("hello", "123456", "hello", "goodbye", "hello@utdallas.edu", 0));
        assertEquals(true, db.validCredentials("hello", "123456"));
    }
}

