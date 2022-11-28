package com.example.myapplication.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.myapplication.R;
import com.example.myapplication.utils.WeatherNetUtil;

public class Weather_Main extends AppCompatActivity {

    private Spinner sp_weather_city;
    private ArrayAdapter<String> arrayAdapter;
    private String[] citys;
    private TextView tv_weather_temp, tv_weather_info, tv_weather_temp_max_min, tv_weather_win, tv_weather_air;
    private ImageView iv_weather_show;
    private RecyclerView rlv_weather_future;
    private Handler handler = new Handler(Looper.myLooper()) {
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            if (msg.what == 0){
                String weather = (String) msg.obj;
                Log.d("awa","----主线程收到天气数据-weather----"+weather);
            }
        }
    };

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.weather_main);
        initview();
    }

    private void initview() {
        sp_weather_city = findViewById(R.id.sp_weather_city);
        tv_weather_temp = findViewById(R.id.tv_weather_temp);
        tv_weather_info = findViewById(R.id.tv_weather_info);
        tv_weather_temp_max_min = findViewById(R.id.tv_weather_temp_max_min);
        tv_weather_win = findViewById(R.id.tv_weather_win);
        tv_weather_air = findViewById(R.id.tv_weather_air);
        iv_weather_show = findViewById(R.id.iv_weather_show);
        rlv_weather_future = findViewById(R.id.rlv_weather_future);


        citys = getResources().getStringArray(R.array.cities);
        arrayAdapter = new ArrayAdapter<>(this, R.layout.sp_wearther_item_layout, citys);
        sp_weather_city.setAdapter(arrayAdapter);
        sp_weather_city.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selectedCity = citys[position];
                getWeatherOfCity(selectedCity);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    /**
     * 天气城市
     *
     * @param selectedCity 选择城市
     *                     在子线程中请求网络
     */
    private void getWeatherOfCity(String selectedCity) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                //请求网络
                String weatherOfCity = WeatherNetUtil.getWeatherOfCity(selectedCity);
                /*
                 * 使用handler将数据传递给主线程
                 * @param  使用Message.obtain(),在消息池中复用，提高使用效率
                 * */
                Message message = Message.obtain();
                message.what = 0;
                message.obj = weatherOfCity;
                handler.sendMessage(message);
            }
        }).start();
    }
}