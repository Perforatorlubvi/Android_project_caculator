package com.example.androidcaculatorform;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.androidcaculatorform.model.CalculatorModel;

public class MainActivity extends AppCompatActivity {


    private TextView text;
    private CalculatorModel calculator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        int[] numberIds = new int[] {
                R.id.button_zero,
                R.id.button_one,
                R.id.button_two,
                R.id.button_three,
                R.id.button_four,
                R.id.button_five,
                R.id.button_six,
                R.id.button_seven,
                R.id.button_eight,
                R.id.button_nine
        };
        int[] actionsIds = new int[] {
                R.id.button_plus,
                R.id.button_minus,
                R.id.button_multiplication,
                R.id.button_division,
                R.id.button_equal
        };
        text = findViewById(R.id.textView_input_numbers);
        calculator = new CalculatorModel();

        View.OnClickListener numberButtonClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                calculator.onNumPressed(view.getId());
                text.setText(calculator.getText());
            }
        };

        View.OnClickListener actionButtonOnclickListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                calculator.onActionPressed(view.getId());
                text.setText(calculator.getText());
            }
        };

        for (int i = 0; i < numberIds.length; i++) {
            findViewById(numberIds[i]).setOnClickListener(numberButtonClickListener);
        }

        for (int i = 0; i < actionsIds.length; i++) {
            findViewById(actionsIds[i]).setOnClickListener(actionButtonOnclickListener);
        }
        findViewById(R.id.button_clear).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                calculator.reset();
                text.setText(calculator.getText());
            }
        });

    }
    }
