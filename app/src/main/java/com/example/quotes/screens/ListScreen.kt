package com.example.quotes.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.quotes.data.ApiClient
import com.example.quotes.data.Quote
import com.example.quotes.viewModel.ListQuotes
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

@Composable
fun ListScreen() {

    var quotesList by remember { mutableStateOf<List<Quote>>(emptyList()) }
    var isLoading by remember { mutableStateOf(true) }
    var errorMessage by remember { mutableStateOf<String?>(null) }

    LaunchedEffect(Unit) {
        try {
            isLoading = true

            val fetchedListQuotes = withContext(Dispatchers.IO) { ApiClient.api.getListOfQuote() }
            quotesList = fetchedListQuotes

            errorMessage = null
        } catch (e: Exception) {
            errorMessage = e.localizedMessage ?: "An error occurred while fetching the quote."
        } finally {
            isLoading = false
        }
    }

    Column(
        modifier = Modifier.fillMaxSize(),
//        contentAlignment = Alignment.Center
    ) {

        ListQuotes(
            quotes = quotesList,
        )
    }
}
