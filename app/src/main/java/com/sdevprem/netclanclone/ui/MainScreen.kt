package com.sdevprem.netclanclone.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.DrawerState
import androidx.compose.material.DrawerValue
import androidx.compose.material.Scaffold
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.rememberScaffoldState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.sdevprem.netclanclone.R
import com.sdevprem.netclanclone.ui.common.navigateToBottomNavDestination
import com.sdevprem.netclanclone.ui.navigation.AppNavigation
import com.sdevprem.netclanclone.ui.navigation.BottomNavDestination
import com.sdevprem.netclanclone.ui.navigation.Destination
import com.sdevprem.netclanclone.ui.navigation.bottomNavDestination
import kotlinx.coroutines.launch

@Composable
fun MainScreen(
    navHostController: NavHostController,
    startDestination: Destination
) {
    var isTopAppBarVisible by rememberSaveable { mutableStateOf(false) }
    var isBottomAppBarVisible by rememberSaveable { mutableStateOf(false) }
    val scaffoldState = rememberScaffoldState(
        drawerState = DrawerState(initialValue = DrawerValue.Closed)
    )
    val scope = rememberCoroutineScope()
    Scaffold(
        topBar = {
            if (isTopAppBarVisible)
                AppBar {
                    scope.launch {
                        if (scaffoldState.drawerState.isClosed)
                            scaffoldState.drawerState.open()
                    }
                }
        },
        drawerContent = {
            if (isTopAppBarVisible) {
                DrawerHeader()
                DrawerMenu()
            }
        },
        bottomBar = {
            if (isBottomAppBarVisible)
                BottomAppBar(navController = navHostController)
        },
        drawerGesturesEnabled = isTopAppBarVisible,
        scaffoldState = scaffoldState
    ) {
        Box(
            modifier = Modifier
                .padding(it)
        ) {
            AppNavigation(
                navHostController = navHostController,
                startDestination = startDestination
            )
        }
    }

    navHostController.addOnDestinationChangedListener { _, destination, _ ->
        when (destination.route) {
            Destination.ProfileEdit.route -> {
                isBottomAppBarVisible = false
                isTopAppBarVisible = false
            }

            Destination.Welcome.route -> {
                isBottomAppBarVisible = false
                isTopAppBarVisible = false
            }

            Destination.OnBoarding.route -> {
                isBottomAppBarVisible = false
                isTopAppBarVisible = false
            }

            else -> {
                isBottomAppBarVisible = true
                isTopAppBarVisible = true
            }
        }
    }
}

@Composable
@Preview
private fun DrawerHeader() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(color = MaterialTheme.colorScheme.primary)
            .padding(vertical = 16.dp)
    ) {
        IconButton(
            onClick = { /*TODO*/ },
            modifier = Modifier
                .align(Alignment.TopEnd)
        ) {
            Icon(
                imageVector = Icons.Default.Settings,
                contentDescription = null,
                tint = MaterialTheme.colorScheme.onPrimary
            )
        }
        Column(
            modifier = Modifier
                .padding(start = 24.dp)
                .wrapContentHeight()
                .padding(vertical = 8.dp)
        ) {

            Icon(
                imageVector = Icons.Default.Person,
                contentDescription = null,
                modifier =
                Modifier
                    .size(64.dp)
                    .background(
                        color = MaterialTheme.colorScheme.surface,
                        shape = CircleShape
                    ),
                tint = MaterialTheme.colorScheme.primary
            )
            Spacer(Modifier.size(16.dp))
            Text(
                text = "Jackson Perry",
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.surface
            )
            Text(
                text = "JJCHIC00030023",
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.surface,
                modifier = Modifier
                    .wrapContentWidth()
            )
            LinearProgressIndicator(
                trackColor = MaterialTheme.colorScheme.surface.copy(alpha = 0.5f),
                color = MaterialTheme.colorScheme.surface,
                strokeCap = StrokeCap.Round,
                progress = 0.3f,
                modifier = Modifier
                    .padding(vertical = 4.dp)
                    .height(8.dp)
                    .width(100.dp)
            )

            Text(
                text = "Available",
                style = MaterialTheme.typography.labelSmall,
                color = MaterialTheme.colorScheme.surface
            )
        }
    }
}

