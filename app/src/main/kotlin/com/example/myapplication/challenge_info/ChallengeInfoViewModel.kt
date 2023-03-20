package com.example.myapplication.challenge_info

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.use_case.ChallengeInfoUseCase
import com.example.domain.utils.DomainResponse
import com.example.myapplication.navigation.ChallengeInfoNavigation
import com.example.myapplication.utils.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ChallengeInfoViewModel @Inject constructor(
    private val challengeInfoUseCase: ChallengeInfoUseCase,
    private val savedStateHandle: SavedStateHandle,
) : ViewModel() {

    private val _stateFlow = MutableStateFlow(ChallengeInfoState())
    val stateFlow = _stateFlow.asStateFlow()

    init {
        initData()
    }

    private fun getChallengeInfo(challengeId: String) {
        viewModelScope.launch {
            val challengeInfoResponse = challengeInfoUseCase.getChallengeInfo(challengeId)
            if (challengeInfoResponse is DomainResponse.Success && challengeInfoResponse.data != null) {
                _stateFlow.value = ChallengeInfoState(
                    challengeData = challengeInfoResponse.data,
                    viewState = UiState.READY
                )
            } else {
                _stateFlow.value = ChallengeInfoState(viewState = UiState.ERROR)
            }
        }
    }

    fun reload() {
        _stateFlow.value = ChallengeInfoState()
        initData()
    }

    private fun initData() {
        if (savedStateHandle.contains(ChallengeInfoNavigation.KEY_CHALLENGE_ID)) {
            savedStateHandle.get<String>(ChallengeInfoNavigation.KEY_CHALLENGE_ID)?.let {
                getChallengeInfo(it)
            }
        } else {
            _stateFlow.value = ChallengeInfoState(viewState = UiState.ERROR)
        }
    }
}