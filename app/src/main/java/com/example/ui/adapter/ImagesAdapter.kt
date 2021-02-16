package com.example.ui.adapter

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.model.local.entities.DoggiesImage
import com.example.ui.databinding.ImageItemBinding

class ImagesAdapter : RecyclerView.Adapter<ImagesAdapter.ImageDogVH>() {

    var listImages = listOf<DoggiesImage>()
    val selectedImage = MutableLiveData<DoggiesImage>()

    fun update(list: List<DoggiesImage>) {
        listImages = list
        notifyDataSetChanged()
    }

    fun selectedImage(): LiveData<DoggiesImage> = selectedImage

    inner class ImageDogVH(private val binding: ImageItemBinding) :
        RecyclerView.ViewHolder(binding.root), View.OnLongClickListener {

        fun bind(image: DoggiesImage) {
            Glide.with(binding.root).load(image.imageUrl).into(binding.ivDog)
            if (image.fav) {
                binding.ivFab.setColorFilter(Color.RED)
                binding.ivFab.visibility = View.VISIBLE
            } else {
                binding.ivFab.visibility = View.GONE
            }
            itemView.setOnLongClickListener(this)
        }

        override fun onLongClick(v: View?): Boolean {
            selectedImage.value = listImages[adapterPosition]
            return true
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageDogVH {
        return ImageDogVH(ImageItemBinding.inflate(LayoutInflater.from(parent.context)))
    }

    override fun onBindViewHolder(holder: ImageDogVH, position: Int) {
        holder.bind(listImages[position])
    }

    override fun getItemCount() = listImages.size

}