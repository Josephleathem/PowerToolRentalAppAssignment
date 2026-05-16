package com.example.powertoolrental;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.text.DecimalFormat;

public class RentalSummaryActivity extends AppCompatActivity {

    private static final String EXTRA_TOOL_TYPE = "toolType";
    private static final String EXTRA_DAILY_RATE = "dailyRate";
    private static final String EXTRA_DAYS_RENTED = "daysRented";
    private static final String EXTRA_TOTAL_COST = "totalCost";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rental_summary);

        TextView toolTypeView = findViewById(R.id.tvToolType);
        TextView dailyRateView = findViewById(R.id.tvDailyRate);
        TextView daysRentedView = findViewById(R.id.tvDaysRented);
        TextView totalCostView = findViewById(R.id.tvTotalCost);
        Button rentAnotherButton = findViewById(R.id.btRentAnother);

        String toolType = getIntent().getStringExtra(EXTRA_TOOL_TYPE);
        double dailyRate = getIntent().getDoubleExtra(EXTRA_DAILY_RATE, 0.0);
        int daysRented = getIntent().getIntExtra(EXTRA_DAYS_RENTED, 0);
        double totalCost = getIntent().getDoubleExtra(EXTRA_TOTAL_COST, 0.0);

        DecimalFormat currency = new DecimalFormat("$###,###.00");
        toolTypeView.setText(getString(R.string.summary_tool_type, toolType));
        dailyRateView.setText(getString(R.string.summary_daily_rate, currency.format(dailyRate)));
        daysRentedView.setText(getString(R.string.summary_days_rented, daysRented));
        totalCostView.setText(getString(R.string.summary_total_cost, currency.format(totalCost)));

        rentAnotherButton.setOnClickListener(v -> {
            Intent homeIntent = new Intent(RentalSummaryActivity.this, MainActivity.class);
            homeIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(homeIntent);
            finish();
        });
    }
}