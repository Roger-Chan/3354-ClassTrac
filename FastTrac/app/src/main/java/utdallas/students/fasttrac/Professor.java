package utdallas.students.fasttrac;

public class Professor extends User{
    //DatabaseHelper db;
    Professor(String user_name, String passwrd, String first_name, String last_name, String email){
        super(user_name,passwrd,first_name,last_name,email);
        setAuthorization(PROFESSOR);
        //db.addUser(this);
    }
}
