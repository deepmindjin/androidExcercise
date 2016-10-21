package com.hongseokandrewjang.android.customize_component;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by HongSeokAndrewJang on 2016-10-20.
 */

public class Today extends TextView{

    public String delimiter;

    public Today(Context context) {
        super(context);
    }

    public Today(Context context, AttributeSet attrs) {
        super(context, attrs);
        // attrs
        // 해당 이름으로 정의된 attr의 개수를 가져오고
        TypedArray typedArray = context.obtainStyledAttributes(attrs,R.styleable.Today);
        int size = typedArray.getIndexCount();
        // 반복문을 돌면서 해당 attr에 입력된 값을 처리해 준다
        for (int i=0;i<size;i++){
            int attr = typedArray.getIndex(i);
            switch (attr){
                case R.styleable.Today_delimiter:
                    delimiter = typedArray.getString(attr);
                    setDate();
                    break;
            }
        }
    }

    private void setDate(){
        Date today = Calendar.getInstance().getTime();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy"+delimiter+"MM"+delimiter+"dd");
        setText(sdf.format(today));
    }

    public Today(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }
}
