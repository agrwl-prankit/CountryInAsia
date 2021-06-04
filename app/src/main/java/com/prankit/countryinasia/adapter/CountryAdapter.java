package com.prankit.countryinasia.adapter;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.prankit.countryinasia.R;
import com.prankit.countryinasia.Utils;
import com.prankit.countryinasia.model.CountryModel;

import java.util.List;

public class CountryAdapter extends RecyclerView.Adapter<CountryAdapter.ViewHolder>{

    private Context context;
    private List<CountryModel> countryList;

    public CountryAdapter(Context context, List<CountryModel> countryList) {
        this.context = context;
        this.countryList = countryList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.show_country_view, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        CountryModel countryModel = countryList.get(position);

        Log.i("getFlag", countryModel.getFlag());
        holder.name.setText(countryModel.getName());
        holder.capital.setText(countryModel.getCapital());
        holder.region.setText(countryModel.getRegion());
        holder.subRegion.setText(countryModel.getSubregion());
        holder.population.setText(countryModel.getPopulation().toString());
        holder.border.setText(countryModel.getBorders().toString());
        holder.language.setText(countryModel.getLanguages().get(0).getLanguage_name());
        Utils.fetchSvg(context, countryModel.getFlag(), holder.flag);
    }

    @Override
    public int getItemCount() {
        return countryList.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView name, capital, region, subRegion, population, border, language;
        ImageView flag;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.country_name);
            capital = itemView.findViewById(R.id.country_capital);
            region = itemView.findViewById(R.id.country_region);
            subRegion = itemView.findViewById(R.id.country_sub_region);
            population = itemView.findViewById(R.id.country_population);
            border = itemView.findViewById(R.id.country_borders);
            language = itemView.findViewById(R.id.country_language);
            flag = itemView.findViewById(R.id.country_flag);
        }
    }
}
