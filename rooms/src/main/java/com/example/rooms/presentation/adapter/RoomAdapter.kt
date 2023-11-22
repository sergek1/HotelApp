package com.example.rooms.presentation.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.resources.TextViewAdapter
import com.example.resources.ViewPagerAdapter
import com.example.rooms.databinding.ItemRoomBinding
import com.example.rooms.domain.model.Room
import com.google.android.flexbox.FlexWrap
import com.google.android.flexbox.FlexboxLayoutManager
import com.google.android.flexbox.JustifyContent

class RoomAdapter(
    private val context: Context,
    private val onClicked: () -> Unit
) :
    RecyclerView.Adapter<RoomAdapter.RoomViewHolder>() {

    var items: List<Room> = emptyList()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    inner class RoomViewHolder(
        private val binding: ItemRoomBinding,
        private val onClicked: () -> Unit,
        private val context: Context
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(room: Room) = with(binding) {
            tvName.text = room.name

            val price = room.price?.let {
                it.div(1000).toString() + " " + it.rem(1000).toString() + " ₽"
            }

            tvPrice.text = price
            tvPricePer.text = room.pricePer?.lowercase() ?: ""
            setupPeculiarities(room.peculiarities)
            btnChoose.setOnClickListener {
                onClicked()
            }
            setupViewPager(room.imageUrls)
        }

        private fun setupPeculiarities(peculiarities: List<String>) {
            val adapterPeculiarities = TextViewAdapter(peculiarities)
            val flexLayoutManager = FlexboxLayoutManager(context)
            flexLayoutManager.flexWrap = FlexWrap.WRAP
            flexLayoutManager.justifyContent = JustifyContent.FLEX_START

            binding.rvPeculiarities.apply {
                layoutManager = flexLayoutManager
                adapter = adapterPeculiarities
            }
        }

        private fun setupViewPager(imageUrls: List<String>) {
            val viewPagerAdapter = ViewPagerAdapter(imageUrls)
            binding.viewPager.adapter = viewPagerAdapter
            binding.dotsIndicator.setViewPager2(binding.viewPager)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RoomViewHolder {
        val binding = ItemRoomBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return RoomViewHolder(binding, onClicked, context)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: RoomViewHolder, position: Int) {
        holder.bind(items[position])
    }
}