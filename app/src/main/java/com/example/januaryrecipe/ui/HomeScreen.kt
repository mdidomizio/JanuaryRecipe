package com.example.januaryrecipe.ui

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.pulltorefresh.PullToRefreshBox
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
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
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.januaryrecipe.R
import com.example.januaryrecipe.data.Recipe
import com.example.januaryrecipe.ui.theme.InstrumentSerif

@SuppressLint("StringFormatInvalid")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    windowWidthSizeClass: WindowWidthSizeClass,
    homeViewModel: HomeViewModel = viewModel(),
    modifier: Modifier = Modifier
) {
    val backgroundColor = colorResource(R.color.background)
    var searchQuery by remember { mutableStateOf("") }
    var selectedRecipe by remember { mutableStateOf<Recipe?>(null) }

    val favoritesOrder = homeViewModel.favorites
    val isRefreshing by homeViewModel.isRefreshing.collectAsStateWithLifecycle()


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

            PullToRefreshBox(
                isRefreshing = isRefreshing,
                onRefresh = { homeViewModel.refresh() },
                modifier = Modifier
            ) {
                if (displayedRecipes.isEmpty()){
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.TopCenter
                    ) {
                        Text(
                            text = stringResource(id =  R.string.no_search_result),
                            fontFamily = InstrumentSerif,
                            fontWeight = FontWeight.Normal,
                            fontSize = 28.sp
                        )
                    }
                } else {
                    when (windowWidthSizeClass) {
                        WindowWidthSizeClass.Compact -> {
                            CompactRecipeList(
                                recipes =displayedRecipes,
                                homeViewModel = homeViewModel,
                                snackbarHostState = snackbarHostState,
                                onRecipeClicked = { selectedRecipe = it}
                            )
                        }

                        WindowWidthSizeClass.Medium,  WindowWidthSizeClass.Expanded -> {
                            MediumRecipeGrid(
                                recipes =displayedRecipes,
                                homeViewModel = homeViewModel,
                                snackbarHostState = snackbarHostState,
                                onRecipeClicked = { selectedRecipe = it}
                            )
                        }
                    }
                }
            }
        }
        selectedRecipe?.let {
            RecipeDialog(
                recipe = it,
                onDismissRequestClicked = {},
                windowWidthSizeClass = windowWidthSizeClass
            )
        }
    }
}
