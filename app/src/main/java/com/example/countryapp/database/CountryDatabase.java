package com.example.countryapp.database;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.DatabaseConfiguration;
import androidx.room.InvalidationTracker;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;
import androidx.sqlite.db.SupportSQLiteOpenHelper;

import com.example.countryapp.dao.CountryDao;
import com.example.countryapp.models.Country;

@Database(entities = {Country.class}, version = 5,exportSchema = false)
public abstract class CountryDatabase  extends RoomDatabase {

     private final static String DATABASE_NAME="CountryDatabase";
     private static CountryDatabase instance;
     public abstract CountryDao countryDao();
     public static synchronized CountryDatabase getInstance(Context context) {
          if (instance == null) {
               instance = Room.databaseBuilder(context.getApplicationContext(),
                       CountryDatabase.class, DATABASE_NAME)
                       .fallbackToDestructiveMigration()
                       .addCallback(roomCallback)
                       .build();
          }
          return instance;
     }
     private static RoomDatabase.Callback roomCallback = new RoomDatabase.Callback() {
          @Override
          public void onCreate(@NonNull SupportSQLiteDatabase db) {
               super.onCreate(db);
               new PopulateDbAsyncTask(instance).execute();
          }
     };
     private static class PopulateDbAsyncTask extends AsyncTask<Void, Void, Void> {
          private CountryDao countryDao;
          private PopulateDbAsyncTask(CountryDatabase db) {
               countryDao = db.countryDao();
          }
          @Override
          protected Void doInBackground(Void... voids) {
               countryDao.deleteAll();
               return null;
          }
     }
}
