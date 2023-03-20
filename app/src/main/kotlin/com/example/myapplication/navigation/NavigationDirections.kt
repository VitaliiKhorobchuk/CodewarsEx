package com.example.myapplication.navigation

import androidx.navigation.NamedNavArgument

object NavigationDirections {

    val challengesList = object : NavigationCommand {

        override val arguments = emptyList<NamedNavArgument>()

        override val destination = "challenges_list"

    }
}