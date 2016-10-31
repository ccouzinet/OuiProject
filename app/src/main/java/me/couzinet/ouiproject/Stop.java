package me.couzinet.ouiproject;

import com.google.gson.annotations.SerializedName;

import java.util.Arrays;
import java.util.HashMap;

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

    @SerializedName("stops")
    private Stop[] stops;

    private float latitude;
    private float longitude;

    @SerializedName("destinations_ids")
    private int[] destinationsId;

    /**
     * Instantiates a new Stop.
     *
     * @param id             the id
     * @param address        the address
     * @param shortName      the short name
     * @param longName       the long name
     * @param latitude       the latitude
     * @param longitude      the longitude
     * @param destinationsId the destinations id
     */
    public Stop(int id, String address, String shortName, String longName, float latitude, float longitude, int[] destinationsId) {
        this.id = id;
        this.address = address;
        this.shortName = shortName;
        this.longName = longName;
        this.latitude = latitude;
        this.longitude = longitude;
        this.destinationsId = destinationsId;
    }

    /**
     * Gets address.
     *
     * @return the address
     */
    public String getAddress() {
        return address;
    }

    /**
     * Sets address.
     *
     * @param address the address
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * Gets short name.
     *
     * @return the short name
     */
    public String getShortName() {
        return shortName;
    }

    /**
     * Sets short name.
     *
     * @param shortName the short name
     */
    public void setShortName(String shortName) {
        this.shortName = shortName;
    }

    /**
     * Gets long name.
     *
     * @return the long name
     */
    public String getLongName() {
        return longName;
    }

    /**
     * Sets long name.
     *
     * @param longName the long name
     */
    public void setLongName(String longName) {
        this.longName = longName;
    }

    /**
     * Gets latitude.
     *
     * @return the latitude
     */
    public float getLatitude() {
        return latitude;
    }

    /**
     * Sets latitude.
     *
     * @param latitude the latitude
     */
    public void setLatitude(float latitude) {
        this.latitude = latitude;
    }

    /**
     * Gets longitude.
     *
     * @return the longitude
     */
    public float getLongitude() {
        return longitude;
    }

    /**
     * Sets longitude.
     *
     * @param longitude the longitude
     */
    public void setLongitude(float longitude) {
        this.longitude = longitude;
    }

    /**
     * Gets id.
     *
     * @return the id
     */
    public int getId() {

        return id;
    }

    /**
     * Sets id.
     *
     * @param id the id
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Get stops stop [ ].
     *
     * @return the stop [ ]
     */
    public Stop[] getStops() {
        return stops;
    }

    /**
     * Sets stops.
     *
     * @param stops the stops
     */
    public void setStops(Stop[] stops) {
        this.stops = stops;
    }

    /**
     * Get destinations id int [ ].
     *
     * @return the int [ ]
     */
    public int[] getDestinationsId() {
        return destinationsId;
    }

    /**
     * Sets destinations id.
     *
     * @param destinationsId the destinations id
     */
    public void setDestinationsId(int[] destinationsId) {
        this.destinationsId = destinationsId;
    }

    @Override
    public String toString() {
        return "Stop{" +
                "id=" + id +
                ", address='" + address + '\'' +
                ", shortName='" + shortName + '\'' +
                ", longName='" + longName + '\'' +
                ", stops=" + Arrays.toString(stops) +
                ", latitude=" + latitude +
                ", longitude=" + longitude +
                ", destinationsId=" + Arrays.toString(destinationsId) +
                '}';
    }

    /**
     * Indicates whether some other object is "equal to" this one.
     * <p>
     * The {@code equals} method implements an equivalence relation
     * on non-null object references:
     * <ul>
     * <li>It is <i>reflexive</i>: for any non-null reference value
     * {@code x}, {@code x.equals(x)} should return
     * {@code true}.
     * <li>It is <i>symmetric</i>: for any non-null reference values
     * {@code x} and {@code y}, {@code x.equals(y)}
     * should return {@code true} if and only if
     * {@code y.equals(x)} returns {@code true}.
     * <li>It is <i>transitive</i>: for any non-null reference values
     * {@code x}, {@code y}, and {@code z}, if
     * {@code x.equals(y)} returns {@code true} and
     * {@code y.equals(z)} returns {@code true}, then
     * {@code x.equals(z)} should return {@code true}.
     * <li>It is <i>consistent</i>: for any non-null reference values
     * {@code x} and {@code y}, multiple invocations of
     * {@code x.equals(y)} consistently return {@code true}
     * or consistently return {@code false}, provided no
     * information used in {@code equals} comparisons on the
     * objects is modified.
     * <li>For any non-null reference value {@code x},
     * {@code x.equals(null)} should return {@code false}.
     * </ul>
     * <p>
     * The {@code equals} method for class {@code Object} implements
     * the most discriminating possible equivalence relation on objects;
     * that is, for any non-null reference values {@code x} and
     * {@code y}, this method returns {@code true} if and only
     * if {@code x} and {@code y} refer to the same object
     * ({@code x == y} has the value {@code true}).
     * <p>
     * Note that it is generally necessary to override the {@code hashCode}
     * method whenever this method is overridden, so as to maintain the
     * general contract for the {@code hashCode} method, which states
     * that equal objects must have equal hash codes.
     *
     * @param obj the reference object with which to compare.
     * @return {@code true} if this object is the same as the obj
     * argument; {@code false} otherwise.
     * @see #hashCode()
     * @see HashMap
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Stop other = (Stop) obj;
        if (id != other.id)
            return false;
        return true;
    }
}
