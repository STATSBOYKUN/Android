package com.umaru.helloworld

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.umaru.helloworld.ui.theme.HelloWorldTheme

class MainActivity : ComponentActivity() {
    private val MyActivityTag = "lifecycle"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            HelloWorldTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Greeting("Umar Hadi Pranoto")
                }
            }
        }
        Log.i(MyActivityTag, "onCreate State")
    }

    override fun onStart() {
        super.onStart()
        Log.i(MyActivityTag, "onStart State")
    }

    override fun onResume() {
        super.onResume()
        Log.i(MyActivityTag, "onResume State")
    }

    override fun onPause() {
        super.onPause()
        Log.i(MyActivityTag, "onPause State")
    }

    override fun onStop() {
        super.onStop()
        Log.i(MyActivityTag, "onStop State")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.i(MyActivityTag, "onDestroy State")
    }

    override fun onRestart() {
        super.onRestart()
        Log.i(MyActivityTag, "onRestart State")
    }

}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello, $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    HelloWorldTheme {
        Greeting("Android")
    }
}