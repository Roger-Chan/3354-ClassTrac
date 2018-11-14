package utdallas.students.fasttrac;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

//import org.w3c.dom.Text;

public class LoginActivity extends AppCompatActivity {
    DatabaseHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        db = new DatabaseHelper(this);

        Button bLoginbtn = (Button)findViewById(R.id.bLogin);
        final EditText user_name = (EditText)findViewById(R.id.etUsername);
        final EditText pass_wrd = (EditText)findViewById(R.id.etPassword);
        final TextView invalid = (TextView) findViewById(R.id.invalid_login);
        final TextView tvRegister = (TextView) findViewById(R.id.tvRegister);

        bLoginbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // get the strings from the field
                String un = user_name.getText().toString();
                String pwd = pass_wrd.getText().toString();
                int autho = -1; // initially error code

                System.out.println(un + " " + pwd);
                if((autho = db.validCredentials(un, pwd)) == 0 || autho == 1){
                    Intent secondActivity;
                    if(autho == 0)  // go to student page
                        secondActivity = new Intent(getApplicationContext(), StudentPage.class);
                    else
                        secondActivity = new Intent(getApplicationContext(), ProfessorPage.class);
                    startActivity(secondActivity);
                }   else{
                    invalid.setVisibility(View.VISIBLE);
                }
            }
        });

        tvRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent registerIntent = new Intent(LoginActivity.this, RegisterActivity.class);
                LoginActivity.this.startActivity(registerIntent);

            }
        });
    }
}