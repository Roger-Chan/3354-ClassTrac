package utdallas.students.fasttrac;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class UserTest {

    User tester;


    @Before
    public void setup(){
        tester = new User("name","password","firstname","lastname","name@email.com",0);
    }

    @Test

    public void AuthorizationStudent(){
        //User tUser = new User("name","password","firstname","lastname","name@email.com",0);

        int result = tester.getAuthorization();
        assertEquals("student",0,result);
    }

    @Test

    public void getFirstName(){

        String result = tester.getFirst_name();
        assertEquals("Correct Fist Name","firstname",result);
    }
    @Test

    public void getLastName(){

        String result = tester.getLast_name();
        assertEquals("Correct Last Name","lastname",result);
    }

    @Test
    public void getEmail(){

        String result = tester.getEmail();
        assertEquals("Correct Email","name@email.com",result);
    }



}
