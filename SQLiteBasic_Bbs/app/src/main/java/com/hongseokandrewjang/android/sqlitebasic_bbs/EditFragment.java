package com.hongseokandrewjang.android.sqlitebasic_bbs;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;

public class EditFragment extends Fragment {

    public ArrayList<BbsData> datas = new ArrayList<>();
    public final static int ACTION_SAVE = 1;
    public final static int ACTION_CANCEL = 2;
    private OnFragmentInteractionListener mListener;
    EditText inputTitle;
    EditText inputName;
    EditText inputContents;

    public EditFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_edit, container, false);
        inputTitle = (EditText)view.findViewById(R.id.putTitle);
        inputName = (EditText)view.findViewById(R.id.putName);
        inputContents = (EditText)view.findViewById(R.id.putContents);

        datas = mListener.getDatas();

        Button btnCancel = (Button)view.findViewById(R.id.btnCancelInEdit);
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.onFragmentInteraction(ACTION_CANCEL);
            }
        });
        Button btnSave = (Button)view.findViewById(R.id.btnSaveInEdit);
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String title = inputTitle.getText().toString();
                String name = inputName.getText().toString();
                String contents = inputContents.getText().toString();
                mListener.addData(title, name, contents);
                mListener.onFragmentInteraction(ACTION_SAVE);
            }
        });
        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
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

}
