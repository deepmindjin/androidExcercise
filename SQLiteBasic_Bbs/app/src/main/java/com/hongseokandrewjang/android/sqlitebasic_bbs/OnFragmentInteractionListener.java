package com.hongseokandrewjang.android.sqlitebasic_bbs;

import java.util.ArrayList;

public interface OnFragmentInteractionListener {
        // 일반 Action을 처리하는 함수
        void onFragmentInteraction(int actionFlag);
//        // 메인의 목록을 가져오는 함수
        ArrayList<BbsData> getDatas();
//        // 개별 레코드(글)을 가져오는 함수
        BbsData getData(int position);

        void setPosition(int position);

        int getPosition();

        void addData(String title, String name, String contents);
}
