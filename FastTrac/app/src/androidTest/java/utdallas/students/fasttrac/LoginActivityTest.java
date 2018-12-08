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
    EditText invalid_field = null;
    @Rule
    public ActivityTestRule<LoginActivity> lActivity =
            new ActivityTestRule(LoginActivity.class);

    @Before
    public void setUp() throws Exception {
        password_field = lActivity.getActivity().pass_wrd;
        username_field = lActivity.getActivity().user_name;
        invalid_field = lActivity.getActivity().invalid;
    }

    // Test if User name is correct
    @Test
    public void usernameTest(){
        username_field.setText("username");
        assertEquals("username", username_field.getText().toString());
    }

    // Test if Password is correct
    @Test
    public void passwordTest(){
        password_field.setText("123friends");
        assertEquals("123friends",password_field.getText().toString());
    }

    // Test when user has wrong Username/Password
    @Test
    public void validTest(){
        invalid_field.setText("Wrong Username and/or Password");
        assertEquals("Wrong Username and/or Password",invalid_field.getText().toString());
    }

}

