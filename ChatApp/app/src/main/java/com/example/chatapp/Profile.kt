package com.example.chatapp


import android.content.Intent
import android.net.Uri
import android.os.Binder
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageButton
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.example.chatapp.databinding.ActivityProfileBinding
import com.example.chatapp.model.UserModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.storage.FirebaseStorage
import java.util.Date

private lateinit var binding: ActivityProfileBinding
private  lateinit var auth: FirebaseAuth
private lateinit var database: FirebaseDatabase
private lateinit var storage: FirebaseStorage
private lateinit var selectedImg: Uri
private lateinit var dialog: AlertDialog.Builder

class Profile : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //hide MainActivity Toolbar
        supportActionBar?.hide()

        dialog = AlertDialog.Builder(this)
            .setMessage("Updating Profile......")
            .setCancelable(false)
        database = FirebaseDatabase.getInstance()
        storage = FirebaseStorage.getInstance()
        auth = FirebaseAuth.getInstance()

        binding.profileImage.setOnClickListener {
            val intent = Intent()
            intent.action = Intent.ACTION_GET_CONTENT
            intent.type = "image/*"
            startActivityForResult(intent,1)
        }
        binding.setupBtn.setOnClickListener {
            if (binding.nameBox.text.isEmpty()){
                Toast.makeText(this,"Please Type Your Name", Toast.LENGTH_SHORT).show()
            }else if (selectedImg == null){
                Toast.makeText(this,"Please Select Your Image", Toast.LENGTH_SHORT).show()
            }else uploadData()

        }

        }

    private fun uploadData() {
        val reference = storage.reference.child("Profile").child(Date().time.toString())
        reference.putFile(selectedImg).addOnCompleteListener{
            if(it.isSuccessful){
                reference.downloadUrl.addOnSuccessListener { task ->
                    uploadInfo(task.toString())
                    dialog.show()
                }
            }
        }
    }

    private fun uploadInfo(imgUrl: String) {
        val user = UserModel(auth.uid.toString(), binding.nameBox.text.toString(), binding.profileBio.text.toString(),imgUrl)
        database.reference.child("users")
            .child(auth.uid.toString())
            .setValue(user)
            .addOnSuccessListener {
                Toast.makeText(this,"Data Inserted",Toast.LENGTH_SHORT).show()
                startActivity(Intent(this, Users::class.java))
                finish()

            }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (data != null){
            if (data.data != null){
                selectedImg = data.data!!

                binding.profileImage.setImageURI(selectedImg)

            }
        }
    }
    }
