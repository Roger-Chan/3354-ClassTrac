package utdallas.students.fasttrac;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.Toast;

public class RegisterActivity extends AppCompatActivity {

    DatabaseHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        db = DatabaseHelper.getInstance(this);

        final EditText etEmail = (EditText) findViewById(R.id.etEmail);
        final EditText etFirstname = (EditText) findViewById(R.id.etFirstname);
        final EditText etLastname = (EditText) findViewById(R.id.etLastname);
        final EditText etPassword = (EditText) findViewById(R.id.etPassword);
        final EditText etUsername = (EditText) findViewById(R.id.etUsername);
        final Switch swVerification = (Switch) findViewById(R.id.swverification);
        final Button bRegister = (Button) findViewById(R.id.bRegister);

        bRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = etUsername.getText().toString();
                String email = etEmail.getText().toString();
                String password = etPassword.getText().toString();
                String firstname = etFirstname.getText().toString();
                String lastname = etLastname.getText().toString();
                boolean isProfessor = swVerification.isChecked();
                Intent homeactivity;
                if (isProfessor)
                {
                    db.addUser(new User(username, password, firstname, lastname, email, 1));
                    Toast.makeText(RegisterActivity.this, "Successfully added professor " + username + "!", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    db.addUser(new User(username, password, firstname, lastname, email, 0));
                    Toast.makeText(RegisterActivity.this, "Successfully added student " + username + "!", Toast.LENGTH_SHORT).show();
                }
                homeactivity = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(homeactivity);
            }
        });

    }

}
