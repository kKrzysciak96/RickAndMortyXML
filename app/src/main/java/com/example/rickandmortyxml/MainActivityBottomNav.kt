package com.example.rickandmortyxml

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.rickandmortyxml.databinding.ActivityMainBottomNavBinding
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivityBottomNav : AppCompatActivity() {

    private lateinit var binding: ActivityMainBottomNavBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBottomNavBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navView: BottomNavigationView = binding.navBottomMenu

        val navController = findNavController(R.id.nav_host_fragment_activity_main_bottom_nav)

        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.navigation_character_screen,
                R.id.navigation_episode_screen,
                R.id.navigation_location_screen
            )
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)
    }
}