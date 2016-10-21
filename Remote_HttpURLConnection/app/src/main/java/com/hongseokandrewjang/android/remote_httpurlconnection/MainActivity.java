package com.hongseokandrewjang.android.remote_httpurlconnection;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {

    // http://openAPI.seoul.go.kr:8088/(인증키)/xml/GeoInfoWalkway/1/5

    Button btnCallNaver;
    TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnCallNaver = (Button)findViewById(R.id.btnCallOpenApi);
        tv = (TextView)findViewById(R.id.textView);
        btnCallNaver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getNaver();
            }
        });
    }
    private void getNaver(){
        new AsyncTask<Void, Void, String>(){
            ProgressDialog progress;

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                progress = new ProgressDialog(MainActivity.this);
                progress.setTitle("다운로드");
                progress.setMessage("downloading...");
                progress.setProgressStyle(ProgressDialog.STYLE_SPINNER);
                progress.setCancelable(false);
                progress.show();
            }

            @Override
            protected String doInBackground(Void... params) {
                String result ="";
                try {result = Remote.getData("http://openAPI.seoul.go.kr:8088/sample/json/GeoInfoWalkwayWGS/1/5");
                }catch (Exception e){}
                return result;
            }

            // 위에서 보낸 return 값을 밑에서 s로 받는다
            @Override
            protected void onPostExecute(String s) {
                StringBuffer sb = new StringBuffer();
                super.onPostExecute(s);
                try {

                    // 전체 문자열을 JSON 오브젝트로 변환
                    // json object로 바꿔준다
                    JSONObject json = new JSONObject(s);
                    // 특정 키를 가진 단일 값을 꺼낸다
                    JSONObject topObject = json.getJSONObject("GeoInfoWalkwayWGS");
                    // 특정 키를 가진 배열 형태의 값을 꺼낸다
                    // 반복되는 부분을 가져올 수 있다.
                    JSONArray rows = topObject.getJSONArray("row");
                    // 배열을 반복문을 돌면서 배열의 index로 값을 꺼낸다
                    int rowsCount = rows.length();
                    for (int i = 0; i < rowsCount; i++) {
                        JSONObject result = (JSONObject) rows.get(i);
                        // 최종적으로 각 열의 컬럼의 키이름에 해당하는 값을 꺼낸다
                        String RV_CD = result.getInt("RV_CD")+"";
                        sb.append(RV_CD + "\n");
                    }
                    tv.setText(sb.toString());
                    progress.dismiss();
                }catch (Exception e){}
            }
        }.execute();
    }
}
