package com.example.myapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.myapplication.challenge_info.ChallengeInfoScreen
import com.example.myapplication.challenges_list.ChallengesListScreen
import com.example.myapplication.navigation.ChallengeInfoNavigation
import com.example.myapplication.navigation.NavigationDirections
import com.example.myapplication.theme.CustomAppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            val navController = rememberNavController()
            NavHost(navController = navController, startDestination = NavigationDirections.challengesList.destination) {
                composable(NavigationDirections.challengesList.destination) {
                    CustomAppTheme {
                        ChallengesListScreen(navController = navController)
                    }
                }
                composable(ChallengeInfoNavigation.route) {
                    CustomAppTheme {
                        ChallengeInfoScreen()
                    }
                }
            }
        }
    }
}