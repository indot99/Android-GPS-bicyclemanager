package org.richcode.bicyclesuperultra.Adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.kwabenaberko.openweathermaplib.constants.Lang;
import com.kwabenaberko.openweathermaplib.constants.Units;
import com.kwabenaberko.openweathermaplib.implementation.OpenWeatherMapHelper;
import com.kwabenaberko.openweathermaplib.implementation.callbacks.CurrentWeatherCallback;
import com.kwabenaberko.openweathermaplib.models.currentweather.CurrentWeather;

import org.richcode.bicyclesuperultra.DataClass.DataWeather;
import org.richcode.bicyclesuperultra.R;

import java.util.ArrayList;

public class WeatherAdapter extends RecyclerView.Adapter<WeatherAdapter.ViewHolder> {
    private ArrayList<DataWeather> list = new ArrayList<DataWeather>();
    private Context context;

    public WeatherAdapter(ArrayList<DataWeather> WeatherList, Context context){
        this.list = WeatherList;
        this.context = context;
    }

    @NonNull
    @Override
    public WeatherAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_weather,viewGroup,false);

        WeatherAdapter.ViewHolder vh = new WeatherAdapter.ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull final WeatherAdapter.ViewHolder holder, final int position) {
        final int pos = position;

        holder.TextLoc.setText(list.get(pos).getLockor());
        final OpenWeatherMapHelper helper = new OpenWeatherMapHelper("6932d492475637411225676b4e7105ce");
        helper.setUnits(Units.METRIC);
        helper.setLang(Lang.KOREAN);


        helper.getCurrentWeatherByCityName(list.get(pos).getLoceng(), new CurrentWeatherCallback() {
            @Override
            public void onSuccess(CurrentWeather currentWeather) {

                if(currentWeather.getMain().getTemp()>30)
                    holder.TextTemp.setTextColor(Color.parseColor("#FF5A5A"));
                if(currentWeather.getMain().getTemp()<10)
                    holder.TextTemp.setTextColor(Color.parseColor("#2478FF"));

                holder.TextTemp.setText(currentWeather.getMain().getTemp()+"℃");
                holder.TextWeather.setText(transferWeather(currentWeather.getWeather().get(0).getMain()));
                holder.TextWindSpeed.setText("풍속 : " + currentWeather.getWind().getSpeed()+"m/s");
                holder.TextCloud.setText("구름 : " + currentWeather.getClouds().getAll()+"%");
            }

            @Override
            public void onFailure(Throwable throwable) {

            }
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView TextLoc;
        TextView TextWindSpeed;
        TextView TextTemp;
        TextView TextCloud;
        TextView TextWeather;

        public ViewHolder(View view) {
            super(view);

            TextLoc = (TextView)view.findViewById(R.id.item_weather_location);
            TextWindSpeed = (TextView)view.findViewById(R.id.item_weather_windspeed);
            TextTemp = (TextView)view.findViewById(R.id.item_weather_temp);
            TextWeather = (TextView)view.findViewById(R.id.item_weather_weather);
            TextCloud = (TextView)view.findViewById(R.id.item_weather_cloud);

        }
    }

    private String transferWeather(String weather){
        weather = weather.toLowerCase();

        if(weather.equals("haze")){
            return "흐림";
        }else if(weather.equals("fog")){
            return "안개";
        }else if(weather.equals("clouds")){
            return "구름";
        }else if(weather.equals("few clouds")){
            return "구름 조금";
        }else if(weather.equals("scattered clouds")){
            return "구름 낌";
        }else if(weather.equals("broken clouds")){
            return "구름 많음";
        }else if(weather.equals("overcast clouds")){
            return "구름 많음";
        }else if(weather.equals("clear sky")){
            return "맑음";
        }else if(weather.equals("rain")){
            return "비옴";
        }else if(weather.equals("clear")){
            return "맑음";
        }else if(weather.equals("snow")){
            return "눈내림";
        }

        return "";
    }

}
