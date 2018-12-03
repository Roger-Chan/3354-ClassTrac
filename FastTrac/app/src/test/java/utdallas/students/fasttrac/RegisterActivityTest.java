package utdallas.students.fasttrac;

import android.view.View;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class RegisterActivityTest {

    RegisterActivity uRegisterActivity;

    @Before
    public void setUp() throws Exception {

        uRegisterActivity = new RegisterActivity();

    }

    @Test

    public void test(){

        View result = uRegisterActivity.findViewById(R.id.swverification);

        assertEquals("Professor", true, result);
    }
}