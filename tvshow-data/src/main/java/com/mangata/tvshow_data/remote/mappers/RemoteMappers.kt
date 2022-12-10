package com.mangata.tvshow_data.remote.mappers

import com.mangata.core.extensions.toDate
import com.mangata.core.images.ImageUrlManager
import com.mangata.tvshow_data.remote.dto.imagesResponse.ImagesDto
import com.mangata.tvshow_data.remote.dto.tvShowDetailResponse.GenreDto
import com.mangata.tvshow_data.remote.dto.tvShowDetailResponse.NetworkDto
import com.mangata.tvshow_data.remote.dto.tvShowDetailResponse.SeasonDto
import com.mangata.tvshow_data.remote.dto.tvShowDetailResponse.TvShowDetailDto
import com.mangata.tvshow_data.remote.dto.tvShowResponse.TvShowDto
import com.mangata.tvshow_data.remote.dto.videoResponse.TrailerDto
import com.mangata.tvshow_domain.model.image.Poster
import com.mangata.tvshow_domain.model.video.SourceSite
import com.mangata.tvshow_domain.model.video.Video
import com.mangata.tvshow_domain.model.video.VideoType
import com.mangata.tvshow_domain.model.tvShowDetail.Genre
import com.mangata.tvshow_domain.model.tvShowDetail.Network
import com.mangata.tvshow_domain.model.tvShowDetail.Season
import com.mangata.tvshow_domain.model.tvShowDetail.TvShowDetails
import com.mangata.tvshow_domain.model.tvShowList.TvShow

internal fun TvShowDto.toTvShow() : TvShow? {
    return TvShow(
        id = id ?: return null,
        name = name ?: return null,
        overview = overview,
        posterPath = ImageUrlManager.getPosterUrl(poster_path) ?: return null,
        backdropPath = ImageUrlManager.getBackdropUrl(backdrop_path) ?: return null,
        voteAverage = vote_average ?: return null,
    )
}

internal fun TvShowDetailDto.toTvShowDetails() : TvShowDetails? {
    return  TvShowDetails(
        id = id ?: return null,
        name = name ?: return null,
        backdropPath = ImageUrlManager.getBackdropUrl(backdrop_path) ?: return null,
        posterPath = ImageUrlManager.getPosterUrl(poster_path) ?: return null,
        genres = genres.mapNotNull { it.toGenre() },
        seasons = seasons.mapNotNull { it.toSeason() },
        networks = networks.mapNotNull { it.toNetwork() },
        number_of_episodes = number_of_episodes ?: return null,
        number_of_seasons = number_of_seasons ?: return null,
        overview = overview ?: return null,
        firstAirDate = first_air_date?.toDate(),
        lastAiredDate = last_air_date?.toDate(),
        episodeRunTime = episode_run_time,
        homepage = homepage,
        inProduction = in_production ?: return null,
        voteAverage = vote_average ?: return null,
        voteCount = vote_count ?: return null,
        status = status ?: return null
    )
}

internal fun GenreDto.toGenre() : Genre? {
    return Genre(
        id = id ?: return null,
        name = name ?: return null
    )
}

internal fun SeasonDto.toSeason() : Season? {
    return Season(
        posterPath = ImageUrlManager.getPosterUrl(poster_path) ?: return null,
        airDate = air_date,
        episodeCount = episode_count ?: return null,
        id = id ?: return null,
        name = name ?: return null,
        overview = overview ?: return null,
        seasonNumber = season_number ?: return null,
    )
}

internal fun NetworkDto.toNetwork() : Network? {
    return Network(
        id = id ?: return null,
        logoPath = ImageUrlManager.getLogoUrl(logo_path) ?: return null,
        name = name ?: return null,
    )
}

internal fun TrailerDto.toVideo() : Video? {
    return Video(
        id = id ?: return null,
        title = name ?: return null,
        sourceSite = site?.let { site -> SourceSite.fromString(site) } ?: return null,
        official = official ?: return null,
        videoQuality = size,
        key = key ?: return null,
        videoType = type?.let { type -> VideoType.fromString(type) } ?: return null,
    )
}

internal fun ImagesDto.toImage() : List<Poster> {
    return this.posters.map {
        Poster(
            aspectRatio = it.aspect_ratio,
            filePath = ImageUrlManager.getPosterUrl(it.file_path),
            height = it.height ?: return emptyList(),
            width = it.width ?: return emptyList(),
        )
    }
}