package utdallas.students.fasttrac;

import android.app.Instrumentation;
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
    Context context =
    EditText ed;
    @Before
    public void setUp() throws Exception {
        uLoginAcvity = new LoginActivity();

    }

    @Test
    public void test(){
        ed.setText("username");
        assertEquals("username", ed.getText());
    }
}

