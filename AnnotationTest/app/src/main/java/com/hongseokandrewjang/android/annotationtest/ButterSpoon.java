package com.hongseokandrewjang.android.annotationtest;

import android.widget.Button;

import java.lang.reflect.Field;

public class ButterSpoon {
    public static void bind(MainActivity object){

        // 특정되지 않은 field 들을 모두 꺼내기 위해서 자바의 reflection을 사용한다
        Class<?> klass = object.getClass();

        try {
            // 반복문을 돌면서 class 에 들어 있는 멤버 field들을 모두 꺼내고
            for(Field field : object.getClassLoader().loadClass(klass.getName()).getDeclaredFields()){
                // 멤버 필드에 BindView 에너테이션이 적용되어 있으면
                if (field.isAnnotationPresent(com.hongseokandrewjang.android.annotationtest.BindView.class)){
                    // 해당 필드를 통해
                    BindView bindView = field.getAnnotation(BindView.class);
                    // 뷰 ID를 가져오고
                    int viewID = bindView.viewId();
                    Button button = (Button)object.mButton;
                    // 뷰 ID를 해당 뷰에 적용한다.
                    button = (Button)object.findViewById(viewID);
                }
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
