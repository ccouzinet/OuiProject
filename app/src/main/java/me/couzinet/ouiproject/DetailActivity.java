package me.couzinet.ouiproject;

import android.support.v4.app.NavUtils;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class DetailActivity extends AppCompatActivity implements OnMapReadyCallback {
    private double lat;
    private double lng;
    private String stopName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbarDetail);
        setSupportActionBar(toolbar);
        ActionBar ab = getSupportActionBar();
        ab.setDisplayHomeAsUpEnabled(true);

        MapFragment mapFragment = (MapFragment) getFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        stopName = getIntent().getStringExtra("stopName");
        String stopShortName = getIntent().getStringExtra("stopShortName");
        String stopAddress = getIntent().getStringExtra("stopAddress");
        lat = getIntent().getDoubleExtra("stopLat", 0.0);
        lng = getIntent().getDoubleExtra("stopLng", 0.0);

        TextView textViewName = (TextView) findViewById(R.id.textView);
        TextView textViewShortName = (TextView) findViewById(R.id.textView4);
        TextView textViewAddress = (TextView) findViewById(R.id.textView5);

        textViewAddress.setText(stopAddress);
        textViewName.setText(stopName);
        textViewShortName.setText(stopShortName);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        LatLng stopCoords = new LatLng(lat, lng);
        googleMap.addMarker(new MarkerOptions().position(stopCoords).title(stopName));
        //googleMap.moveCamera(CameraUpdateFactory.newLatLng(stopCoords));
        googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(stopCoords,8));
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            // Respond to the action bar's Up/Home button
            case android.R.id.home:
                NavUtils.navigateUpFromSameTask(this);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_detail, menu);
        return true;
    }
}
