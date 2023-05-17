package com.example.rickandmortyxml.core.network

interface NetworkStateProvider {
    fun isNetworkAvailable(): Boolean
}