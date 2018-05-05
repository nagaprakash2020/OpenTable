package com.ndanda.opentable.repository;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import com.ndanda.opentable.data.Results;

import java.util.List;

@Dao
public interface ReviewsDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertReviewResults(List<Results> results);

    @Query("SELECT * FROM Results")
    LiveData<List<Results>> getReviewResults();

}
