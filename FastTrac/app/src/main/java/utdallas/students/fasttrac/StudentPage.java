package utdallas.students.fasttrac;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.KeyListener;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.io.Serializable;

public class StudentPage extends AppCompatActivity implements Serializable {


    ListView Listview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_page);

        // get the info of the student who is accessing this page
        Student student = (Student) getIntent().getSerializableExtra("Student");

        Button takeAttendencebtn = (Button) findViewById(R.id.Take_Attendence_btn);
        Button editInfobtn = (Button) findViewById(R.id.edit_info_btn);
        TextView UserText = (TextView) findViewById(R.id.Username_field);
        TextView FirstText = (TextView) findViewById(R.id.First_name_field);
        TextView LastText = (TextView) findViewById(R.id.Last_name_field);
        TextView EmailText = (TextView) findViewById(R.id.email_field);

        // set the info to the correct stuff
        UserText.setText(student.getUsername());
        FirstText.setText(student.getFirst_name());
        LastText.setText(student.getLast_name());
        EmailText.setText(student.getEmail());

        // make the textfields not editable but first get the tags to allow for edit later
        UserText.setTag(UserText.getKeyListener());
        FirstText.setTag(FirstText.getKeyListener());
        LastText.setTag(LastText.getKeyListener());
        EmailText.setTag(EmailText.getKeyListener());
        UserText.setKeyListener(null);
        FirstText.setKeyListener(null);
        LastText.setKeyListener(null);
        EmailText.setKeyListener(null);

        // when the attendence button is clicked
        takeAttendencebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               Intent attendenceActivity = new Intent(getApplicationContext(), TakeAttendence.class);
               attendenceActivity.putExtra("Student", student);
               startActivity(attendenceActivity);
            }
        });

        // when the edit info button is clicked
        editInfobtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // check if button was clicked to change info
                if(UserText.getKeyListener() == null){
                    // dissable the take attendence button
                    takeAttendencebtn.setEnabled(false);

                    // change the text of the button to say update
                    editInfobtn.setText("UPDATE");

                    // set all the textviews to editable
                    UserText.setKeyListener((KeyListener) UserText.getTag());
                    FirstText.setKeyListener((KeyListener) FirstText.getTag());
                    LastText.setKeyListener((KeyListener) LastText.getTag());
                    EmailText.setKeyListener((KeyListener) EmailText.getTag());
                }
                else{
                    // enable the take attendence button
                    takeAttendencebtn.setEnabled(true);

                    // change text of edit info button back to edit info
                    editInfobtn.setText("EDIT INFO");

                    // make the textfields not editable
                    UserText.setKeyListener(null);
                    FirstText.setKeyListener(null);
                    LastText.setKeyListener(null);
                    EmailText.setKeyListener(null);

                    //update the students status

                }
            }
        });
    }
}
