package com.ibrahimethem.firebasenotificationyazi.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.ibrahimethem.firebasenotificationyazi.databinding.RecyclerRowBinding
import com.ibrahimethem.firebasenotificationyazi.model.PostModel
import com.ibrahimethem.firebasenotificationyazi.view.HomeFragmentDirections

class DetailAdapter(private val list : ArrayList<PostModel>) : RecyclerView.Adapter<DetailAdapter.DetailViewHolder>() {
    class DetailViewHolder(val binding : RecyclerRowBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DetailViewHolder {
        return DetailViewHolder(RecyclerRowBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun onBindViewHolder(holder: DetailViewHolder, position: Int) {
        holder.binding.title.text   = list[position].pTitle
        holder.binding.message.text = list[position].pMessage

        holder.binding.linearLayout.setOnClickListener {
            val action = HomeFragmentDirections.actionHomeFragmentToDetailFragment(list[position].uuid)
            Navigation.findNavController(it).navigate(action)
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }

    fun adapterUpdate(newList : List<PostModel>){
        list.clear()
        list.addAll(newList)
        notifyDataSetChanged()
    }
}