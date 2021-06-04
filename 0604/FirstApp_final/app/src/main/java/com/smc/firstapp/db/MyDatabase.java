package com.smc.firstapp.db;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.smc.firstapp.model.ListItem;

@Database(entities = {ListItem.class}, version = 1)
public abstract class MyDatabase extends RoomDatabase {
    public abstract ListItemDao getDao();
}
