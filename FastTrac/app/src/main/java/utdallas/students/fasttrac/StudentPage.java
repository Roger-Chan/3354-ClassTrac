package utdallas.students.fasttrac;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class StudentPage extends AppCompatActivity {


    ListView Listview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_page);

        // Create a Listview object
        Listview = (ListView)findViewById(R.id.mylistview);

        //defind array values to show into Listview
        String[] my_class_list = new String []{ "Class 1","Class 2","Class 4","Class 5"};

        //Define an Adapter
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,
                android.R.id.text1,my_class_list);
        //Define parameters for the Adapter, a)Context , b) layout for the rows of list
        // c) ID for TextView to which data is written, d) array of data
        Listview.setAdapter(adapter);


    }
}
