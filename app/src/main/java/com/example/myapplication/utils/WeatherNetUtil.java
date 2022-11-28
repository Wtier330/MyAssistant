package com.example.myapplication.utils;

import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class WeatherNetUtil {
    public static final String URL_WEATHER_WITH_FUTURE = "https://tianqiapi.com/api?version=v1&appid=36646344&appsecret=c1lgQbP9";

    public static String doGet(String urlStr) {
        String result = "";
        //连接网络
        HttpURLConnection connection = null;
        InputStreamReader inputStreamReader = null;
        BufferedReader bufferedReader = null;

        try {
            URL url = new URL(urlStr);
            connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setConnectTimeout(5000);
            //从连接中读取数据(二进制)
            InputStream inputStream = connection.getInputStream();
            inputStreamReader = new InputStreamReader(inputStream);
            //二进制流进入缓冲区
            bufferedReader = new BufferedReader(inputStreamReader);
            //从缓冲区一行行读取字符串进行拼接
            String line = "";
            StringBuilder stringBuilder = new StringBuilder();
            while ((line = bufferedReader.readLine()) != null) {
                stringBuilder.append(line);
            }
            result = stringBuilder.toString();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (connection != null) {
                connection.disconnect();
            }
            if (inputStreamReader != null) {
                try {
                    inputStreamReader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                if (bufferedReader != null) {
                    try {
                        bufferedReader.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }

        return result;
    }

    public static String getWeatherOfCity(String city) {
        String weatherResult = "";
        //拼接出获取天气数据的url
        //https://tianqiapi.com/api?version=v1&appid=36646344&appsecret=c1lgQbP9
        String weatherUrl = URL_WEATHER_WITH_FUTURE + "&city=" + city;
        //打印测试
        Log.d("awa", "-------weatherUrl---------" + weatherUrl);
        weatherResult = doGet(weatherUrl);
        Log.d("awa", "-------weatherResult---------" + weatherResult);

        return weatherResult;

    }
}
