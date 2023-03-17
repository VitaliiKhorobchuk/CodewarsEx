package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import com.example.data.TestApi
import com.example.data.repository.ChallengesRepositoryImpl
import com.example.domain.data.ChallengeData
import com.example.domain.use_case.ChallengesInfoUseCase
import com.example.domain.use_case.ChallengesListUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainActivity : AppCompatActivity() {

    val testChallengeId = "56f8fe6a2e6c0dc83b0008a7"
    val testUser = "g964"
    val startingPage = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val getListButton = findViewById<Button>(R.id.buttonGetList)
        getListButton.setOnClickListener {
            Log.d("VKTAG", "MainActivity getChallengesList: start")

            getChallengesList()
        }

        val getChallengesInfoButton = findViewById<Button>(R.id.buttonGetInfo)
        getChallengesInfoButton.setOnClickListener {
            Log.d("VKTAG", "MainActivity getChallengeInfo: start")

            getChallengeInfo()
        }
    }

    private fun getChallengesList() {
        val service = TestApi().test()
        val repository = ChallengesRepositoryImpl(service)
        val useCase = ChallengesListUseCase(repository)

        GlobalScope.launch(Dispatchers.IO) {
            val challengesList = useCase.getChallengesList(
                testUser,
                startingPage
            )

            Log.d("VKTAG", "MainActivity getChallengesList: $challengesList")
        }
    }

    private fun getChallengeInfo() {
        val service = TestApi().test()
        val repository = ChallengesRepositoryImpl(service)
        val useCase = ChallengesInfoUseCase(repository)

        GlobalScope.launch(Dispatchers.IO) {
            val challengeInfo = useCase.getChallengeInfo(testChallengeId)

            Log.d("VKTAG", "MainActivity getChallengeInfo: $challengeInfo")
        }
    }

    private fun getStr(list: List<ChallengeData>): String {
        val sb = StringBuilder()
        list.forEach {
            sb.append(it.name)
            sb.append("\n")
        }
        return sb.toString()
    }
}