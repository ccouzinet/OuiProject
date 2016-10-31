package me.couzinet.ouiproject;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by coren on 29/09/2016.
 */
public class SharedPreference {

    /**
     * The constant PREF_TAG.
     */
    public static final String PREF_TAG = "OUIAPP";
    /**
     * The constant FAVORITES.
     */
    public static final String FAVORITES = "OuiStops_Favorites";

    /**
     * Instantiates a new Shared preference.
     */
    public SharedPreference() {
        super();
    }

    /**
     * Save favorites on the device
     *
     * @param context   the context
     * @param favorites the favorites
     */
    public void saveFavorites(Context context, List<Stop> favorites) {
        SharedPreferences sharedPref;
        SharedPreferences.Editor editor;

        sharedPref = context.getSharedPreferences(PREF_TAG, Context.MODE_PRIVATE);
        editor = sharedPref.edit();

        Gson gson = new Gson();
        editor.putString(FAVORITES, gson.toJson(favorites));
        editor.commit();
    }

    /**
     * Add favorite to the favorites list
     *
     * @param context the context
     * @param stop    the stop to add
     */
    public void addFavorite(Context context, Stop stop){
        List<Stop> favorites = getFavorites(context);
        if(favorites == null)
            favorites = new ArrayList<Stop>();
        favorites.add(stop);
        saveFavorites(context, favorites);
    }

    /**
     * Remove favorite from the favorites list
     *
     * @param context the context
     * @param stop    the stop to add
     */
    public void removeFavorite(Context context, Stop stop){
        ArrayList<Stop> favorites = getFavorites(context);
        if(favorites != null){
            favorites.remove(stop);
            saveFavorites(context,favorites);
        }
    }

    /**
     * Get favorites array list from the device
     *
     * @param context the context
     * @return the array list containing the favorites stops
     */
    public ArrayList<Stop> getFavorites(Context context){
        SharedPreferences sharedPref;
        SharedPreferences.Editor editor;
        List<Stop> favorites;

        sharedPref = context.getSharedPreferences(PREF_TAG, Context.MODE_PRIVATE);
        editor = sharedPref.edit();

        if(sharedPref.contains(FAVORITES)){
            String jsonFavorites = sharedPref.getString(FAVORITES, null);
            Gson gson = new Gson();
            Stop[] favoritesStops = gson.fromJson(jsonFavorites,
                    Stop[].class);
            favorites = Arrays.asList(favoritesStops);
            favorites = new ArrayList<Stop>(favorites);
        } else {
            return null;
        }

        return (ArrayList<Stop>) favorites;
    }
}
