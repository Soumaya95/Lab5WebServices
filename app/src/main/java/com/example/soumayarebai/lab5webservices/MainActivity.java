package com.example.soumayarebai.lab5webservices;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.soumayarebai.lab5webservices.POJO.Model;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

// retrofit bilio java tkhalini ncommunici maa wbservice
public class MainActivity extends AppCompatActivity {
TextView city,status,humidity,press;
    String[] cities={"Tunis","London","Paris"};
    Spinner spinner;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        city = (TextView) findViewById(R.id.tv_city);
        status = (TextView) findViewById(R.id.tv_status);
        humidity = (TextView) findViewById(R.id.tv_humidity);
        press = (TextView) findViewById(R.id.tv_press);
        spinner = (Spinner) findViewById(R.id.spinner);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line,cities);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            callWS(cities[position]);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        callWS(cities[0]);
    }

      public void callWS(String cityName){
        RestInterface restInterface=RestInterface.RETROFIT.create(RestInterface.class);
        Call< Model> call=restInterface.getWeatherReport(cityName);
        call.enqueue(new Callback<Model>() {
            @Override
            public void onResponse(Call<Model> call, Response<Model> response) {
                Model report=response.body();
                city.setText("City: "+report.getName());
                status.setText("Status: "+report.getWeather().get(0).getDescription());
                humidity.setText("Humidity: "+report.getMain().getHumidity().toString());
                press.setText("pressure: "+report.getMain().getPressure().toString());
            }

            @Override
            public void onFailure(Call<Model> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Failed to call we service\n"+t.getCause(), Toast.LENGTH_SHORT).show();

            }
        });
    }

    }

