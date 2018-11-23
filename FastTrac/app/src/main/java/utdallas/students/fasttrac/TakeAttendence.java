package utdallas.students.fasttrac;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class TakeAttendence extends AppCompatActivity {
    CoursesDatabase cd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        int ERROR = -1;
        int SUCCESS = 1;

        // make the course database
        cd = new CoursesDatabase(this);

        // get the student object
        Student student = (Student) getIntent().getSerializableExtra("Student");

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_take_attendence);

        Button addCoursebtn = (Button) findViewById(R.id.addCourse_btn);
        TextView addPrompt = (TextView) findViewById(R.id.promt_code);
        TextView codeArea = (TextView) findViewById(R.id.code_enter);


        addCoursebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println("You have " + student.getCourse().size() + " courses");
                // check to see if the prompt is already visable
                if (codeArea.getVisibility() == View.INVISIBLE){
                    // prompt the user to enter the class code in a textfield
                    addPrompt.setVisibility(View.VISIBLE);
                    codeArea.setVisibility(View.VISIBLE);
                }   else{
                    // get the code from the textview and see in it matches any in our database
                    String code = codeArea.getText().toString();

                    if (code.isEmpty()){
                        //prompt the user to enter a code

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

                        // if we have a weird character then dont chack the database
                        if(weird_char){
                            // clock it and then if tey do it three times, report them to the system
                        }   else{
                            Course course = cd.findCourse(code);
                            if (course == null){
                                // print out that we coludnt find the code
                            }   else{
                                int status = student.addCourse(course);
                                if(status == ERROR){
                                    // say we couldnt add the user to the class
                                }   else{
                                    // asay we added the user to the class
                                }
                            }
                        }
                    }
                }

            }
        });
        /*
        // Create a Listview object
        //Listview = (ListView)findViewById(R.id.mylistview);

        //defind array values to show into Listview
        //String[] my_class_list = new String []{ "Class 1","Class 2","Class 4","Class 5"};

        //Define an Adapter
        //ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,
        //        android.R.id.text1,my_class_list);
        //Define parameters for the Adapter, a)Context , b) layout for the rows of list
        // c) ID for TextView to which data is written, d) array of data
        //Listview.setAdapter(adapter);

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
