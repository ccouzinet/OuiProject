package me.couzinet.ouiproject;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class DetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        String stopName = getIntent().getStringExtra("stopName");
        String stopShortName = getIntent().getStringExtra("stopShortName");
        String stopAddress = getIntent().getStringExtra("stopAddress");

        TextView textViewName = (TextView) findViewById(R.id.textView);
        TextView textViewShortName = (TextView) findViewById(R.id.textView4);
        TextView textViewAddress = (TextView) findViewById(R.id.textView5);

        textViewAddress.setText(stopAddress);
        textViewName.setText(stopName);
        textViewShortName.setText(stopShortName);
    }
}
