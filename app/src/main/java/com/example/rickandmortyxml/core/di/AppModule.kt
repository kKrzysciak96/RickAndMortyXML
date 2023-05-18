package com.example.rickandmortyxml.core.di

import android.content.Context
import android.net.ConnectivityManager
import com.example.rickandmortyxml.R
import com.example.rickandmortyxml.core.exception.ErrorMapper
import com.example.rickandmortyxml.core.exception.ErrorMapperImpl
import com.example.rickandmortyxml.core.exception.ErrorWrapper
import com.example.rickandmortyxml.core.exception.ErrorWrapperImpl
import com.example.rickandmortyxml.core.navigation.FragmentNavigator
import com.example.rickandmortyxml.core.navigation.FragmentNavigatorImpl
import com.example.rickandmortyxml.core.network.NetworkStateProvider
import com.example.rickandmortyxml.core.network.NetworkStateProviderImpl
import com.example.rickandmortyxml.core.provider.ActivityProvider
import org.koin.android.ext.koin.androidApplication
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val appModule = module {
    factory { androidContext().getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager }
    factory<NetworkStateProvider> { NetworkStateProviderImpl(get<ConnectivityManager>()) }
    factory<ErrorWrapper> { ErrorWrapperImpl() }
    factory<ErrorMapper> { ErrorMapperImpl(androidContext()) }
    single(createdAtStart = true) { ActivityProvider(androidApplication()) }
    factory<FragmentNavigator> {
        FragmentNavigatorImpl(
            activityProvider = get<ActivityProvider>(),
            navHostFragmentRes = R.id.nav_host_fragment_activity_main_bottom_nav,
            homeDestinationRes = R.id.navigation_character_screen
        )
    }

}