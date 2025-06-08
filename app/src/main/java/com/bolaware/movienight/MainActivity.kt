package com.bolaware.movienight

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.bolaware.movienight.ui.theme.MovieNightTheme
import kotlinx.serialization.Serializable

sealed interface AppDestination {
    @Serializable
    data object Home: AppDestination

    @Serializable
    data object Details: AppDestination
}

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MovieNightTheme {
                ComposeApp()
/*                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Greeting(
                        name = "Android",
                        modifier = Modifier.padding(innerPadding)
                    )
                }*/
            }
        }
    }

    @Composable
    fun ComposeApp() {
        val navController = rememberNavController()

        Scaffold { innerPadding ->
            NavHost(
                navController = navController,
                startDestination = AppDestination.Home,
                modifier = Modifier
                    .statusBarsPadding()
                    .padding(bottom = innerPadding.calculateBottomPadding())
            ) {
                composable<AppDestination.Home> {
                    Column {
                        Greeting(
                            name = "Android 1",
                            modifier = Modifier.padding(innerPadding)
                        )

                        Button(onClick = {
                            navController.navigate(AppDestination.Details)
                        }) { Text("Navigate to details") }
                    }
                }
                composable<AppDestination.Details> {
                    Greeting(
                        name = "Android 2",
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    MovieNightTheme {
        Greeting("Android")
    }
}