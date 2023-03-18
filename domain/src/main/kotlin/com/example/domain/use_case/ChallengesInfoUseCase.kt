package com.example.domain.use_case

import com.example.data.repository.ChallengesRepository
import com.example.domain.data.ChallengeInfoData
import javax.inject.Inject

class ChallengesInfoUseCase @Inject constructor(
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