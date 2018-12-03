package utdallas.students.fasttrac;

import android.view.View;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class LoginActivityTest {


    LoginActivity uLoginAcvity;

    @Before
    public void setUp() throws Exception {
        uLoginAcvity = new LoginActivity();

    }
    @Test
    public void test(){
        View view = uLoginAcvity.findViewById(R.id.bLogin);
        assertEquals("Login succesful", true, view);

    }
}

