package me.couzinet.ouiproject;


import android.app.Activity;
import android.app.ListFragment;
import android.content.Intent;
import android.os.Bundle;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.gson.Gson;

import java.util.Arrays;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class StopListFragment extends ListFragment {
    private StopListAdapter adapter;
    private List<Stop> stopDisplayed;

    /**
     * Instantiates a new Stop list fragment.
     */
    public StopListFragment() {
        // Required empty public constructor
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        MainActivity currentActivity = (MainActivity) getActivity();
        if(getArguments() != null){
            Gson gson = new Gson();
            String jsonStop = getArguments().getString("sStops");
            stopDisplayed = Arrays.asList(gson.fromJson(jsonStop, Stop[].class));
            adapter = new StopListAdapter(currentActivity, android.R.layout.simple_list_item_1, stopDisplayed);
            setListAdapter(adapter);
        } else {
            if(currentActivity.getStops() != null){
                adapter = new StopListAdapter(currentActivity, android.R.layout.simple_list_item_1, Arrays.asList(currentActivity.getStops()));
                setListAdapter(adapter);
            }
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
        Gson gson = new Gson();
        if(stop.getStops() != null && stop.getStops().length != 0){
            FragmentManager fragmentManager = getFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            StopListFragment stopListFragment = new StopListFragment();
            Bundle args = new Bundle();
            args.putString("sStops",gson.toJson(stop.getStops()));
            stopListFragment.setArguments(args);
            fragmentTransaction.replace(R.id.testFra, stopListFragment);
            fragmentTransaction.addToBackStack("tag");
            fragmentTransaction.commit();
        } else {
            Intent intent = new Intent(getActivity(), DetailActivity.class);
            intent.putExtra("stopObj", gson.toJson(stop));
            this.startActivity(intent);
        }
    }
}
