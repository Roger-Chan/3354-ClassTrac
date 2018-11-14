package utdallas.students.fasttrac;

public class User {
    int STUDENT = 0;
    int PROFESSOR = 1;

    String user_name = null;
    String passwrd = null;
    String first_name = null;
    String last_name = null;
    String email = null;
    int authorization;

    String courses = null;

    User(String user_name, String passwrd, String first_name, String last_name, String email){
        this.user_name = user_name;
        this.passwrd = passwrd;
        this.first_name = first_name;
        this.last_name = last_name;
        this.email = email;
    }

    public void setUsername(String user_name){this.user_name = user_name;}
    public void setPasswrd(String passwrd) {this.passwrd = passwrd;}
    public void setFirst_name(String first_name) {this.first_name = first_name;}
    public void setLast_name(String last_name) {this.last_name = last_name;}
    public void setEmail(String email) {this.email = email;}
    public void setAuthorization(int authorization){this.authorization = authorization;}

    public String getUsername(){return user_name;}
    public String getPasswrd() {return passwrd;}
    public String getFirst_name() {return first_name;}
    public String getLast_name() {return last_name;}
    public String getEmail() {return email;}
    public int getAuthorization() {return authorization;}
}
