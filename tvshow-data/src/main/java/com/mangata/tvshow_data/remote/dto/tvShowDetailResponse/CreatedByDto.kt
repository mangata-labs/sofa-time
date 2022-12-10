package com.mangata.tvshow_data.remote.dto.tvShowDetailResponse

import kotlinx.serialization.Serializable

@Serializable
internal data class CreatedByDto(
    val credit_id: String? = null,
    val gender: Int? = null,
    val id: Int? = null,
    val name: String? = null,
    val profile_path: String? = null
)