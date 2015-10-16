package com.example.madslistviewexample;

import android.widget.BaseAdapter;

import java.util.ArrayList;

public abstract class AdvertisingViewAdapter<T> extends BaseAdapter {

    private final ArrayList<T> list = new ArrayList<>();

    private int groupSize = 0; // interval + 1, including ad
    private int offset = 3; // default?

    private String placement = null;

    /**
     * @param interval number of posts between the ads + 1
     * @param offset number of posts before first ad
     */
    public void setGroupSize(int offset, int interval) {
        this.groupSize = interval + 1;
        this.offset = offset;
    }

    /**
     * @param placement MADS placement ID
     */
    public void setPlacement(String placement) {
        this.placement = placement;
    }

    public String getPlacement() {
        return placement;
    }

    public ArrayList<T> getList() {
        return list;
    }

    @Override
    public T getItem(int position) {
        if(isAdvertising(position))
            return null;

        return list.get(shiftPosition(position));
    }

    /**
     * Adding count of ads into total count of elements
     * @return count of elements + count of ads
     */
    @Override
    public int getCount() {
        int count = getOriginalCount();

        if(isAdvertisingEnabled())
            return count + (count - offset) / groupSize; //add ads to the total count

        return count;
    }

    private int getOriginalCount() {
        return list.size();
    }

    /**
     * @param position position of element in adapter
     * @return true if specified position should be advertising element
     */
    protected boolean isAdvertising(int position) {
        if(isAdvertisingEnabled())
            return position >= offset && (position - offset) % (groupSize) == 0; //show ads each %groupSize% items

        return false;
    }

    /**
     * @param position original position of element in adapter
     * @return updated position for element (original position - amount of ads above)
     */
    protected int shiftPosition(int position) {
        if(isAdvertisingEnabled())
            return position - (position - offset) / groupSize - (position < offset ? 0 : 1); //shift up for places that was took by ads

        return position;
    }

    /**
     * @return true if ads are enabled (when interval > 0 and count > offset)
     */
    public boolean isAdvertisingEnabled() {
        return groupSize > 1 && getOriginalCount() >= offset;
    }
}

