package com.example.mynewcv

import android.content.ContentValues.TAG
import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.mynewcv.databinding.ActivityRegistrationBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class Registration : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth
    private lateinit var binding: ActivityRegistrationBinding
    override fun onCreate(savedInstanceState: Bundle?) {


        auth = Firebase.auth

        super.onCreate(savedInstanceState)
        binding = ActivityRegistrationBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        val currentUser = auth.currentUser
        if (currentUser !=null){
            val intent = Intent (this,MainCreateActiity::class.java)
            startActivity(intent)
            finish()
        }


        binding.RegistrationButton.setOnClickListener(View.OnClickListener {
            Showloader()
            val email = binding.email.text.toString()
            val password = binding.password.text.toString()

            if (TextUtils.isEmpty(email)) {
                Toast.makeText(this, "Enter email", Toast.LENGTH_SHORT).show()
            }
            if (TextUtils.isEmpty(password)) {
                Toast.makeText(this, "Enter email", Toast.LENGTH_SHORT).show()
            }

            auth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {
                        Hideloader()
                        Toast.makeText(
                            this,
                            "Register is succes",
                            Toast.LENGTH_SHORT
                        ).show()
                        val intent = Intent(this,Login::class.java)
                        startActivity(intent)
                        finish()
                    } else {
                        Hideloader()
                        // If sign in fails, display a message to the user.
                        Log.w("AAA", "createUserWithEmail:failure", task.exception)
                        Toast.makeText(
                            this,
                            "Authentication failed.",
                            Toast.LENGTH_SHORT
                        ).show()

                    }
                }

        })
        binding.GoToLog.setOnClickListener(View.OnClickListener {
             val intent = Intent(this,Login::class.java)
             startActivity(intent)
            finish()
        })

    }
    private fun Showloader (){
        binding.loader.visibility = View.VISIBLE
    }
    private fun Hideloader (){
        binding.loader.visibility = View.GONE
    }
}