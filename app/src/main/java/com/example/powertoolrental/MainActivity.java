package com.example.powertoolrental;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity {
    double washerCost = 55.99;
    double tillerCost = 68.99;
    double daysEntered;
    double total;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setLogo(R.mipmap.ic_launcher);
        getSupportActionBar().setDisplayUseLogoEnabled(true);

        final EditText days = (EditText) findViewById(R.id.tvDays);
        final RadioButton rbWasher = (RadioButton) findViewById(R.id.rbWasher);
        final RadioButton rbTiller = (RadioButton) findViewById(R.id.rbTiller);
        final TextView result = (TextView) findViewById(R.id.tvResult);
        Button calculate = (Button) findViewById(R.id.btCalculate);
        calculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                daysEntered = Double.parseDouble(days.getText().toString());
                DecimalFormat currency = new DecimalFormat("$###,###.##");
                if (rbWasher.isChecked()) {
                    if (daysEntered <= 7) {
                        total = washerCost * daysEntered;
                        result.setText("Total Cost: " + currency.format(total));
                    } else {
                        Toast.makeText(MainActivity.this, "You can not rent for more than 7 days", Toast.LENGTH_LONG).show();
                        result.setText("");
                    }
                }
                if (rbTiller.isChecked()) {
                    if (daysEntered <= 7) {
                        total = tillerCost * daysEntered;
                        result.setText("Total Cost: " + currency.format(total));
                    } else {
                        Toast.makeText(MainActivity.this, "You can not rent for more than 7 days", Toast.LENGTH_LONG).show();
                        result.setText("");
                    }
                }
            }
        });
    }
}