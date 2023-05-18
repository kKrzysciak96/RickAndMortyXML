package com.example.rickandmortyxml.core.navigation

import androidx.annotation.IdRes
import androidx.core.os.bundleOf
import androidx.fragment.app.FragmentActivity
import androidx.navigation.fragment.findNavController
import com.example.rickandmortyxml.core.provider.ActivityProvider

class FragmentNavigatorImpl(
    private val activityProvider: ActivityProvider,
    @IdRes private val navHostFragmentRes: Int,
    @IdRes private val homeDestinationRes: Int
) : FragmentNavigator {
    private fun getSupportFragmentManager() =
        (activityProvider.foregroundActivity as FragmentActivity).supportFragmentManager

    private fun getNavController() =
        getSupportFragmentManager().findFragmentById(navHostFragmentRes)?.findNavController()

    override fun navigateTo(destination: Int) {
        navigateTo<Unit>(destination)
    }

    override fun <T> navigateTo(destination: Int, pair: Pair<String, T>?) {
        val bundle = pair?.let { bundleOf(it) }
        getNavController()?.navigate(destination, bundle)
    }


    override fun goBack(destination: Int?, inclusive: Boolean) {
        if (destination == null) getNavController()?.popBackStack()
        else getNavController()?.popBackStack(destination, inclusive)
    }

    override fun clearHistory() {
        goBack(homeDestinationRes)
    }

}

