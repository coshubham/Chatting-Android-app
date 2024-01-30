package com.example.chatapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import com.example.chatapp.databinding.ActivitySignInBinding
import com.google.firebase.auth.FirebaseAuth

class Login : AppCompatActivity() {

    private lateinit var binding:ActivitySignInBinding
    private lateinit var firebaseAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignInBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //hide MainActivity Toolbar
        supportActionBar?.hide()

        val buttonback = findViewById<ImageButton>(R.id.backbuttonimage)

        buttonback.setOnClickListener({
            val nextPage = Intent(this, Welcome::class.java)
            startActivity(nextPage)
            finish()
        })
        val textView = findViewById<TextView>(R.id.view_register)

        textView.setOnClickListener({
            val nextPage = Intent(this, SignupActivity::class.java)
            startActivity(nextPage)
            finish()
        })

        firebaseAuth = FirebaseAuth.getInstance()

        binding.button.setOnClickListener {
            val email = binding.editTextTextEmailAddress.text.toString()
            val pass = binding.editTextTextPassword.text.toString()


            if (email.isNotEmpty() && pass.isNotEmpty()) {

                firebaseAuth.signInWithEmailAndPassword(email, pass).addOnCompleteListener {
                    if (it.isSuccessful) {
                        val intent = Intent(this, Users::class.java)
                        startActivity(intent)
                    } else {
                        Toast.makeText(this, it.exception.toString(), Toast.LENGTH_SHORT).show()
                    }
                }
            } else {
                Toast.makeText(this, "Empty Fields Are Not Allowed !!", Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onStart() {
        super.onStart()
        if (firebaseAuth.currentUser != null) {

            val intent = Intent(this, Users::class.java)
            startActivity(intent)

        }
    }
}
