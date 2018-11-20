package utdallas.students.fasttrac;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class ProfessorPage extends AppCompatActivity {

    ListView Listview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_professor_page);


        Listview = (ListView)findViewById(R.id.mylistview);


        String[] my_class_list = new String []{ "Class 1","Class 2","Class 4","Class 5"};


        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,
                android.R.id.text1,my_class_list);

        Listview.setAdapter(adapter);
    }
}
