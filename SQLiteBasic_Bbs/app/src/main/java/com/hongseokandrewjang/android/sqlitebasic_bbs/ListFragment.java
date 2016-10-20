package com.hongseokandrewjang.android.sqlitebasic_bbs;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class ListFragment extends Fragment {

    public ArrayList<BbsData> datas = new ArrayList<>();
    public final static int ACTION_WRITE = 0;
    public final static int ACTION_DETAIL = 4;
    private OnFragmentInteractionListener mListener;

    public ListFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        datas = mListener.getDatas();
        View view = inflater.inflate(R.layout.fragment_list, container, false);
        ListView listView = (ListView)view.findViewById(R.id.listVIew);
        ListAdapter adapter = new ListAdapter(getContext());
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                mListener.onFragmentInteraction(ACTION_DETAIL);
            }
        });
        listView.setAdapter(adapter);
        Button btnWrite = (Button)view.findViewById(R.id.btnWrite);
        btnWrite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.onFragmentInteraction(ACTION_WRITE);
            }
        });

        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        // 부모 activity에 interface가 구현되지 않았으면 Exception을 발생시켜 강제로 App을 종료시킨다.
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }


    private class ListAdapter extends BaseAdapter{
        Context context;
        LayoutInflater inflater;

        public ListAdapter(Context context){
            this.context = context;
            inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }
        @Override
        public int getCount() {
            return datas.size();
        }

        @Override
        public Object getItem(int position) {
            return datas.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if(convertView == null) {
                convertView = inflater.inflate(R.layout.listitem_layout, null);
            }
            TextView no = (TextView)convertView.findViewById(R.id.item_id_View);
            no.setText(datas.get(position).no+"");
            TextView title = (TextView)convertView.findViewById(R.id.item_title_view);
            title.setText(datas.get(position).title);

            return convertView;
        }
    }


}


