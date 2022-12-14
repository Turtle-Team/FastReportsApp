package com.iubip.fastreportsapp.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.iubip.fastreportsapp.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val bottomNavigationMenu = findViewById<BottomNavigationView>(R.id.nav_bar)
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        val navController = navHostFragment.navController

//        supportActionBar?.setDisplayHomeAsUpEnabled(true)
//        supportActionBar?.setDisplayShowHomeEnabled(true)

        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.templateFragment,
                R.id.reportsFragment,
                R.id.exportsFragment,
                R.id.additionallyFragment
            )
        )

        setupActionBarWithNavController(navController, appBarConfiguration)
        bottomNavigationMenu.setupWithNavController(navController)
    }

//    override fun onOptionsItemSelected(item: MenuItem): Boolean {
//        when(item.itemId){
//            android.R.id.home -> {
//                this.bac
//            }
//        }
//        return true
//    }


}