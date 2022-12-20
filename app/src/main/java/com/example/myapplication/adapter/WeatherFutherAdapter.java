package com.example.myapplication.adapter;

import android.content.Context;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.myapplication.R;
import com.example.myapplication.activity.Weather_Main;
import com.example.myapplication.bean.DayWeather;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import lombok.Data;

public class WeatherFutherAdapter extends RecyclerView.Adapter<WeatherFutherAdapter.futherViewHolder> {
    private Context weaContext;
    private List<DayWeather> futherweaBean;
    Weather_Main w = new Weather_Main();

    public WeatherFutherAdapter(Context weaContext,List<DayWeather>futherweaBean) {
        this.weaContext = weaContext;
        this.futherweaBean = futherweaBean;
    }

    @NonNull
    @Override
    public futherViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(weaContext).inflate(R.layout.weather_futher_item, parent, false);
        futherViewHolder futherViewHolder = new futherViewHolder(view);
        return futherViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull futherViewHolder holder, int position) {
        DayWeather dayWeather = futherweaBean.get(position);
        holder.weatherItemInfo.setText(dayWeather.getWea());
        holder.weatherItemAir.setText(String.valueOf("空气:"+dayWeather.getAir()));
        holder.weatherItemDate.setText(dayWeather.getDate());
        holder.weatherItemTemp.setText(dayWeather.getTem());
        holder.weatherItemWin.setText(dayWeather.getWin()[0]+dayWeather.getWinSpeed());
        holder.weatherItemTempMaxMin.setText(dayWeather.getTem2()+"~"+dayWeather.getTem1());
        holder.weatherItemShow.setImageResource(w.getImageResOfWeather(dayWeather.getWeaImg()));
    }

    @Override
    public int getItemCount() {
        return (futherweaBean == null) ? 0 : futherweaBean.size();
    }

    class futherViewHolder extends RecyclerView.ViewHolder {
        private ImageView weatherItemShow;
        private TextView weatherItemTemp;
        private TextView weatherItemInfo;
        private TextView weatherItemDate;
        private TextView weatherItemTempMaxMin;
        private TextView weatherItemWin;
        private TextView weatherItemAir;


        public futherViewHolder(@NonNull View itemView) {
            super(itemView);
            weatherItemShow = itemView.findViewById(R.id.weather_item_show);
            weatherItemTemp = itemView.findViewById(R.id.weather_item_temp);
            weatherItemInfo = itemView.findViewById(R.id.weather_item_info);
            weatherItemDate = itemView.findViewById(R.id.weather_item_date);
            weatherItemTempMaxMin = itemView.findViewById(R.id.weather_item_temp_max_min);
            weatherItemWin = itemView.findViewById(R.id.weather_item_win);
            weatherItemAir = itemView.findViewById(R.id.weather_item_air);
        }
    }
}
