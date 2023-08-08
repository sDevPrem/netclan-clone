package com.sdevprem.netclanclone.ui.home.explore

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.sdevprem.netclanclone.ui.common.isScrollingUp
import com.sdevprem.netclanclone.ui.home.explore.model.ExploreListItem

private val list = listOf(
    ExploreListItem(
        firstName = "Kunal",
        lastName = "Singh",
        profession = "Front End Enginner",
        place = "Bhagalpur",
        progress = 0.5f,
        activities = listOf(
            "Coffee",
            "Business",
            "Friendship",
        ),
        description = "HI community! I am open to new connections\nHey I am Kunal. A passionate frontend developer with strong knowledge of HTML.",
    ),
    ExploreListItem(
        firstName = "Lakshman",
        lastName = "Das",
        profession = "Consultant Fonctionnel",
        place = "Karnataka",
        progress = 0.4f,
        activities = listOf(
            "Dating",
            "Business",
            "Hobbies",
            "Friendship",
            "Movies",
        ),
        description = "Business consultant",
    ),
    ExploreListItem(
        firstName = "Sharad",
        lastName = "Priya",
        profession = "Student",
        place = "Jamui",
        progress = 0.3f,
        activities = listOf(
            "Coffee",
            "Business",
            "Friendship",
        ),
        description = "HI community! I am open to new connections",
    ),
    ExploreListItem(
        firstName = "Shivam",
        lastName = "Kumar",
        profession = "Student",
        place = "Bangalore",
        progress = 0.8f,
        activities = listOf(
            "Coffee",
            "Business",
            "Friendship",
        ),
        description = "HI community! I am open to new connections",
    ),
    ExploreListItem(
        firstName = "Prashant",
        lastName = "Kumar",
        profession = "Tester",
        place = "Kolkata",
        progress = 0.2f,
        activities = listOf(
            "Dating",
        ),
        description = "HI community! I am open to new connections",
    ),

    )

@OptIn(ExperimentalAnimationApi::class)
@Composable
@Preview(showBackground = true)
fun ExploreTabContent() {
    Box(modifier = Modifier.fillMaxSize()) {
        val lazyListState = rememberLazyListState()
        ExplorePagerContent(
            onFilterButtonClick = {},
            onSearchButtonClick = {}
        ) {
            LazyColumn(
                modifier = Modifier
                    .padding(horizontal = 8.dp)
                    .weight(1f),
                state = lazyListState,
            ) {
                items(list) {
                    ExploreListItemCard(
                        item = it,
                        modifier = Modifier
                            .padding(vertical = 16.dp)
                    ) {}
                }
            }
        }

        AnimatedVisibility(
            visible = lazyListState.isScrollingUp(),
            enter = scaleIn(),
            exit = scaleOut(),
            modifier = Modifier
                .padding(16.dp)
                .align(Alignment.BottomEnd)
        ) {
            FloatingActionButton(
                onClick = { /*TODO*/ },
                containerColor = MaterialTheme.colorScheme.primary,
                contentColor = MaterialTheme.colorScheme.onPrimary,
            ) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = null
                )
            }
        }

    }
}