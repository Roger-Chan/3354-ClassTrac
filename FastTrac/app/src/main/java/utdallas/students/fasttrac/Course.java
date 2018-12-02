package utdallas.students.fasttrac;

public class Course {
    String id = null;
    String name = null;
    String code = null;
    int hour = 0;
    int minute = 0;
    String instructor = null;
    Student students = null;
    int available = 0;

    public Course(){
        setId(null);
        setName(null);
        setCode(null);
        setTime(0,0);
        setInstructor(null);
    }

    public Course(String id, String name, String code, int hour, int minute, String instructor){
        setId(id);
        setName(name);
        setCode(code);
        setTime(hour,minute);
        setInstructor(instructor);
    }

    public String getId(){return id;}
    public String getName(){return name;}
    public String getCode(){return code;}
    public int getHour() {return hour;}
    public int getMinute() {return minute;}
    public String getInstructor(){return instructor;}

    public void setId(String id){this.id = id;}
    public void setName(String name){this.name = name;}
    public void setCode(String code){this.code = code;}
    public void setTime(int hour, int minute){this.hour = hour; this.minute = minute;}
    public void setInstructor(String instructor){this.instructor = instructor;}
    public void setAvailableOn(){available = 1;}
    public void setAvailableOff(){available = 0;}
    public boolean isAvailable(){return (available == 1);}

    public void clone(Course course){
        setId(course.getId());
        setName(course.getName());
        setCode(course.getCode());
        setTime(course.getHour(), course.getMinute());
        setInstructor(course.getInstructor());
    }
}
