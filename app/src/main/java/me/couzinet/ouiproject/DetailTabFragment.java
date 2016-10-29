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
        TextView textView1 = (TextView) rootView.findViewById(R.id.url_String1);
        textView1.setText(("URL : " + MainActivity.BASE_URL));
        TextView textView2 = (TextView) rootView.findViewById(R.id.url_String2);
        textView2.setText(("KEY : " + MainActivity.API_KEY_OUIBUS));
        int numberOfStops = ((MainActivity) getActivity()).getStops().length;
        TextView textView3 = (TextView) rootView.findViewById(R.id.url_String3);
        textView3.setText(("Number of Stops : " + String.valueOf(numberOfStops)));
        TextView textView4 = (TextView) rootView.findViewById(R.id.url_String4);
        textView4.setText(("API utilisée : OuiBUS API"));

        return rootView;
    }
}
