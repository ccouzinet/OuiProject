package me.couzinet.ouiproject;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.support.design.widget.TabLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.ProgressBar;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;
import java.util.WeakHashMap;

public class MainActivity extends AppCompatActivity implements OnMapReadyCallback {

    private final static String BASE_URL = "https://api.idbus.com/v2";
    private final static String ENDPOINT_STOPS = "/stops";
    private final static String API_KEY_OUIBUS = "lkyAQryj-IoQK6Xb9VtIPQ";
    private final static String TAG = MainActivity.class.getSimpleName();

    private RequestQueue queue;
    private Stop[] stops;
    private TabLayout tabs;
    private MapFragment mMapFragment;
    private HashMap <Marker, Stop> hashMapMarker;

    public Stop[] getStops() {
        return stops;
    }

    public void setStops(Stop[] stops) {
        this.stops = stops;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tabs = (TabLayout) findViewById(R.id.tabs);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        queue = Volley.newRequestQueue(this);
        getData();

        createTabs();



        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, FavoritesActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        if (id == R.id.refresh){
            getData();
        }

        return super.onOptionsItemSelected(item);
    }

    public void getData(){
        String urlStops = BASE_URL + ENDPOINT_STOPS;
        final ProgressBar progressBar = (ProgressBar) findViewById(R.id.progressBar);
        progressBar.setVisibility(View.VISIBLE);

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, urlStops, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                Log.d(TAG, "Request DONE");
                Gson gson = new GsonBuilder().create();
                try {
                    JSONArray jsonArray = response.getJSONArray("stops");
                    stops = gson.fromJson(jsonArray.toString(), Stop[].class);
                    Log.d("TAG", stops[1].toString());

                    FragmentManager fragmentManager = getFragmentManager();
                    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                    StopListFragment stopListFragment = new StopListFragment();
                    fragmentTransaction.replace(R.id.testFra, stopListFragment);
                    fragmentTransaction.commit();
                    TabLayout.Tab tab = tabs.getTabAt(0);
                    tab.select();

                    progressBar.setVisibility(View.GONE);


                   /* StopListAdapter adapter = new StopListAdapter(MainActivity.this, android.R.layout.simple_list_item_1, stops);
                    ListView listView = (ListView) findViewById(R.id.stopListView);
                    listView.setAdapter(adapter); */

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.v("TAG", error.getCause());
            }
        }){
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                HashMap<String, String> headers = new HashMap<String, String>();
                String param = String.format("Token %s", API_KEY_OUIBUS);
                headers.put("Accept", "application/json");
                headers.put("Content-Type", "application/json");
                headers.put("Authorization", param);
                return headers;
            }
        };

        queue.add(request);

    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        //TODO : Prendre en compte les sous-stops
        LatLngBounds.Builder mapBounds = new LatLngBounds.Builder();
        hashMapMarker = new HashMap<Marker, Stop>();

        for(Stop s: stops){
            if(s.getLatitude() != null && s.getLongitude() != null){
                LatLng stopCoords = new LatLng(Double.parseDouble(s.getLatitude()), Double.parseDouble(s.getLongitude()));
                Marker m = googleMap.addMarker(new MarkerOptions().position(stopCoords).title(s.getLongName()));
                hashMapMarker.put(m, s);
                mapBounds.include(stopCoords);
            } else {
                for(Stop sousS: s.getStops()){
                    if(sousS.getLatitude() != null && sousS.getLongitude() != null){
                        LatLng stopCoords = new LatLng(Double.parseDouble(sousS.getLatitude()), Double.parseDouble(sousS.getLongitude()));
                        Marker m = googleMap.addMarker(new MarkerOptions().position(stopCoords).title(sousS.getLongName()));
                        hashMapMarker.put(m, sousS);
                        mapBounds.include(stopCoords);
                    }
                }
            }
        }
        googleMap.moveCamera(CameraUpdateFactory.newLatLngBounds(mapBounds.build(), 50));
        googleMap.setOnInfoWindowClickListener(new GoogleMap.OnInfoWindowClickListener() {
            @Override
            public void onInfoWindowClick(Marker marker) {
                Stop stop = (Stop) hashMapMarker.get(marker);
                Log.d(TAG, "Objet : "+stop.toString());
                Intent intent = new Intent(MainActivity.this, DetailActivity.class);
                Gson gson = new Gson();
                intent.putExtra("stopObj", gson.toJson(stop));
                startActivity(intent);
            }
        });
    }

    /**
     * Create the tabs
     */
    private void createTabs(){

        tabs.addTab(tabs.newTab().setText("Liste"));
        tabs.addTab(tabs.newTab().setText("Carte"));
        tabs.addTab(tabs.newTab().setText("Details"));

        tabs.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                FragmentManager fragmentManager = getFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                switch (tab.getPosition()){
                    case 0:
                        Log.d("Tab", "Tab 0");
                        StopListFragment stopListFragment = new StopListFragment();
                        fragmentTransaction.replace(R.id.testFra, stopListFragment);
                        fragmentTransaction.commit();
                        break;
                    case 1:
                        Log.d("Tab", "Tab 1");
                        mMapFragment = MapFragment.newInstance();
                        fragmentTransaction.replace(R.id.testFra, mMapFragment);
                        fragmentTransaction.commit();
                        mMapFragment.getMapAsync(MainActivity.this);
                        break;
                    case 2:
                        Log.d("Tab", "Tab 2");
                        break;
                }

            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }
}
