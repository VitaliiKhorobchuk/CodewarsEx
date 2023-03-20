package com.example.domain.use_case

import android.util.Log
import com.example.data.repository.ChallengesRepository
import com.example.domain.data.ChallengesListData
import com.example.domain.utils.DomainResponse
import javax.inject.Inject

class ChallengesListUseCase @Inject constructor(
    private val challengesRepository: ChallengesRepository
) {

    suspend fun getChallengesList(user: String, page: Int): DomainResponse<ChallengesListData> {
        val challengesListDataModel = challengesRepository.getChallengesList(user, page)

        return if (challengesListDataModel.isSuccessful && challengesListDataModel.body() != null) {
            DomainResponse.Success(ChallengesListData(challengesListDataModel.body()!!))
        } else {
            DomainResponse.Error()
        }
    }
}