package com.hongseokandrewjang.android.remote_httpurlconnection;

import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class Remote {
    private static final String TAG = "REMOTE";



    public static String getData(String webUrl) throws IOException {
        StringBuffer result = new StringBuffer();
        URL url = new URL(webUrl);
        // 아래 한 줄로 webUrl 주소에 해당하는 서버와 연결이 된 상태가 된다
        // 아래에 있는걸로 내가 http와 통신을 시작하겠다고 말해줌
        HttpURLConnection connection = (HttpURLConnection)url.openConnection();
        // REST API = GET(조회), POST(입력), DELETE, PUT(수정)
        // DELETE와 PUT은 지원하지 않는 서버가 있음
        connection.setRequestMethod("GET");
        // 얘가 지금 내 요청에 정상적으로 통신햇는지 알려주는거
        int responseCode = connection.getResponseCode();
        // 코드는 상수로 정해져있음
        if (responseCode == HttpURLConnection.HTTP_OK){
            // 여기서 이미 연결이 되어있는 상태
            BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String dataLine = null;
            while((dataLine = br.readLine()) != null){
                result.append(dataLine);
            }
            br.close();
        }else{
            Log.e(TAG,"HTTP error code= "+responseCode);
        }
        return result.toString();
    }
}
