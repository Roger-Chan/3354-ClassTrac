package utdallas.students.fasttrac;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import android.support.test.rule.ActivityTestRule;
import android.widget.Button;
import android.widget.EditText;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import android.support.test.InstrumentationRegistry;
import android.widget.TextView;

import static org.junit.Assert.*;

public class UpdateInfoTest {

    Context context = InstrumentationRegistry.getTargetContext().getApplicationContext();
    DatabaseHelper db;
    SQLiteDatabase data;
    TextView user_field = null;
    TextView first_field = null;
    TextView last_field = null;
    TextView email_field = null;
    TextView password_field = null;
    @Rule
    public ActivityTestRule<StudentPage> lActivity =
            new ActivityTestRule(StudentPage.class);

    @Before
    public void setUp() throws Exception {
        user_field = lActivity.getActivity().UserText;
        first_field = lActivity.getActivity().FirstText;
        last_field =  lActivity.getActivity().LastText;
        email_field = lActivity.getActivity().EmailText;
        password_field = lActivity.getActivity().PasswordText;
    }

    // Test if User name is correct
    @Test
    public void usernameTest(){
        user_field.setText("username");
        assertEquals("username", user_field.getText().toString());
    }

    // Test if Password is correct
    @Test
    public void passwordTest(){
        password_field.setText("123friends");
        assertEquals("123friends",password_field.getText().toString());
    }

    // Test if firstname
    @Test
    public void firstTest(){
        first_field.setText("first name");
        assertEquals("first name",first_field.getText().toString());
    }

    // Test last name
    @Test
    public void lastTest(){
        last_field.setText("lastname Succesful");
        assertEquals("lastname Succesful",last_field.getText().toString());
    }

    // Testing for email
    @Test
    public void emailTest(){
        email_field.setText("email Succesful");
        assertEquals("email Succesful",email_field.getText().toString());
    }
}