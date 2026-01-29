package com.example.januaryrecipe.ui

import android.app.Application
import android.content.Context
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.januaryrecipe.data.Recipe
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class HomeViewModel(application: Application) : AndroidViewModel(application) {
    private val prefsName = "favorites_prefs"
    private val key = "favorites_key"
    val favorites: SnapshotStateList<String> = mutableStateListOf()

    private val _isRefreshing = MutableStateFlow(false)
    val isRefreshing = _isRefreshing.asStateFlow()

    init {
        loadFavorites()
    }

    fun refresh() {
        viewModelScope.launch {
            _isRefreshing.value = true
            delay(1500) // Simulate a network call
            loadFavorites()
            _isRefreshing.value = false
        }
    }

    private fun loadFavorites() {
        val prefs = getApplication<Application>()
            .getSharedPreferences(prefsName, Context.MODE_PRIVATE)
        val joined = prefs.getString(key, "") ?: ""
        if (joined.isNotBlank()) {
            favorites.clear()
            favorites.addAll(joined.split("||"))
        }
    }

    private fun saveFavorites() {
        val prefs = getApplication<Application>()
            .getSharedPreferences(prefsName, Context.MODE_PRIVATE)
        prefs.edit().putString(key, favorites.joinToString("||")).apply()
    }

    fun toggleFavorite(recipe: Recipe) {
        viewModelScope.launch {
            val title = recipe.title
            if (favorites.contains(title)) {
                favorites.remove(title)
            } else {
                favorites.add(0, title)
            }
            saveFavorites()
        }
    }

    fun isFavorite(title: String): Boolean = favorites.contains(title)
}
