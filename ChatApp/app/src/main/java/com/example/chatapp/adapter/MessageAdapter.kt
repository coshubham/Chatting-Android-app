package com.example.chatapp.adapter

import android.app.AlertDialog
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.chatapp.R
import com.example.chatapp.databinding.DeleteBinding
import com.example.chatapp.databinding.ReceiveMsgBinding
import com.example.chatapp.databinding.SendMsgBinding
import com.example.chatapp.model.Message
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase

class MessageAdapter(
    var context:Context,
    var message: ArrayList<Message>,
    var  senderRoom: String,
    var receiverRoom: String
):RecyclerView.Adapter<RecyclerView.ViewHolder?>() {
    var ITEM_SENT = 1
    var ITEM_RECEIVE = 2

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return if (viewType == ITEM_SENT)
           SentViewHolder(
               LayoutInflater.from(context).inflate(R.layout.send_msg, parent,false)
           )
            else   ReceiveViewHolder(
            LayoutInflater.from(context).inflate(R.layout.receive_msg, parent,false)
        )
    }

    override fun getItemViewType(position: Int): Int {
        return if (FirebaseAuth.getInstance().uid == message[position].senderId) ITEM_SENT else ITEM_RECEIVE
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val message = message[position]

        if (holder.itemViewType == ITEM_SENT) {
            val viewHolder = holder as SentViewHolder
//            if (message.message.equals("photo")) {
//                viewHolder.binding.image.visibility = View.VISIBLE
//                viewHolder.binding.message.visibility = View.GONE
//                viewHolder.binding.mLinear.visibility = View.GONE
//                Glide.with(context)
//                    .load(message.imageUrl)
//                    .placeholder(R.drawable.image)
//                    .into(viewHolder.binding.image)
//            }
            viewHolder.binding.message.text = message.message
            viewHolder.itemView.setOnLongClickListener {

                val view = LayoutInflater.from(context).inflate(R.layout.delete, null)
                val binding: DeleteBinding = DeleteBinding.bind(view)
                val dialog = AlertDialog.Builder(context).setTitle("Delete Massage")
                    .setView(binding.root)
                    .create()
//                binding.everyone.setOnClickListener {
//                    message.message = "This message is removed"
//                    message.messageId?.let {
//                        FirebaseDatabase.getInstance().reference.child("chats")
//                            .child(senderRoom)
//                            .child("message")
//                            .setValue(message)
//
//                    }
//                    message.messageId.let {
//                        FirebaseDatabase.getInstance().reference.child("chats")
//                            .child(receiverRoom)
//                            .child("message")
//                            .removeValue()
//                    }
//                    dialog.dismiss()
//                }
                binding.delete.setOnClickListener {
                    message.messageId.let {
                        FirebaseDatabase.getInstance().reference.child("chats")
                            .child(senderRoom)
                            .child("message")
                            .setValue(null)
                    }
                    dialog.dismiss()
                }
                binding.cancel.setOnClickListener { dialog.dismiss() }
                dialog.show()
                false
            }
        } else {
            val viewHolder = holder as ReceiveViewHolder
//            if (message.message.equals("photo")) {
//                viewHolder.binding.image.visibility = View.VISIBLE
//                viewHolder.binding.message.visibility = View.GONE
//                viewHolder.binding.mLinear.visibility = View.GONE
//                Glide.with(context)
//                    .load(message.imageUrl)
//                    .placeholder(R.drawable.image)
//                    .into(viewHolder.binding.image)
//            }
            viewHolder.binding.message.text = message.message
            viewHolder.itemView.setOnLongClickListener {

                val view = LayoutInflater.from(context).inflate(R.layout.delete, null)
                val binding: DeleteBinding = DeleteBinding.bind(view)
                val dialog = AlertDialog.Builder(context).setTitle("Delete Massage")
                    .setView(binding.root)
                    .create()
//                binding.everyone.setOnClickListener {
//                    message.message = "This message is removed"
//                    message.messageId?.let {
//                        FirebaseDatabase.getInstance().reference.child("chats")
//                            .child(senderRoom)
//                            .child("message")
//                            .setValue(message)
//
//                    }
//                    message.messageId.let {
//                        FirebaseDatabase.getInstance().reference.child("chats")
//                            .child(receiverRoom)
//                            .child("message")
//                            .setValue(message)
//                    }
//                    dialog.dismiss()
//                }
                binding.delete.setOnClickListener {
                    message.messageId.let {
                        FirebaseDatabase.getInstance().reference.child("chats")
                            .child(senderRoom)
                            .child("message")
                            .setValue(null)
                    }
                    dialog.dismiss()
                }
                binding.cancel.setOnClickListener { dialog.dismiss() }
                dialog.show()
                false
            }
        }
    }


    override fun getItemCount(): Int {
        return message.size
    }

    inner class SentViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var binding = SendMsgBinding.bind(view)
    }

    inner class ReceiveViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var binding = ReceiveMsgBinding.bind(view)

    }
}

