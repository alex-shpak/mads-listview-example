package com.example.madslistviewexample;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.madsadview.MadsInlineAdView;

public class MadsViewAdapter<T> extends AdvertisingViewAdapter<T> {

    public static final int VIEW_TYPE_ITEM = 0, VIEW_TYPE_ADS = 1;

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getItemViewType(int position) {
        if(isAdvertising(position))
            return VIEW_TYPE_ADS;
        else
            return VIEW_TYPE_ITEM;
    }

    @Override
    public int getViewTypeCount() {
        return 2;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());

        switch (getItemViewType(position)) {
            case VIEW_TYPE_ITEM:
                //update item
                ItemViewHolder itemViewHolder;
                if(convertView == null) {
                    convertView = inflater.inflate(android.R.layout.simple_list_item_1, parent, false);
                    itemViewHolder = new ItemViewHolder(convertView);
                } else {
                    itemViewHolder = (ItemViewHolder) convertView.getTag();
                }

                Object item = getItem(position);
                itemViewHolder.text1.setText(item.toString());

                return convertView;

            case VIEW_TYPE_ADS:

                AdsViewHolder adsViewHolder;
                if(convertView == null) {
                    convertView = inflater.inflate(R.layout.layout_inline_ads, parent, false);
                    adsViewHolder = new AdsViewHolder(convertView);

                    adsViewHolder.adView.setLocationDetection(false);
                    adsViewHolder.adView.setEnableExpandInActivity(false);
                    adsViewHolder.adView.setPlacementId(getPlacement());
                } else {
                    adsViewHolder = (AdsViewHolder) convertView.getTag();
                }

                if(adsViewHolder.position != position) {
                    adsViewHolder.adView.update();
                    adsViewHolder.position = position;
                }

                return convertView;

            default:
                return null;
        }
    }

    public static class AdsViewHolder {
        public final MadsInlineAdView adView;
        public int position = 0;

        public AdsViewHolder(View converView) {
            this.adView = (MadsInlineAdView) converView.findViewById(R.id.mads_inline);
            converView.setTag(this);
        }
    }

    public static class ItemViewHolder {
        public final TextView text1;

        public ItemViewHolder(View converView) {
            this.text1 = (TextView) converView.findViewById(android.R.id.text1);
            converView.setTag(this);
        }
    }
}
