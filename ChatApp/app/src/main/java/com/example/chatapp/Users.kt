package com.example.chatapp

import android.app.ProgressDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.SearchView
import android.widget.Toast
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import androidx.recyclerview.widget.GridLayoutManager
import androidx.viewpager.widget.ViewPager
import com.example.chatapp.adapter.UserAdapter
import com.example.chatapp.databinding.ActivityUsersBinding
import com.example.chatapp.model.UserModel
import com.google.android.material.tabs.TabLayout
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference

private lateinit var binding: ActivityUsersBinding
private  lateinit var auth: FirebaseAuth
private lateinit var database: FirebaseDatabase
private lateinit var storage: FirebaseStorage
private  lateinit var dialog: ProgressDialog
private lateinit var user: ArrayList<UserModel>
private lateinit var userAdapter: UserAdapter
private lateinit var userModel: UserModel


class Users : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityUsersBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //hide MainActivity Toolbar
        supportActionBar?.hide()
        dialog = ProgressDialog(this@Users)
        dialog.setMessage("Uploading Image....")
        dialog.setCancelable(false)

        auth = FirebaseAuth.getInstance()
        database = FirebaseDatabase.getInstance()
        user = ArrayList<UserModel>()
        userAdapter = UserAdapter(this@Users,user)
        val layoutManager = GridLayoutManager(this@Users, 2)
        binding.mRec.layoutManager = layoutManager
        database.reference.child("users")
            .child(FirebaseAuth.getInstance().uid!!)
            .addValueEventListener(object :ValueEventListener{
                override fun onDataChange(snapshot: DataSnapshot) {

                    userModel = snapshot.getValue(UserModel::class.java)!!
                }

                override fun onCancelled(error: DatabaseError) {}

            })
        binding.mRec.adapter = userAdapter
        database.reference.child("users").addValueEventListener(object :ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                user.clear()

                for (snapshot1 in snapshot.children){
                    val userModel:UserModel? = snapshot1.getValue(UserModel::class.java)
                    if(!userModel!!.uid.equals(FirebaseAuth.getInstance().uid)) user!!.add(userModel)
                }
                userAdapter.notifyDataSetChanged()
            }

            override fun onCancelled(error: DatabaseError) {}

        })

        binding.logout.setOnClickListener {
            auth.signOut()
            startActivity(
                Intent(this,Welcome::class.java)
            )
            finish()
            Toast.makeText(this, "Logout Done Successful!!", Toast.LENGTH_SHORT).show()
        }

        setSupportActionBar(binding.toolbarMain)
        val toolbar: Toolbar = findViewById(R.id.toolbar_main)
        setSupportActionBar(toolbar)
        supportActionBar!!.title = ""


        val viewPager: ViewPager = findViewById(R.id.view_pager)
        val viewPagerAdapter = ViewPagerAdapter(supportFragmentManager)



        viewPager.adapter = viewPagerAdapter



    }



    override fun onCreateOptionsMenu(menu: Menu?): Boolean {

        menuInflater.inflate(R.menu.menu,menu)

            val searchItem = menu?.findItem(R.id.action_search)

            val searchView = searchItem?.actionView as SearchView
             searchView.queryHint= " Search Users....."

            searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
                override fun onQueryTextSubmit(query: String?): Boolean {
                    return false
                }

                override fun onQueryTextChange(newText: String): Boolean {
                    searchList(newText)
                    return true
                }

            })


        return super.onCreateOptionsMenu(menu)
    }

    fun searchList(text: String){
        val searchList = ArrayList<UserModel>()
        for (userModel in user){
            if (userModel.name?.lowercase()?.contains(text.lowercase()) == true){
                searchList.add(userModel)
            }
        }
        userAdapter.searchDataList(searchList)
    }





    override fun onResume() {
        super.onResume()
        val currentId = FirebaseAuth.getInstance().uid
        database.reference.child("presence")
            .child(currentId!!).setValue("Online")
    }

//    override fun onPause() {
//        super.onPause()
//        val currentId = FirebaseAuth.getInstance().uid
//        database.reference.child("presence")
//            .child(currentId!!).setValue("offline")
//    }


    internal class ViewPagerAdapter(fragmentManager: FragmentManager) :
        FragmentPagerAdapter(fragmentManager) {
        private val fragments: ArrayList<Fragment>
        private val titles: ArrayList<String>

        init {
            fragments = ArrayList<Fragment>()
            titles = ArrayList<String>()
        }

        override fun getCount(): Int {
            return fragments.size
        }

        override fun getItem(position: Int): Fragment {
            return fragments[position]
        }

        fun addFragment(fragment: Fragment, title: String) {
            fragments.add(fragment)
            titles.add(title)
        }

        override fun getPageTitle(i: Int): CharSequence? {
            return titles[i]
        }
    }
}


