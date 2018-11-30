package utdallas.students.fasttrac;

import android.provider.ContactsContract;

import java.io.Serializable;
import java.util.ArrayList;

public class User implements Serializable {

    static int STUDENT = 0;
    static int PROFESSOR = 1;
    static int ERROR = -1;
    static int SUCCESS = 1;

    String user_name = null;
    String passwrd = null;
    String first_name = null;
    String last_name = null;
    String email = null;
    int authorization = 0;

    // make an array list of courses
    //ArrayList<Course> courses = new ArrayList<Course>(5);

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
    /*
    public int addCourse(Course course){
        // check if we are already in the course
        if(equals(course)){
            return ERROR;
        }   else{
            getCourse().add(course);
            return SUCCESS;
        }
    }
    */

    /*
    public boolean equals(Course course){
        for(int i = 0; i < getCourse().size(); i++){
            // grab the course in the array
            Course temp = getCourse().get(i);
            if (temp.getCode().equals(course.getCode())){
                return true;
            }
        }
        return false;
    }
    */

    public String getUsername(){return user_name;}
    public String getPasswrd() {return passwrd;}
    public String getFirst_name() {return first_name;}
    public String getLast_name() {return last_name;}
    public String getEmail() {return email;}
    public int getAuthorization() {return authorization;}
    //public ArrayList<Course> getCourse() {return courses;}
}
