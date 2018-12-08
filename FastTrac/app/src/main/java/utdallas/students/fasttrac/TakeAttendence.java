package utdallas.students.fasttrac;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class TakeAttendence extends AppCompatActivity implements AddClassDialog.AddClassListener {
    CoursesDatabase cd;
    DatabaseHelper db;
    String code = "";
    Course course = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        // make the course database
        cd = CoursesDatabase.getInstance(this);
        db = DatabaseHelper.getInstance(this);

        // get the student object
        User student = (User) getIntent().getSerializableExtra("Student");

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_take_attendence);

        Button addCoursebtn = (Button) findViewById(R.id.addCourse_btn);
        Button submitCoursebtn = (Button) findViewById(R.id.submitcourse_btn);
        TextView addPrompt = (TextView) findViewById(R.id.promt_code);
        TextView codeArea = (TextView) findViewById(R.id.code_enter);
        TextView invalid_input = (TextView) findViewById(R.id.attendence_invalid);
        EditText input = new EditText(this);


        //define array values to show into Listview
        ArrayList<String> my_class_list = new ArrayList<>();
        ArrayList<String> students_current_codes = new ArrayList<>(db.getCodes(student.getUsername(), student.getPasswrd()));

        // 5 is the max number of classes the student can have
        for(int i = 0; i < 5; i++){
            // add the courses they are already in to our arraylist
            if(!students_current_codes.get(i).contains("NULL") ) {
                // ADD TO THE LIST
                my_class_list.add(cd.findCourse(students_current_codes.get(i)).getName());
            }
        }

        //initial setup
        final ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, my_class_list);
        ListView list = (ListView) findViewById(R.id.view2);
        list.setAdapter(adapter);

        //get courses information for the student
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Course clickedcourse = cd.findCourse(students_current_codes.get(position));
                    if (clickedcourse.isAvailable()) {
                        cd.attendStudentInCourse(student, clickedcourse);
                        Toast.makeText(TakeAttendence.this, "Attended " + clickedcourse.getId(), Toast.LENGTH_SHORT).show();
                    } else {
                        //updates to current time
                        Toast.makeText(TakeAttendence.this, clickedcourse.getId() + " is not open ", Toast.LENGTH_SHORT).show();
                    }

            }
        });


        addCoursebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // check to see if the prompt is already visable

                openDialog();
                Toast.makeText(TakeAttendence.this, "input " +code, Toast.LENGTH_SHORT).show();
                course = cd.findCourse(code);

            }

        });
        submitCoursebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(course != null){
                    // if we have a weird character, then don't check the database
                    if(!IsNumOrUpper(code) || course == null){
                        // clock it and then if they do it three times, report them to the system
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
                            //my_class_list.add(cd.findCourse(code).getName());
                            //students_current_codes.add(cd.findCourse(code).getCode());
                            //list.setAdapter(adapter);
                            finish();
                            startActivity(getIntent());
                        }
                    }
                }
            }
        });
    }
    public void openDialog(){
        AddClassDialog addclassdialog = new AddClassDialog();
        addclassdialog.show(getSupportFragmentManager(), "example dialog");
    }

    public static boolean IsNumOrUpper(String string){
        boolean IsNumOrUpper = true;
        //checks if empty
        if(string.isEmpty())
        {
            IsNumOrUpper = false;
        }
        //checks if code falls outside of numbers or uppercase letters
        for(int i = 0; i < string.length(); i++){
            if (string.charAt(i) > 90
                    || string.charAt(i) < 48
                    || (string.charAt(i) > 58 && string.charAt(i) < 65))
            {
                IsNumOrUpper = false;
            }
        }

        return IsNumOrUpper;
    }

    @Override
    public void applyTexts(String getcode) {
        code = getcode;

    }
}
