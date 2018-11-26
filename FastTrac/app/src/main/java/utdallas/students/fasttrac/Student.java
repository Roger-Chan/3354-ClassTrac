package utdallas.students.fasttrac;

import java.io.Serializable;

public class Student extends User implements Serializable {
    //DatabaseHelper db;

    Student(String user_name, String passwrd, String first_name, String last_name, String email){
        super(user_name,passwrd,first_name,last_name,email);
        setAuthorization(STUDENT);
    }
}
