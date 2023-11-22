package com.example.resources

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class ViewPagerAdapter(private val images: List<String>) :
    RecyclerView.Adapter<ViewPagerAdapter.ViewPagerHolder>() {
    inner class ViewPagerHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imageView: ImageView = itemView.findViewById(R.id.iv_image)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewPagerHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_view_pager, parent, false)
        return ViewPagerHolder(view)
    }

    override fun getItemCount(): Int {
        return images.size
    }

    override fun onBindViewHolder(holder: ViewPagerHolder, position: Int) {
        val currentImage = images[position]
        holder.itemView.apply {
            Glide.with(this).load(currentImage).into(holder.imageView)
        }
    }
}