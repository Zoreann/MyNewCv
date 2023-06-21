package com.example.mynewcv

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.widget.Toast
import com.example.mynewcv.databinding.ActivityLoginBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class Login : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth
    private lateinit var binding: ActivityLoginBinding
    override fun onCreate(savedInstanceState: Bundle?) {

        auth = Firebase.auth

        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        val currentUser = auth.currentUser
        if (currentUser !=null){
            val intent = Intent (this,MainCreateActiity::class.java)
            startActivity(intent)
            finish()
        }

        binding.GoToReg.setOnClickListener(View.OnClickListener {
            val intent = Intent (this,Registration::class.java)
            startActivity(intent)
            finish()
        })

        binding.LoginButton.setOnClickListener(View.OnClickListener {
            Showloader()
            val email = binding.email.text.toString()
            val password = binding.password.text.toString()

            if (TextUtils.isEmpty(email)) {
                Toast.makeText(this, "Enter email", Toast.LENGTH_SHORT).show()
            }
            if (TextUtils.isEmpty(password)) {
                Toast.makeText(this, "Enter email", Toast.LENGTH_SHORT).show()
            }
            auth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {
                        Hideloader()
                        Toast.makeText(
                            this,
                            "Authentication success.",
                            Toast.LENGTH_SHORT
                        ).show()
                        val intent = Intent (this,MainCreateActiity::class.java)
                        startActivity(intent)
                        finish()
                    } else {
                        Hideloader()

                        Toast.makeText(
                            this,
                            "Authentication failed.",
                            Toast.LENGTH_SHORT,
                        ).show()

                    }
                }


        })

    }
    private fun Showloader (){
        binding.loader.visibility = View.VISIBLE
    }
    private fun Hideloader (){
        binding.loader.visibility = View.GONE
    }
}