package com.sdevprem.netclanclone.ui.home.explore

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.TabRowDefaults
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.sdevprem.netclanclone.ui.theme.AppTheme
import kotlinx.coroutines.launch

private val tabs = listOf(
    "Personal",
    "Business",
    "Merchant"
)

@OptIn(ExperimentalFoundationApi::class)
@Composable
@Preview(showBackground = true)
fun ExploreScreen() {
    val pagerState = rememberPagerState()
    val coroutineScope = rememberCoroutineScope()
    Box(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier
                .fillMaxSize()
        ) {
            Tabs(selectedTabIndex = pagerState.currentPage, pagerState = pagerState) {
                coroutineScope.launch {
                    pagerState.animateScrollToPage(it)
                }
            }
            HorizontalPager(
                pageCount = tabs.size,
                state = pagerState
            ) {
                ExploreTabContent()
            }
        }
    }

}

@OptIn(ExperimentalFoundationApi::class)
@Composable
private fun Tabs(
    selectedTabIndex: Int,
    pagerState: PagerState,
    onTabClick: (Int) -> Unit
) {
    TabRow(
        selectedTabIndex = selectedTabIndex,
        containerColor = MaterialTheme.colorScheme.primary.copy(alpha = 0.9f),
        indicator = { tabPositions ->
            if (selectedTabIndex < tabPositions.size) {
                TabRowDefaults.Indicator(
                    Modifier.tabIndicatorOffset(tabPositions[selectedTabIndex]),
                    color = MaterialTheme.colorScheme.onPrimary
                )
            }
        }
    ) {
        tabs.forEachIndexed { index, tabString ->
            Tab(
                selected = pagerState.currentPage == index,
                onClick = { onTabClick(index) },
                text = {
                    Text(
                        text = tabString,
                        style = MaterialTheme.typography.bodyLarge.copy(
                            fontWeight = FontWeight.Medium,
                        ),
                        modifier = Modifier
                            .padding(vertical = 8.dp)
                    )
                },
                selectedContentColor = MaterialTheme.colorScheme.onPrimary,
                unselectedContentColor = MaterialTheme.colorScheme.surfaceVariant,
            )
        }
    }
}

@Composable
@Preview(showBackground = true)
private fun ExploreScreenPreview() {
    AppTheme {
        // A surface container using the 'background' color from the theme
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            ExploreScreen()
        }
    }
}