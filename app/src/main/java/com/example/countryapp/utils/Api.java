package com.example.countryapp.utils;

import com.example.countryapp.models.Country;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.GET;

public interface Api {


    @GET("rest/v2/region/asia")
    Call<ArrayList<Country>> getCountries();

}
