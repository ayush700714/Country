package com.example.countryapp.adapters;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.countryapp.R;
import com.example.countryapp.models.Country;

import com.example.countryapp.models.Language;
import com.github.twocoffeesoneteam.glidetovectoryou.GlideToVectorYou;

import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CountryAdapter extends RecyclerView.Adapter<CountryAdapter.RecycleHolder>{
    private Context context;
    private List<Country> mEa;
    public class RecycleHolder extends RecyclerView.ViewHolder
    {
        private TextView name,region,subregion,borders,languages,population;
        private ImageView imageView;
        public RecycleHolder( View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.Name);
            region= itemView.findViewById(R.id.region);
            subregion = itemView.findViewById(R.id.subregion);
            borders = itemView.findViewById(R.id.borders);
            languages =itemView.findViewById(R.id.language);
            imageView =itemView.findViewById(R.id.image);
            population =itemView.findViewById(R.id.population);
        }
    }
    public CountryAdapter(List<Country> a)
    {
        mEa=a;
    }

    @NonNull
    @Override
    public CountryAdapter.RecycleHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycle_list,parent,false);
        RecycleHolder recycleHolder = new RecycleHolder(view);
        context=view.getContext();
        return recycleHolder;
    }


    @Override
    public void onBindViewHolder(@NonNull CountryAdapter.RecycleHolder holder, int position) {
        Country b = mEa.get(position);
        holder.name.setText(b.getName());
        holder.region.setText(b.getRegion());
        holder.subregion.setText(b.getSubregion());
        List<String> bor = b.getBorders();

        holder.borders.setText("");
        for(int i=0;i<bor.size();i++)
        {
            holder.borders.append(bor.get(i)+ " ");
        }

        holder.population.setText(b.getPopulation().toString());
        List<Language> lan = b.getLanguages();
        holder.languages.setText("");
        for(int i=0;i<lan.size();i++)
        {
            holder.languages.append(lan.get(i).getName()+" ");
        }
        Glide.with(context)
                .load(b.getFlag())
                .apply(new RequestOptions().override(600, 200))
                .into(holder.imageView);

        GlideToVectorYou.justLoadImage((Activity)context, Uri.parse(b.getFlag()), holder.imageView);
    }

    @Override
    public int getItemCount() {
        return mEa.size();
    }

}

