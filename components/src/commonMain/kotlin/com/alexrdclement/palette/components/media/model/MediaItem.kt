package com.alexrdclement.palette.components.media.model

data class MediaItem(
    val artworkThumbnailUrl: String?,
    val artworkLargeUrl: String?,
    val title: String,
    val artists: List<Artist>,
)
