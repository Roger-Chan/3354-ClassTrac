package utdallas.students.fasttrac;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.junit.Before;
import org.junit.Test;
import org.robolectric.internal.bytecode.InstrumentationConfiguration;
import android.support.test.InstrumentationRegistry;

import static org.junit.Assert.*;

public class LoginActivityTest {

    LoginActivity uLoginAcvity;
    Context context = InstrumentationRegistry.getTargetContext();

    DatabaseHelper db;
    SQLiteDatabase data;
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

