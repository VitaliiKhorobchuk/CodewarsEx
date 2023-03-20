package com.example.myapplication.challenges_list

import com.example.domain.data.ChallengesListData
import com.example.domain.use_case.ChallengesListUseCase
import com.example.domain.utils.DomainResponse
import com.example.myapplication.MainDispatcherRule
import com.example.myapplication.utils.ListState
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.every
import io.mockk.mockk
import junit.framework.Assert.assertEquals
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class ChallengesListViewModelTest {

    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    private val challengesListUseCase: ChallengesListUseCase = mockk()
    private val challengesListData: ChallengesListData = mockk()

    private val testUserId = "g964"
    private val testPage = 1


    @Before
    fun setUp() {
        coEvery { challengesListUseCase.getChallengesList(testUserId, testPage) } returns DomainResponse.Success(challengesListData)
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `should pass data if network success`() = runTest {
        coEvery { challengesListUseCase.getChallengesList(testUserId, testPage) } returns DomainResponse.Success(challengesListData)

        val challengesListViewModel = ChallengesListViewModel(challengesListUseCase)

        assertEquals(ListState.IDLE, challengesListViewModel.stateFlow.value.listState)
        assertEquals(challengesListData, challengesListViewModel.stateFlow.value.challengesData)
        coVerify { challengesListUseCase.getChallengesList(testUserId, testPage) }
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `should load next page correctly`() = runTest {
        every { challengesListData.totalPages } returns 10
        val challengesListViewModel = ChallengesListViewModel(challengesListUseCase)

        challengesListViewModel.getNextPage()

        assertEquals(ListState.IDLE, challengesListViewModel.stateFlow.value.listState)
        assertEquals(challengesListData, challengesListViewModel.stateFlow.value.challengesData)
        coVerify(exactly = 2) { challengesListUseCase.getChallengesList(testUserId, testPage) }
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `should raise error if network error happens`() = runTest {
        coEvery { challengesListUseCase.getChallengesList(testUserId, testPage) } returns DomainResponse.Error()

        val challengesListViewModel = ChallengesListViewModel(challengesListUseCase)

        assertEquals(ListState.ERROR, challengesListViewModel.stateFlow.value.listState)
        coVerify { challengesListUseCase.getChallengesList(testUserId, testPage) }
    }

    @Test
    fun `should return correct user id `() {
        val challengesListViewModel = ChallengesListViewModel(challengesListUseCase)

        assertEquals(testUserId, challengesListViewModel.getUserName())
    }
}