package com.example.quotes.Nav

import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.quotes.screens.FavoritesScreen
import com.example.quotes.screens.ListScreen
import com.example.quotes.screens.RandomQuoteScreen

@Composable
fun AppNavGraph(navController: NavHostController){
    NavHost(
        navController = navController,
        startDestination =  "home",
        modifier = Modifier.padding(0.dp)
    ){
        composable("home") {
            RandomQuoteScreen ()
        }
        composable("list") {
            ListScreen()
        }
        composable("favorites") {
            FavoritesScreen()
        }



    }
}