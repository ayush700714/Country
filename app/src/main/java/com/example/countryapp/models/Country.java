package com.example.countryapp.models;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Index;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

import com.example.countryapp.Converter2;
import com.example.countryapp.Converters;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

@Entity(tableName = "country",indices = @Index(value = {"name"},unique = true))
public class Country {


    @PrimaryKey(autoGenerate = false)
    @SerializedName("name")
    @NonNull
    @ColumnInfo(name = "name")
    private String name;

    @SerializedName("region")
    private String region;

    @SerializedName("subregion")
    private String subregion;

    @SerializedName("population")
    private Integer population;

    @SerializedName("capital")
    private String capital;

    @SerializedName("borders")
    @TypeConverters(Converters.class)
    public  List<String> borders;

    @SerializedName("flag")
    private String flag;

    @SerializedName("languages")
    @TypeConverters(Converter2.class)
    public  List<Language> languages;

    public Country(@NonNull String name, String region, String subregion, Integer population, String capital, List<String> borders, String flag, List<Language> languages) {
        this.name = name;
        this.region = region;
        this.subregion = subregion;
        this.population = population;
        this.capital = capital;
        this.borders = borders;
        this.flag = flag;
        this.languages = languages;
    }

    @NonNull
    public String getName() {
        return name;
    }

    public void setName(@NonNull String name) {
        this.name = name;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getSubregion() {
        return subregion;
    }

    public void setSubregion(String subregion) {
        this.subregion = subregion;
    }

    public Integer getPopulation() {
        return population;
    }

    public void setPopulation(Integer population) {
        this.population = population;
    }

    public String getCapital() {
        return capital;
    }

    public void setCapital(String capital) {
        this.capital = capital;
    }

    public List<String> getBorders() {
        return borders;
    }

    public void setBorders(List<String> borders) {
        this.borders = borders;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public List<Language> getLanguages() {
        return languages;
    }

    public void setLanguages(List<Language> languages) {
        this.languages = languages;
    }
}
