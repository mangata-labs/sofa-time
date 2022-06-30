package com.mangata.tvshow_presentation.util

import com.mangata.core.extensions.toYear
import com.mangata.tvshow_domain.model.tvShowDetail.TvShowDetails
import com.mangata.tvshow_presentation.tvShowDetail.DetailHeaderModel

fun TvShowDetails.toDetailHeaderModel(): DetailHeaderModel {
    return DetailHeaderModel(
        title = name,
        genres = genres,
        runTime = episode_run_time.firstOrNull(),
        lastAiredYear = last_air_date?.toYear(),
        startYear = first_air_date?.toYear(),
        numberOfSeasons = number_of_seasons,
        score = vote_average,
        inProduction = in_production
    )
}

