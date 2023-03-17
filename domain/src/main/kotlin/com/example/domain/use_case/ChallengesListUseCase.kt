package com.example.domain.use_case

import com.example.data.repository.ChallengesRepository
import com.example.domain.data.ChallengesListData

class ChallengesListUseCase(
    private val challengesRepository: ChallengesRepository
) {

    suspend fun getChallengesList(user: String, page: Int): ChallengesListData {
        val challengesListDataModel = challengesRepository.getChallengesList(user, page)

        return if (challengesListDataModel.body() != null) {
            ChallengesListData(
                challengesListDataModel.body()!!
            )
        } else {
            ChallengesListData(
                0, 0, emptyList()
            )
        }
    }
}