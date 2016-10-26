package com.hongseokandrewjang.android.remote_httpurlconnection_cookie;

import android.app.ProgressDialog;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.net.HttpCookie;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    TextView tvResult;
    EditText etPassword;
    EditText etId;
    Button btnSignIn;

    SharedPreferences.Editor editor;
    SharedPreferences sp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sp = getApplicationContext().getSharedPreferences("cookie",0); //
        editor = sp.edit();

        etPassword = (EditText)findViewById(R.id.inputPassword);
        etId = (EditText)findViewById(R.id.inputId);
        tvResult = (TextView)findViewById(R.id.tvResult);
        btnSignIn = (Button)findViewById(R.id.btnSignin);
        btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signIn();
            }
        });

        tvResult.setText("USER ID : "+sp.getString("USERID",null)+"\nUSER PW : "+sp.getString("USERPW",null));
    }

    private void signIn(){

        Map userinfo = new HashMap();
        userinfo.put("user_id",etId.getText().toString());
        userinfo.put("user_pw",etPassword.getText().toString());

        new AsyncTask<Map,Void,String>(){
            ProgressDialog mDialog;

            @Override
            protected String doInBackground(Map... params) {
                String result = "";
                String url = "http://192.168.0.163/setCookies.jsp";
                try{
                    result = Remote.postData(url, params[0], "POST");
                }catch (Exception e){e.printStackTrace();}
                return result;
            }

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                mDialog = new ProgressDialog(MainActivity.this);
                mDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
                mDialog.setMessage("downloading...");
                mDialog.setTitle("다운로드");
                mDialog.setCancelable(false);
                mDialog.show();
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);

                //
                List<HttpCookie> cookies = Remote.sCookieManager.getCookieStore().getCookies();
                StringBuffer cookieString = new StringBuffer();

                for (HttpCookie cookie : cookies){
                    if (!cookie.getName().equals("JSESSIONID")) {
                        cookieString.append(cookie.getName() + "=" + cookie.getValue() + "\n");
                        editor.putString(cookie.getName(),cookie.getValue());
                        // 삭제 editor.remove("키"),   editor.clear();하면 다날라감
                    }
                }
                editor.commit();
                tvResult.setText("USER ID : "+sp.getString("USERID",null)+"\nUSER PW : "+sp.getString("USERPW",null));
                mDialog.dismiss();
            }
        }.execute(userinfo);
    }
}
