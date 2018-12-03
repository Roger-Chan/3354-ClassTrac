package utdallas.students.fasttrac;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.KeyListener;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


import java.io.Serializable;

public class StudentPage extends AppCompatActivity implements Serializable {
    DatabaseHelper db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_page);

        db = DatabaseHelper.getInstance(this);

        // get the info of the student who is accessing this page
        User student = (User) getIntent().getSerializableExtra("Student");

        Button takeAttendancebtn = (Button) findViewById(R.id.Take_Attendence_btn);
        Button editInfobtn = (Button) findViewById(R.id.edit_info_btn);
        TextView UserText = (TextView) findViewById(R.id.Username_field);
        TextView FirstText = (TextView) findViewById(R.id.First_name_field);
        TextView LastText = (TextView) findViewById(R.id.Last_name_field);
        TextView EmailText = (TextView) findViewById(R.id.email_field);
        TextView PasswordText = (TextView) findViewById(R.id.password_field);

        // set the info to the correct stuff
        UserText.setText(student.getUsername());
        FirstText.setText(student.getFirst_name());
        LastText.setText(student.getLast_name());
        EmailText.setText(student.getEmail());
        PasswordText.setText(student.getPasswrd());

        // make the text fields not editable but first get the tags to allow for edit later
        UserText.setTag(UserText.getKeyListener());
        FirstText.setTag(FirstText.getKeyListener());
        LastText.setTag(LastText.getKeyListener());
        EmailText.setTag(EmailText.getKeyListener());
        PasswordText.setTag(PasswordText.getKeyListener());
        UserText.setKeyListener(null);
        FirstText.setKeyListener(null);
        LastText.setKeyListener(null);
        EmailText.setKeyListener(null);
        PasswordText.setKeyListener(null);

        // when the attendance button is clicked
        takeAttendancebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               Intent attendanceActivity = new Intent(getApplicationContext(), TakeAttendence.class);
               attendanceActivity.putExtra("Student", student);
               startActivity(attendanceActivity);
            }
        });

        // when the edit info button is clicked
        editInfobtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                // check if button was clicked to change info
                if(UserText.getKeyListener() == null){
                    // disable the take attendance button
                    takeAttendancebtn.setEnabled(false);

                    // change the text of the button to say update
                    editInfobtn.setText("UPDATE");

                    // set all the text views to editable
                    UserText.setKeyListener((KeyListener) UserText.getTag());
                    FirstText.setKeyListener((KeyListener) FirstText.getTag());
                    LastText.setKeyListener((KeyListener) LastText.getTag());
                    EmailText.setKeyListener((KeyListener) EmailText.getTag());
                    PasswordText.setKeyListener((KeyListener) PasswordText.getTag());
                }   else{
                    // enable the take attendance button
                    takeAttendancebtn.setEnabled(true);

                    // change text of edit info button back to edit info
                    editInfobtn.setText("EDIT INFO");

                    // make the text fields not editable
                    UserText.setKeyListener(null);
                    FirstText.setKeyListener(null);
                    LastText.setKeyListener(null);
                    EmailText.setKeyListener(null);
                    PasswordText.setKeyListener(null);

                    //update the students status and database information
                    String UserUp = UserText.getText().toString();
                    String FirstUp = FirstText.getText().toString();
                    String LastUp = LastText.getText().toString();
                    String EmailUp = EmailText.getText().toString();
                    String PasswordUp = PasswordText.getText().toString();
                    boolean updated = db.updateData(student.getUsername(), UserUp, PasswordUp, FirstUp, LastUp, EmailUp);
                    if (updated) {
                        Toast.makeText(StudentPage.this, "Successfully Updated", Toast.LENGTH_SHORT).show();
                    }
                    else {
                        Toast.makeText(StudentPage.this, "Username already taken", Toast.LENGTH_SHORT).show();
                        UserText.setText(student.getUsername());
                    }

                }
            }
        });
    }
}
