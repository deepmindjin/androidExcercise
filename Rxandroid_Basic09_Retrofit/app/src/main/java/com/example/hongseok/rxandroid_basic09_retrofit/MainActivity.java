package com.example.hongseok.rxandroid_basic09_retrofit;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;
import rx.Scheduler;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {

    static final String BaseURL = "http://api.openweathermap.org";
    static final String APIKey = "52611ad848eecb8b1ecdf323d61a1d4d";
    TextView tvResult;
    EditText etCityInput;
    Button btnGetWeather;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnGetWeather = (Button)findViewById(R.id.btnGetWeather);
        btnGetWeather.setOnClickListener(e -> getWeather());
        etCityInput = (EditText)findViewById(R.id.etCityInput);
        tvResult = (TextView)findViewById(R.id.tvResult);
    }

    public void getWeather(){
        String cityName = etCityInput.getText().toString();
        // 1. 레트로핏 클라이언트 생성
        Retrofit client = new Retrofit.Builder()
                .baseUrl(BaseURL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();

        // 2. Rest API 서비스 생성
        IWeather service = client.create(IWeather.class);

        // 3. 데이터 Observable 생성
        Observable<Data> weatherData = service.getData(cityName,APIKey);

        // 4. subScribeOn =
        //  A. 데이터를 가져오는 대상 : newThread 로 새로운 Thread 에서 작업한다
        //  B. 화면에 세팅하는 Observer : mainThread 에서 작업한다.
       weatherData.subscribeOn(Schedulers.newThread())
               .observeOn(AndroidSchedulers.mainThread())
               .subscribe(
                       data -> {
                           String result = "";
                           result = result + "ID:" + data.getId();
                           result = result + "\nNAME:" + data.getName();
                           result = result + "\nBASE:" + data.getBase();

                           tvResult.setText(result);
                       }
               );

    }
}
