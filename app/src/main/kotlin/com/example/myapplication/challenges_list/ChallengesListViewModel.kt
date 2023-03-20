package com.example.myapplication.challenges_list

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.data.ChallengeData
import com.example.domain.data.ChallengesListData
import com.example.domain.use_case.ChallengesListUseCase
import com.example.myapplication.utils.ListState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ChallengesListViewModel @Inject constructor(
    private val challengesListUseCase: ChallengesListUseCase
) : ViewModel() {

    private val _stateFlow = MutableStateFlow(ChallengesListState(listState = ListState.LOADING))
    val stateFlow = _stateFlow.asStateFlow()
    var canPaginate = true
    private val totalChallengesList = mutableListOf<ChallengeData>()

    private var currentPage = FIRST_PAGE
    private var totalPages = DEFAULT_PAGES_NUMBER

    init {
        getChallenges()
    }

    fun getNextPage() {
        if (currentPage < totalPages) {
            currentPage++
            _stateFlow.value = ChallengesListState(
                challengesData = totalChallengesList,
                listState = ListState.PAGINATING
            )
            getChallenges()
        } else {
            canPaginate = false
        }
    }

    fun reset() {
        _stateFlow.value = ChallengesListState(listState = ListState.LOADING)
        canPaginate = true
        currentPage = FIRST_PAGE
        totalPages = DEFAULT_PAGES_NUMBER
        getChallenges()
    }

    private fun getChallenges() {
        viewModelScope.launch {
            Log.d("VKTAG", "ChallengesListViewModel: getChallenges page - $currentPage")

            val challengesList = challengesListUseCase.getChallengesList(USER_ID, currentPage)
            if (currentPage == FIRST_PAGE) {
                totalChallengesList.clear()
            }
            totalChallengesList.addAll(challengesList.challenges)

            Log.d("VKTAG", "ChallengesListViewModel: getChallenges obtained - ${challengesList.challenges}")
            handlePages(challengesList)

            _stateFlow.value = ChallengesListState(
                challengesData = totalChallengesList,
                listState = ListState.IDLE
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