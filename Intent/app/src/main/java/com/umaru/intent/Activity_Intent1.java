package com.umaru.intent;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class Activity_Intent1 extends AppCompatActivity {

    private Button btn1;
    TextView name, surname, age;

    private boolean isNumeric(String str) {
        return str.matches("-?\\d+(\\.\\d+)?");
    }

    private boolean containsNumber(String str) {
        return str.matches(".*\\d.*");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_1);
        btn1 = (Button) findViewById(R.id.button);
        name = (TextView) findViewById(R.id.name);
        surname = (TextView) findViewById(R.id.surname);
        age = (TextView) findViewById(R.id.age);

        View.OnClickListener listener = new View.OnClickListener() {
            public void onClick(View v) {
                Intent customIntent = new Intent(Activity_Intent1.this, Activity_Intent2.class);
                try {
                    if (name.getText().toString().isEmpty() || surname.getText().toString().isEmpty() || age.getText().toString().isEmpty()) {
                        Toast.makeText(Activity_Intent1.this, "Please fill all the fields", Toast.LENGTH_SHORT).show();
                        return;
                    }

                    if (containsNumber(name.getText().toString())) {
                        Toast.makeText(Activity_Intent1.this, "Name must not contain number", Toast.LENGTH_SHORT).show();
                        return;
                    }

                    if (containsNumber(surname.getText().toString())) {
                        Toast.makeText(Activity_Intent1.this, "Surname must not contain number", Toast.LENGTH_SHORT).show();
                        return;
                    }

                    if (!isNumeric(age.getText().toString())) {
                        Toast.makeText(Activity_Intent1.this, "Age must be numeric", Toast.LENGTH_SHORT).show();
                        return;
                    }

                    int enteredAgeValue = Integer.parseInt(age.getText().toString());

                    if (enteredAgeValue < 0 || enteredAgeValue > 100) {
                        Toast.makeText(Activity_Intent1.this, "Age must be between 0 and 100", Toast.LENGTH_SHORT).show();
                        return;
                    }

                    customIntent.putExtra("name", name.getText().toString());
                    customIntent.putExtra("surname", surname.getText().toString());
                    customIntent.putExtra("age", Integer.parseInt(age.getText().toString()));
                    startActivity(customIntent);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };

        btn1.setOnClickListener(listener);
    }
}