package me.couzinet.ouiproject;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by coren on 23/09/2016.
 */

public class StopListAdapter extends ArrayAdapter<Stop> {

    /**
     * Constructor
     *
     * @param context  The current context.
     * @param resource The resource ID for a layout file containing a TextView to use when
     *                 instantiating views.
     * @param objects  The objects to represent in the ListView.
     */
    public StopListAdapter(Context context, int resource, Stop[] objects) {
        super(context, resource, objects);
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View itemView = LayoutInflater.from(getContext()).inflate(R.layout.layout_stop_list, parent, false);

        TextView textView = (TextView) itemView.findViewById(R.id.textView2);
        TextView textView2 = (TextView) itemView.findViewById(R.id.textView3);

        Stop stop = getItem(position);

        textView.setText(stop.getShortName());
        textView2.setText(stop.getLongName());

        return itemView;
    }
}
