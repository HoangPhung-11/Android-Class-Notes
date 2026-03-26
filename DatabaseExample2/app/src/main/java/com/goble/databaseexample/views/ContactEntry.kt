package com.goble.databaseexample.views

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.calculateEndPadding
import androidx.compose.foundation.layout.calculateStartPadding
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.goble.databaseexample.data.ContactEvent
import com.goble.databaseexample.data.ContactState
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddContactScreen(
    state: ContactState,
    onEvent: (ContactEvent) -> Unit,
    onSubmission: () -> Unit
) {
    val coroutineScope = rememberCoroutineScope()

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text("Add Contact")
                }
            )
        },
        bottomBar = {
            Button(
                onClick = {
                    coroutineScope.launch {
                        onEvent(ContactEvent.SaveContact)
                    }
                    onSubmission()
                },
                modifier = Modifier.fillMaxWidth()
                    .padding(16.dp)
            ) {
                Text("Save")
            }
        }
    ) { padding ->
        PersonEntryBody(
            modifier = Modifier
                .padding(
                    start = padding.calculateStartPadding(LocalLayoutDirection.current),
                    top = padding.calculateTopPadding(),
                    end = padding.calculateEndPadding(LocalLayoutDirection.current)
                )
                .verticalScroll(rememberScrollState())
                .fillMaxWidth(),
            state = state,
            onEvent = onEvent
        )
    }
}


@Composable
fun PersonEntryBody(
    modifier: Modifier = Modifier,
    state: ContactState,
    onEvent: (ContactEvent) -> Unit
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {

        PersonInputForm(
            modifier = Modifier.fillMaxWidth(),
            state = state,
            onEvent = onEvent
        )
    }
}

@Composable
fun PersonInputForm(
    modifier: Modifier = Modifier,
    state: ContactState,
    onEvent: (ContactEvent) -> Unit
) {
    var name by remember { mutableStateOf("") }
    var age by remember { mutableStateOf("") }
    var phone by remember { mutableStateOf("") }

    Column(
        modifier = modifier
            .padding(horizontal = 16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        OutlinedTextField(
            modifier = modifier.fillMaxWidth(),
            value = name,
            onValueChange = {
                name = it
                onEvent(ContactEvent.SetName(it))
            },
            label = {
                Text("Name")
            },
            colors = OutlinedTextFieldDefaults.colors(
                focusedContainerColor = MaterialTheme.colorScheme.secondaryContainer,
                unfocusedContainerColor = MaterialTheme.colorScheme.secondaryContainer
            )
        )

        OutlinedTextField(
            modifier = modifier.fillMaxWidth(),
            value = age,
            onValueChange = { newVal ->
                val filteredText = newVal.filter { it.isDigit() }

                if(filteredText.isNotBlank()){
                    age = newVal
                    onEvent(ContactEvent.SetAge(newVal.toInt()))
                }
            },
            label = {
                Text("Age")
            },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            colors = OutlinedTextFieldDefaults.colors(
                focusedContainerColor = MaterialTheme.colorScheme.secondaryContainer,
                unfocusedContainerColor = MaterialTheme.colorScheme.secondaryContainer
            )
        )

        OutlinedTextField(
            modifier = modifier.fillMaxWidth(),
            value = phone,
            onValueChange = {
                phone = it
                onEvent(ContactEvent.SetPhone(it))
            },
            label = {
                Text("Phone")
            },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone),
            colors = OutlinedTextFieldDefaults.colors(
                focusedContainerColor = MaterialTheme.colorScheme.secondaryContainer,
                unfocusedContainerColor = MaterialTheme.colorScheme.secondaryContainer
            )
        )
    }
}

@Preview(showBackground = true)
@Composable
fun ShowAddContactScreen() {
    AddContactScreen(ContactState(), onEvent = {}, onSubmission = {})
}