package com.mangata.tvshow_presentation.tvShowDetail

import com.mangata.tvshow_domain.model.image.Poster
import com.mangata.tvshow_domain.model.video.Video
import com.mangata.tvshow_domain.model.tvShowDetail.Genre
import com.mangata.tvshow_domain.model.tvShowDetail.TvShowDetails

data class TvShowDetailState(
    val tvShowDetails: TvShowDetails? = null,
    val headerModel: DetailHeaderModel? = null,
    val videoModel: Video? = null,
    val imageModel: List<Poster> = emptyList(),
    val error: String = "",
    val isLoading: Boolean = false,
)

data class DetailHeaderModel(
    val title: String = "",
    val genres: List<Genre> = emptyList(),
    val runTime: Int? = null,
    val startYear: Int? = null,
    val lastAiredYear: Int? = null,
    val numberOfSeasons: Int = 0,
    val score: Double = 0.0,
    val inProduction: Boolean,
) {
    fun displayDate(): String {
        if (startYear == null) return "Unknown"
        return if (!inProduction ) "$startYear - $lastAiredYear"
        else "$startYear - Present"
    }

    fun displayGenres() : String {
        val genreNames = genres.map { it.name }
        return if (genreNames.size == 1) {
            genreNames.first()
        } else {
            val numberOfGenres = genreNames.size
            if (numberOfGenres == 1)
                return genreNames.first()

            var displayString = ""
            for (i in genreNames.indices) {
                displayString +=
                    if (i == genreNames.indices.last) genreNames[i]
                    else genreNames[i] + ", "
            }
            return displayString
        }
    }
}