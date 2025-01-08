package com.example.quotes.viewModel

import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.dp
import com.example.quotes.data.Quote

@Composable
fun HorizontalQuotesList(quotes: List<Quote>) {
    val screenWidth = LocalConfiguration.current.screenWidthDp.dp
    val cardWidth = screenWidth * 0.80f
    val cardSpacing = screenWidth * 0.00f
    val cardHeight = 150.dp


    Row(
        modifier = Modifier
            .fillMaxWidth()
            .horizontalScroll(rememberScrollState())
            .padding(8.dp)
    ) {
        quotes.forEach { quote ->
            Box(
                modifier = Modifier
                    .width(cardWidth)
                    .height(cardHeight)
                    .padding(horizontal = cardSpacing / 2)
            ) {
                QuoteCard(
                    quote = quote,
                    modifier = Modifier
                        .fillMaxSize()
                        .align(Alignment.Center)
                )

            }
        }


    }
}


