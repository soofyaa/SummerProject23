package com.itis.summerproject23

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val controller =
            (supportFragmentManager.findFragmentById(R.id.container) as NavHostFragment)
                .navController
        findViewById<BottomNavigationView>(R.id.bnv_main).apply {
            setupWithNavController(controller)
        }
        val bottomNav: BottomNavigationView = findViewById(R.id.bnv_main)

        controller.addOnDestinationChangedListener { _, destination, _ ->
            if (destination.id == R.id.beginFragment || destination.id == R.id.registrationFragment
                || destination.id == R.id.chooseFragment || destination.id == R.id.entryFragment
                || destination.id == R.id.recipePageFragment
            ) {
                bottomNav.visibility = View.GONE
            } else {
                bottomNav.visibility = View.VISIBLE
            }
        }
    }
}