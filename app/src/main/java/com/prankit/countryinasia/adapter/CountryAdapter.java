package com.prankit.countryinasia.adapter;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

import com.prankit.countryinasia.R;
import com.prankit.countryinasia.Utils;
import com.prankit.countryinasia.model.CountryModel;
import com.prankit.countryinasia.model.RoomModel;

import java.util.List;

public class CountryAdapter extends RecyclerView.Adapter<CountryAdapter.ViewHolder>{

    private Context context;
    private List<CountryModel> countryList;
    private List<RoomModel> roomList;
    private int i;

    public CountryAdapter(Context context, List<CountryModel> countryList, List<RoomModel> roomList, int i) {
        this.context = context;
        this.countryList = countryList;
        this.roomList = roomList;
        this.i = i;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.show_country_view, parent, false);
        return new ViewHolder(view);
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        if (i==1) {
            CountryModel countryModel = countryList.get(position);
            holder.name.setText(countryModel.getName());
            holder.capital.setText(countryModel.getCapital());
            holder.region.setText(countryModel.getRegion());
            holder.subRegion.setText(countryModel.getSubregion());
            holder.population.setText(countryModel.getPopulation().toString());
            holder.border.setText(countryModel.getBorders().toString());
            holder.language.setText(countryModel.getLanguages().get(0).getLanguage_name());
            Utils.fetchSvg(context, countryModel.getFlag(), holder.flag);
        } else if (i==2){
            RoomModel roomModel = roomList.get(position);
            holder.name.setText(roomModel.getName());
            holder.capital.setText(roomModel.getCapital());
            holder.region.setText(roomModel.getRegion());
            holder.subRegion.setText(roomModel.getSubregion());
            holder.population.setText(Math.toIntExact(roomModel.getPopulation()));
            holder.border.setText(roomModel.getBorders().toString());
            holder.language.setText(roomModel.getLanguage());
            Utils.fetchSvg(context, roomModel.getFlag(), holder.flag);
        }
    }

    @Override
    public int getItemCount() {
        if (i==1) return countryList.size();
        else return roomList.size();
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
