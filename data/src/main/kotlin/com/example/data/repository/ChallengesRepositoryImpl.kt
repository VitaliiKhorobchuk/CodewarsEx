package com.example.data.repository

import com.example.data.model.ChallengeInfoDataModel
import com.example.data.model.ChallengesListDataModel
import com.example.data.service.CodeWarsChallengesService
import retrofit2.Response

class ChallengesRepositoryImpl(
    private val codeWarsChallengesService: CodeWarsChallengesService
): ChallengesRepository {

    override suspend fun getChallengesList(
        user: String,
        page: Int
    ): Response<ChallengesListDataModel> {
        return codeWarsChallengesService.getCompletedChallenges(user, page)
    }

    override suspend fun getChallengeInfo(challengeId: String): Response<ChallengeInfoDataModel> {
        return codeWarsChallengesService.getChallengeInfo(challengeId)
    }
}