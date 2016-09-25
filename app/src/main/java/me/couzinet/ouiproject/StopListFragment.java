package me.couzinet.ouiproject;


import android.app.Activity;
import android.app.ListFragment;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;


/**
 * A simple {@link Fragment} subclass.
 */
public class StopListFragment extends ListFragment {


    public StopListFragment() {
        // Required empty public constructor
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);


        final String[] items = {"Test", "France", "LOL", "Test", "France", "LOL", "Test", "France", "LOL", "Test", "France", "LOL"};
        final ArrayAdapter<String> aa = new ArrayAdapter<String>(getActivity(),
                android.R.layout.simple_list_item_1, items);


        MainActivity currentActivity = (MainActivity) getActivity();
        StopListAdapter adapter = new StopListAdapter(currentActivity, android.R.layout.simple_list_item_1, currentActivity.getStops());
        setListAdapter(adapter);
    }


}
