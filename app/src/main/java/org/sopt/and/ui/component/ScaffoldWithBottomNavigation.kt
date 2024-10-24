package org.sopt.and.ui.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.Icon
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import org.sopt.and.R
import org.sopt.and.ui.theme.WavveBg
import org.sopt.and.ui.theme.WavveDisabled
import org.sopt.and.navigation.Screen
import org.sopt.and.ui.theme.BottomNavigationItemUnselected
import org.sopt.and.ui.theme.White
import org.sopt.and.utils.SnackBarUtils

@Composable
fun ScaffoldWithBottomNavigation(
    navController: NavController,
    content: @Composable () -> Unit
) {

    val colors = NavigationBarItemDefaults.colors(
        selectedIconColor = White,
        unselectedIconColor = BottomNavigationItemUnselected,
        selectedTextColor = White,
        unselectedTextColor = BottomNavigationItemUnselected,
        indicatorColor = Color.Transparent
    )
    //현재 라우트
    var currentRoute by remember { mutableStateOf<String?>(null) }
    LaunchedEffect(navController) {
        navController.currentBackStackEntryFlow.collect { backStackEntry ->
            currentRoute = backStackEntry.destination.route.toString()
        }
    }
    val bottomBarScreens = listOf(
        Screen.Home.javaClass.canonicalName ,
        Screen.Search.javaClass.canonicalName,
        Screen.My.javaClass.canonicalName
    )
    val snackbarHostState = remember { SnackbarHostState() }

    LaunchedEffect(Unit) {
        SnackBarUtils.init(snackbarHostState)
    }

    Scaffold(
        snackbarHost = { SnackbarHost(hostState = snackbarHostState) },
        bottomBar = {
            if (currentRoute in bottomBarScreens) {
                BottomAppBar(
                    containerColor = WavveBg,
                    contentColor = WavveDisabled
                ) {
                    NavigationBarItem(
                        icon = {
                            Icon(
                                ImageVector.vectorResource(R.drawable.ic_home),
                                contentDescription = "${Screen.Home}",
                                Modifier.size(32.dp)
                            )
                        },
                        label = { Text(stringResource(R.string.title_home)) },
                        selected = currentRoute == Screen.Home.javaClass.canonicalName,
                        onClick = {
                            navigateToScreen(navController, Screen.Home)
                        },
                        colors = colors
                    )
                    NavigationBarItem(
                        icon = {
                            Icon(
                                ImageVector.vectorResource(R.drawable.ic_search),
                                contentDescription = "${Screen.Search}",
                                Modifier.size(32.dp)
                            )
                        },
                        label = { Text(stringResource(R.string.title_search)) },
                        selected = currentRoute == Screen.Search.javaClass.canonicalName,
                        onClick = {
                            navigateToScreen(navController, Screen.Search)
                        },
                        colors = colors
                    )
                    NavigationBarItem(
                        icon = {
                            Image(
                                painter = painterResource(
                                    R.drawable.profile_default
                                ),
                                contentDescription = stringResource(R.string.my_page_image_description_profile),
                                modifier = Modifier.size(32.dp)
                            )
                        },
                        label = { Text(stringResource(R.string.title_my)) },
                        selected = currentRoute == Screen.My.javaClass.canonicalName,
                        onClick = {
                            navigateToScreen(navController, Screen.My)
                        },
                        colors = colors,
                    )
                }
            }
        }
    ) { innerPadding ->
        Column(modifier = Modifier.padding(innerPadding)) {
            content()
        }
    }
}

private fun navigateToScreen(navController: NavController, screen: Screen) {
    navController.navigate(screen) {
        popUpTo(screen) { inclusive = false }
        launchSingleTop = true
    }
}