package com.example.myapplication.di

import com.example.data.repository.ChallengesRepository
import com.example.data.repository.ChallengesRepositoryImpl
import com.example.data.service.CodeWarsChallengesService
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object MainModule {

    @Provides
    fun provideCodeWarsChallengesService(): CodeWarsChallengesService {
        val retrofit = Retrofit.Builder()
            .baseUrl("http://www.codewars.com/api/v1/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        return retrofit.create(CodeWarsChallengesService::class.java)
    }

}
@Module
@InstallIn(SingletonComponent::class)
abstract class ActivityModule {

    @Binds
    abstract fun bindChallengesRepository(
        challengesRepositoryImpl: ChallengesRepositoryImpl
    ): ChallengesRepository
}