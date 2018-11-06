package com.example.hicks.attendence_sheet;

public class Student {
    String user_name = null;
    String passwrd = null;
    String first_name = null;
    String last_name = null;

    Student(){
    }

    Student(String user_name, String passwrd, String first_name, String last_name){
        this.user_name = user_name;
        this.passwrd = passwrd;
        this.first_name = first_name;
        this.last_name = last_name;
    }

    public void setUsername(String user_name){this.user_name = user_name;}
    public void setPasswrd(String passwrd) {this.passwrd = passwrd;}
    public void setFirst_name(String first_name) {this.first_name = first_name;}
    public void setLast_name(String last_name) {this.last_name = last_name;}


    public String getUsername(){return user_name;}
    public String getPasswrd() {return passwrd;}
    public String getFirst_name() {return first_name;}
    public String getLast_name() {return last_name;}

}
