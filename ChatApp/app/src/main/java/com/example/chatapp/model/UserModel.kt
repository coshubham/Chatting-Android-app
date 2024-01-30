package com.example.chatapp.model

    class UserModel{
    var uid:String? = null
    var name: String? = null
    var bio: String? = null
    var imageUrl: String? = null

    constructor(){}
    constructor(

        uid:String?,
        name: String?,
        bio: String?,
        imageUrl: String?

    ){
        this.uid = uid
        this.name = name
        this.bio = bio
        this. imageUrl = imageUrl
    }
    }
