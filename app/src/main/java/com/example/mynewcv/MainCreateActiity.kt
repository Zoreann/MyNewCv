package com.example.mynewcv

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.mynewcv.databinding.ActivityMainCreateActiityBinding
import com.google.android.material.navigation.NavigationView
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase


class MainCreateActiity : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityMainCreateActiityBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        auth = Firebase.auth
        val user = Firebase.auth.currentUser

        binding = ActivityMainCreateActiityBinding.inflate(layoutInflater)
       val view = binding.root
        setContentView(view)

        setSupportActionBar(binding.appBarMainCreateActiity.toolbar)

        binding.appBarMainCreateActiity.fab.setOnClickListener { view ->
            val intent = Intent (this,MainCreateActiity::class.java)
            startActivity(intent)
            finish()
        }
        val drawerLayout: DrawerLayout = binding.drawerLayout
        val navView: NavigationView = binding.navView
        val headerView: View = navView.getHeaderView(0)
        val userEmailTextView: TextView = headerView.findViewById(R.id.userEmail)


        val navController = findNavController(R.id.nav_host_fragment_content_main_create_actiity)
        appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.nav_home, R.id.nav_gallery
            ), drawerLayout
        )

        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)
        if (user == null){
            val intent  = Intent(this,Login::class.java)
            startActivity(intent)
            finish()
        }
        else  {
           userEmailTextView.text = user.email
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.main_create_actiity, menu)
        return true
    }


    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment_content_main_create_actiity)
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }
}