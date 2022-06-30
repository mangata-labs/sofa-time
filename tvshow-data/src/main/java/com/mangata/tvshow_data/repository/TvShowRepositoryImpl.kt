package com.mangata.tvshow_data.repository

import com.mangata.tvshow_data.remote.mappers.toImage
import com.mangata.tvshow_data.remote.mappers.toTvShow
import com.mangata.tvshow_data.remote.mappers.toTvShowDetails
import com.mangata.tvshow_data.remote.mappers.toVideo
import com.mangata.tvshow_data.remote.service.TmdbService
import com.mangata.tvshow_domain.model.image.Poster
import com.mangata.tvshow_domain.model.video.SourceSite
import com.mangata.tvshow_domain.model.video.Video
import com.mangata.tvshow_domain.model.video.VideoType
import com.mangata.tvshow_domain.model.tvShowList.TvShow
import com.mangata.tvshow_domain.model.tvShowDetail.TvShowDetails
import com.mangata.tvshow_domain.repository.TvShowRepository

internal class TvShowRepositoryImpl(private val tmdbService: TmdbService) : TvShowRepository {
    override suspend fun getPopularTvShows(pageNumber: Int): Result<List<TvShow>> {
        return try {
            val response = tmdbService.getPopularTvShows(pageNumber)
            val result = response.results
            Result.success(result.mapNotNull { it.toTvShow() })
        } catch (e: Exception) {
            println("here ${e.message}")
            return Result.failure(e)
        }
    }

    override suspend fun getTvShowDetails(id: Int): Result<TvShowDetails?> {
        return try {
            val response = tmdbService.getTvShowDetails(id)
            Result.success(response.toTvShowDetails())
        } catch (e: Exception) {
            println("here ${e.message}")
            return  Result.failure(e)
        }
    }

    override suspend fun getVideoForTvShow(id: Int): Result<Video?> {
        return try {
            val trailers = tmdbService.getVideoForTvShow(id).toVideo()
            Result.success(trailers.firstOrNull { it.official &&
                    (it.sourceSite is SourceSite.YouTube) &&
                    (it.videoType is VideoType.Trailer || it.videoType is VideoType.Teaser)
            })
        } catch (e: Exception) {
            println("here ${e.message}")
            return  Result.failure(e)
        }
    }

    override suspend fun getImagesForTvShow(id: Int): Result<List<Poster>> {
        return try {
            val result = tmdbService.getImagesForTvShow(id)
            val numberOfImages: Int = if (result.posters.size >= 10) 10 else result.posters.size
            Result.success(result.toImage().shuffled().take(numberOfImages))
        } catch (e: Exception) {
            println("here ${e.message}")
            return  Result.failure(e)
        }
    }
}