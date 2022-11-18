
package com.wisdomleaf.unsplash.utils

import androidx.recyclerview.widget.DiffUtil
import com.wisdomleaf.unsplash.model.UnsplashItem

class DiffUtilCallBack : DiffUtil.ItemCallback<UnsplashItem>() {
    override fun areItemsTheSame(oldItem: UnsplashItem, newItem: UnsplashItem): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: UnsplashItem, newItem: UnsplashItem): Boolean {
        return oldItem.id == newItem.id
                && oldItem.author == newItem.author
                && oldItem.downloadUrl == newItem.downloadUrl
    }
}