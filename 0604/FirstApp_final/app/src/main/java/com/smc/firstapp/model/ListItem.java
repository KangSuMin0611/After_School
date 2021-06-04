package com.smc.firstapp.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class ListItem {
    @PrimaryKey
    public long productId;
    @ColumnInfo(name = "title")
    public String title;
    @ColumnInfo(name="link")
    public String link;
    @ColumnInfo(name="image")
    public String image;
    @ColumnInfo(name="lprice")
    public int lprice;
    @ColumnInfo(name="mallName")
    public String mallName;
}