package edu.uga.cs.faangfocus;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.content.Intent;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

/**
 * Main Activity of the application.
 * <p>
 * This Activity displays a Spinner with all the FAANG companies, and two buttons
 * to navigate to the OverviewActivity and DetailActivity, respectively.
 * <p>
 * The selected company from the Spinner is passed as an extra to the
 * OverviewActivity and DetailActivity with the key "COMPANY".
 */
public class MainActivity extends AppCompatActivity {

        private String selectedCompany;

        /**
         * Called when the activity is starting.
         * <p>
         * Sets up the UI, initializes the Spinner with the list of FAANG companies,
         * and sets up the click listeners for the two buttons.
         * <p>
         * The selected company from the Spinner is passed as an extra to the
         * OverviewActivity and DetailActivity with the key "COMPANY".
         * <p>
         * @param savedInstanceState If the activity is being re-initialized after
         *     previously being shut down then this Bundle contains the data it most
         *     recently supplied in {@link #onSaveInstanceState}.
         *     Note: Otherwise it is null.
         */
        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);

            // Initialize the spinner with FAANG companies
            Spinner companySpinner = findViewById(R.id.companySpinner);
            ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                    R.array.faang_companies, android.R.layout.simple_spinner_item);
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            companySpinner.setAdapter(adapter);

            // Set listener for spinner selection
            companySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    selectedCompany = parent.getItemAtPosition(position).toString();
                }

                /**
                 * Called when the selection disappears from view.
                 * <p>
                 * In this case, the selected company is reset to the first company
                 * in the list of FAANG companies.
                 *
                 * @param parent The AdapterView that now contains no selected item.
                 */
                @Override
                public void onNothingSelected(AdapterView<?> parent) {
                    // Default selection if nothing is selected
                    selectedCompany = getResources().getStringArray(R.array.faang_companies)[0];
                }
            });

            // Set up buttons and their click listeners
            Button overviewButton = findViewById(R.id.overviewButton);
            Button detailsButton = findViewById(R.id.detailsButton);

            overviewButton.setOnClickListener(v -> {
                Intent intent = new Intent(MainActivity.this, OverviewActivity.class);
                intent.putExtra("COMPANY", selectedCompany);
                startActivity(intent);
            });

            detailsButton.setOnClickListener(v -> {
                Intent intent = new Intent(MainActivity.this, DetailActivity.class);
                intent.putExtra("COMPANY", selectedCompany);
                startActivity(intent);
            });
        }
    }
