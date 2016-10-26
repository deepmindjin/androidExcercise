package com.hongseokandrewjang.android.remote_retrofit;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Path;

/*
    OPEN API KEY : 4b677653716465653239614e6e636a
    서울시 도시 고속도로 월간 돌발 통계
    ADDRESS : http://openapi.seoul.go.kr:8088/4b677653716465653239614e6e636a/xml/MonthlyOutbreakStatsKor/1/5
                                                                                    여기가 서비스명
 */

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String key = "4b677653716465653239614e6e636a";
        String serviceName = "MonthlyOutbreakStatsKor";
        int begin = 1;
        int end = 10;

        // 1. Retrofit client 생성
        Retrofit client = new Retrofit.Builder().baseUrl("http://openapi.seoul.go.kr:8088") // base url 지정
                .addConverterFactory(GsonConverterFactory.create()). // json 컨버팅 라이브러리 지정
                        build();

        // 2. Retrofit client 에서 사용할 interface 지정
        ISeoulOpenData service = client.create(ISeoulOpenData.class);

        // 3. interface(서비스)를 통해서 데이터를 호출한다
        Call<RemoteData> remoteData = service.getData(key, serviceName, begin, end);

        // 4. 비동기 데이터를 받기 위한 리스너 셋팅
        remoteData.enqueue(new Callback<RemoteData>() {
            @Override
            public void onResponse(Call<RemoteData> call, Response<RemoteData> response) {
                if (response.isSuccessful()){
                    RemoteData data = response.body();
                    for (RemoteData.Row row : data.getMonthlyOutbreakStatsKor().getRow()){
                        Log.i("Remote",row.getACCIDENT());
                    }
                }else{
                    Log.e("RemoteData",response.message());
                }
            }

            @Override
            public void onFailure(Call<RemoteData> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }
}

interface ISeoulOpenData{
    @GET("/{key}/json/{serviceName}/{begin}/{end}")
    Call getData(@Path("key") String key, @Path("serviceName")String serviceName, @Path("begin")int begin, @Path("end")int end);

}

class RemoteData
{
    class MonthlyOutbreakStatsKor
    {
        private RESULT RESULT;

        private String list_total_count;

        private Row[] row;

        public RESULT getRESULT ()
        {
            return RESULT;
        }

        public void setRESULT (RESULT RESULT)
        {
            this.RESULT = RESULT;
        }

        public String getList_total_count ()
        {
            return list_total_count;
        }

        public void setList_total_count (String list_total_count)
        {
            this.list_total_count = list_total_count;
        }

        public Row[] getRow ()
        {
            return row;
        }

        public void setRow (Row[] row)
        {
            this.row = row;
        }

        @Override
        public String toString()
        {
            return "ClassPojo [RESULT = "+RESULT+", list_total_count = "+list_total_count+", row = "+row+"]";
        }
    }

    class Row
    {
        private String ACCIDENT;

        private String MAINTENACE_WK;

        private String ETC;

        private String FALL;

        private String YEAR;

        private String MONTH;

        private String ROADNM;

        private String BREAKDOWN;

        private String NO_OUTBK_SIT;

        public String getACCIDENT ()
        {
            return ACCIDENT;
        }

        public void setACCIDENT (String ACCIDENT)
        {
            this.ACCIDENT = ACCIDENT;
        }

        public String getMAINTENACE_WK ()
        {
            return MAINTENACE_WK;
        }

        public void setMAINTENACE_WK (String MAINTENACE_WK)
        {
            this.MAINTENACE_WK = MAINTENACE_WK;
        }

        public String getETC ()
        {
            return ETC;
        }

        public void setETC (String ETC)
        {
            this.ETC = ETC;
        }

        public String getFALL ()
        {
            return FALL;
        }

        public void setFALL (String FALL)
        {
            this.FALL = FALL;
        }

        public String getYEAR ()
        {
            return YEAR;
        }

        public void setYEAR (String YEAR)
        {
            this.YEAR = YEAR;
        }

        public String getMONTH ()
        {
            return MONTH;
        }

        public void setMONTH (String MONTH)
        {
            this.MONTH = MONTH;
        }

        public String getROADNM ()
        {
            return ROADNM;
        }

        public void setROADNM (String ROADNM)
        {
            this.ROADNM = ROADNM;
        }

        public String getBREAKDOWN ()
        {
            return BREAKDOWN;
        }

        public void setBREAKDOWN (String BREAKDOWN)
        {
            this.BREAKDOWN = BREAKDOWN;
        }

        public String getNO_OUTBK_SIT ()
        {
            return NO_OUTBK_SIT;
        }

        public void setNO_OUTBK_SIT (String NO_OUTBK_SIT)
        {
            this.NO_OUTBK_SIT = NO_OUTBK_SIT;
        }

        @Override
        public String toString()
        {
            return "ClassPojo [ACCIDENT = "+ACCIDENT+", MAINTENACE_WK = "+MAINTENACE_WK+", ETC = "+ETC+", FALL = "+FALL+", YEAR = "+YEAR+", MONTH = "+MONTH+", ROADNM = "+ROADNM+", BREAKDOWN = "+BREAKDOWN+", NO_OUTBK_SIT = "+NO_OUTBK_SIT+"]";
        }
    }

    class RESULT
    {
        private String MESSAGE;

        private String CODE;

        public String getMESSAGE ()
        {
            return MESSAGE;
        }

        public void setMESSAGE (String MESSAGE)
        {
            this.MESSAGE = MESSAGE;
        }

        public String getCODE ()
        {
            return CODE;
        }

        public void setCODE (String CODE)
        {
            this.CODE = CODE;
        }

        @Override
        public String toString()
        {
            return "ClassPojo [MESSAGE = "+MESSAGE+", CODE = "+CODE+"]";
        }
    }

    private MonthlyOutbreakStatsKor MonthlyOutbreakStatsKor;

    public MonthlyOutbreakStatsKor getMonthlyOutbreakStatsKor ()
    {
        return MonthlyOutbreakStatsKor;
    }

    public void setMonthlyOutbreakStatsKor (MonthlyOutbreakStatsKor MonthlyOutbreakStatsKor)
    {
        this.MonthlyOutbreakStatsKor = MonthlyOutbreakStatsKor;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [MonthlyOutbreakStatsKor = "+MonthlyOutbreakStatsKor+"]";
    }
}

