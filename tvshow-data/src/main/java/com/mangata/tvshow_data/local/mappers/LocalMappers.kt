package com.mangata.tvshow_data.local.mappers

import com.mangata.tvshow_data.local.dao.tvShow.TvShowEntity
import com.mangata.tvshow_domain.model.tvShowList.TvShow

internal fun TvShowEntity.toTvShow() : TvShow {
    return TvShow(
        id = id,
        name = name,
        overview = description,
        posterPath = posterPath,
        backdropPath = backdropPath,
        voteAverage = voteAverage
    )
}

internal fun TvShow.toTvShowEntity() : TvShowEntity {
    return TvShowEntity(
        id = id,
        name = name,
        description = overview,
        posterPath = posterPath,
        backdropPath = backdropPath,
        voteAverage = voteAverage
    )
}