package com.example.chatapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.google.firebase.auth.FirebaseAuth

private lateinit var firebaseAuth: FirebaseAuth


class Welcome : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        firebaseAuth = FirebaseAuth.getInstance()


        //hide MainActivity Toolbar
        supportActionBar?.hide()

        val btnRegister = findViewById<Button>(R.id.btnRegister)

        btnRegister.setOnClickListener({
            val nextPage = Intent(this, SignupActivity::class.java)
            startActivity(nextPage)
            finish()
        })

        val btnLogin = findViewById<Button>(R.id.btnLogin)

        btnLogin.setOnClickListener({
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
        }

    }
}