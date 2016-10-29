package me.couzinet.ouiproject;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import java.util.List;

public class FavoritesActivity extends AppCompatActivity {
    SharedPreference sharedPreference;
    ListView listView;
    StopListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorites);

        sharedPreference = new SharedPreference();

        List<Stop> favorites = sharedPreference.getFavorites(getApplicationContext());
        listView = (ListView) findViewById(R.id.favoriteListView);
        adapter = new StopListAdapter(this, android.R.layout.simple_list_item_1, favorites);
        listView.setAdapter(adapter);
    }
}
