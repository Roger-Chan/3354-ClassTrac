package utdallas.students.fasttrac;

public class Student extends User{
    //DatabaseHelper db;

    Student(String user_name, String passwrd, String first_name, String last_name, String email){
        super(user_name,passwrd,first_name,last_name,email);
        setAuthorization(STUDENT);
        //db.addUser(this);
    }
}
