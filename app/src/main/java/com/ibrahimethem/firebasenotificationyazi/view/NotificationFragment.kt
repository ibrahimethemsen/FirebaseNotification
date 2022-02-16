package com.ibrahimethem.firebasenotificationyazi.view

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.ibrahimethem.firebasenotificationyazi.databinding.FragmentNotificationBinding
import com.ibrahimethem.firebasenotificationyazi.model.NotificationData
import com.ibrahimethem.firebasenotificationyazi.model.PushNotification
import com.ibrahimethem.firebasenotificationyazi.service.RetrofitObject
import com.ibrahimethem.firebasenotificationyazi.util.Constants.Companion.TAG
import com.ibrahimethem.firebasenotificationyazi.util.Constants.Companion.TOPIC
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class NotificationFragment : Fragment() {
    private var _binding : FragmentNotificationBinding? = null
    private val binding get() = _binding!!

    lateinit var uuid : String

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentNotificationBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        arguments?.let {
            uuid = NotificationFragmentArgs.fromBundle(it).uuid
        }
        println("uuid : $uuid")
        binding.notificationBtn.setOnClickListener {
            pushNotification()
        }
    }

    private fun pushNotification(){
        val title   = binding.titleET.text.toString()
        val message = binding.messageET.text.toString()
        if (title != "" && message != ""){
            val data = NotificationData(title,message,uuid)
            Log.d(TAG, "pushNotification:$uuid")
            val notification = PushNotification(data,TOPIC)
            notification(notification)
        }else{
            Toast.makeText(requireContext(), "Boş Bırakmayınız", Toast.LENGTH_SHORT).show()
        }
    }

    private fun notification(notification : PushNotification) = CoroutineScope(Dispatchers.IO).launch {
        try {
            val response = RetrofitObject.api.postNotification(notification)
            if (response.isSuccessful){
                println("Cevap Başarılı : $response")
            }else{
                println("Cevap Başarısız : ${response.errorBody()}")
            }
        }catch (e : Exception){
            e.printStackTrace()
        }
    }

}