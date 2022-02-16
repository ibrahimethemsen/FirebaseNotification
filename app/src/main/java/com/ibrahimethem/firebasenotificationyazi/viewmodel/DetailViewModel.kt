package com.ibrahimethem.firebasenotificationyazi.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.firebase.firestore.FirebaseFirestore
import com.ibrahimethem.firebasenotificationyazi.model.PostModel

class DetailViewModel {
    private lateinit var fbDatabase : FirebaseFirestore

    private var _detail = MutableLiveData<PostModel>()
    val detail : LiveData<PostModel>
        get() = _detail

    private var _notification = MutableLiveData<PostModel>()
    val notification : LiveData<PostModel>
        get() = _notification
    fun firebaseDetailGetData(uuid : String){
        fbDatabase = FirebaseFirestore.getInstance()
        try {
            fbDatabase.collection("post").document(uuid).addSnapshotListener { value, error ->
                if (error != null){
                    println("Detail hata ${error.localizedMessage}")
                }else{
                    if (value != null){
                        val title   = value.get("title") as String?
                        val message = value.get("message") as String?
                        val uuid    = value.id

                        val pDetail = PostModel(title,message,uuid)

                        _detail.value = pDetail
                        _notification.value = pDetail
                    }
                }
            }
        }catch (e : Exception){
            println("Exception ${e.localizedMessage}")
        }
    }
}