package com.example.quotes.Nav

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.List
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.NavHostController

data class NavigationItem(
    val route:String,
    val icon:ImageVector,
    val label:String
) {

}

@Composable
fun BottomNavigationBar(navController: NavHostController) {
    val items = listOf(
        NavigationItem("home", Icons.Default.Home, "Home"),
        NavigationItem("list", Icons.Default.List, "List"),
        NavigationItem("help", Icons.Default.Info, "About ")
    )

    NavigationBar(
        containerColor = MaterialTheme.colorScheme.primary, // Background color
        contentColor = MaterialTheme.colorScheme.primary // Icon/Text color
    ) {
        val currentRoute = navController.currentBackStackEntry?.destination?.route

        items.forEach { item ->
            NavigationBarItem(
                selected = currentRoute == item.route,
                onClick = {
                    if (currentRoute != item.route) {
                        navController.navigate(item.route) {
                            popUpTo(navController.graph.startDestinationId) {
                                saveState = true
                            }
                            launchSingleTop = true
                            restoreState = true
                        }
                    }
                },
                icon = { Icon(item.icon, contentDescription = item.label, tint = MaterialTheme.colorScheme.onPrimary) },
                label = { Text(item.label, color = MaterialTheme.colorScheme.onPrimary) }

            )
        }
    }
}
