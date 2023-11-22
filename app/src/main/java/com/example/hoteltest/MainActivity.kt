package com.example.hoteltest

import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.example.booking.domain.model.Tourist
import com.example.booking.domain.usecase.InsertTouristUseCase
import com.example.hoteltest.databinding.ActivityMainBinding
import com.example.hoteltest.util.NetworkUtils
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.koin.android.ext.android.inject

class MainActivity : AppCompatActivity() {
    private val insertTouristUseCase: InsertTouristUseCase by inject()

    private lateinit var navController: NavController
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        checkInternet()
        addFirstTourist()
    }

    private fun checkInternet() {
        val networkUtils = NetworkUtils(this as Activity)
        binding.progressBar.visibility = View.VISIBLE
        binding.btnRetry.visibility = View.GONE
        if (networkUtils.isInternetAvailable()) {
            setNavigator()
        } else {
            binding.btnRetry.visibility = View.VISIBLE
            binding.progressBar.visibility = View.GONE
            binding.tvError.visibility = View.VISIBLE
            binding.tvError.text = "Отсутствует подключение к интернету"
            binding.btnRetry.setOnClickListener {
                if (networkUtils.isInternetAvailable()) {
                    setNavigator()
                }
            }
        }
    }

    private fun setNavigator() {
        binding.progressBar.visibility = View.GONE
        binding.tvError.visibility = View.GONE
        binding.btnRetry.visibility = View.GONE
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        navController = navHostFragment.navController
    }

    private fun addFirstTourist() {
        val isFirstLaunch = getSharedPreferences("YourAppPrefs", Context.MODE_PRIVATE)
            .getBoolean("isFirstLaunch", true)

        if (isFirstLaunch) {
            val tourist = Tourist()
            CoroutineScope(Dispatchers.IO).launch {
                insertTouristUseCase(tourist)
            }
            getSharedPreferences("YourAppPrefs", Context.MODE_PRIVATE)
                .edit()
                .putBoolean("isFirstLaunch", false)
                .apply()
        }
    }

}