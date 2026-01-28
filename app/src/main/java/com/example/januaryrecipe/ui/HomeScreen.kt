package com.example.januaryrecipe.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.januaryrecipe.R
import com.example.januaryrecipe.data.Recipe
import com.example.januaryrecipe.ui.theme.InstrumentSans
import com.example.januaryrecipe.ui.theme.InstrumentSerif

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    // onRecipeClicked: () -> Unit,
    modifier: Modifier = Modifier
) {
    val backgroundColor = colorResource(R.color.background)
    var searchQuery by remember { mutableStateOf("") }
    val filteredRecipes = if (searchQuery.isEmpty()) {
        Recipe.entries
    } else {
        Recipe.entries.filter {
            it.title.contains(searchQuery, ignoreCase = true) ||
                    it.ingredients.contains(searchQuery, ignoreCase = true)
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = stringResource(id = R.string.home_screen_title),
                        fontFamily = InstrumentSerif,
                        fontWeight = FontWeight.Normal,
                        fontSize = 40.sp
                    )
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = backgroundColor,
                    titleContentColor = colorResource(R.color.text_primary)
                )
            )
        },
        containerColor = backgroundColor,
        modifier = modifier
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier
                .padding(innerPadding)
                .padding(horizontal = 16.dp, vertical = 8.dp)
                .fillMaxSize()
        ) {
            item {
                OutlinedTextField(
                    value = searchQuery,
                    onValueChange = { searchQuery = it },
                    modifier = Modifier.fillMaxWidth(),
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
                        unfocusedContainerColor = colorResource(id = R.color.surface_input ),
                        focusedBorderColor = colorResource(id = R.color.outline),
                        unfocusedBorderColor = colorResource(id = R.color.outline)
                    ),
                    shape = RoundedCornerShape(12.dp)
                )

                Spacer(modifier = Modifier.height(16.dp))
            }
            items(
                items = filteredRecipes,
                key = { it.title}
            ) { recipe ->
                RecipeCard(
                    recipe = recipe,
                    onCardClicked = {}
                )
            }
        }
    }
}
