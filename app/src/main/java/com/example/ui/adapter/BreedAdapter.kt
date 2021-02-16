package com.example.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.RecyclerView
import com.example.model.local.entities.Breed
import com.example.ui.databinding.BreedItemBinding

class BreedAdapter : RecyclerView.Adapter<BreedAdapter.BreedDogVH>() {

    private var listBreed = listOf<Breed>()
    private val selectedBreed = MutableLiveData<Breed>()

    fun update(list: List<Breed>) {
        listBreed = list
        notifyDataSetChanged()
    }

    fun selectedBreed(): LiveData<Breed> = selectedBreed

    inner class BreedDogVH(private val binding: BreedItemBinding) :
        RecyclerView.ViewHolder(binding.root), View.OnClickListener {

        fun bind(breed: Breed) {
            binding.tvbreed.text = breed.breed
            itemView.setOnClickListener(this)
        }

        override fun onClick(v: View?) {
            selectedBreed.value = listBreed[adapterPosition]
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BreedDogVH {
        return BreedDogVH(BreedItemBinding.inflate(LayoutInflater.from(parent.context)))
    }

    override fun onBindViewHolder(holder: BreedDogVH, position: Int) {
        holder.bind(listBreed[position])
    }

    override fun getItemCount() = listBreed.size

}