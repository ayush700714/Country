package com.example.countryapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.countryapp.adapters.CountryAdapter;
import com.example.countryapp.models.Country;
import com.example.countryapp.utils.Api;
import com.example.countryapp.viewmodels.MainActivityViewModel;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private MainActivityViewModel mMainActivityViewModel;
    RecyclerView recyclerView;
    CountryAdapter adapter;
    private List<Country> ap=new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        mMainActivityViewModel = new ViewModelProvider(MainActivity.this).get(MainActivityViewModel.class);


        mMainActivityViewModel.getAll().observe(MainActivity.this, new Observer<List<Country>>() {
            @Override
            public void onChanged(List<Country> countries) {
                ap.addAll(countries);
                adapter.notifyDataSetChanged();
            }
        });
        mMainActivityViewModel.makeApiCall();



        recyclerView = findViewById(R.id.recycler_view);
        adapter = new CountryAdapter(ap);
        LinearLayoutManager l = new LinearLayoutManager(MainActivity.this);
        l.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(l);
        recyclerView.setAdapter(adapter);

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.options_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        super.onOptionsItemSelected(item);

        if(item.getItemId()==R.id.logout)
        {
            mMainActivityViewModel.delete();
        }
        return true;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ap.clear();
    }

}