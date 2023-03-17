package com.example.data

import com.example.data.service.CodeWarsChallengesService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class TestApi {

    fun test(): CodeWarsChallengesService {
        val retrofit = Retrofit.Builder()
            .baseUrl("http://www.codewars.com/api/v1/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        return retrofit.create(CodeWarsChallengesService::class.java)
    }
}