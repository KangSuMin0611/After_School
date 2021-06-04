package com.smc.firstapp.db;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.smc.firstapp.model.ListItem;

import java.util.List;

// Data Access Object
@Dao
public interface ListItemDao {

    @Query("SELECT * FROM ListItem")
    List<ListItem> getAll();

    @Insert
    void insertAll(ListItem... items);

    @Delete
    void delete(ListItem item);
}
