package com.itis.summerproject23

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import androidx.room.Room
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.itis.summerproject23.database.UsersDatabase

class MainActivity : AppCompatActivity() {
    companion object {
        lateinit var database: UsersDatabase
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
//        database = Room.databaseBuilder(
//            applicationContext,
//            UsersDatabase::class.java,
//            "my-database"
//        ).build()

        val controller =
            (supportFragmentManager.findFragmentById(R.id.container) as NavHostFragment)
                .navController
        findViewById<BottomNavigationView>(R.id.bnv_main).apply {
            setupWithNavController(controller)
        }
        val bottomNav: BottomNavigationView = findViewById(R.id.bnv_main)

        controller.addOnDestinationChangedListener { _, destination, _ ->
            if (destination.id == R.id.profileFragment || destination.id == R.id.registrationFragment) {
                bottomNav.visibility = View.GONE
            } else {
                bottomNav.visibility = View.VISIBLE
            }
        }
    }
}