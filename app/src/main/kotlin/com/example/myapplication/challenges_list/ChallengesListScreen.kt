package com.example.myapplication.challenges_list

import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.KeyboardArrowRight
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.myapplication.navigation.ChallengeInfoNavigation
import com.example.myapplication.theme.CustomAppTheme
import com.example.myapplication.utils.ErrorScreenState
import com.example.myapplication.utils.GeneralText
import com.example.myapplication.utils.ListState
import com.example.myapplication.utils.LoadingScreenState
import com.example.myapplication.utils.TitleText

@Composable
fun ChallengesListScreen(
    modifier: Modifier = Modifier,
    viewModel: ChallengesListViewModel = hiltViewModel(),
    navController: NavController
) {
    val screenState = viewModel.stateFlow.collectAsState()

    if (screenState.value.listState == ListState.ERROR) {
        ErrorScreenState(modifier) {
            viewModel.reset()
        }
    } else {
        MainStructure(challengeListState = screenState.value, modifier, viewModel, navController)
    }
}

@Composable
fun MainStructure(
    challengeListState: ChallengesListState,
    modifier: Modifier,
    viewModel: ChallengesListViewModel,
    navController: NavController
) {
    val lazyColumnListState = rememberLazyListState()

    val shouldStartPaginate = remember {
        derivedStateOf {
            viewModel.canPaginate && (lazyColumnListState.layoutInfo.visibleItemsInfo.lastOrNull()
                ?.index ?: -9) >= (lazyColumnListState.layoutInfo.totalItemsCount - 6)
        }
    }
    LaunchedEffect(key1 = shouldStartPaginate.value) {
        if (shouldStartPaginate.value) {
            viewModel.getNextPage()
        }
    }
    Column(
        modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        TitleText(text = "Challenges list for ${viewModel.getUserName()}")

        LazyColumn(state = lazyColumnListState) {
            items(items = challengeListState.challengesData) {
                Row(
                    modifier
                        .fillMaxWidth()
                        .clickable {
                            Log.d("VKTAG", "ChallengesListScreen MainStructure: ${it.id}")
                            navController.navigate(ChallengeInfoNavigation.challengeInfo(it.id).destination)
                        },
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    GeneralText(
                        modifier = modifier.weight(1f),
                        text = it.name ?: "Generic name"
                    )
                    Icon(
                        imageVector = Icons.Rounded.KeyboardArrowRight,
                        contentDescription = "",
                        tint = MaterialTheme.colors.primary
                    )
                }
                Divider(
                    color = MaterialTheme.colors.primary
                )
            }
            item(key = challengeListState.listState) {
                when (challengeListState.listState) {
                    ListState.LOADING -> {
                        LoadingScreenState()
                    }
                    ListState.PAGINATING -> {
                        Column(
                            modifier = modifier
                                .fillMaxWidth(),
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.Center,
                        ) {
                            GeneralText(text = "Pagination Loading")

                            CircularProgressIndicator(color = MaterialTheme.colors.primary)
                        }
                    }
                    else -> {}
                }
            }
        }
    }
}

@Preview
@Composable
fun PreviewChallengesListScreen() {
    CustomAppTheme {
        ChallengesListScreen(navController = rememberNavController())
    }
}