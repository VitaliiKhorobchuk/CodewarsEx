package com.example.domain.use_case

import com.example.data.repository.ChallengesRepository
import com.example.domain.data.ChallengeInfoData

class ChallengesInfoUseCase(
    private val challengesRepository: ChallengesRepository
) {

    suspend fun getChallengeInfo(challengeId: String): ChallengeInfoData? {
        val challengesListDataModel = challengesRepository.getChallengeInfo(challengeId)

        return if (challengesListDataModel.body() != null) {
            ChallengeInfoData(
                challengesListDataModel.body()!!
            )
        } else {
            null
        }
    }
}