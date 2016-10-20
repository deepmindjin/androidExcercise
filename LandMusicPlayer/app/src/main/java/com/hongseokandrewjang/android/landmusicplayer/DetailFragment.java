package com.hongseokandrewjang.android.landmusicplayer;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class DetailFragment extends Fragment {

    TextView artist;
    TextView title;

    public DetailFragment() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_detail, container, false);
        artist = (TextView)view.findViewById(R.id.artistTextViewOnDetail);
        title = (TextView)view.findViewById(R.id.titleTextViewOnDetail);

        return view;
    }

}
