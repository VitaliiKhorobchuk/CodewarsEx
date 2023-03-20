package com.example.domain.use_case

import com.example.data.repository.ChallengesRepository
import com.example.domain.data.ChallengeInfoData
import com.example.domain.utils.DomainResponse
import javax.inject.Inject

class ChallengeInfoUseCase @Inject constructor(
    private val challengesRepository: ChallengesRepository
) {

    suspend fun getChallengeInfo(challengeId: String): DomainResponse<ChallengeInfoData?> {
        val challengeInfoDataModel = challengesRepository.getChallengeInfo(challengeId)

        return if (challengeInfoDataModel.isSuccessful && challengeInfoDataModel.body() != null) {
            DomainResponse.Success(ChallengeInfoData(challengeInfoDataModel.body()!!))
        } else {
            DomainResponse.Error()
        }
    }
}