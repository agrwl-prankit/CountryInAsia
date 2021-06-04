package com.prankit.countryinasia;

import com.prankit.countryinasia.model.CountryModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface Api {

    @GET("asia")
    Call<List<CountryModel>> asia();
}
