package utdallas.students.fasttrac;

public class Course {
    String id = null;
    String name = null;
    String code = null;
    String time = null;
    String instructor = null;
    Student students = null;

    public Course(){
        setId(null);
        setName(null);
        setCode(null);
        setTime(null);
        setInstructor(null);
    }

    public Course(String id, String name, String code, String time, String instructor){
        setId(id);
        setName(name);
        setCode(code);
        setTime(time);
        setInstructor(instructor);
    }

    public String getId(){return id;}
    public String getName(){return name;}
    public String getCode(){return code;}
    public String getTime(){return time;}
    public String getInstructor(){return instructor;}

    public void setId(String id){this.id = id;}
    public void setName(String name){this.name = name;}
    public void setCode(String code){this.code = code;}
    public void setTime(String time){this.time = time;}
    public void setInstructor(String instructor){this.instructor = instructor;}

    public void clone(Course course){
        setId(course.getId());
        setName(course.getName());
        setCode(course.getCode());
        setTime(course.getTime());
        setInstructor(course.getInstructor());
    }
}
