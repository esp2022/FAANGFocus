package edu.uga.cs.faangfocus;


import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * An activity that displays the overview text for the given company.
 */
public class OverviewActivity extends AppCompatActivity {

    /**
     * Called when the activity is starting.
     * <p>
     * Sets up the UI, retrieves the selected company name from the intent,
     * sets the title of the activity to the company name, displays the
     * overview text for the company, and sets an image related to the
     * company.
     *
     * @param savedInstanceState If the activity is being re-initialized after
     *     previously being shut down then this Bundle contains the data it most
     *     recently supplied in {@link #onSaveInstanceState}.
     *     Note: Otherwise it is null.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_overview);

        // Get the selected company from the intent
        Intent intent = getIntent();
        String companyName = intent.getStringExtra("COMPANY");

        // Set the title of the activity to the company name
        setTitle(companyName + " Overview");

        // Display the overview text
        TextView overviewTextView = findViewById(R.id.overviewText);
        assert companyName != null;
        overviewTextView.setText(getOverviewText(companyName));

        // Set an image related to the company
        ImageView companyImageView = findViewById(R.id.companyImage);
        int imageResource = getResources().getIdentifier(companyName.toLowerCase(), "drawable", getPackageName());
        companyImageView.setImageResource(imageResource);
    }

    /**
     * Reads and returns the overview text for the given company from a raw resource file.
     * The raw resource file must be named <code>companyName.toLowerCase() + "_overview"</code>
     * and must be located in the <code>res/raw</code> directory.
     * @param companyName The name of the company.
     * @return The overview text.
     */
    private String getOverviewText(String companyName) {
        int resourceId = getResources().getIdentifier(companyName.toLowerCase() + "_overview", "raw", getPackageName());
        InputStream inputStream = getResources().openRawResource(resourceId);
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));

        StringBuilder stringBuilder = new StringBuilder();
        String line;
        try {
            while ((line = reader.readLine()) != null) {
                stringBuilder.append(line).append("\n");
            }
            reader.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return stringBuilder.toString();
    }
}
