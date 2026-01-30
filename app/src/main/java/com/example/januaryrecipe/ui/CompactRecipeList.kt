package com.example.januaryrecipe.ui

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
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
fun CompactRecipeList(
    recipes: List<Recipe>,
    homeViewModel: HomeViewModel,
    snackbarHostState: SnackbarHostState,
    onRecipeClicked: (Recipe) -> Unit
) {
    val scope = rememberCoroutineScope()
    val context = LocalContext.current

    LazyColumn(
        modifier = Modifier
            .padding(bottom = 8.dp)
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
