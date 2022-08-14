package com.mangata.tvshow_data.local.dao.tvShow

import androidx.room.*

@Dao
interface TvShowDao {
    @Query("SELECT * FROM tv_shows_table")
    suspend fun getAllTrackedTvShows(): List<TrackedTvShow>
}