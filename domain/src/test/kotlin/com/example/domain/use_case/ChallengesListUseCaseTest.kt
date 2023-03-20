package com.example.domain.use_case

import com.example.data.model.ChallengesListDataModel
import com.example.data.repository.ChallengesRepository
import com.example.domain.MainDispatcherRule
import com.example.domain.data.ChallengesListData
import com.example.domain.utils.DomainResponse
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.every
import io.mockk.mockk
import junit.framework.Assert.assertEquals
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.hamcrest.CoreMatchers.instanceOf
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Rule
import org.junit.Test
import retrofit2.Response

@OptIn(ExperimentalCoroutinesApi::class)
class ChallengesListUseCaseTest {

    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    private val challengesRepository: ChallengesRepository = mockk(relaxed = true)
    private val mockResponse: Response<ChallengesListDataModel> = mockk(relaxed = true)
    private val mockListDataModel: ChallengesListDataModel = ChallengesListDataModel(
        1, 2, listOf()
    )

    private val challengeInfoUseCase: ChallengesListUseCase by lazy {
        ChallengesListUseCase(challengesRepository)
    }

    private val testUser = "testUser"
    private val testPage = 1

    @Test
    fun `should return success response if no errors happens `() = runTest {
        val expectedResult = DomainResponse.Success(ChallengesListData(mockListDataModel))
        coEvery { challengesRepository.getChallengesList(testUser, testPage) } returns mockResponse
        every { mockResponse.isSuccessful } returns true
        every { mockResponse.body() } returns mockListDataModel

        val actualResult = challengeInfoUseCase.getChallengesList(testUser, testPage)

        assertEquals(expectedResult.data, actualResult.data)
        coVerify { challengesRepository.getChallengesList(testUser, testPage) }
    }

    @Test
    fun `should return error if error happens `() = runTest {
        coEvery { challengesRepository.getChallengesList(testUser, testPage) } returns mockResponse
        every { mockResponse.isSuccessful } returns false

        val actualResult = challengeInfoUseCase.getChallengesList(testUser, testPage)

        assertThat(actualResult, instanceOf(DomainResponse.Error::class.java))
        coVerify { challengesRepository.getChallengesList(testUser, testPage) }
    }

    @Test
    fun `should return error if data is null`() = runTest {
        coEvery { challengesRepository.getChallengesList(testUser, testPage) } returns mockResponse
        every { mockResponse.isSuccessful } returns true
        every { mockResponse.body() } returns null

        val actualResult = challengeInfoUseCase.getChallengesList(testUser, testPage)

        assertThat(actualResult, instanceOf(DomainResponse.Error::class.java))
        coVerify { challengesRepository.getChallengesList(testUser, testPage) }
    }
}