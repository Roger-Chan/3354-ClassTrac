package utdallas.students.fasttrac;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
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
        Course currentCourse = (Course) getIntent().getSerializableExtra("clickedCourse");

        // make objects of the EditTexts
        EditText edit_code = (EditText) findViewById(R.id.code_edit);
        EditText edit_name = (EditText) findViewById(R.id.name_edit);
        EditText edit_id = (EditText) findViewById(R.id.id_edit);
        TextView code_error = (TextView) findViewById(R.id.codeError);
        TextView invalid_text = (TextView) findViewById(R.id.invalid_code);

        // get the button and time clock objects
        Button submit_btn = (Button) findViewById(R.id.submit_btn);
        Button cancel_btn = (Button) findViewById(R.id.cancel_button);
        ImageView delete_btn = (ImageView) findViewById(R.id.delete_button);

        TimePicker clock = (TimePicker) findViewById(R.id.time_clock);


        if(currentCourse != null){
            // change the add button to the delete button
            submit_btn.setText("SUBMIT CHANGES");
            delete_btn.setVisibility(View.VISIBLE);

            // set the textfields with the coresponding info
            edit_code.setText(currentCourse.getCode());
            edit_id.setText(currentCourse.getId());
            edit_name.setText(currentCourse.getName());

            // set the time picker
            clock.setCurrentHour(currentCourse.getHour());
            clock.setCurrentMinute(currentCourse.getMinute());
        }

        submit_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(delete_btn.getVisibility() == View.VISIBLE){
                    // update the course database


                    // go back to the professor page
                    goBack(professor);
                }   else {
                    // add the course to our database
                    String id = edit_id.getText().toString();
                    String name = edit_name.getText().toString();
                    String code = edit_code.getText().toString();
                    int hour = clock.getCurrentHour();
                    int minute = clock.getCurrentMinute();
                    Course newCourse = new Course(id, name, code, hour, minute, professor.getFirst_name() + " " + professor.getLast_name(), 0, "");

                    // add the new course to the courses database so the students can see it
                    if (!code.matches("[0-9]+")) {
                        invalid_text.setVisibility(View.VISIBLE);
                    } else if (cb.addCourse(newCourse) != false) {
                        invalid_text.setVisibility(View.INVISIBLE);
                        code_error.setVisibility(View.INVISIBLE);

                        // adds corresponding attendance sheet for course
                        cb.newClassTable(newCourse);
                        // add the course to the users database so that it can be associated with the professor
                        db.addCourse(professor.getUsername(), professor.getPasswrd(), newCourse);

                        goBack(professor);
                    } else {
                        invalid_text.setVisibility(View.INVISIBLE);
                        code_error.setVisibility(View.VISIBLE);
                    }
                }
            }
        });

        cancel_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goBack(professor);
            }
        });

        delete_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // ask if the user truly wants to delete the course
                // make an alert dialog builder
                AlertDialog.Builder alertDialog = new AlertDialog.Builder(makeCourse.this);
                alertDialog.setMessage("Are you sure you wish to delete this course?");
                alertDialog.setPositiveButton("Yes", new DialogInterface.OnClickListener(){
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // delete the course from the teachers database
                        cb.deleteCourse(currentCourse.getCode());
                        db.profDeleteCourse(currentCourse.getCode());
                        goBack(professor);
                    }
                });
                alertDialog.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // DO NOTHING
                    }
                });
                alertDialog.show();
            }
        });
    }

    public void goBack(User professor){
        // go bake to the professor page
        Intent goBack = new Intent(getApplicationContext(), ProfessorPage.class);
        goBack.putExtra("Professor", professor);
        startActivity(goBack);
    }
}
