package com.sdevprem.netclanclone.ui.home.explore.model

data class ExploreListItem(
    val firstName: String,
    val lastName: String,
    val profession: String,
    val place: String,
    val progress: Float,
    val activities: List<String>,
    val description: String
)