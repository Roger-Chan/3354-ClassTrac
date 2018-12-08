package utdallas.students.fasttrac;

import org.junit.Test;

import static org.junit.Assert.*;

public class UserTest {

    @Test

    public void AuthorizationStudent(){

        User tUser = new User("name","password","firstname","lastname","name@email.com",0);

        int result = tUser.getAuthorization();
        assertEquals("student",0,result);
    }


}