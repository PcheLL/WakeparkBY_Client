package com.wakeparkby.Database;

import java.util.List;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

/**
 * Created by gleb on 11/16/17.
 */

@Dao
public interface DataDao {
    @Insert
    void insert(DataModel dataModel);

    @Delete
    void delete(DataModel dataModel);

    @Query("SELECT * FROM DataModel")
    List<DataModel> getAllData();

    @Query("SELECT * FROM DataModel WHERE title LIKE :title")
    List<DataModel> getByTitle(String title);
}
