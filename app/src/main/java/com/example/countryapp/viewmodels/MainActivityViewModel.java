package com.example.countryapp.viewmodels;

import android.app.Application;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.countryapp.models.Country;
import com.example.countryapp.repositories.MainActivityRepository;
import com.example.countryapp.utils.Api;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivityViewModel extends AndroidViewModel {

    private LiveData<List<Country>> mExampleData;
    private MainActivityRepository mainActivityRepository;

    public MainActivityViewModel(@NonNull  Application application) {
        super(application);
        mainActivityRepository= new MainActivityRepository(application);
        mExampleData = mainActivityRepository.getAll();
    }

    public void delete(){mainActivityRepository.delete();}

    public LiveData<List<Country>> getAll()
    {
        return mExampleData;
    }





    public void makeApiCall()
    {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://restcountries.eu/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        Api users = retrofit.create(Api.class);


        Call<ArrayList<Country>> call = users.getCountries();

        call.enqueue(new Callback<ArrayList<Country>>() {
            @Override
            public void onResponse(Call<ArrayList<Country>> call, Response<ArrayList<Country>> response) {
                if(!response.isSuccessful())
                {
                    Log.d("fail", response.code() +" ");
                }
                else
                {
                    mainActivityRepository.insert(response.body());
                    Log.d("size", "size");
                }
            }

            @Override
            public void onFailure(Call<ArrayList<Country>> call, Throwable t) {

            }
        });
    }


}

