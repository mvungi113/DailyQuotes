package com.example.quotes.screens

import android.annotation.SuppressLint
import android.provider.CalendarContract.Colors
import androidx.compose.foundation.background
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.quotes.data.ApiClient
import com.example.quotes.data.Quote
import com.example.quotes.viewModel.HorizontalQuotesList
import com.example.quotes.viewModel.QuoteCard
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext



@Composable
fun RandomQuoteScreen() {

    var todayQuotes by remember { mutableStateOf<Quote?>(null) }
    var randomQuotes by remember { mutableStateOf<Quote?>(null) }
    var quotesList by remember { mutableStateOf<List<Quote>>(emptyList()) }
    var isLoading by remember { mutableStateOf(true) }
    var errorMessage by remember { mutableStateOf<String?>(null) }

    LaunchedEffect(Unit) {
        try {
            isLoading = true

            // Fetch the quote from the API
            val fetchedTodayQuotes = withContext(Dispatchers.IO) { ApiClient.api.getTodayQuote() }
            todayQuotes = fetchedTodayQuotes.firstOrNull() ?: throw Exception("No today's quote found")

            val fetchedListQuotes = withContext(Dispatchers.IO) { ApiClient.api.getListOfQuote() }
            quotesList = fetchedListQuotes

            val fetchedRandomQuotes = withContext(Dispatchers.IO) { ApiClient.api.getRandomQuote() }
            randomQuotes = fetchedRandomQuotes.randomOrNull()

            errorMessage = null
        } catch (e: Exception) {
            errorMessage = e.localizedMessage ?: "An error occurred while fetching the quote."
        } finally {
            isLoading = false
        }
    }

            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(5.dp),
                contentAlignment = Alignment.Center
            ) {
                when {
                    isLoading -> CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
                    errorMessage != null -> Text(
                        text = errorMessage ?: "Error",
                        color = MaterialTheme.colorScheme.error,
                        modifier = Modifier.align(Alignment.Center)
                    )

                    else -> Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .verticalScroll(rememberScrollState()) // Make the column scrollable
                            .padding(16.dp)
                    ) {
                        // Today's Quote
                        todayQuotes?.let { quote ->
                            Text(
                                text = "Quote of the Day",
                                color = MaterialTheme.colorScheme.onPrimary,
                                style = MaterialTheme.typography.titleLarge,
                                modifier = Modifier.padding(start = 16.dp, top = 16.dp)

                            )
                            QuoteCard(
                                quote = quote,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(16.dp),
                            )
                        }

                        // Horizontal Scroll List of Quotes
                        Text(
                            text = "Quotes List",
                            color = MaterialTheme.colorScheme.onPrimary,
                            style = MaterialTheme.typography.titleLarge,
                            modifier = Modifier.padding(start = 16.dp, top = 16.dp),
                        )
                        HorizontalQuotesList(
                            quotes = quotesList.take(5), // Display only the first 5 quotes

                        )

                        // Random Quote
                        randomQuotes?.let { quote ->
                            Text(
                                text = "For You",
                                color = MaterialTheme.colorScheme.onPrimary,
                                style = MaterialTheme.typography.titleLarge,
                                modifier = Modifier.padding(start = 16.dp, top = 16.dp)

                            )
                            QuoteCard(
                                quote = quote,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(16.dp),

                            )
                        }
                    }
                }
            }
        }



