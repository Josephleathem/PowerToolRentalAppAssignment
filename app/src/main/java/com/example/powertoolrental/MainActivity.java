package com.example.powertoolrental;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;

import androidx.appcompat.app.AppCompatActivity;

import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity {
    private static final double WASHER_COST = 55.99;
    private static final double TILLER_COST = 68.99;
    private static final int MAX_RENTAL_DAYS = 7;

    private static final String EXTRA_TOOL_TYPE = "toolType";
    private static final String EXTRA_DAILY_RATE = "dailyRate";
    private static final String EXTRA_DAYS_RENTED = "daysRented";
    private static final String EXTRA_TOTAL_COST = "totalCost";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayShowHomeEnabled(true);
            getSupportActionBar().setLogo(R.mipmap.ic_launcher);
            getSupportActionBar().setDisplayUseLogoEnabled(true);
        }

        final EditText days = findViewById(R.id.tvDays);
        final RadioButton rbWasher = findViewById(R.id.rbWasher);
        final RadioButton rbTiller = findViewById(R.id.rbTiller);
        final TextView result = findViewById(R.id.tvResult);
        Button calculate = findViewById(R.id.btCalculate);
        Button help = findViewById(R.id.btHelp);

        help.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent helpIntent = new Intent(MainActivity.this, HelpActivity.class);
                startActivity(helpIntent);
            }
        });

        calculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String daysText = days.getText().toString().trim();
                if (daysText.isEmpty()) {
                    showValidationPrompt(getString(R.string.error_empty_days));
                    result.setText("");
                    return;
                }

                int daysEntered;
                try {
                    daysEntered = Integer.parseInt(daysText);
                } catch (NumberFormatException exception) {
                    showValidationPrompt(getString(R.string.error_non_numeric_days));
                    result.setText("");
                    return;
                }

                if (daysEntered < 1) {
                    showValidationPrompt(getString(R.string.error_invalid_day_count));
                    result.setText("");
                    return;
                }

                if (daysEntered > MAX_RENTAL_DAYS) {
                    showValidationPrompt(getString(R.string.error_too_many_days));
                    result.setText("");
                    return;
                }

                String toolType;
                double dailyRate;
                if (rbWasher.isChecked()) {
                    toolType = getString(R.string.rbWasher);
                    dailyRate = WASHER_COST;
                } else if (rbTiller.isChecked()) {
                    toolType = getString(R.string.rbTiller);
                    dailyRate = TILLER_COST;
                } else {
                    showValidationPrompt(getString(R.string.error_choose_tool));
                    result.setText("");
                    return;
                }

                double total = dailyRate * daysEntered;
                DecimalFormat currency = new DecimalFormat("$###,###.00");
                result.setText(getString(R.string.preview_total, currency.format(total)));

                Intent summaryIntent = new Intent(MainActivity.this, RentalSummaryActivity.class);
                summaryIntent.putExtra(EXTRA_TOOL_TYPE, toolType);
                summaryIntent.putExtra(EXTRA_DAILY_RATE, dailyRate);
                summaryIntent.putExtra(EXTRA_DAYS_RENTED, daysEntered);
                summaryIntent.putExtra(EXTRA_TOTAL_COST, total);
                startActivity(summaryIntent);
            }
        });
    }

    private void showValidationPrompt(String message) {
        new AlertDialog.Builder(this)
                .setTitle(R.string.validation_title)
                .setMessage(message)
                .setPositiveButton(R.string.ok, null)
                .show();
    }
}