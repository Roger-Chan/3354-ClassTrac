package utdallas.students.fasttrac;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Course {
    String id = null;
    String name = null;
    String code = null;
    int hour = 0;
    int minute = 0;
    String instructor = null;
    int available = 0;
    String latest_time = null;

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
        setLatestTimeAuto();
    }

    public String getId(){return id;}
    public String getName(){return name;}
    public String getCode(){return code;}
    public int getHour() {return hour;}
    public int getMinute() {return minute;}
    public String getInstructor(){return instructor;}
    public String getLatestTime(){return latest_time;}

    public void setId(String id){this.id = id;}
    public void setName(String name){this.name = name;}
    public void setCode(String code){this.code = code;}
    public void setTime(int hour, int minute){this.hour = hour; this.minute = minute;}
    public void setInstructor(String instructor){this.instructor = instructor;}
    public void setAvailableOn(){available = 1;}
    public void setAvailableOff(){available = 0;}
    public boolean isAvailable(){return (available == 1);}

    public void setLatestTime(String time){
        latest_time = time;
    }
    public void setLatestTimeAuto(){
        DateFormat df = new SimpleDateFormat("dd/MM/yy HH:mm:ss");
        latest_time = df.format(Calendar.getInstance().getTime());
    }

    public void clone(Course course){
        setId(course.getId());
        setName(course.getName());
        setCode(course.getCode());
        setTime(course.getHour(), course.getMinute());
        setInstructor(course.getInstructor());
        setLatestTime(course.getLatestTime());
    }
}
