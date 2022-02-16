package com.ibrahimethem.firebasenotificationyazi.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.ibrahimethem.firebasenotificationyazi.adapter.DetailAdapter
import com.ibrahimethem.firebasenotificationyazi.databinding.FragmentHomeBinding
import com.ibrahimethem.firebasenotificationyazi.viewmodel.HomeViewModel

class HomeFragment : Fragment() {
    private var _binding : FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private var detailAdapter = DetailAdapter(arrayListOf())

    //viewModel
    private val viewModel by lazy { HomeViewModel() }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater,container,false)
        return  binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.firebaseGetData()
        observeLiveData()

        //adapter tanımlaması
        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())
        binding.recyclerView.adapter       = detailAdapter

        binding.notificationBtn.setOnClickListener {
            val action = HomeFragmentDirections.actionHomeFragmentToNotificationFragment()
            Navigation.findNavController(it).navigate(action)
        }
    }


    private fun observeLiveData(){
        viewModel.list.observe(viewLifecycleOwner){ list->
            list?.let {
                detailAdapter.adapterUpdate(it)
            }
        }
    }


    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}