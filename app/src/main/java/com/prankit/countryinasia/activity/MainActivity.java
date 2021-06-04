package com.prankit.countryinasia.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.util.Log;

import com.prankit.countryinasia.Api;
import com.prankit.countryinasia.R;
import com.prankit.countryinasia.adapter.CountryAdapter;
import com.prankit.countryinasia.model.CountryModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    public String BASE_URL = "https://restcountries.eu/rest/v2/region/";
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private RecyclerView.Adapter adapter;
    private Retrofit retrofit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolBar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("SpaceX Crew Members");

        recyclerView = findViewById(R.id.crew_recycle_view);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);

        getCountry();
    }

    private boolean isNetworkConnected() {
        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        return cm.getActiveNetworkInfo() != null && cm.getActiveNetworkInfo().isConnected();
    }

    private void getCountry(){
        ((Api) new Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create()).build().create(Api.class))
                .asia().enqueue(new Callback<List<CountryModel>>() {
            @Override
            public void onResponse(Call<List<CountryModel>> call, Response<List<CountryModel>> response) {
                if (response.isSuccessful()){
                    adapter = new CountryAdapter(MainActivity.this, response.body());
                    recyclerView.setAdapter(adapter);
                    recyclerView.setLayoutManager(layoutManager);
                } else {
                    Log.i("getDatError", response.errorBody().toString());
                }
            }

            @Override
            public void onFailure(Call<List<CountryModel>> call, Throwable t) {
                Log.i("getDatFail", t.getMessage() + "");
            }
        });
    }
}