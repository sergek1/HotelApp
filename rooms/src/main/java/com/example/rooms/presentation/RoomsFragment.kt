package com.example.rooms.presentation

import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.NavOptions
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.resources.Result
import com.example.rooms.databinding.FragmentRoomsBinding
import com.example.rooms.domain.model.Room
import com.example.rooms.presentation.adapter.RoomAdapter
import com.example.rooms.presentation.viewmodel.RoomsViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel


class RoomsFragment : Fragment() {
    private lateinit var binding: FragmentRoomsBinding
    private lateinit var roomAdapter: RoomAdapter
    private val viewModel by viewModel<RoomsViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentRoomsBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getArgs()
        setBackButton()
        setupAdapter()
        observeViewModel()
    }

    private fun getArgs() {
        val argumentValue =
            findNavController().currentBackStackEntry?.arguments?.getString("hotel_name")
        binding.tvName.text = argumentValue
//        arguments?.getString("name")?.let {
        binding.tvName.text = "Лучший пятизвездочный отель в Хургаде, Египет"
//        }
    }

    private fun setBackButton() {
        binding.icArrowBack.setOnClickListener {
            val navController = findNavController()
            val navOptions = NavOptions.Builder()
                .setLaunchSingleTop(true)
                .build()
            navController.popBackStack()
            val uri = Uri.parse("example://hotel")
            navController.navigate(uri, navOptions)
        }
    }


    private fun setupAdapter() {
        roomAdapter = RoomAdapter(context = requireContext()) {
            val uri = Uri.parse("example://booking")
            findNavController().navigate(uri)
        }

        binding.rvRooms.apply {
            adapter = roomAdapter
            layoutManager = LinearLayoutManager(context)
        }
    }

    private fun observeViewModel() {
        viewModel.items.observe(viewLifecycleOwner) { result ->
            when (result) {
                is Result.Success -> {
                    result.data?.let { data -> successUI(data) }
                }

                is Result.Failure -> {
                    result.message?.let { message -> failureUI(message) }
                }

                is Result.Loading -> loadingUI()
            }
        }
    }

    private fun loadingUI() {
        binding.rvRooms.visibility = View.GONE
        binding.tvError.visibility = View.GONE
        binding.progressBar.visibility = View.VISIBLE
    }

    private fun failureUI(message: String) {
        binding.rvRooms.visibility = View.GONE
        binding.tvError.visibility = View.VISIBLE
        binding.tvError.text = message
        binding.progressBar.visibility = View.GONE
    }

    private fun successUI(rooms: List<Room>) {
        binding.rvRooms.visibility = View.VISIBLE
        binding.tvError.visibility = View.GONE
        binding.progressBar.visibility = View.GONE
        roomAdapter.items = rooms
    }
}