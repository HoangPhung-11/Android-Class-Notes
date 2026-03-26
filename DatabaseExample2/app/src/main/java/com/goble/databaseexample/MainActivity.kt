package com.goble.databaseexample

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import com.goble.databaseexample.data.PersonDatabase
import com.goble.databaseexample.nav.ContactNavGraph
import com.goble.databaseexample.ui.theme.DatabaseExampleTheme
import com.goble.databaseexample.viewmodels.PersonViewModel
import com.goble.databaseexample.views.AddContactScreen
import com.goble.databaseexample.views.ContactScreen

class MainActivity : ComponentActivity() {
    private val db by lazy {
        PersonDatabase.getDatabase(this)
    }

    private val viewModel by viewModels<PersonViewModel> {
        PersonViewModel.factory(db.personDao())
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            DatabaseExampleTheme {
                val state by viewModel.state.collectAsState()
                val navController = rememberNavController()
                ContactNavGraph(
                    state = state,
                    navController = navController,
                    onEvent = viewModel::onEvent
                )
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
    DatabaseExampleTheme {
        Greeting("Android")
    }
}