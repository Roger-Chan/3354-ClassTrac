package utdallas.students.fasttrac;
import android.app.Activity;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import android.support.test.rule.ActivityTestRule;
import android.widget.EditText;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import android.support.test.InstrumentationRegistry;

import static org.junit.Assert.*;

public class LoginActivityTest {

    Context context = InstrumentationRegistry.getTargetContext().getApplicationContext();
    DatabaseHelper db;
    SQLiteDatabase data;
    EditText username_field = null;
    EditText password_field = null;
    @Rule
    public ActivityTestRule<LoginActivity> lActivity =
            new ActivityTestRule(LoginActivity.class);

    @Before
    public void setUp() throws Exception {
        password_field = lActivity.getActivity().pass_wrd;
        username_field = lActivity.getActivity().user_name;
    }

    @Test
    public void usernameTest(){
        username_field.setText("username");
        assertEquals("username", username_field.getText().toString());
    }

    @Test
    public void passwordTest(){
        password_field.setText("123friends");
        assertEquals("123friends",password_field.getText().toString());
    }
}

