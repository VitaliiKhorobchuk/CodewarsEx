package com.example.myapplication.challenge_info

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.use_case.ChallengesInfoUseCase
import com.example.myapplication.navigation.ChallengeInfoNavigation
import com.example.myapplication.utils.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ChallengeInfoViewModel @Inject constructor(
    private val challengeInfoUseCase: ChallengesInfoUseCase,
    private val savedStateHandle: SavedStateHandle,
) : ViewModel() {

    private val _stateFlow = MutableStateFlow(ChallengeInfoState())
    val stateFlow = _stateFlow.asStateFlow()

    init {
        initData()
    }

    private fun getChallengeInfo(challengeId: String) {
        viewModelScope.launch {
            val challengeInfo = challengeInfoUseCase.getChallengeInfo(challengeId)
            _stateFlow.value = ChallengeInfoState(
                challengeData = challengeInfo,
                viewState = UiState.READY
            )
        }
    }

    fun reload() {
        _stateFlow.value = ChallengeInfoState()
        initData()
    }

    private fun initData() {
        savedStateHandle.get<String>(ChallengeInfoNavigation.KEY_CHALLENGE_ID)?.let {
            getChallengeInfo(it)
        } ?: {
            _stateFlow.value = ChallengeInfoState(viewState = UiState.ERROR)
        }
    }
}