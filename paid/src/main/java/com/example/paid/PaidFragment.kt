package com.example.paid

import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.NavOptions
import androidx.navigation.fragment.findNavController
import com.example.paid.databinding.FragmentPaidBinding
import kotlin.random.Random

class PaidFragment : Fragment() {

    private lateinit var binding: FragmentPaidBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentPaidBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setBackButton()
        setSuperButton()
        setMessage()
    }

    private fun setBackButton() {
        binding.icArrowBack.setOnClickListener {
            val navController = findNavController()
            val navOptions = NavOptions.Builder()
                .setLaunchSingleTop(true)
                .build()
            navController.popBackStack()
            val uri = Uri.parse("example://booking")
            navController.navigate(uri, navOptions)
        }
    }

    private fun setSuperButton() {
        binding.btnSuper.setOnClickListener {
            val navController = findNavController()
            val navOptions = NavOptions.Builder()
                .setLaunchSingleTop(true)
                .build()
            navController.popBackStack()
            val uri = Uri.parse("example://hotel")
            navController.navigate(uri, navOptions)
        }
    }

    private fun setMessage() {
        val orderNumber = Random.nextInt(100000, 999999)
        val message = "Подтверждение заказа №$orderNumber может занять некоторое время " +
                "(от 1 часа до суток). Как только мы получим ответ от туроператора, " +
                "вам на почту придет уведомление."
        binding.tvMessage.text = message
    }

}