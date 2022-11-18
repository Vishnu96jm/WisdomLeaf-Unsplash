
package com.wisdomleaf.unsplash.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.wisdomleaf.unsplash.databinding.ItemRowBinding
import com.wisdomleaf.unsplash.model.UnsplashItem
import com.wisdomleaf.unsplash.utils.DiffUtilCallBack

//DiffUtil is a utility class to optimize the performance of RecyclerView
class UnsplashAdapter :
    PagingDataAdapter<UnsplashItem, UnsplashAdapter.UnsplashViewHolder>(DiffUtilCallBack()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UnsplashViewHolder {
        val binding = ItemRowBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return UnsplashViewHolder(binding)
    }

    override fun onBindViewHolder(holder: UnsplashViewHolder, position: Int) {
        //getItem method provided by PagingDataAdapter to get UnsplashItem
        getItem(position)?.let {
            //bind the item at that position
            holder.bindPost(it) }
    }

    class UnsplashViewHolder(binding: ItemRowBinding) : RecyclerView.ViewHolder(binding.root) {
        private val imageId: TextView = binding.imageId
        private val imageAuthor: TextView = binding.imageAuthor
        private val unsplashImage: ImageView = binding.unsplashImage

        fun bindPost(unsplashItem: UnsplashItem) {
            with(unsplashItem) {
                imageId.text = unsplashItem.id.toString()
                imageAuthor.text = unsplashItem.author
                Glide.with(itemView).load(unsplashItem.downloadUrl).into(unsplashImage)
            }
        }
    }
}