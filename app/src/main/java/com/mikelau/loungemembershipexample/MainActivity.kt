package com.mikelau.loungemembershipexample

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import com.mikelau.loungemembership.MileagePlanCardActivity
import com.mikelau.loungemembershipexample.ui.theme.LoungeMembershipExampleTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            LoungeMembershipExampleTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Greeting(
                        name = "Android",
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    val context = LocalContext.current
    Text(
        text = "Hello $name!",
        modifier = modifier.clickable {
            val intent = Intent(context, MileagePlanCardActivity::class.java)
            intent.putExtras(Bundle().apply {
                putString("token", "token")
                putString("userId", "userId")
            })
            context.startActivity(intent)
        }
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    LoungeMembershipExampleTheme {
        Greeting("Android")
    }
}