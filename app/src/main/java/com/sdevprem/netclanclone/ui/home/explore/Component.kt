package com.sdevprem.netclanclone.ui.home.explore

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.sdevprem.netclanclone.R
import com.sdevprem.netclanclone.ui.common.SearchBar
import com.sdevprem.netclanclone.ui.home.explore.model.ExploreListItem

@Composable
@Preview(showBackground = true)
fun SearchHeader(
    modifier: Modifier = Modifier,
    onSearch: (String) -> Unit = {},
    onFilterButtonClick: (String) -> Unit = {},
) {
    var searchText by remember { mutableStateOf("") }
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(start = 24.dp, end = 8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        SearchBar(
            searchText = searchText,
            onSearchTextChange = { searchText = it },
            onSearch = onSearch,
            modifier = Modifier
                .weight(1f),
            color = MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.4f)
        )
        Spacer(modifier = Modifier.size(8.dp))
        IconButton(onClick = { onFilterButtonClick(searchText) }) {
            Icon(
                imageVector = ImageVector.vectorResource(id = R.drawable.ic_filter),
                contentDescription = "Filter",
                tint = MaterialTheme.colorScheme.primary,
                modifier = Modifier
                    .size(32.dp)
            )
        }
    }
}

@Composable
fun ExplorePagerContent(
    modifier: Modifier = Modifier,
    onFilterButtonClick: (String) -> Unit,
    onSearchButtonClick: (String) -> Unit,
    content: @Composable ColumnScope.() -> Unit
) {
    Column(
        modifier = modifier
    ) {
        SearchHeader(
            modifier = Modifier
                .background(color = MaterialTheme.colorScheme.surface)
                .padding(top = 16.dp, bottom = 8.dp),
            onSearch = onSearchButtonClick,
            onFilterButtonClick = onFilterButtonClick,
        )
        content()
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun ExploreListItemCard(
    modifier: Modifier = Modifier,
    item: ExploreListItem,
    onClick: () -> Unit
) {
    val color = MaterialTheme.colorScheme.primary
    val variantColor = MaterialTheme.colorScheme.primary.copy(alpha = 0.8f)
    Box(
        modifier = modifier
    ) {
        Card(
            onClick = onClick,
            backgroundColor = MaterialTheme.colorScheme.surface,
            elevation = 2.dp,
            modifier = Modifier
                .padding(start = 32.dp),
            contentColor = MaterialTheme.colorScheme.primary,
            shape = MaterialTheme.shapes.large
        ) {
            Column(
                modifier = Modifier
                    .padding(vertical = 16.dp)
                    .padding(start = 16.dp, end = 16.dp)
            ) {
                Box(modifier = Modifier.fillMaxWidth()) {
                    Text(
                        text = "+ INVITE",
                        style = MaterialTheme.typography.bodyLarge,
                        fontWeight = FontWeight.Medium,
                        modifier = Modifier
                            .align(Alignment.CenterEnd),
                        color = color
                    )
                }
                ExploreListItemCardHeader(
                    item = item,
                    modifier = Modifier.padding(start = 56.dp),
                    color = color,
                    variantColor = variantColor
                )
                Spacer(modifier = Modifier.height(16.dp))
                ExploreListItemCardBody(
                    item = item,
                    color = color,
                    variantColor = variantColor
                )
            }
        }

        ExploreListItemCardPhoto(
            item = item,
            modifier = Modifier
                .padding(
                    top = 16.dp,
                    start = 8.dp
                )
        )
    }
}

@Composable
private fun ExploreListItemCardPhoto(
    modifier: Modifier = Modifier,
    item: ExploreListItem
) {
    Box(
        modifier = modifier
            .shadow(
                elevation = 4.dp,
                shape = MaterialTheme.shapes.large,
                clip = true
            )
            .background(
                color = MaterialTheme.colorScheme.secondaryContainer,
                shape = MaterialTheme.shapes.large
            )
            .padding(vertical = 24.dp, horizontal = 20.dp)

    ) {
        Text(
            text = "${item.firstName.first()}${item.lastName.first()}",
            style = MaterialTheme.typography.headlineMedium.copy(
                color = MaterialTheme.colorScheme.onSecondaryContainer,
                fontWeight = FontWeight.Medium
            ),
            modifier = Modifier
                .align(Alignment.Center)
        )
    }
}

@Composable
private fun ExploreListItemCardHeader(
    modifier: Modifier = Modifier,
    item: ExploreListItem,
    color: Color,
    variantColor: Color
) {
    Column(modifier = modifier) {
        Text(
            text = "${item.firstName} ${item.lastName}",
            style = MaterialTheme.typography.titleMedium.copy(
                fontWeight = FontWeight.Medium,
                color = color
            )
        )
        Text(
            text = "${item.place} | ${item.profession}",
            style = MaterialTheme.typography.bodyMedium,
            color = variantColor
        )

        LinearProgressIndicator(
            progress = item.progress,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            backgroundColor = MaterialTheme.colorScheme.surfaceVariant,
            strokeCap = StrokeCap.Round,
            modifier = Modifier
                .padding(vertical = 8.dp)
                .height(12.dp)
                .width(100.dp)

        )
    }
}

@Composable
private fun ExploreListItemCardBody(
    modifier: Modifier = Modifier,
    item: ExploreListItem,
    color: Color,
    variantColor: Color
) {
    Column(modifier = modifier) {
        val activityString = buildString {
            if (item.activities.isEmpty())
                return@buildString

            append(item.activities[0])
            for (i in 1 until item.activities.size)
                append(" | ${item.activities[i]}")
        }
        Text(
            text = activityString,
            style = MaterialTheme.typography.labelLarge.copy(
                fontWeight = FontWeight.Medium,
                color = color
            )
        )
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            text = item.description,
            style = MaterialTheme.typography.bodyMedium,
            maxLines = 6,
            overflow = TextOverflow.Ellipsis,
            color = variantColor
        )
    }
}

@Composable
@Preview(showBackground = true)
private fun ExploreListItemCardPreview() {
    ExploreListItemCard(
        item = ExploreListItem(
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
        onClick = {},
        modifier = Modifier.fillMaxWidth()
    )
}