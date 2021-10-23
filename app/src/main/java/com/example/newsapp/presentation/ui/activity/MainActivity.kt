package com.example.newsapp.presentation.ui.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI.setupWithNavController
import androidx.navigation.ui.setupWithNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.newsapp.R
import com.example.newsapp.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity(R.layout.activity_main) {

    private val binding by viewBinding(ActivityMainBinding::bind)
    private lateinit var bottomNavigationItemReselectListener: OnBottomNavigationItemReselect

    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.Theme_NewsApp)
        super.onCreate(savedInstanceState)
        setupNavigation()
    }

    private fun setupNavigation() {
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        val navController = navHostFragment.navController

        with(binding.bottomNavigation) {
            setupWithNavController(navController)
            setOnItemReselectedListener {
                when (it.itemId) {
                    R.id.everythingFragment,
                    R.id.topheadlinsFragment,
                    R.id.sourcesFragment -> {
                        bottomNavigationItemReselectListener.onItemReselect()
                    }
                }
            }
        }

        setupWithNavController(binding.bottomNavigation, navController)
    }

    fun interface OnBottomNavigationItemReselect {
        fun onItemReselect()
    }

    fun setOnBottomNavigationItemReselectListener(bottomNavigationItemReselectListener: OnBottomNavigationItemReselect) {
        this.bottomNavigationItemReselectListener = bottomNavigationItemReselectListener
    }
}