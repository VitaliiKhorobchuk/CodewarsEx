package com.example.myapplication.challenge_info

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.domain.data.ChallengeInfoData
import com.example.myapplication.theme.CustomAppTheme
import com.example.myapplication.utils.ErrorScreenState
import com.example.myapplication.utils.GeneralText
import com.example.myapplication.utils.LoadingScreenState
import com.example.myapplication.utils.SmallText
import com.example.myapplication.utils.TitleText
import com.example.myapplication.utils.UiState

@Composable
fun ChallengeInfoScreen(
    modifier: Modifier = Modifier,
    challengeInfoViewModel: ChallengeInfoViewModel = hiltViewModel(),
) {
    val collectAsState = challengeInfoViewModel.stateFlow.collectAsState()
    when (collectAsState.value.viewState) {
        UiState.READY -> {
            if (collectAsState.value.challengeData != null) {
                MainStructure(challengeInfoData = collectAsState.value.challengeData!!, modifier)
            } else {
                ErrorScreenState {
                    challengeInfoViewModel.reload()
                }
            }
        }
        UiState.LOADING -> {
            LoadingScreenState()
        }
        UiState.ERROR -> {
            ErrorScreenState {
                challengeInfoViewModel.reload()
            }
        }
    }
}

@Composable
fun MainStructure(challengeInfoData: ChallengeInfoData, modifier: Modifier = Modifier) {
    Column(
        modifier
            .fillMaxSize()
            .verticalScroll(ScrollState(0), true)
            .background(MaterialTheme.colors.background),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {

        TitleText(text = challengeInfoData.name ?: "General nameCle")
        SmallText(text = "Challenge category: ${challengeInfoData.category}")
        GeneralText(text = challengeInfoData.description)
        val rank = challengeInfoData.rankName + challengeInfoData.rankColor
        GeneralText(text = "Rank: $rank")

        Attempts(challengeInfoData = challengeInfoData)
        Spacer(modifier = modifier.height(16.dp))
        Score(challengeInfoData = challengeInfoData)
        Spacer(modifier = modifier.height(16.dp))
        AuthorInfo(challengeInfoData = challengeInfoData)
    }
}

@Composable
fun Attempts(modifier: Modifier = Modifier, challengeInfoData: ChallengeInfoData) {
    OutlinedRow {
        GeneralText(text = "Total attempts: \n\n${challengeInfoData.totalAttempts}")
        Spacer(modifier = modifier.width(16.dp))
        GeneralText(text = "Total completed: \n\n${challengeInfoData.totalCompleted}")
    }
}

@Composable
fun Score(modifier: Modifier = Modifier, challengeInfoData: ChallengeInfoData) {
    OutlinedRow {
        GeneralText(text = "Total stars: \n\n${challengeInfoData.totalStars}")
        Spacer(modifier = modifier.width(16.dp))
        GeneralText(text = "Vote score: \n\n${challengeInfoData.voteScore}")
    }
}

@Composable
fun AuthorInfo(modifier: Modifier = Modifier, challengeInfoData: ChallengeInfoData) {
    OutlinedRow {
        GeneralText(text = "Created by: ${challengeInfoData.createdBy}, at \n\n ${challengeInfoData.publishedAt}")
        Spacer(modifier = modifier.width(16.dp))
        GeneralText(
            text = "Approved by: ${challengeInfoData.approvedBy}, at \n" +
                "\n" +
                " ${challengeInfoData.publishedAt}"
        )
    }
}

@Composable
fun OutlinedRow(function: @Composable RowScope.() -> Unit) {
    Row(
        modifier = Modifier
            .border(BorderStroke(2.dp, MaterialTheme.colors.primary), shape = RoundedCornerShape(8.dp))
            .padding(12.dp), content = function
    )
}

@Preview(showBackground = true, uiMode = UI_MODE_NIGHT_YES)
@Composable
fun ChallengesInfoPreview() {
    CustomAppTheme {
        ChallengeInfoScreen()
    }
}