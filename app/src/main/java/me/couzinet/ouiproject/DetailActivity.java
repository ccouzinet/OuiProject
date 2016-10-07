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
import com.google.gson.Gson;

import java.util.List;

public class DetailActivity extends AppCompatActivity implements OnMapReadyCallback {
    private double lat;
    private double lng;
    private String stopName;
    private MenuItem favoritesMenu;
    private Stop stop;
    SharedPreference sharedPreference;

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

        Gson gson = new Gson();
        String jsonStop = getIntent().getStringExtra("stopObj");
        stop = gson.fromJson(jsonStop, Stop.class);

        //TODO : Stocker directement les latitudes et longitudes en Double
        if(stop.getLongitude() != null && stop.getLatitude() != null){
            lat = Double.parseDouble(stop.getLatitude());
            lng = Double.parseDouble(stop.getLongitude());
        }

        TextView textViewName = (TextView) findViewById(R.id.textView);
        TextView textViewShortName = (TextView) findViewById(R.id.textView4);
        TextView textViewAddress = (TextView) findViewById(R.id.textView5);

        textViewAddress.setText(stop.getAddress());
        textViewName.setText(stop.getLongName());
        textViewShortName.setText(stop.getShortName());
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
            case R.id.action_favorite:
                //TODO : Gérer le cas où le stop est déjà en favori.
                if(checkFavoriteItem(stop)){
                    favoritesMenu.setIcon(R.drawable.ic_favorite_border_black_24dp);
                    sharedPreference.removeFavorite(getApplicationContext(), stop);
                }else{
                    favoritesMenu.setIcon(R.drawable.ic_favorite_black_24dp);
                    sharedPreference.addFavorite(getApplicationContext(), stop);
                }
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_detail, menu);
        favoritesMenu = menu.findItem(R.id.action_favorite);
        if(checkFavoriteItem(stop)){
            favoritesMenu.setIcon(R.drawable.ic_favorite_black_24dp);
        }else{
            favoritesMenu.setIcon(R.drawable.ic_favorite_border_black_24dp);
        }
        return true;
    }

    public boolean checkFavoriteItem(Stop checkStop) {
        sharedPreference = new SharedPreference();
        boolean check = false;
        List<Stop> favorites = sharedPreference.getFavorites(getApplicationContext());
        if (favorites != null) {
            for (Stop product : favorites) {
                if (product.equals(checkStop)) {
                    check = true;
                    break;
                }
            }
        }
        return check;
    }
}
