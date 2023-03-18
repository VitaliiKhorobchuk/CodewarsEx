package com.example.myapplication.challenge_info

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.use_case.ChallengesInfoUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ChallengeInfoViewModel @Inject constructor(
    private val challengeInfoUseCase: ChallengesInfoUseCase
) : ViewModel() {

    private val _stateFlow = MutableStateFlow(ChallengeInfoState(isLoading = true))
    val stateFlow = _stateFlow.asStateFlow()

    init {
        getChallengeInfo()
    }

    private fun getChallengeInfo() {
        viewModelScope.launch {
            val challengeInfo = challengeInfoUseCase.getChallengeInfo(CHALLENGE_ID)
            _stateFlow.value = ChallengeInfoState(
                challengeData  = challengeInfo
            )
        }
    }

    companion object {
        private const val CHALLENGE_ID = "56f8fe6a2e6c0dc83b0008a7"
    }
}