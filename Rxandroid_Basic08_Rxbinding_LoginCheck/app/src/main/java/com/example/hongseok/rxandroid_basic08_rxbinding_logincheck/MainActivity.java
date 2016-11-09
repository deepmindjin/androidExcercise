package com.example.hongseok.rxandroid_basic08_rxbinding_logincheck;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import com.jakewharton.rxbinding.widget.RxTextView;
import com.jakewharton.rxbinding.widget.TextViewTextChangeEvent;

import rx.Observable;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnSign = (Button)findViewById(R.id.btnSignIn);

        Observable<TextViewTextChangeEvent> idObs = RxTextView.textChangeEvents((EditText)findViewById(R.id.etId));
        Observable<TextViewTextChangeEvent> passwordObs = RxTextView.textChangeEvents((EditText)findViewById(R.id.etPassword));

        Observable.combineLatest(idObs,passwordObs,
                (idChange,pwChanges) -> {
                    boolean idCheck = idChange.text().length() >= 5;
                    boolean passwordCheck = pwChanges.text().length() >= 8;
                    return idCheck&&passwordCheck;
                })
                .subscribe(
                        checkFlag -> btnSign.setEnabled(checkFlag)
                );
    }
}
