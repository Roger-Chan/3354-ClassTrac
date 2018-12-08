package utdallas.students.fasttrac;

import android.content.Context;
import android.support.test.InstrumentationRegistry;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class RegisterActivityTest {
    private DatabaseHelper tester;
    Context context = InstrumentationRegistry.getTargetContext();
    User addUser;

    @Before
    public void setup(){
        tester = new DatabaseHelper(context);
    }

    @Test
    public void testAdd(){
        addUser = new User();
        addUser.setUsername("name");
        assertEquals("name",addUser.getUsername());
    }

}