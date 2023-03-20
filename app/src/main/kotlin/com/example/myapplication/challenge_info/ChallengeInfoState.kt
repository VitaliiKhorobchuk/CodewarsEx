package com.example.myapplication.challenge_info

import com.example.domain.data.ChallengeInfoData
import com.example.myapplication.utils.UiState

class ChallengeInfoState(
    val challengeData: ChallengeInfoData? = null,
    val viewState: UiState = UiState.LOADING,
)
