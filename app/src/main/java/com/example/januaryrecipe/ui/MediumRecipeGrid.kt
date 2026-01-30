package com.example.januaryrecipe.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.example.januaryrecipe.R
import com.example.januaryrecipe.data.Recipe
import kotlinx.coroutines.launch

@Composable
fun MediumRecipeGrid(
    recipes: List<Recipe>,
    homeViewModel: HomeViewModel,
    snackbarHostState: SnackbarHostState,
    onRecipeClicked: (Recipe) -> Unit,
    modifier: Modifier = Modifier
) {
    val scope = rememberCoroutineScope()
    val context = LocalContext.current

    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        modifier = modifier.padding(bottom = 8.dp),
        horizontalArrangement = Arrangement.spacedBy(12.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        items(
            items = recipes,
            key = { it.title }
        ) { recipe ->
            val isFavorite = homeViewModel.isFavorite(recipe.title)
            RecipeCard(
                recipe = recipe,
                onCardClicked = { onRecipeClicked(recipe) },
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
