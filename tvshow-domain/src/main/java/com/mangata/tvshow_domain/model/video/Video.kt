package com.mangata.tvshow_domain.model.video

data class Video(
    val id: Int,
    val title: String,
    val sourceSite: SourceSite,
    val official: Boolean,
    val videoQuality: Int,
    val key: String,
    val videoType: VideoType,
) {
    fun createUrl() : String? {
        return when(sourceSite) {
            is SourceSite.YouTube -> "https://www.youtube.com/watch?v=$key"
            is SourceSite.Unknown -> null
        }
    }
}

sealed class SourceSite {
    object YouTube : SourceSite()
    object Unknown : SourceSite()

    companion object {
        fun fromString(input: String) : SourceSite {
            return when(input) {
                "YouTube" -> YouTube
                else -> Unknown
            }
        }
    }
}

sealed class VideoType(val type: String) {
    object Trailer : VideoType("Trailer")
    object BehindTheScenes : VideoType("Behind the Scenes")
    object Featured : VideoType("Featurette")
    object Teaser : VideoType("Teaser")
    object Unknown : VideoType("Unknown")

    companion object {
        fun fromString(input: String) : VideoType {
            return when(input) {
                "Trailer" -> Trailer
                "Behind the Scenes" -> BehindTheScenes
                "Featurette" -> Featured
                "Teaser" -> Teaser
                else -> Unknown
            }
        }
    }
}