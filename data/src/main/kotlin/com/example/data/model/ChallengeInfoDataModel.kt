package com.example.data.model

class ChallengeInfoDataModel(
    val id: String,
    val name: String?,
    val slug: String?,
    val url: String,
    val category: String,
    val description: String,
    val tags: List<String>,
    val languages: List<String>,
    val rank: ChallengeInfoRankModel,
    val createdBy: ChallengeInfoCreatedByModel,
    val approvedBy: ChallengeInfoApprovedByModel,
    val totalAttempts: Int,
    val totalCompleted: Int,
    val totalStars: Int,
    val voteScore: Int,
    val publishedAt: String,
    val approvedAt: String,
)

class ChallengeInfoRankModel(
    val id: String,
    val name: String?,
    val color: String,
)

class ChallengeInfoCreatedByModel(
    val username: String?,
    val url: String,
)

class ChallengeInfoApprovedByModel(
    val username: String?,
    val url: String,
)