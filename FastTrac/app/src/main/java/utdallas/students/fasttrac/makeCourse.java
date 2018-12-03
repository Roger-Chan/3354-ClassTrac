package utdallas.students.fasttrac;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TimePicker;

public class makeCourse extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_make_course);

        // make a course database instance
        CoursesDatabase cb = CoursesDatabase.getInstance(this);
        DatabaseHelper db = DatabaseHelper.getInstance(this);

        // get the professors info
        User professor = (User) getIntent().getSerializableExtra("Professor");

        // make objects of the EditTexts
        EditText edit_code = (EditText) findViewById(R.id.code_edit);
        EditText edit_name = (EditText) findViewById(R.id.name_edit);
        EditText edit_id = (EditText) findViewById(R.id.id_edit);

        // get the button and time clock objects
        Button submit_btn = (Button) findViewById(R.id.submit_btn);
        TimePicker clock = (TimePicker) findViewById(R.id.time_clock);

        submit_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // add the course to our database
                String id = edit_id.getText().toString();
                String name = edit_name.getText().toString();
                String code = edit_code.getText().toString();
                int hour = clock.getCurrentHour();
                int minute = clock.getCurrentMinute();
                Course newCourse = new Course(id,name,code,hour,minute,professor.getFirst_name() + " " + professor.getLast_name());

                // add the new course to the courses database so the students can see it
                cb.addCourse(newCourse);

                // adds corresponding attendance sheet for course
                cb.newClassTable(newCourse);

                // add the course to the users database so that it can be associated with the professor
                db.addCourse(professor.getUsername(), professor.getPasswrd(), newCourse);


                // go bake to the professor page
                Intent goBack = new Intent(getApplicationContext(), ProfessorPage.class);
                goBack.putExtra("Professor", professor);
                startActivity(goBack);

            }
        });
    }
}
