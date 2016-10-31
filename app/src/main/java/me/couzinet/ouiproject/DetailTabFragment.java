package me.couzinet.ouiproject;


import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * A simple {@link Fragment} subclass.
 */
public class DetailTabFragment extends Fragment {


    public DetailTabFragment() {
        // Required empty public construction
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_detail_tab,container, false);
        TextView textView1 = (TextView) rootView.findViewById(R.id.txtView_url);
        textView1.setText(("URL : " + MainActivity.BASE_URL));
        int numberOfStops = ((MainActivity) getActivity()).getStops().length;

        return rootView;
    }
}
