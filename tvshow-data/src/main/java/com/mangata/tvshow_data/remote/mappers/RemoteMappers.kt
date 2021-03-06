package com.mangata.tvshow_data.remote.mappers

import com.mangata.core.extensions.toDate
import com.mangata.core.images.ImageUrlManager
import com.mangata.tvshow_data.remote.dto.imagesResponse.ImagesDto
import com.mangata.tvshow_data.remote.dto.videoResponse.TrailerDto
import com.mangata.tvshow_data.remote.dto.tvShowDetailResponse.GenreDto
import com.mangata.tvshow_data.remote.dto.tvShowDetailResponse.NetworkDto
import com.mangata.tvshow_data.remote.dto.tvShowDetailResponse.SeasonDto
import com.mangata.tvshow_data.remote.dto.tvShowDetailResponse.TvShowDetailDto
import com.mangata.tvshow_data.remote.dto.tvShowResponse.TvShowDto
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
        id = id,
        name = name,
        description = overview,
        posterPath = ImageUrlManager.getPosterUrl(poster_path) ?: return null,
        backdropPath = ImageUrlManager.getBackdropUrl(backdrop_path) ?: return null
    )
}

internal fun TvShowDetailDto.toTvShowDetails() : TvShowDetails? {
    return  TvShowDetails(
        id = id,
        name = name,
        backdrop_path = ImageUrlManager.getBackdropUrl(backdrop_path) ?: return null,
        genres = genres.map { it.toGenre() },
        number_of_episodes = number_of_episodes,
        number_of_seasons = number_of_seasons,
        overview = overview,
        first_air_date = first_air_date.toDate(),
        last_air_date = last_air_date.toDate(),
        poster_path = ImageUrlManager.getPosterUrl(poster_path) ?: return null,
        seasons = seasons.mapNotNull { it.toSeason() },
        episode_run_time = episode_run_time,
        homepage = homepage,
        in_production = in_production,
        networks = networks.mapNotNull { it.toNetwork() },
        vote_average = vote_average,
        vote_count = vote_count,
        status = status
    )
}

internal fun GenreDto.toGenre() : Genre {
    return Genre(
        id = id,
        name = name
    )
}

internal fun SeasonDto.toSeason() : Season? {
    return Season(
        air_date = air_date,
        episode_count = episode_count,
        id = id,
        name = name,
        overview = overview,
        poster_path = ImageUrlManager.getPosterUrl(poster_path) ?: return null,
        season_number = season_number,
    )
}

internal fun NetworkDto.toNetwork() : Network? {
    return Network(
        id = id,
        logo_path = ImageUrlManager.getLogoUrl(logo_path) ?: return null,
        name = name,
        origin_country = origin_country
    )
}

internal fun TrailerDto.toVideo() : List<Video> {
    return this.results.map {
         Video(
             id = id,
             title = it.name,
             sourceSite = SourceSite.fromString(it.site),
             official = it.official,
             videoQuality = it.size,
             key = it.key,
             videoType = VideoType.fromString(it.type)
        )
    }
}

internal fun ImagesDto.toImage() : List<Poster> {
    return this.posters.map {
        Poster(
            aspectRatio = it.aspect_ratio,
            filePath = ImageUrlManager.getPosterUrl(it.file_path),
            height = it.height,
            width = it.width,
            voteCount = it.vote_count
        )
    }
}