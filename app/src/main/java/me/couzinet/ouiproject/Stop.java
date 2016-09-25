package me.couzinet.ouiproject;

import com.google.gson.annotations.SerializedName;

import java.util.Arrays;

/**
 * Created by coren on 23/09/2016.
 */

public class Stop {

    private int id;
    private String address;

    @SerializedName("short_name")
    private String shortName;

    @SerializedName("long_name")
    private String longName;

    private String latitude;
    private String longitude;

    public int[] getDestinationsId() {
        return destinationsId;
    }

    public void setDestinationsId(int[] destinationsId) {
        this.destinationsId = destinationsId;
    }

    @SerializedName("destinations_ids")
    private int[] destinationsId;

    public Stop(int id, String address, String shortName, String longName, String latitude, String longitude, int[] destinationsId) {
        this.id = id;
        this.address = address;
        this.shortName = shortName;
        this.longName = longName;
        this.latitude = latitude;
        this.longitude = longitude;
        this.destinationsId = destinationsId;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getShortName() {
        return shortName;
    }

    public void setShortName(String shortName) {
        this.shortName = shortName;
    }

    public String getLongName() {
        return longName;
    }

    public void setLongName(String longName) {
        this.longName = longName;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public int getId() {

        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Stop{" +
                "id=" + id +
                ", address='" + address + '\'' +
                ", shortName='" + shortName + '\'' +
                ", longName='" + longName + '\'' +
                ", latitude='" + latitude + '\'' +
                ", longitude='" + longitude + '\'' +
                ", destinationsId=" + Arrays.toString(destinationsId) +
                '}';
    }
}
