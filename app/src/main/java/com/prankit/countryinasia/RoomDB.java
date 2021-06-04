package com.prankit.countryinasia;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.prankit.countryinasia.model.RoomModel;

@Database(entities = {RoomModel.class}, version = 1)
public abstract class RoomDB extends RoomDatabase {

    public abstract RoomDAO roomDAO();
}
