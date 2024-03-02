package com.nicksidiropoulos.calculator

import android.annotation.SuppressLint
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Calculate
import androidx.compose.material.icons.filled.Money
import androidx.compose.material.icons.outlined.Calculate
import androidx.compose.material.icons.outlined.Money
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.nicksidiropoulos.calculator.data.DataSource

data class TabBarItem(
    val title: String,
    val selectedIcon: ImageVector,
    val unselectedIcon: ImageVector
)

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter", "SuspiciousIndentation")
@Composable
fun CalculatorApp() {
    val dataSource = DataSource()
    val context = LocalContext.current

    val calculatorTab = TabBarItem(
        title = stringResource(id = R.string.app_name),
        selectedIcon = Icons.Filled.Calculate,
        unselectedIcon = Icons.Outlined.Calculate
    )
    val converterTab = TabBarItem(
        title = stringResource(id = R.string.currency_converter),
        selectedIcon = Icons.Filled.Money,
        unselectedIcon = Icons.Outlined.Money
    )

    val tabBarItems = listOf(calculatorTab, converterTab)

    //Create NavController
    val navController = rememberNavController()

    //Bottom Navigation Bar
    Scaffold(
        bottomBar = {
            NavigationBar {
                val navBackStackEntry by navController.currentBackStackEntryAsState()
                val currentDestination = navBackStackEntry?.destination
                tabBarItems.forEach { tabBarItem ->
                    NavigationBarItem(
                        icon = {
                            TabBarIconView(
                                isSelected = currentDestination?.hierarchy?.any { it.route == tabBarItem.title } == true,
                                selectedIcon = tabBarItem.selectedIcon,
                                unselectedIcon = tabBarItem.unselectedIcon,
                                title = tabBarItem.title
                            )
                        },
                        label = { Text(tabBarItem.title) },
                        selected = currentDestination?.hierarchy?.any { it.route == tabBarItem.title } == true,
                        onClick = {
                            navController.navigate(tabBarItem.title) {
                                // Pop up to the start destination of the graph to
                                // avoid building up a large stack of destinations
                                // on the back stack as users select items
                                popUpTo(navController.graph.findStartDestination().id) {
                                    saveState = true
                                }
                                // Avoid multiple copies of the same destination when
                                // reselecting the same item
                                launchSingleTop = true
                                // Restore state when reselecting a previously selected item
                                restoreState = true
                            }
                        }
                    )
                }
            }
        }
    ) {
        NavHost(
            navController = navController,
            startDestination = calculatorTab.title
        )
        {
            composable(calculatorTab.title) {
                CalculatorScreen()
            }
            composable(converterTab.title) {
                ConverterScreen(dataSource, context)
            }
        }
    }
}

@Composable
fun TabBarIconView(
    isSelected: Boolean,
    selectedIcon: ImageVector,
    unselectedIcon: ImageVector,
    title: String
) {
    Icon(
        imageVector = if (isSelected) {
            selectedIcon
        } else {
            unselectedIcon
        },
        contentDescription = title
    )
}


@Preview(showSystemUi = true)
@Composable
fun AppPre() {
    CalculatorApp()
}