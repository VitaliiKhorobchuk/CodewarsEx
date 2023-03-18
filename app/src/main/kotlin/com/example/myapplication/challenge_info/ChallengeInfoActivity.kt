package com.example.myapplication.challenge_info

import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.example.myapplication.R

import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class ChallengeInfoActivity: AppCompatActivity() {

    private val viewModel: ChallengeInfoViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Log.d("VKTAG", "ChallengeInfoActivity: start")

        val getListButton = findViewById<Button>(R.id.buttonGetInfo)
        getListButton.setOnClickListener {
            Log.d("VKTAG", "ChallengeInfoActivity getChallengeInfo: start")

            lifecycleScope.launch {
                repeatOnLifecycle(Lifecycle.State.STARTED) {
                    viewModel.stateFlow.collectLatest {
                        Log.d("VKTAG", "ChallengeInfoActivity getChallengeInfo: $it")
                    }
                }
            }
        }

    }
}