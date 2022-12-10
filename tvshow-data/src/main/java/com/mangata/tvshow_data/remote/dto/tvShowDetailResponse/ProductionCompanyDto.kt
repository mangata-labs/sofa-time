package com.mangata.tvshow_data.remote.dto.tvShowDetailResponse

import kotlinx.serialization.Serializable

@Serializable
internal data class ProductionCompanyDto(
    val id: Int? = null,
    val logo_path: String? = null,
    val name: String? = null,
    val origin_country: String? = null,
)