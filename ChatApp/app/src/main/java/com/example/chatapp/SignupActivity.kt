package com.example.chatapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import com.example.chatapp.databinding.ActivitySignupBinding
import com.google.firebase.auth.FirebaseAuth

class SignupActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySignupBinding
    private lateinit var firebaseAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignupBinding.inflate(layoutInflater)

        setContentView(binding.root)

        firebaseAuth = FirebaseAuth.getInstance()

        binding.button.setOnClickListener {
            val email = binding.editTextTextEmailAddress.text.toString()
            val pass = binding.editTextTextpassword.text.toString()
            val confirmPass = binding.editTextTextcpassword.text.toString()

            if (email.isNotEmpty() && pass.isNotEmpty() && confirmPass.isNotEmpty()) {
                if (pass == confirmPass) {

                    firebaseAuth.createUserWithEmailAndPassword(email, pass).addOnCompleteListener {
                        if (it.isSuccessful) {
                            val intent = Intent(this, Profile::class.java)
                            startActivity(intent)

                        } else {
                            Toast.makeText(this, it.exception.toString(), Toast.LENGTH_SHORT).show()

                        }
                    }
                } else {
                    Toast.makeText(this, "PassWord is not same", Toast.LENGTH_SHORT).show()
                }
            } else {
                Toast.makeText(this, "Empty Fields Are Not Allowed !!", Toast.LENGTH_SHORT).show()

            }


        }

        //hide MainActivity Toolbar
        supportActionBar?.hide()


        val btnback = findViewById<ImageButton>(R.id.backbuttonimage)

        btnback.setOnClickListener({
            val nextPage = Intent(this, Welcome::class.java)
            startActivity(nextPage)
            finish()
        })

        val textView = findViewById<TextView>(R.id.view_login)

        textView.setOnClickListener({
            val nextPage = Intent(this, Login::class.java)
            startActivity(nextPage)
            finish()
        })
    }
        override fun onStart() {
            super.onStart()
            if (firebaseAuth.currentUser != null) {

                val intent = Intent(this, Users::class.java)
                startActivity(intent)
                finish()
            }
        }

    }
