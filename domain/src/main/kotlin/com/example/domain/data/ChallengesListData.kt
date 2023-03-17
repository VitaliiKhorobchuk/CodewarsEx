package com.example.domain.data

import com.example.data.model.ChallengeDataModel
import com.example.data.model.ChallengesListDataModel

data class ChallengesListData(
    val totalItems: Int,
    val totalPages: Int,
    val challenges: List<ChallengeData>
) {

    constructor(
        challengesListDataModel: ChallengesListDataModel
    ) : this(
        challengesListDataModel.totalItems,
        challengesListDataModel.totalPages,
        createChallengeData(challengesListDataModel.data)
    )
}

data class ChallengeData(
    val id: String,
    val name: String,
    val slug: String,
    val completedAt: String,
    val completedLanguages: List<String>?,
) {

    constructor(challengeDataModel: ChallengeDataModel) : this(
        challengeDataModel.id,
        challengeDataModel.name,
        challengeDataModel.slug,
        challengeDataModel.completedAt,
        challengeDataModel.completedLanguages,
    )
}

fun createChallengeData(challengeDataModel: List<ChallengeDataModel>?): List<ChallengeData> {
    return challengeDataModel?.map {
        ChallengeData(it)
    } ?: emptyList()
}