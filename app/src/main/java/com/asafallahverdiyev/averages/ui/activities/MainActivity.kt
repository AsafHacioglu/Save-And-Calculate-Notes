package com.asafallahverdiyev.averages.ui.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.NavHost
import androidx.navigation.findNavController
import androidx.navigation.ui.setupActionBarWithNavController
import com.asafallahverdiyev.averages.R


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val navHostFragment = supportFragmentManager.findFragmentById(R.id.fragmentsContainer) as NavHost
        val navController = navHostFragment.navController
        setupActionBarWithNavController(navController)

    }

    override fun onSupportNavigateUp(): Boolean {

        val navHost = findNavController(R.id.fragmentsContainer)
        return navHost.navigateUp() || super.onSupportNavigateUp()

    }
}