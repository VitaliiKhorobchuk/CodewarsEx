package com.example.data

import retrofit2.Retrofit




class TestApi {

    fun test() {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://api.github.com/")
            .build()
    }
}