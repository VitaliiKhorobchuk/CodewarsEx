package com.example.myapplication.navigation

import androidx.navigation.NamedNavArgument
import androidx.navigation.NavType
import androidx.navigation.navArgument

object ChallengeInfoNavigation {

  const val KEY_CHALLENGE_ID = "userId"
  const val route = "challenge_info/{$KEY_CHALLENGE_ID}"
  val namedNavArguments = listOf(
    navArgument(KEY_CHALLENGE_ID) { type = NavType.StringType }
  )

  fun challengeInfo(
    userId: String? = null
  ) = object : NavigationCommand {
    
    override val arguments: List<NamedNavArgument> = namedNavArguments

    override val destination = "challenge_info/$userId"
  }
}