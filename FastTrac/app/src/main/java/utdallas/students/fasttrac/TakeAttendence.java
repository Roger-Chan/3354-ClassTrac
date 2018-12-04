package utdallas.students.fasttrac;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class TakeAttendence extends AppCompatActivity {
    CoursesDatabase cd;
    DatabaseHelper db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        // make the course database
        cd = CoursesDatabase.getInstance(this);
        db = DatabaseHelper.getInstance(this);

        // get the student object
        User student = (User) getIntent().getSerializableExtra("Student");

        // add a fake course for the student to test
        Course fake = cd.findCourse("13346");
        db.addCourse(student.getUsername(), student.getPasswrd(), fake);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_take_attendence);

        Button addCoursebtn = (Button) findViewById(R.id.addCourse_btn);
        TextView addPrompt = (TextView) findViewById(R.id.promt_code);
        TextView codeArea = (TextView) findViewById(R.id.code_enter);
        TextView invalid_input = (TextView) findViewById(R.id.attendence_invalid);


        //defind array values to show into Listview
        ArrayList<String> my_class_list = new ArrayList<>();
        ArrayList<String> students_current_codes = new ArrayList<>(db.getCodes(student.getUsername(), student.getPasswrd()));

        // 5 is the max number of classes the student can have
        for(int i = 0; i < 5; i++){
            // add the courses they are already in to our arraylist
            if(!students_current_codes.get(i).contains("NULL")) {
                // ADD TO THE LIST
                my_class_list.add(cd.findCourse(students_current_codes.get(i)).getName());
            }
        }

        //Define an Adapter
        final ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, my_class_list);

        // Create a Listview object
        ListView list = (ListView) findViewById(R.id.view2);

        //Define parameters for the Adapter, a)Context , b) layout for the rows of list
        // c) ID for TextView to which data is written, d) array of data
        list.setAdapter(adapter);

        addCoursebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // check to see if the prompt is already visable
                if (codeArea.getVisibility() == View.INVISIBLE){
                    // prompt the user to enter the class code in a textfield
                    addPrompt.setVisibility(View.VISIBLE);
                    codeArea.setVisibility(View.VISIBLE);
                }   else{
                    // get the code from the textview and see in it matches any in our database
                    String code = codeArea.getText().toString();

                    if (code.isEmpty()){
                        //tell the user that there was invalid input
                        invalid_input.setVisibility(View.VISIBLE);
                    }   else {
                        boolean weird_char = false;

                        // check to see if there are any weird characters
                        for(int i = 0; i < code.length(); i++){
                            if((code.charAt(i) >= 65 && code.charAt(i) <= 90) || (code.charAt(i) >= 48 && code.charAt(i) <= 57)){
                                // dont change weird_char
                            }   else{
                                weird_char = true;
                            }
                        }

                        // if we have a weird character, then don't check the database
                        if(weird_char){
                            // clock it and then if they do it three times, report them to the system
                            invalid_input.setVisibility(View.VISIBLE);
                        }   else{
                            Course course = cd.findCourse(code);
                            if (course == null){
                                //tell the user that there was invalid input
                                invalid_input.setVisibility(View.VISIBLE);
                            }   else{
                                // add the user to the course if we can
                                Boolean status = db.addCourse(student.getUsername(), student.getPasswrd(), course);
                                if(!status){
                                    //tell the user that there was invalid input
                                    invalid_input.setVisibility(View.VISIBLE);
                                }   else{
                                    // remove the invalid input textfield if its showing
                                    invalid_input.setVisibility(View.INVISIBLE);
                                    // update the listview
                                    adapter.add(course.name);
                                }
                            }
                        }
                    }
                }
            }
        });


    }
}
