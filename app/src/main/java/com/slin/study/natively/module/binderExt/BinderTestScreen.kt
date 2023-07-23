package com.slin.study.natively.module.binderExt

import android.icu.text.SimpleDateFormat
import android.icu.util.Calendar
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import java.util.Locale


@Preview
@Composable
fun BinderTestScreen(modifier: Modifier = Modifier) {
    val context = LocalContext.current
    val client = BinderTestClient(context)
    client.init()
    DisposableEffect(key1 = client) {
        onDispose {
            client.deinit()
        }
    }


    val format = SimpleDateFormat("yyyy-MM-dd HH:mm:ss SSS", Locale.getDefault())
    var testText by remember { mutableStateOf("") }
    var extMsgText by remember { mutableStateOf("") }
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp)
    ) {
        Button(onClick = {
            client.sendMsg(format.format(Calendar.getInstance().time))
        }) {
            Text(text = "Send Message DataTime")
        }
        Button(onClick = {
            testText = client.getMsg()
        }) {
            Text(text = "Read($testText)")
        }


        Button(onClick = {
            client.setExtMsg("hello ext. ${System.currentTimeMillis()}")
        }) {
            Text(text = "Send Ext Message(hello ext)")
        }
        Button(onClick = {
            extMsgText = client.getExtMsg()
        }) {
            Text(text = "Read Ext Message($extMsgText)")
        }
    }
}