package com.mangata.tvshow_presentation.tvShowDetail.components.headingSection

import com.mangata.core.extensions.toYear
import com.mangata.tvshow_domain.model.tvShowDetail.Genre
import com.mangata.tvshow_domain.model.tvShowDetail.TvShowDetails

data class TvDetailsHeadingModel(
    val title: String = "",
    val genres: List<Genre> = emptyList(),
    val runTime: Int? = null,
    val startYear: Int? = null,
    val lastAiredYear: Int? = null,
    val numberOfSeasons: Int = 0,
    val score: Double = 0.0,
    val inProduction: Boolean = false,
) {
    fun displayDate(): String {
        return if (startYear == null) "Unknown"
        else if (inProduction) "$startYear - Present"
        else "$startYear - ${lastAiredYear ?: "Unknown"}"
    }

    fun displayGenres() : String {
        val genreNames = genres.map { it.name }
        return if (genreNames.size == 1) {
            genreNames.first()
        } else {
            var displayString = ""
            for (i in genreNames.indices) {
                displayString +=
                    if (i == genreNames.indices.last) genreNames[i]
                    else genreNames[i] + ", "
            }
            return displayString
        }
    }

    fun displaySeasons() : String {
        return if (numberOfSeasons == 1) {
            "$numberOfSeasons Season"
        } else if (numberOfSeasons >= 2) {
            "$numberOfSeasons Seasons"
        } else { "" }
    }
}

fun TvShowDetails.toDetailHeaderModel(): TvDetailsHeadingModel {
    return TvDetailsHeadingModel(
        title = name,
        genres = genres,
        runTime = episodeRunTime.firstOrNull(),
        lastAiredYear = lastAiredDate?.toYear(),
        startYear = firstAirDate?.toYear(),
        numberOfSeasons = numberOfSeasons,
        score = voteAverage,
        inProduction = inProduction
    )
}