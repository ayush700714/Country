package com.example.countryapp.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.countryapp.models.Country;

import java.util.ArrayList;
import java.util.List;

@Dao
public interface CountryDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(List<Country> countryList);

    @Query("SELECT * FROM country")
    LiveData<List<Country>> getAll();


    @Query("DELETE FROM country")
    void deleteAll();
}
