package com.sdevprem.netclanclone.ui.common

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
@Preview(showBackground = true)
fun SearchBar(
    modifier: Modifier = Modifier,
    searchText: String = "",
    onSearchTextChange: (String) -> Unit = {},
    color: Color = MaterialTheme.colorScheme.onSurfaceVariant,
    onSearch: (String) -> Unit = {}
) {
    Row(
        modifier = modifier
            .border(
                shape = RoundedCornerShape(50),
                color = color,
                width = 1.dp
            )
            .padding(
                vertical = 4.dp,
                horizontal = 16.dp
            ),
        verticalAlignment = Alignment.CenterVertically
    ) {
        val searchTextStyle = MaterialTheme.typography.bodyMedium.copy(
            color = color
        )

        Icon(
            imageVector = Icons.Default.Search,
            contentDescription = "search",
            tint = color,
            modifier = Modifier
                .padding(horizontal = 8.dp)
                .size(16.dp)
        )

        BasicTextField(
            value = searchText,
            onValueChange = onSearchTextChange,
            decorationBox = {
                it.invoke()
                if (searchText.isBlank())
                    Text(
                        text = "Search",
                        style = searchTextStyle
                    )
            },
            textStyle = searchTextStyle,
            modifier = Modifier
                .weight(1f),
            keyboardActions = KeyboardActions(onSearch = {
                onSearch(searchText)
            }),
            keyboardOptions = KeyboardOptions.Default.copy(
                imeAction = ImeAction.Search
            ),
            maxLines = 1,
            singleLine = true
        )

        if (searchText.isNotBlank())
            Icon(
                imageVector = Icons.Default.Close,
                contentDescription = "search",
                tint = color,
                modifier = Modifier
                    .padding(horizontal = 8.dp)
                    .size(16.dp)
                    .clickable {
                        onSearchTextChange("")
                    }
            )
    }
}