package com.example.myapplication.challenges_list

import com.example.domain.data.ChallengeData
import com.example.myapplication.utils.ListState
import com.example.myapplication.utils.UiState

class ChallengesListState(
    val challengesData: List<ChallengeData> = emptyList(),
    val listState: ListState,
)