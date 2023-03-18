package com.example.data.service

import com.example.data.model.ChallengeInfoDataModel
import com.example.data.model.ChallengesListDataModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface CodeWarsChallengesService {
    @GET("users/{user}/code-challenges/completed")
    suspend fun getCompletedChallenges(@Path("user") user: String, @Query("page") page: Int): Response<ChallengesListDataModel>

    @GET("code-challenges/{challenge}")
    suspend fun getChallengeInfo(@Path("challenge") challengeId: String): Response<ChallengeInfoDataModel>
}