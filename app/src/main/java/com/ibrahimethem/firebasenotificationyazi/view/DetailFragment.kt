package com.ibrahimethem.firebasenotificationyazi.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import com.ibrahimethem.firebasenotificationyazi.databinding.FragmentDetailBinding
import com.ibrahimethem.firebasenotificationyazi.util.Constants.Companion.arg
import com.ibrahimethem.firebasenotificationyazi.viewmodel.DetailViewModel

class DetailFragment : Fragment() {
    private var _binding : FragmentDetailBinding? = null
    private val binding get() = _binding!!

    //viewmodel
    private val viewModel by lazy { DetailViewModel() }

    //uuid
    lateinit var uuid : String
    lateinit var notification : String

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDetailBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        arguments?.let {
            uuid = DetailFragmentArgs.fromBundle(it).uuid
        }

        notification = arg.getString("uuid").toString()

        observeLiveData()
        if (uuid == "detail"){
            viewModel.firebaseDetailGetData(notification)
        }else{
            viewModel.firebaseDetailGetData(uuid)
        }

        binding.detailNotification.setOnClickListener {
            val action = DetailFragmentDirections.actionDetailFragmentToNotificationFragment(uuid)
            Navigation.findNavController(it).navigate(action)
        }

    }

    private fun observeLiveData(){
        viewModel.detail.observe(viewLifecycleOwner){detail ->
            detail?.let {
                binding.titleTV.text   = it.pTitle
                binding.messageTV.text = it.pMessage
            }
        }

        viewModel.notification.observe(viewLifecycleOwner){notification ->
            notification?.let {
                binding.titleTV.text   = it.pTitle
                binding.messageTV.text = it.pMessage
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}