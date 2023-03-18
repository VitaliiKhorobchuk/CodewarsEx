package com.example.myapplication.challenges_list

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.example.myapplication.R
import com.example.myapplication.challenge_info.ChallengeInfoActivity

import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class ChallengesListActivity : AppCompatActivity() {

    private val viewModel: ChallengesListViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Log.d("VKTAG", "ChallengesListActivity: start")
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.stateFlow.collectLatest {
                    Log.d("VKTAG", "ChallengesListActivity getChallengesList: $it")
                }
            }
        }

        val getListButton = findViewById<Button>(R.id.buttonGetList)
        getListButton.setOnClickListener {
            Log.d("VKTAG", "ChallengesListActivity getChallengesList: button click")

            lifecycleScope.launch {
                viewModel.getNextPage()
                // viewModel.stateFlow.collectLatest {
                //     Log.d("VKTAG", "ChallengesListActivity getChallengesList: $it")
                // }

            }
        }

        val getInfoButton = findViewById<Button>(R.id.buttonGetInfo)
        getInfoButton.setOnClickListener {
            startActivity(Intent(this, ChallengeInfoActivity::class.java))
        }
    }
}