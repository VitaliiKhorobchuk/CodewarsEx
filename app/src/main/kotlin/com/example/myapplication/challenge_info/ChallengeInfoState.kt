package com.example.myapplication.challenge_info

import com.example.domain.data.ChallengeInfoData
import com.example.domain.data.ChallengesListData
import com.example.myapplication.utils.UiState

class ChallengeInfoState(
    val challengeData: ChallengeInfoData? = null,
    isLoading: Boolean = false,
    error: Error? = null
) : UiState(isLoading, error) {

    override fun toString(): String {
        return "ChallengeInfoState { " +
                "\n challenge: $challengeData" +
                "\n" + super.toString() +
                "\n }"
    }
}
