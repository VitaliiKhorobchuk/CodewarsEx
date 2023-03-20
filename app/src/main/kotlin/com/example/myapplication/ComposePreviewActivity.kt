package com.example.myapplication

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.example.myapplication.challenge_info.ChallengeInfoScreen
import com.example.myapplication.challenge_info.ChallengesInfoPreview
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ComposePreviewActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ChallengesInfoPreview()
        }
    }

    // @Preview(showBackground = true)
    // @Composable
    // fun Preview() {
    //     setContent {
    //         ChallengeInfoScreen()
    //     }
    // }
}