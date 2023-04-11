package com.hoppers.max.domain

data class JobResponse(
    val page: Int,
    val size: Int,
    val hasNext: Boolean,
    val total: Int,
    val jobs: List<Job>,
)
