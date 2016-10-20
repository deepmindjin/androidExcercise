package com.hongseokandrewjang.android.sqlitebasic_bbs;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

public class DetailFragment extends Fragment {

    public ArrayList<BbsData> datas = new ArrayList<>();
    private OnFragmentInteractionListener mListener;
    public final static int ACTION_CANCEL = 2;
    public final static int ACTION_MODIFY = 3;
    int position = 0;

    public DetailFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mListener = (OnFragmentInteractionListener) getActivity();
        datas = mListener.getDatas();
        this.position = mListener.getPosition();
        View view = inflater.inflate(R.layout.fragment_detail, container, false);
        Button btnCancel = (Button) view.findViewById(R.id.btnCancelInDetail);
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.onFragmentInteraction(ACTION_CANCEL);
            }
        });

        Button btnModify = (Button) view.findViewById(R.id.btnModifyInDetail);
        btnModify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.setPosition(position);
                mListener.onFragmentInteraction(ACTION_MODIFY);
            }
        });

        if (mListener.getDatas().size() != 0) {
            BbsData data = mListener.getData(position);
            TextView no = (TextView) view.findViewById(R.id.noInDetail);
            no.setText("no : "+data.no + "");
            TextView title = (TextView) view.findViewById(R.id.titleInDetail);
            title.setText("제목 : "+data.title);
            TextView name = (TextView) view.findViewById(R.id.nameInDetail);
            name.setText("작성자 : "+data.name);
            TextView date = (TextView) view.findViewById(R.id.dateInDetail);
            date.setText("작성일자 : "+data.ndate);
            TextView contents = (TextView) view.findViewById(R.id.contentsInDetail);
            contents.setText(data.contents);
        }
        return view;
    }

}
