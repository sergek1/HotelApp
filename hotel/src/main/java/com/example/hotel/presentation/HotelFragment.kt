package com.example.hotel.presentation

import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.net.toUri
import androidx.fragment.app.Fragment
import androidx.navigation.NavDeepLinkRequest
import androidx.navigation.fragment.findNavController
import com.example.hotel.databinding.FragmentHotelBinding
import com.example.hotel.domain.model.Hotel
import com.example.hotel.presentation.viewmodel.HotelViewModel
import com.example.resources.Result
import com.example.resources.TextViewAdapter
import com.example.resources.ViewPagerAdapter
import com.google.android.flexbox.FlexWrap
import com.google.android.flexbox.FlexboxLayoutManager
import com.google.android.flexbox.JustifyContent
import org.koin.androidx.viewmodel.ext.android.viewModel

class HotelFragment : Fragment() {
    private val navController by lazy { findNavController() }

    private val viewModel by viewModel<HotelViewModel>()
    private lateinit var binding: FragmentHotelBinding
    private lateinit var hotel: Hotel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHotelBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.getHotel()
        observeViewModel()
        setGoToRoomButton()
    }

    private fun observeViewModel() {
        viewModel.hotel.observe(viewLifecycleOwner) { result ->
            when (result) {
                is Result.Success -> result.data?.let { data -> successUI(data) }
                is Result.Failure -> result.message?.let { message -> failureUI(message) }
                is Result.Loading -> loadingUI()
            }
        }
    }

    private fun successUI(hotel: Hotel) {
        binding.layoutCommon.visibility = View.VISIBLE
        binding.tvError.visibility = View.GONE
        binding.progressBar.visibility = View.GONE
        this.hotel = hotel
        with(binding) {
            setupViewPager(hotel)
            setupPeculiarities(hotel.peculiarities)
            tvRating.text = hotel.rating.toString()
            tvRatingName.text = hotel.ratingName
            tvName.text = hotel.name
            tvAddress.text = hotel.address
            val price = hotel.minimalPrice?.let {
                it.div(1000).toString() + " " + it.rem(1000).toString() + " ₽"
            }
            tvMinimalPrice.text = price
            tvPriceForIt.text = hotel.priceForIt?.lowercase() ?: ""
            tvDescription.text = hotel.description
        }
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

    private fun setupViewPager(data: Hotel) {
        val images = data.imageUrls
        val viewPagerAdapter = ViewPagerAdapter(images)
        binding.viewPager.adapter = viewPagerAdapter
        binding.dotsIndicator.setViewPager2(binding.viewPager)
    }


    private fun failureUI(message: String) {
        binding.layoutCommon.visibility = View.GONE
        binding.tvError.visibility = View.VISIBLE
        binding.tvError.text = message
        binding.progressBar.visibility = View.GONE
    }

    private fun loadingUI() {
        binding.layoutCommon.visibility = View.GONE
        binding.tvError.visibility = View.GONE
        binding.progressBar.visibility = View.VISIBLE
    }

    private fun setGoToRoomButton() {
        binding.btnToRoom.setOnClickListener {
            val uri = Uri.parse("example://rooms?hotel_name=${hotel.name.toString()}")
            findNavController().navigate(
                uri
            )
        }
    }

    override fun onResume() {
        super.onResume()
        observeViewModel()
    }
}