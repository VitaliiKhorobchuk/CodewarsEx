package com.example.data.model

class ChallengesListDataModel(
    val totalPages: Int,
    val totalItems: Int,
    val data: List<ChallengeDataModel>? = null,
)

class ChallengeDataModel(
    val id: String,
    val name: String?,
    val slug: String?,
    val completedAt: String,
    val completedLanguages: List<String>?,
)
