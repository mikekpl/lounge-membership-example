package com.mikelau.loungemembershipexample

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.mikelau.loungemembership.MileagePlanCardActivity
import com.mikelau.loungemembershipexample.ui.theme.LoungeMembershipExampleTheme
import kotlin.random.Random

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            LoungeMembershipExampleTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Greeting(modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}

@Composable
fun Greeting(modifier: Modifier = Modifier) {
    val context = LocalContext.current
    val token = remember { mutableStateOf("") }
    val userId = remember { mutableStateOf("") }

    Column(modifier = modifier) {
        TextField(
            value = token.value,
            onValueChange = { token.value = it },
            label = { Text("Token") }
        )
        TextField(
            value = userId.value,
            onValueChange = { userId.value = it },
            label = { Text("User ID") }
        )
        Button(
            onClick = {
                val intent = Intent(context, MileagePlanCardActivity::class.java)
                intent.putExtras(Bundle().apply {
                    putString("token", token.value)
                    putString("userId", userId.value)
                })
                context.startActivity(intent)
            },
            colors = ButtonDefaults.buttonColors(
                Color(
                    Random.nextInt(256),
                    Random.nextInt(256),
                    Random.nextInt(256)
                )
            ),
            modifier = Modifier.padding(16.dp)
        ) {
            Text(text = "Standalone App, Click Me to Open!")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    LoungeMembershipExampleTheme {
        Greeting()
    }
}