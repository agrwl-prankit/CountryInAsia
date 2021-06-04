package com.prankit.countryinasia;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.prankit.countryinasia.model.RoomModel;

import java.util.List;

@Dao
public interface RoomDAO {

    @Insert
    void addCountry(RoomModel roomModel);

    @Query("SELECT * FROM Country")
    List<RoomModel> getAllCountry();
}
