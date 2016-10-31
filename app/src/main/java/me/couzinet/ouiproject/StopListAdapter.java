package me.couzinet.ouiproject;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
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
     * @param resource The resource ID for a layout file containing a TextView to use when                 instantiating views.
     * @param objects  The objects to represent in the ListView.
     */
    public StopListAdapter(Context context, int resource, List<Stop> objects) {
        super(context, resource, objects);
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View itemView = LayoutInflater.from(getContext()).inflate(R.layout.layout_stop_list, parent, false);

        TextView villeTextView = (TextView) itemView.findViewById(R.id.textview_ville_stop_list);
        TextView nomTextView = (TextView) itemView.findViewById(R.id.textview_nom_stop_list);
        ImageView imgView = (ImageView) itemView.findViewById(R.id.imageView);

        Stop stop = getItem(position);

        if(stop.getStops() != null && stop.getStops().length != 0){
            imgView.setImageResource(R.drawable.ouibuslogoblue);
        }
        villeTextView.setText(stop.getShortName());
        nomTextView.setText(stop.getLongName());

        return itemView;
    }
}
