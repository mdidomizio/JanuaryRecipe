package com.example.januaryrecipe.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.januaryrecipe.R
import com.example.januaryrecipe.ui.theme.InstrumentSans

@Composable
fun SearchBarHome (
    searchQuery: String,
    onSearchQueryChanged: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    OutlinedTextField(
        value = searchQuery,
        onValueChange = onSearchQueryChanged,
        modifier = modifier
            .fillMaxWidth()
            .padding(top = 8.dp),
        placeholder = {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    Icons.Default.Search,
                    contentDescription = stringResource(id = R.string.search_bar_placeholder),
                    tint = colorResource(id = R.color.text_secondary)
                )
                Text(
                    text = stringResource(id = R.string.search_bar_placeholder),
                    fontFamily = InstrumentSans,
                    fontWeight = FontWeight.Normal,
                    fontSize = 16.sp,
                    color = colorResource(id = R.color.text_secondary),
                    modifier = Modifier.padding(start = 8.dp)
                )
            }
        },
        colors = OutlinedTextFieldDefaults.colors(
            focusedContainerColor = colorResource(id = R.color.surface_input),
            unfocusedContainerColor = colorResource(id = R.color.surface_input),
            focusedBorderColor = colorResource(id = R.color.outline),
            unfocusedBorderColor = colorResource(id = R.color.outline)
        ),
        shape = RoundedCornerShape(12.dp)
    )
}