@Composable
@Preview(showBackground = true)
private fun DrawerMenu() {
    Column(
        modifier = Modifier
            .background(color = MaterialTheme.colorScheme.surface)
            .fillMaxHeight()
            .verticalScroll(rememberScrollState())
    ) {
        DrawerItem(name = "Edit Profile", imageVector = Icons.Default.Person)
        DrawerItem(
            name = "My Network",
            imageVector = ImageVector.vectorResource(id = R.drawable.ic_people)
        )
        DrawerItem(name = "Business Cards", imageVector = Icons.Default.AccountBox)
        DrawerItem(name = "Live Location", imageVector = Icons.Default.LocationOn)
    }
}

@Composable
private fun DrawerItem(
    name: String,
    imageVector: ImageVector
) {
    NavigationDrawerItem(
        label = {
            Text(
                text = name,
                style = MaterialTheme.typography.labelLarge,
                color = MaterialTheme.colorScheme.primary
            )
        },
        selected = false,
        onClick = { /*TODO*/ },
        icon = {
            Icon(
                imageVector = imageVector,
                contentDescription = name,
                tint = MaterialTheme.colorScheme.primary
            )
        }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun AppBar(
    onDrawerButtonClick: () -> Unit
) {
    TopAppBar(
        title = {
            Column(verticalArrangement = Arrangement.Center) {
                Text(
                    text = "Howdy Jackson Perry!!",
                    style = MaterialTheme.typography.titleMedium
                )
                Spacer(modifier = Modifier.size(4.dp))
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        imageVector = Icons.Default.LocationOn,
                        contentDescription = "User location",
                        modifier = Modifier
                            .size(16.dp)
                    )
                    Spacer(modifier = Modifier.size(4.dp))
                    Text(
                        text = "Washington DC, USA",
                        style = MaterialTheme.typography.bodyMedium
                    )
                }

            }
        },
        navigationIcon = {
            IconButton(onClick = onDrawerButtonClick) {
                Icon(
                    imageVector = Icons.Default.Menu,
                    contentDescription = "More menu",
                    tint = MaterialTheme.colorScheme.onPrimary,
                    modifier = Modifier
                        .size(40.dp)
                )
            }
        },
        actions = {
            Column(
                modifier = Modifier
                    .padding(end = 8.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Icon(
                    imageVector = Icons.Default.Menu,
                    contentDescription = "Refine",
                    tint = MaterialTheme.colorScheme.onPrimary,
                    modifier = Modifier
                        .size(32.dp)
                )
                Text(
                    text = "Refine",
                    style = MaterialTheme.typography.labelLarge,
                )
            }
        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = MaterialTheme.colorScheme.primary,
            titleContentColor = MaterialTheme.colorScheme.onPrimary,
            actionIconContentColor = MaterialTheme.colorScheme.onPrimary,
            navigationIconContentColor = MaterialTheme.colorScheme.onPrimary,
        )
    )
}

@Composable
fun RowScope.AddBottomNavItem(
    screen: BottomNavDestination,
    currentDestination: NavDestination?,
    onItemClick: (Destination) -> Unit
) {
    val isSelected = currentDestination?.hierarchy?.any {
        it.route == screen.route
    } == true

    val contentColor = if (isSelected)
        MaterialTheme.colorScheme.primary
    else MaterialTheme.colorScheme.primary.copy(alpha = .5f)

    BottomNavigationItem(
        label = {
            Text(
                text = screen.title,
                style = MaterialTheme.typography.labelLarge,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                color = contentColor
            )
        },
        icon = {
            Icon(
                imageVector = ImageVector.vectorResource(id = screen.icon),
                contentDescription = screen.title,
                modifier = Modifier
                    .size(24.dp),
                tint = contentColor
            )
        },
        selected = isSelected,
        onClick = { onItemClick(screen) },
    )
}

@Composable
private fun BottomAppBar(
    navController: NavController
) {
    BottomNavigation(
        backgroundColor = MaterialTheme.colorScheme.surface,
    ) {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        bottomNavDestination.forEach {
            AddBottomNavItem(
                screen = it,
                currentDestination = navBackStackEntry?.destination,
            ) { destination ->
                navController.navigateToBottomNavDestination(destination)
            }
        }
    }
}

@Composable
@Preview(showBackground = true)
private fun MainScreenPreview() {
    MainScreen(
        navHostController = rememberNavController(),
        startDestination = BottomNavDestination.Explore
    )
}

