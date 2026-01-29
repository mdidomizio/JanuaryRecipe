package com.example.januaryrecipe.ui

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.januaryrecipe.R
import com.example.januaryrecipe.data.Recipe
import com.example.januaryrecipe.ui.theme.InstrumentSerif
import kotlinx.coroutines.launch

@SuppressLint("StringFormatInvalid")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    // onRecipeClicked: () -> Unit,
    homeViewModel: HomeViewModel = viewModel(),
    modifier: Modifier = Modifier
) {
    val backgroundColor = colorResource(R.color.background)
    var searchQuery by remember { mutableStateOf("") }

    val favoritesOrder = homeViewModel.favorites

    val allMatching = if (searchQuery.isEmpty()) {
        Recipe.entries
    } else {
        Recipe.entries.filter {
            it.title.contains(searchQuery, ignoreCase = true)
        }
    }
    val pinned = favoritesOrder.mapNotNull { favTitle ->
        allMatching.find { it.title == favTitle }
    }
    val others = allMatching.filterNot { it.title in favoritesOrder }
    val displayedRecipes = pinned + others

    val snackbarHostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()
    val context = LocalContext.current

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
        snackbarHost = {
            SnackbarHost(hostState = snackbarHostState) { data ->
                IconSnackbar(data)
            }
        },
        containerColor = backgroundColor,
        modifier = modifier
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .padding(horizontal = 16.dp)
                .fillMaxSize()
        ) {
            SearchBarHome(
                searchQuery = searchQuery,
                onSearchQueryChanged = { newQuery ->
                    searchQuery = newQuery
                }
            )

            Spacer(modifier = Modifier.height(16.dp))

            LazyColumn(
                modifier = Modifier
                    .weight(1f)
                    .padding(bottom = 8.dp)
            ) {
                items(
                    items = displayedRecipes,
                    key = { it.title }
                ) { recipe ->
                    val isFavorite = homeViewModel.isFavorite(recipe.title)
                    RecipeCard(
                        recipe = recipe,
                        onCardClicked = {},
                        onFavoriteClicked = {
                            val willBeFavorite = !isFavorite
                            homeViewModel.toggleFavorite(recipe)

                            scope.launch {
                                snackbarHostState.showSnackbar(
                                    IconSnackbarVisuals(
                                        message =
                                            if (willBeFavorite) {
                                                context.getString(
                                                    R.string.favorites_recipe_added,
                                                    recipe.title
                                                )
                                            } else {
                                                context.getString(
                                                    R.string.favorites_recipe_removed,
                                                    recipe.title
                                                )
                                            },
                                        icon = Icons.Filled.FavoriteBorder,
                                        duration = SnackbarDuration.Short
                                    )
                                )
                            }
                        },
                        isFavorite = isFavorite
                    )
                }
            }
        }
    }
}
