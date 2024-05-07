package com.umaru.intent;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Activity_Intent2 extends AppCompatActivity {
    Button btn1;
    TextView name, surname, age;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_2);

        name = (TextView) findViewById(R.id.name_result);
        surname = (TextView) findViewById(R.id.surname_result);
        age = (TextView) findViewById(R.id.age_result);
        btn1 = (Button) findViewById(R.id.button2);

        Intent intent = getIntent();
        name.setText(": " + intent.getStringExtra("name"));
        surname.setText(": " + intent.getStringExtra("surname"));
        age.setText(": " + String.valueOf(intent.getIntExtra("age", 0)));

        View.OnClickListener listener = new View.OnClickListener() {
            public void onClick(View v) {
                Intent customIntent = new Intent(Activity_Intent2.this, Activity_Intent1.class);
                startActivity(customIntent);
            }
        };

        btn1.setOnClickListener(listener);
    }
}