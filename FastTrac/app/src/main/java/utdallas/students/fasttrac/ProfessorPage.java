package utdallas.students.fasttrac;

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

        Button add_class_btn = (Button) findViewById(R.id.addCourse_btn);
        Button edit_course_btn = (Button) findViewById(R.id.edit_course_btn);
        ListView list = (ListView)findViewById(R.id.mylistview);
        TextView course_prompt = (TextView) findViewById(R.id.course_prompt);
        Professor professor = (Professor) getIntent().getSerializableExtra("Professor");

        // set the prompt
        String prompt = course_prompt.getText().toString();
        course_prompt.setText(professor.getFirst_name() + " " + prompt);

        //defind array values to show into Listview
        ArrayList<String> my_class_list = new ArrayList<>();
        for(int i = 0; i < professor.getCourse(). size(); i++){
            my_class_list.add(professor.getCourse().get(i).name);
        }

        //Define an Adapter
        final ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, my_class_list);

        //Define parameters for the Adapter, a)Context , b) layout for the rows of list
        // c) ID for TextView to which data is written, d) array of data
        list.setAdapter(adapter);

        /*
        Listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Toast.makeText(getApplicationContext(),my_class_list[position] + " was clicked", Toast.LENGTH_SHORT).show();


            }
        });

        Listview.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                Toast.makeText( getApplicationContext(),my_class_list[position] +"was selected", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {



            }
        });
        */
    }
}
