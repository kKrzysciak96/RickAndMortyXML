package com.example.rickandmortyxml.core.navigation

import androidx.annotation.IdRes

interface FragmentNavigator {
    fun navigateTo(@IdRes destination: Int)
    fun <T> navigateTo(@IdRes destination: Int, pair: Pair<String, T>? = null)
    fun goBack(@IdRes destination: Int? = null, inclusive: Boolean = false)
    fun clearHistory()
}