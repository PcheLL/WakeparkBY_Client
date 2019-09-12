package com.wakeparkby.Database;

import androidx.annotation.BoolRes;
import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.wakeparkby.HTTPController.Booking;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by gleb on 11/15/17.
 */

@Entity
public class DataModel {

    @NonNull
    @PrimaryKey
    private String title;
    private String description;

    @NonNull
    public String getTitle() {
        return title;
    }

    public void setTitle(@NonNull String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}
