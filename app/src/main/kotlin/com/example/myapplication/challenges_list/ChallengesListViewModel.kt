package com.example.myapplication.challenges_list

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.data.ChallengesListData
import com.example.domain.use_case.ChallengesListUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ChallengesListViewModel @Inject constructor(
    private val challengesListUseCase: ChallengesListUseCase
) : ViewModel() {

    private val _stateFlow = MutableStateFlow(ChallengesListState(isLoading = true))
    val stateFlow = _stateFlow.asStateFlow()

    private var currentPage = FIRST_PAGE
    private var totalPages = DEFAULT_PAGES_NUMBER

    init {
        getChallenges()
    }

    fun getNextPage() {
        if (currentPage < totalPages) {
            currentPage++
            getChallenges()
        }
    }

    private fun getChallenges() {
        viewModelScope.launch {
            Log.d("VKTAG", "ChallengesListViewModel: getChallenges page - $currentPage")

            val challengesList = challengesListUseCase.getChallengesList(USER_ID, currentPage)

            Log.d("VKTAG", "ChallengesListViewModel: getChallenges obtained - ${challengesList.challenges.count()}")
            handlePages(challengesList)

            _stateFlow.value = ChallengesListState(
                challengesData = challengesList.challenges
            )
        }
    }

    private fun handlePages(challengesListData: ChallengesListData) {
        if (totalPages == DEFAULT_PAGES_NUMBER && totalPages < challengesListData.totalPages) {
            totalPages = challengesListData.totalPages
        }
    }

    companion object {

        private const val USER_ID = "g964"
        private const val FIRST_PAGE = 1
        private const val DEFAULT_PAGES_NUMBER = 0
    }
}