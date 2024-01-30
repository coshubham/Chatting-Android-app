package com.example.chatapp.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.chatapp.ChatActivity
import com.example.chatapp.R
import com.example.chatapp.Users
import com.example.chatapp.databinding.ItemProfileBinding
import com.example.chatapp.model.UserModel

class UserAdapter(var context:Context, var userList:ArrayList<UserModel>):
RecyclerView.Adapter<UserAdapter.UserViewHolder>()

{
    inner class UserViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val binding :ItemProfileBinding = ItemProfileBinding.bind(itemView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        var v = LayoutInflater.from(context).inflate(R.layout.item_profile,parent,false)
        return UserViewHolder(v)

    }

    override fun onBindViewHolder(holder: UserAdapter.UserViewHolder, position: Int) {
        val user = userList[position]
        holder.binding.nameBox.text = user.name
        holder.binding.profileBio.text = user.bio
        Glide.with(context).load(user.imageUrl)
            .placeholder(R.drawable.user)
            .into(holder.binding.profileImage)

        holder.itemView.setOnClickListener {
            val intent = Intent(context, ChatActivity::class.java)
            intent.putExtra("name",user.name)
            intent.putExtra("image",user.imageUrl)
            intent.putExtra("uid",user.uid)
            context.startActivity(intent)
        }


    }
    override fun getItemCount(): Int = userList.size



fun searchDataList(searchList: List<UserModel>){
    userList = searchList as ArrayList<UserModel>
    notifyDataSetChanged()


}
}




