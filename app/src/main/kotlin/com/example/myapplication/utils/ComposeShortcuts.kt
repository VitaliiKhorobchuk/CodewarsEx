package com.example.myapplication.utils

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.myapplication.R

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

@Composable
fun TitleText(text: String, modifier: Modifier = Modifier) {
    Text(
        modifier = modifier
            .padding(top = 12.dp, bottom = 12.dp),
        text = text,
        style = TextStyle.Default,
        fontSize = 24.sp,
        color = MaterialTheme.colors.onBackground
    )
}

@Composable
fun ErrorScreenState(modifier: Modifier = Modifier, reloadFunction: () -> Unit) {
    Column(
        modifier = modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        TitleText(
            text = stringResource(id = R.string.general_error_message)
        )
        GeneralText(text = stringResource(id = R.string.error_message_hint))

        Button(onClick = reloadFunction) {
            GeneralText(text = stringResource(id = R.string.error_button))
        }
    }
}

@Composable
fun LoadingScreenState(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        GeneralText(
            text = stringResource(id = R.string.general_loading_text)
        )

        CircularProgressIndicator(color = MaterialTheme.colors.primary)
    }
}