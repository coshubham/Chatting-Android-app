package com.example.chatapp

import android.app.ProgressDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.chatapp.adapter.MessageAdapter
import com.example.chatapp.model.Message
import com.example.chatapp.databinding.ActivityChatBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.storage.FirebaseStorage
import java.util.*
import kotlin.collections.ArrayList
import kotlin.collections.HashMap

class ChatActivity : AppCompatActivity() {

    private lateinit var binding: ActivityChatBinding
    private lateinit var list: ArrayList<Message>//model

    private lateinit var senderRoom: String
    private lateinit var receiverRoom: String

    private lateinit var database: FirebaseDatabase
    private lateinit var storage: FirebaseStorage

    private lateinit var dialog: ProgressDialog

    private lateinit var senderUid: String
    private lateinit var receiverUid: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityChatBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)
        database = FirebaseDatabase.getInstance()
        storage = FirebaseStorage.getInstance()
        dialog = ProgressDialog(this@ChatActivity)
//        dialog.setMessage("Uploading image....")
        dialog.setCancelable(false)
        list = ArrayList()
        val name = intent.getStringExtra("name")
        val profile = intent.getStringExtra("image")
        binding.name.text = name
        Glide.with(this@ChatActivity).load(profile)
            .placeholder(R.drawable.image)
            .into(binding.profileImage01)

        binding.backbuttonimage.setOnClickListener { finish() }

        senderUid = FirebaseAuth.getInstance().uid.toString()

        receiverUid = intent.getStringExtra("uid")!!


        database.reference.child("Presence").child(receiverUid)
            .addValueEventListener(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    if (snapshot.exists()) {
                        val status = snapshot.getValue(String::class.java)
                        if (status == "offline") {
                            binding.status.visibility = View.GONE
                        } else {
                            binding.status.setText(status)
                            binding.status.visibility = View.VISIBLE
                        }
                    }
                }

                override fun onCancelled(error: DatabaseError) {}

            })

        senderRoom = senderUid + receiverUid
        receiverRoom = receiverUid + senderUid

        database = FirebaseDatabase.getInstance()



        binding.send.setOnClickListener {

            if(binding.messageBox.text.isEmpty()){
                Toast.makeText(this,"Please enter your message",Toast.LENGTH_SHORT).show()
            }else{
                val message = Message(binding.messageBox.text.toString(), senderUid, Date().time)

                val randomKey = database.reference.push().key

                database.reference.child("chats")
                    .child(senderRoom).child("message").child(randomKey!!).setValue(message)
                    .addOnSuccessListener {

                        database.reference.child("chats").child(receiverRoom).child("message")
                            .child(randomKey!!).setValue(message).addOnSuccessListener {
                                binding.messageBox.text = null

                            }
                    }

            }


        }

        database.reference.child("chats").child(senderRoom).child("message")
            .addValueEventListener(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    list.clear()

                    for (snapshot1 in snapshot.children) {
                        val data = snapshot1.getValue(Message::class.java)
                        list.add(data!!)
                    }

                    binding.recyclerView.adapter =
                        MessageAdapter(this@ChatActivity, list, senderRoom, receiverRoom)
                }

                override fun onCancelled(error: DatabaseError) {
                    Toast.makeText(this@ChatActivity, "Error: $error", Toast.LENGTH_SHORT).show()
                }

            })


//        binding.attach.setOnClickListener {
//            val intent = Intent()
//            intent.action = Intent.ACTION_GET_CONTENT
//            intent.type= "image/*"
//            startActivityForResult(intent,25)
//        }

        val handler = Handler()
        binding.messageBox.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, p1: Int, p2: Int, p3: Int) {}

            override fun onTextChanged(s: CharSequence?, p1: Int, p2: Int, p3: Int) {}

            override fun afterTextChanged(s: Editable?) {
                database.reference.child("Presence")
                    .child(senderUid)
                    .setValue("typing....")
                handler.removeCallbacksAndMessages(null)
                handler.postDelayed(userStoppedTyping, 1000)
            }

            var userStoppedTyping = Runnable {
                database.reference.child("Presence")
                    .child(senderUid)
                    .setValue("Online")
            }

        })

        supportActionBar?.setDisplayShowTitleEnabled(false)
    }

//    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
//        super.onActivityResult(requestCode, resultCode, data)
//        if (requestCode == 25) {
//            if (data != null) {
//                if (data.data != null) {
//                    val selectedImage = data.data
//                    val calendar = Calendar.getInstance()
//                    var refence = storage.reference.child("chats")
//                        .child(calendar.timeInMillis.toString() + "")
//                    dialog.show()
//                    refence.putFile(selectedImage!!)
//                        .addOnCompleteListener { task ->
//                            dialog.dismiss()
//                            if (task.isSuccessful) {
//                                refence.downloadUrl.addOnSuccessListener { uri ->
//                                    val filePath = uri.toString()
//                                    val messageTxt: String = binding.messageBox.text.toString()
//                                    val date = Date()
//                                    val message = Message(messageTxt, senderUid, date.time)
//                                    message.message = "photo"
//                                    message.imageUrl = filePath
//                                    binding.messageBox.setText("")
//                                    val randomKey = database.reference.push().key
//                                    val lastMsgObj = HashMap<String, Any>()
//                                    lastMsgObj["lastMsg"] = message.message!!
//                                    lastMsgObj["lastMsgTime"] = date.time
//                                    database.reference.child("chats")
//                                        .updateChildren(lastMsgObj)
//                                    database.reference.child("chats")
//                                        .child(receiverRoom)
//                                        .updateChildren(lastMsgObj)
//                                    database.reference.child("chats")
//                                        .child(senderRoom)
//                                        .child("messages")
//                                        .child(randomKey!!)
//                                        .setValue(message).addOnSuccessListener {
//                                            database.reference.child("chats")
//                                                .child(receiverRoom)
//                                                .child("messages")
//                                                .child(randomKey)
//                                                .setValue(message)
//                                                .addOnSuccessListener {
//
//                                                }
//                                        }
//                                }
//
//                            }
//                        }
//
//                }
//            }
//        }
//    }


    override fun onResume() {
        super.onResume()
        val currentId = FirebaseAuth.getInstance().uid
        database.reference.child("Presence")
            .child(currentId!!)
            .setValue("Online")
    }

    override fun onPause() {
        super.onPause()
        val currentId = FirebaseAuth.getInstance().uid
        database.reference.child("Presence")
            .child(currentId!!)
            .setValue("offline")

    }
}
