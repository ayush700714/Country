package com.example.countryapp.repositories;

import android.app.Application;
import android.os.AsyncTask;
import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.countryapp.dao.CountryDao;
import com.example.countryapp.database.CountryDatabase;
import com.example.countryapp.models.Country;
import com.example.countryapp.utils.Api;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

// singleton
public class MainActivityRepository {

    private CountryDao countryDao;
    private LiveData<List<Country>> allCountry;

    public MainActivityRepository(Application application) {
        CountryDatabase database = CountryDatabase.getInstance(application);
        countryDao = database.countryDao();
        allCountry = countryDao.getAll();
    }
    public void insert(List<Country> country) {
        new InsertNoteAsyncTask(countryDao).execute(country);
    }
    public void delete()
    {
        new DeleteAllNotesAsyncTask(countryDao).execute();
    }

    public LiveData<List<Country>> getAll()
    {
        return allCountry;
    }



    private static class InsertNoteAsyncTask extends AsyncTask<List<Country>, Void, Void> {
        private CountryDao countryDao;
        private InsertNoteAsyncTask(CountryDao countryDao) {
            this.countryDao = countryDao;
        }
        @Override
        protected Void doInBackground(List<Country>... notes) {
            countryDao.insert(notes[0]);
            return null;
        }

    }
    private static class DeleteAllNotesAsyncTask extends AsyncTask<Void, Void, Void> {
        private CountryDao countryDao;
        private DeleteAllNotesAsyncTask(CountryDao countryDao) {
            this.countryDao = countryDao;
        }
        @Override
        protected Void doInBackground(Void... voids) {
            countryDao.deleteAll();
            return null;
        }
    }
}
