package com.example.domain.data

import com.example.data.model.ChallengeInfoDataModel
import com.example.domain.utils.parseDateString

data class ChallengeInfoData(
    val name: String?,
    val url: String,
    val category: String,
    val description: String,
    val tags: List<String>,
    val languages: List<String>,
    val rankName: String?,
    val rankColor: String,
    val createdBy: String?,
    val approvedBy: String?,
    val totalAttempts: Int,
    val totalCompleted: Int,
    val totalStars: Int,
    val voteScore: Int,
    val publishedAt: String,
    val approvedAt: String,
) {

    constructor(challengeInfoDataModel: ChallengeInfoDataModel) : this(
        challengeInfoDataModel.name,
        challengeInfoDataModel.url,
        challengeInfoDataModel.category,
        challengeInfoDataModel.description,
        challengeInfoDataModel.tags,
        challengeInfoDataModel.languages,
        challengeInfoDataModel.rank.name,
        challengeInfoDataModel.rank.color,
        challengeInfoDataModel.createdBy.username,
        challengeInfoDataModel.approvedBy.username,
        challengeInfoDataModel.totalAttempts,
        challengeInfoDataModel.totalCompleted,
        challengeInfoDataModel.totalStars,
        challengeInfoDataModel.voteScore,
        challengeInfoDataModel.publishedAt.parseDateString(),
        challengeInfoDataModel.approvedAt.parseDateString(),
    )
}
