package com.example.domain.use_case

import com.example.data.model.ChallengeInfoApprovedByModel
import com.example.data.model.ChallengeInfoCreatedByModel
import com.example.data.model.ChallengeInfoDataModel
import com.example.data.model.ChallengeInfoRankModel
import com.example.data.repository.ChallengesRepository
import com.example.domain.MainDispatcherRule
import com.example.domain.data.ChallengeInfoData
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
class ChallengeInfoUseCaseTest {

    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    private val challengesRepository: ChallengesRepository = mockk(relaxed = true)
    private val mockResponse: Response<ChallengeInfoDataModel> = mockk(relaxed = true)
    private val mockInfoDataModel: ChallengeInfoDataModel = ChallengeInfoDataModel(
        "id",
        "name",
        "slug",
        "url",
        "category",
        "description",
        listOf(),
        listOf(),
        ChallengeInfoRankModel("id", "name", "color"),
        ChallengeInfoCreatedByModel("username", "url"),
        ChallengeInfoApprovedByModel("username", "url"),
        1,
        1,
        1,
        1,
        "2019-02-12T12:44:33.222Z",
        "2019-02-12T12:44:33.222Z"
    )

    private val challengeInfoUseCase: ChallengeInfoUseCase by lazy {
        ChallengeInfoUseCase(challengesRepository)
    }

    private val testID = "id"

    @Test
    fun `should return success response if no errors happens `() = runTest {
        val expectedResult = DomainResponse.Success(ChallengeInfoData(mockInfoDataModel))
        coEvery { challengesRepository.getChallengeInfo(testID) } returns mockResponse
        every { mockResponse.isSuccessful } returns true
        every { mockResponse.body() } returns mockInfoDataModel

        val actualResult = challengeInfoUseCase.getChallengeInfo(testID)

        assertEquals(expectedResult.data, actualResult.data)
        coVerify { challengesRepository.getChallengeInfo(testID) }
    }

    @Test
    fun `should return error if error happens `() = runTest {
        coEvery { challengesRepository.getChallengeInfo(testID) } returns mockResponse
        every { mockResponse.isSuccessful } returns false

        val actualResult = challengeInfoUseCase.getChallengeInfo(testID)

        assertThat(actualResult, instanceOf(DomainResponse.Error::class.java))
        coVerify { challengesRepository.getChallengeInfo(testID) }
    }

    @Test
    fun `should return error if data is null`() = runTest {
        coEvery { challengesRepository.getChallengeInfo(testID) } returns mockResponse
        every { mockResponse.isSuccessful } returns true
        every { mockResponse.body() } returns null

        val actualResult = challengeInfoUseCase.getChallengeInfo(testID)

        assertThat(actualResult, instanceOf(DomainResponse.Error::class.java))
        coVerify { challengesRepository.getChallengeInfo(testID) }
    }
}