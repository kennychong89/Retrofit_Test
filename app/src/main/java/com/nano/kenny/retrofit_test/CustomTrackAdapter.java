package com.nano.kenny.retrofit_test;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import model.Result;

/**
 * Created by Kenny on 6/24/2015.
 */
public class CustomTrackAdapter extends ArrayAdapter<Result>{
    private ArrayList<Result> resultList;

    public CustomTrackAdapter(Context context, int textViewResourceId, ArrayList<Result> resultList) {
        super(context, textViewResourceId, resultList);
        this.resultList = resultList;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        View v = convertView;

        if (v == null) {
            LayoutInflater inflater = (LayoutInflater) getContext()
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = inflater.inflate(R.layout.list_track_items, null);
        }

        Result result = resultList.get(position);

        if (result != null) {
            TextView trackNameView = (TextView)v.findViewById(R.id.list_item_toptracks_textview);
            ImageView trackImageView = (ImageView)v.findViewById(R.id.track_image);

            if (trackNameView != null) {
                trackNameView.setText(result.getTrackName());
            }

            if (trackImageView != null) {
                String artistTrackImg = result.getArtworkUrl60();
                Picasso.with(getContext()).load(artistTrackImg).into(trackImageView);
            }
        }

        return v;
    }
}
