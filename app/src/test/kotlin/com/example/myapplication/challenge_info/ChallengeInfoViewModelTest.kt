package com.example.myapplication.challenge_info

import androidx.lifecycle.SavedStateHandle
import com.example.domain.data.ChallengeInfoData
import com.example.domain.use_case.ChallengeInfoUseCase
import com.example.domain.use_case.ChallengesListUseCase
import com.example.domain.utils.DomainResponse
import com.example.myapplication.MainDispatcherRule
import com.example.myapplication.challenges_list.ChallengesListViewModel
import com.example.myapplication.navigation.ChallengeInfoNavigation
import com.example.myapplication.utils.ListState
import com.example.myapplication.utils.UiState
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.every
import io.mockk.mockk
import junit.framework.Assert.assertEquals
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class ChallengeInfoViewModelTest {

    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    private val challengeInfoUseCase: ChallengeInfoUseCase = mockk()
    private val savedStateHandle: SavedStateHandle = mockk()
    private val testChallengeKay = "testKey"
    private val testChallengeInfoData: ChallengeInfoData = mockk()

    @Before
    fun setUp() {
        every { savedStateHandle.contains(ChallengeInfoNavigation.KEY_CHALLENGE_ID) } returns true
        every { savedStateHandle.get<String>(ChallengeInfoNavigation.KEY_CHALLENGE_ID) } returns testChallengeKay
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `should post a value if request was successful`() = runTest {
        coEvery { challengeInfoUseCase.getChallengeInfo(testChallengeKay) } returns DomainResponse.Success(testChallengeInfoData)

        val challengeInfoViewModel = ChallengeInfoViewModel(
            challengeInfoUseCase, savedStateHandle
        )

        assertEquals(UiState.READY, challengeInfoViewModel.stateFlow.value.viewState)
        assertEquals(testChallengeInfoData, challengeInfoViewModel.stateFlow.value.challengeData)
        coVerify { challengeInfoUseCase.getChallengeInfo(testChallengeKay) }
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `should raise error if network error happens`() = runTest {
        coEvery { challengeInfoUseCase.getChallengeInfo(testChallengeKay) } returns DomainResponse.Error()

        val challengeInfoViewModel = ChallengeInfoViewModel(
            challengeInfoUseCase, savedStateHandle
        )

        assertEquals(UiState.ERROR, challengeInfoViewModel.stateFlow.value.viewState)
        coVerify { challengeInfoUseCase.getChallengeInfo(testChallengeKay) }
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `should reset and reload data if reload requested`() = runTest {
        coEvery { challengeInfoUseCase.getChallengeInfo(testChallengeKay) } returns DomainResponse.Success(testChallengeInfoData)

        val challengeInfoViewModel = ChallengeInfoViewModel(
            challengeInfoUseCase, savedStateHandle
        )

        challengeInfoViewModel.reload()

        assertEquals(UiState.READY, challengeInfoViewModel.stateFlow.value.viewState)
        assertEquals(testChallengeInfoData, challengeInfoViewModel.stateFlow.value.challengeData)
        coVerify(exactly = 2) { challengeInfoUseCase.getChallengeInfo(testChallengeKay) }
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `should raise an error if obtaining challenge key fails`() = runTest {
        every { savedStateHandle.contains(ChallengeInfoNavigation.KEY_CHALLENGE_ID) } returns false

        val challengeInfoViewModel = ChallengeInfoViewModel(
            challengeInfoUseCase, savedStateHandle
        )

        assertEquals(UiState.ERROR, challengeInfoViewModel.stateFlow.value.viewState)
    }
}