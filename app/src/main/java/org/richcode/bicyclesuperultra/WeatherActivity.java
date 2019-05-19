package org.richcode.bicyclesuperultra;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import org.richcode.bicyclesuperultra.Adapter.RecordAdapter;
import org.richcode.bicyclesuperultra.Adapter.WeatherAdapter;
import org.richcode.bicyclesuperultra.DataClass.DataRecord;
import org.richcode.bicyclesuperultra.DataClass.DataWeather;

import java.util.ArrayList;

public class WeatherActivity extends AppCompatActivity {

    RecyclerView WeatherList;
    ArrayList<DataWeather> weather_list;
    RecyclerView.LayoutManager layoutManager;
    RecyclerView.Adapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather);
        WeatherList = (RecyclerView)findViewById(R.id.weather_list);

        WeatherList.setHasFixedSize(true);

        layoutManager = new GridLayoutManager(getApplicationContext(),2);
        WeatherList.setLayoutManager(layoutManager);

        weather_list = new ArrayList<DataWeather>();
        adapter = new WeatherAdapter(weather_list,this);
        WeatherList.setAdapter(adapter);

        weather_list.add(new DataWeather("수원","Suigen"));
        weather_list.add(new DataWeather("오산","Osan"));
        weather_list.add(new DataWeather("화성","Hwaseong"));
        weather_list.add(new DataWeather("서울","Seoul"));
        weather_list.add(new DataWeather("전주","Jeonju"));
        weather_list.add(new DataWeather("대전","Daejeon"));
        weather_list.add(new DataWeather("대구","Daegu"));
        weather_list.add(new DataWeather("부산","Busan"));
        weather_list.add(new DataWeather("속초","Sogcho"));
        weather_list.add(new DataWeather("제주","Jeju"));

        adapter.notifyDataSetChanged();
    }
}
