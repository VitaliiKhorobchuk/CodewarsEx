package com.example.data.repository

import com.example.data.model.ChallengeInfoDataModel
import com.example.data.model.ChallengesListDataModel
import retrofit2.Response

interface ChallengesRepository {

    suspend fun getChallengesList(user: String, page: Int): Response<ChallengesListDataModel>

    suspend fun getChallengeInfo(challengeId: String): Response<ChallengeInfoDataModel>
}