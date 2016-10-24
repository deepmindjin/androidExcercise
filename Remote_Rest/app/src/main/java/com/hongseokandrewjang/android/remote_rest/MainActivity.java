package com.hongseokandrewjang.android.remote_rest;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    ListView mListView;
    Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mListView = (ListView)findViewById(R.id.listview_on_main);
        CustomAdapter adapter = new CustomAdapter(MainActivity.this);
        mListView.setAdapter(adapter);

        btn = (Button)findViewById(R.id.btnGetListview);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getData();
            }
        });
    }

    ArrayList<String> datas = new ArrayList<>();
    class CustomAdapter extends BaseAdapter{
        Context mContext;

        public CustomAdapter(Context context){
            mContext = context;
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
            if (convertView == null){
                convertView = View.inflate(MainActivity.this,android.R.layout.simple_list_item_1,parent);
            }
            return convertView;
        }
    }

    public void getData(){
        final String webUri = "http://192.168.0.163/sub/request";

        new AsyncTask<Void, Void, String>(){
            int sum=0;
            ProgressDialog progress;

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                progress = new ProgressDialog(MainActivity.this);
                progress.setTitle("찾는중");
                progress.setMessage(" - 자비스 가동중 - ");
                progress.setProgressStyle(ProgressDialog.STYLE_SPINNER);
                progress.setCancelable(true);
                progress.show();
            }

            @Override
            protected String doInBackground(Void... params) {
                String result ="";
                try {result = Remote.getData(webUri);
                }catch (Exception e){}
                return result;
            }

            @Override
            protected void onPostExecute(String s) {
                StringBuffer sb = new StringBuffer();
                super.onPostExecute(s);
                try {
                    JSONObject json = new JSONObject(s);
                    JSONArray rows = json.getJSONArray("root");

                    int rowsCount = rows.length();
                    for (int i = 0; i < rowsCount; i++) {
                        JSONObject result = (JSONObject) rows.get(i);
                        String value = result.getString("key");
                        sum = sum+Integer.parseInt(value);
                    }
                    progress.dismiss();
                }catch (Exception e){}
            }
        }.execute();
    }

    public static String postData(String webUrl, Map params) throws IOException{
        String result = "";
        URL url = new URL(webUrl);
        HttpURLConnection connection = (HttpURLConnection)url.openConnection();
        connection.setRequestMethod("POST");
        // post 처리일 경우만 ------------------------
        connection.setDoOutput(true);
        OutputStream os = connection.getOutputStream();
        ArrayList<String> key_set = new ArrayList<>(params.keySet());
        for (String key : key_set){
        }
        return result;
    }





















}
