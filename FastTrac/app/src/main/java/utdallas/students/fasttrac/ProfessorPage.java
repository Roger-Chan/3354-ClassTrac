package utdallas.students.fasttrac;

import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.Serializable;
import java.util.ArrayList;

public class ProfessorPage extends AppCompatActivity{

    ListView Listview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_professor_page);

        DatabaseHelper db = DatabaseHelper.getInstance(this);
        CoursesDatabase cd = CoursesDatabase.getInstance(this);

        Button add_course_btn = (Button) findViewById(R.id.make_course_btn);
        Button edit_course_btn = (Button) findViewById(R.id.edit_course_btn);
        Button logout_btn = (Button) findViewById(R.id.logout_btn);
        ListView list = (ListView)findViewById(R.id.mylistview);
        TextView course_prompt = (TextView) findViewById(R.id.course_prompt);
        User professor = (User) getIntent().getSerializableExtra("Professor");

        // set the prompt
        String prompt = course_prompt.getText().toString();
        course_prompt.setText(professor.getFirst_name() + " " + prompt);

        //define array values to show into list view
        //ArrayList<String> my_class_list = new ArrayList<>();
        ArrayList<String> my_class_list = new ArrayList<>(cd.getProfessorCourses(professor.getFirst_name(), professor.getLast_name()));
        ArrayList<String> my_codes_list = new ArrayList<>(cd.getProfessorCoursesCodes(professor.getFirst_name(), professor.getLast_name()));

        //Define an Adapter
        final ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, my_class_list);

        //Define parameters for the Adapter, a)Context , b) layout for the rows of list
        // c) ID for TextView to which data is written, d) array of data
        list.setAdapter(adapter);

        // make an alert dialog builder
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(ProfessorPage.this);
        alertDialog.setMessage("You have too many classes to another.");

        //testing
        /*
        Course samplecourse = cd.findCourse("12345");
        cd.newClassTable(samplecourse);
        cd.addSession(samplecourse);
        User samplestudent = db.validCredentials("username", "password");
        samplecourse.setAvailableOn();
        cd.attendStudentInCourse(samplestudent, samplecourse);
        */
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Course clickedcourse = cd.findCourse(my_codes_list.get(position));
                if(!add_course_btn.isEnabled()){
                    // get the course contents and go to the make course page
                    Intent CreateCourse = new Intent(getApplicationContext(), makeCourse.class);
                    CreateCourse.putExtra("Professor", professor);
                    CreateCourse.putExtra("clickedCourse", clickedcourse);
                    startActivity(CreateCourse);

                }   else {
                    if (clickedcourse.isAvailable()) {
                        //sets availability to 0
                        cd.toggleClass(my_codes_list.get(position), 0);
                        Course ncourse = cd.findCourse(my_codes_list.get(position));
                        boolean ncoursec = ncourse.isAvailable();
                        int j;
                        if (ncoursec) {
                            j = 1;
                        } else {
                            j = 0;
                        }
                        Toast.makeText(ProfessorPage.this, "class is closed availability is now " + j, Toast.LENGTH_SHORT).show();
                    } else {
                        //updates to current time
                        clickedcourse.setLatestTimeAuto();
                        cd.updateTime(my_codes_list.get(position), clickedcourse.getLatestTime());

                        //creates new session
                        cd.addSession(clickedcourse);

                        //sets availability to 1
                        cd.toggleClass(my_codes_list.get(position), 1);
                        Course ncourse = cd.findCourse(my_codes_list.get(position));
                        boolean ncoursec = ncourse.isAvailable();
                        int j;
                        if (ncoursec) {
                            j = 1;
                        } else {
                            j = 0;
                        }
                        Toast.makeText(ProfessorPage.this, "class is open availability is now " + j, Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        add_course_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // go to the add a class activity if the professor doesnt already have 5 classes
                if(my_class_list.size() == 5){
                    alertDialog.show();
                }   else {
                    Intent CreateCourse = new Intent(getApplicationContext(), makeCourse.class);
                    CreateCourse.putExtra("Professor", professor);
                    Course faker = null;
                    CreateCourse.putExtra("clickedCourse", faker);
                    startActivity(CreateCourse);
                }
            }
        });

        edit_course_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // see if we already clicked the button
                if(edit_course_btn.getText().toString().contains("EDIT")){
                    // change the text to done
                    edit_course_btn.setText("DONE");
                    // disable the add course button
                    add_course_btn.setEnabled(false);
                    logout_btn.setEnabled(false);
                }   else{
                    // change the text back and allow the user to add a course again
                    edit_course_btn.setText("EDIT COURSE");
                    add_course_btn.setEnabled(true);
                    logout_btn.setEnabled(true);
                }

            }
        });

        logout_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // go to the login page
                Intent LoginPage = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(LoginPage);
            }
        });
    }
}
