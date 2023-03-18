package com.example.myapplication.challenges_list

import com.example.domain.data.ChallengeData
import com.example.myapplication.utils.UiState

class ChallengesListState(
    val challengesData: List<ChallengeData>? = null,
    isLoading: Boolean = false,
    error: Error? = null
) : UiState(isLoading, error) {

    override fun toString(): String {
        return "ChallengesListState { " +
                "\n challenges: $challengesData" +
                "\n" + super.toString() +
                "\n }"
    }
}
