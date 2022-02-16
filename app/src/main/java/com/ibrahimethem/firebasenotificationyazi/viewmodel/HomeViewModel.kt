package com.ibrahimethem.firebasenotificationyazi.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.firebase.firestore.FirebaseFirestore
import com.ibrahimethem.firebasenotificationyazi.model.PostModel

class HomeViewModel {
    private lateinit var fbDatabase : FirebaseFirestore
    private var fbList = ArrayList<PostModel>()

    private var _list = MutableLiveData<List<PostModel>>()
    val list : LiveData<List<PostModel>>
        get() = _list


    fun firebaseGetData(){
        fbDatabase = FirebaseFirestore.getInstance()
        fbDatabase.collection("post").addSnapshotListener { value, error ->
            try {
                if (error != null){
                   println("Error ${error.localizedMessage}")
                }else{
                    if (value != null && !value.isEmpty){
                        val documents = value.documents
                        fbList.clear()
                        for (document in documents){
                            val title   = document.get("title") as String?
                            val message = document.get("message") as String?
                            val uuid    = document.id

                            val post = PostModel(title,message,uuid)
                            fbList.add(post)
                            println("post : $post")
                        }
                        _list.value = fbList
                    }
                }
            }catch (e : Exception){
                println("Exception ${e.localizedMessage}")
            }
        }
    }
}