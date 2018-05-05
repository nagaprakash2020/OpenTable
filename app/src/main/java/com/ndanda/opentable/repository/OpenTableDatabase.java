package com.ndanda.opentable.repository;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import com.ndanda.opentable.data.Results;

@Database(entities = {Results.class},version = 1,exportSchema = false)
public abstract class OpenTableDatabase extends RoomDatabase{

    public abstract ReviewsDao reviewsDao();
}
