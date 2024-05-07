package com.umaru.calculator;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    private Button calcButton;
    private EditText decNumber;
    private EditText result;

    private View.OnClickListener listener;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstance) {
        super.onCreate(savedInstance);
        setContentView(R.layout.activity_main);

        decNumber = (EditText) findViewById(R.id.numberInput);
        result = (EditText) findViewById(R.id.result);
        calcButton = (Button) findViewById(R.id.calculateBtn);

        listener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    Float dec = Float.parseFloat(decNumber.getText().toString());
                    if (dec < 0) {
                        result.setHint("Side must be positive number");
                    }

                    result.setText(String.valueOf(dec * dec));
                } catch (NumberFormatException e) {
                    result.setHint("Please enter number");
                }
            }
        };

        calcButton.setOnClickListener(listener);
    }

}