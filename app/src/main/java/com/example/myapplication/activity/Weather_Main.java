package com.example.myapplication.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import okhttp3.OkHttpClient;

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
import com.example.myapplication.adapter.WeatherFutherAdapter;
import com.example.myapplication.bean.DayWeather;
import com.example.myapplication.bean.Weather;
import com.example.myapplication.utils.WeatherNetUtil;
import com.google.gson.Gson;

import java.time.DayOfWeek;
import java.util.List;

public class Weather_Main extends AppCompatActivity {

    private Spinner sp_weather_city;
    private ArrayAdapter<String> arrayAdapter;
    private String[] citys;
    private TextView tv_weather_temp, tv_weather_info, tv_weather_temp_max_min, tv_weather_win, tv_weather_air;
    private ImageView iv_weather_show;
    private RecyclerView rlv_weather_future;
    private WeatherFutherAdapter futherrlvAdapter;
    private Handler handler = new Handler(Looper.myLooper()) {
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            if (msg.what == 0) {
                String weather = (String) msg.obj;
                Log.d("awa", "----主线程收到天气数据-weather----" + weather);
                Gson gson = new Gson();
                Weather wbean = gson.fromJson(weather, Weather.class);
                Log.d("awa", "----解析后的数据-w----" + wbean.toString());
                updateUiofWeather(wbean);

            }
        }
    };

    private void updateUiofWeather(Weather w) {
        /*
         * 将数据填充到界面中
         * */
        if (w == null) {
            return;
        }

        List<DayWeather> dayWeathers = w.getDayWeathers();
        DayWeather todayWeather = dayWeathers.get(0);
        if (todayWeather == null) {
            return;
        }
        tv_weather_temp.setText(todayWeather.getTem());
        tv_weather_info.setText(todayWeather.getWea() + "(" + todayWeather.getDate() + todayWeather.getWeek() + ")");
        tv_weather_temp_max_min.setText(todayWeather.getTem2() + "~" + todayWeather.getTem1());
        tv_weather_win.setText(todayWeather.getWin()[0] + todayWeather.getWinSpeed());
        tv_weather_air.setText("空气" + todayWeather.getAir() + todayWeather.getAirLevel() + "\n" + todayWeather.getAirTips());
        iv_weather_show.setImageResource(getImageResOfWeather(todayWeather.getWeaImg()));
        dayWeathers.remove(0);//除去当天的天气
        futherrlvAdapter = new WeatherFutherAdapter(this, dayWeathers);
        rlv_weather_future.setAdapter(futherrlvAdapter);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, RecyclerView.HORIZONTAL, false);
        rlv_weather_future.setLayoutManager(linearLayoutManager);
    }

    public int getImageResOfWeather(String weaStr) {
//xue、lei、shachen、wu、bingbao、yun、yu、yin、qing
        int result = 0;
        switch (weaStr) {
            case "xue":
                result = R.drawable.biz_plugin_weather_daxue;
                break;
            case "lei":
                result = R.drawable.biz_plugin_weather_leizhenyubingbao;
                break;
            case "shachen":
                result = R.drawable.biz_plugin_weather_shachenbao;
                break;
            case "wu":
                result = R.drawable.biz_plugin_weather_wu;
                break;
            case "bingbao":
                result = R.drawable.biz_plugin_weather_leizhenyubingbao;
                break;
            case "yun":
                result = R.drawable.biz_plugin_weather_duoyun;
                break;
            case "yu":
                result = R.drawable.biz_plugin_weather_dayu;
                break;
            case "yin":
                result = R.drawable.biz_plugin_weather_yin;
                break;
            case "qing":
                result = R.drawable.biz_plugin_weather_qing;
                break;
            default:
                result = R.drawable.biz_plugin_weather_qing;
                break;
        }
        return result;
    }

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