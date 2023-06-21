package com.example.mynewcv

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.mynewcv.databinding.ActivityMainBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class MainActivity : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        auth = Firebase.auth
        val user = Firebase.auth.currentUser
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)


        binding.logout.setOnClickListener(View.OnClickListener {
                FirebaseAuth.getInstance().signOut()
            val intent  = Intent(this,Login::class.java)
            startActivity(intent)
            finish()
        })



    }


    }
