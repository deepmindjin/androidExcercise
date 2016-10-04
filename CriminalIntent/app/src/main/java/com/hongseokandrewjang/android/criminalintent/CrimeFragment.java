package com.hongseokandrewjang.android.criminalintent;

import android.os.Bundle;
import android.support.v4.app.Fragment;

public class CrimeFragment extends Fragment {
    private Crime crime;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        crime = new Crime();
    }
}
