package me.couzinet.ouiproject;


import android.app.Activity;
import android.app.ListFragment;
import android.content.Intent;
import android.os.Bundle;
import android.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;


/**
 * A simple {@link Fragment} subclass.
 */
public class StopListFragment extends ListFragment {
    private StopListAdapter adapter;

    public StopListFragment() {
        // Required empty public constructor
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        MainActivity currentActivity = (MainActivity) getActivity();
        if(currentActivity.getStops() != null){
            adapter = new StopListAdapter(currentActivity, android.R.layout.simple_list_item_1, currentActivity.getStops());
            setListAdapter(adapter);
        }
    }

    /**
     * This method will be called when an item in the list is selected.
     * Subclasses should override. Subclasses can call
     * getListView().getItemAtPosition(position) if they need to access the
     * data associated with the selected item.
     *
     * @param l        The ListView where the click happened
     * @param v        The view that was clicked within the ListView
     * @param position The position of the view in the list
     * @param id       The row id of the item that was clicked
     */
    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);
        Stop stop = adapter.getItem(position);
        Log.d("TAG", stop != null ? stop.toString() : null);
        Intent intent = new Intent(getActivity(), DetailActivity.class);
        intent.putExtra("stopName", stop.getLongName());
        intent.putExtra("stopShortName", stop.getShortName());
        intent.putExtra("stopAddress", stop.getAddress());
        intent.putExtra("stopLat", Double.parseDouble(stop.getLatitude()));
        intent.putExtra("stopLng", Double.parseDouble(stop.getLongitude()));
        this.startActivity(intent);
    }
}
