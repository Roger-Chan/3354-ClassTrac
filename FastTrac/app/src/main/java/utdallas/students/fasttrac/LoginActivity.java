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

        db = DatabaseHelper.getInstance(this);

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
                User user = db.validCredentials(un,pwd); // initially error code
                Intent secondActivity;

                //student is 0, professor =1
                if(user.authorization == 0) {
                    secondActivity = new Intent(getApplicationContext(), StudentPage.class);
                    secondActivity.putExtra("Student",user);
                    startActivity(secondActivity);
                }   else if(user.authorization == 1){
                    secondActivity = new Intent(getApplicationContext(), ProfessorPage.class);
                    secondActivity.putExtra("Professor", user);
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