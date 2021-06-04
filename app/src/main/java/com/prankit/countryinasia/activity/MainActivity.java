package com.prankit.countryinasia.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import android.content.Context;
import android.net.ConnectivityManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.prankit.countryinasia.Api;
import com.prankit.countryinasia.R;
import com.prankit.countryinasia.RoomDB;
import com.prankit.countryinasia.adapter.CountryAdapter;
import com.prankit.countryinasia.model.CountryModel;
import com.prankit.countryinasia.model.RoomModel;

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
    private RoomDB db;
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

        db = Room.databaseBuilder(getApplicationContext(), RoomDB.class, "countryDb").allowMainThreadQueries().build();
        retrofit = new Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create()).build();
        convertOnlineToOffline();
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.i("datadata", String.valueOf(isNetworkConnected()));
        if (isNetworkConnected()) getCountryOnline();
        else getCountryOffline();
    }

    private boolean isNetworkConnected() {
        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        return cm.getActiveNetworkInfo() != null && cm.getActiveNetworkInfo().isConnected();
    }

    private void getCountryOnline(){
        ((Api) retrofit.create(Api.class)).asia().enqueue(new Callback<List<CountryModel>>() {
            @Override
            public void onResponse(Call<List<CountryModel>> call, Response<List<CountryModel>> response) {
                if (response.isSuccessful()){
                    adapter = new CountryAdapter(MainActivity.this, response.body(), null, 1);
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

    private void getCountryOffline(){
        Log.i("datadata", "offline");
        GetCountryAsyncTask asyncTask = new GetCountryAsyncTask();
        List<RoomModel> list = asyncTask.doInBackground();
        Log.i("datadata", "offline "+list.size());
        if (list.size() == 0){
            adapter = new CountryAdapter(MainActivity.this, null, list, 2);
            recyclerView.setAdapter(adapter);
            recyclerView.setLayoutManager(layoutManager);
            Toast.makeText(this, "No offline member found", Toast.LENGTH_SHORT).show();
        }
        else{
            for (int pos = 0; pos < list.size(); pos++) {
                adapter = new CountryAdapter(MainActivity.this, null, list, 2);
                recyclerView.setAdapter(adapter);
                recyclerView.setLayoutManager(layoutManager);
            }
        }
    }

    private class GetCountryAsyncTask extends AsyncTask<Void, Void, List<RoomModel>> {
        @Override
        protected List<RoomModel> doInBackground(Void... voids) {
            return db.roomDAO().getAllCountry();
        }
    }

    private void convertOnlineToOffline(){
        GetCountryAsyncTask countryAsyncTask = new GetCountryAsyncTask();
        List<RoomModel> list = countryAsyncTask.doInBackground();
        if (list.size() == 0 && isNetworkConnected()){
            ((Api) retrofit.create(Api.class)).asia().enqueue(new Callback<List<CountryModel>>() {
                @Override
                public void onResponse(Call<List<CountryModel>> call, Response<List<CountryModel>> response) {
                    if (response.isSuccessful()) {
                        List<CountryModel> list1 = response.body();
                        for (int pos = 0; pos < list1.size(); pos++) {
                            RoomModel country = new RoomModel(list1.get(pos).getName(), list1.get(pos).getCapital(), list1.get(pos).getFlag(),
                                    list1.get(pos).getRegion(), list1.get(pos).getSubregion(), list1.get(pos).getPopulation(),
                                    list1.get(pos).getLanguages().get(0).getLanguage_name());
                            db.roomDAO().addCountry(country);
                        }
                        getCountryOnline();
                    } else {
                        Log.i("offlineError", response.errorBody().toString());
                    }
                }

                @Override
                public void onFailure(Call<List<CountryModel>> call, Throwable t) {
                    Log.i("offlineFail", t.getMessage());
                }
            });
        }
    }
}