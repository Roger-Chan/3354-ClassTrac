package utdallas.students.fasttrac;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class ProfessorPage extends AppCompatActivity {

    ListView Listview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_professor_page);

        DatabaseHelper db = DatabaseHelper.getInstance(this);
        CoursesDatabase cd = CoursesDatabase.getInstance(this);

        Button add_course_btn = (Button) findViewById(R.id.make_course_btn);
        Button edit_course_btn = (Button) findViewById(R.id.edit_course_btn);
        ListView list = (ListView)findViewById(R.id.mylistview);
        TextView course_prompt = (TextView) findViewById(R.id.course_prompt);
        Professor professor = (Professor) getIntent().getSerializableExtra("Professor");

        // set the prompt
        String prompt = course_prompt.getText().toString();
        course_prompt.setText(professor.getFirst_name() + " " + prompt);

        //defind array values to show into Listview
        //ArrayList<String> my_class_list = new ArrayList<>();
        ArrayList<String> my_class_list = new ArrayList<>(cd.getProfessorCourses(professor.getFirst_name(), professor.getLast_name()));

        //Define an Adapter
        final ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, my_class_list);

        //Define parameters for the Adapter, a)Context , b) layout for the rows of list
        // c) ID for TextView to which data is written, d) array of data
        list.setAdapter(adapter);

        //testing
        Course samplecourse = cd.findCourse("12345");
        cd.newClassTable(samplecourse);
        cd.addSession(samplecourse);


        add_course_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // go to the add a class activity
                Intent CreateCourse = new Intent(getApplicationContext(), makeCourse.class);
                CreateCourse.putExtra("Professor", professor);
                startActivity(CreateCourse);
            }
        });
    }
}
