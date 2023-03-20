package com.example.myapplication.challenge_info

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import android.util.Log
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.domain.data.ChallengeInfoData
import com.example.myapplication.theme.CustomAppTheme

@Composable
fun ChallengeInfoScreen(
    modifier: Modifier = Modifier,
    challengeInfoViewModel: ChallengeInfoViewModel = hiltViewModel(),
) {
    val collectAsState = challengeInfoViewModel.stateFlow.collectAsState()
    if (collectAsState.value.isLoading) {
        Log.d("VKTAG", "ChallengeInfoScreen loading")
    } else if (collectAsState.value.error != null) {
        Log.d("VKTAG", "ChallengeInfoScreen error")
    } else if (collectAsState.value.challengeData != null) {
        Log.d("VKTAG", "ChallengeInfoScreen data ${collectAsState.value}")
        MainStructure(challengeInfoData = collectAsState.value.challengeData!!)
    }
    // val info = ChallengeInfoData(
    //     "Challenge title",
    //     "Challenge title",
    //     "Java",
    //     "Some very long description with a lot of text to display. Probably a lot of symbols should be here",
    //     listOf("Tag 1", "Tag 2", "Tag3"),
    //     listOf("Java", "Kotlin", "Android"),
    //     "Very cool",
    //     "Gold",
    //     "creator name",
    //     "approver name",
    //     10,
    //     1,
    //     123_000,
    //     5,
    //     "published date",
    //     "approved date"
    // )
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

        Text(
            modifier = modifier
                .padding(top = 12.dp, bottom = 12.dp),
            text = challengeInfoData.name,
            style = TextStyle.Default,
            fontSize = 24.sp,
            color = MaterialTheme.colors.onBackground
        )

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

@Composable
fun GeneralText(text: String, modifier: Modifier = Modifier) {
    Text(
        modifier = modifier
            .padding(top = 12.dp, bottom = 12.dp),
        text = text,
        style = TextStyle.Default,
        fontSize = 16.sp,
        color = MaterialTheme.colors.onBackground,
        textAlign = TextAlign.Center,
    )
}

@Composable
fun SmallText(text: String, modifier: Modifier = Modifier) {
    Text(
        modifier = modifier
            .fillMaxWidth()
            .padding(12.dp),
        text = text,
        style = TextStyle.Default,
        fontSize = 12.sp,
        color = MaterialTheme.colors.onBackground
    )
}

@Preview(showBackground = true, uiMode = UI_MODE_NIGHT_YES)
@Composable
fun ChallengesInfoPreview() {
    CustomAppTheme {
        ChallengeInfoScreen()
    }
}