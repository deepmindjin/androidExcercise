package com.hongseokandrewjang.android.fragmentbasic03;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentOne extends Fragment {

    MainActivity main;
    ArrayList<Listitem> datas = new ArrayList<>();
    ListView listView;
    TextView artist;
    TextView title;


    public FragmentOne() {
        main = (MainActivity)getActivity();
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.list_item, container, false);
        listView = (ListView) view.findViewById(R.id.fragmentOneListView);

        artist = (TextView) view.findViewById(R.id.item_artist);
        title = (TextView) view.findViewById(R.id.item_title);

        ListviewAdapter adapter = new ListviewAdapter(main);
        listView.setAdapter(adapter);

        return view;
    }

}
