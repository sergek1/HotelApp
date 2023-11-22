package com.example.booking.presentation

import android.content.res.ColorStateList
import android.net.Uri
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavOptions
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.booking.databinding.FragmentBookingBinding
import com.example.booking.domain.model.Booking
import com.example.booking.domain.model.Tourist
import com.example.booking.presentation.adapter.TouristAdapter
import com.example.booking.presentation.viewmodel.BookingViewModel
import com.example.booking.presentation.viewmodel.TouristsViewModel
import com.example.booking.util.EMAIL_PATTERN
import com.example.booking.util.PHONE_MASK
import com.example.booking.util.setupEditText
import com.example.resources.R
import com.example.resources.Result
import com.redmadrobot.inputmask.MaskedTextChangedListener
import com.redmadrobot.inputmask.MaskedTextChangedListener.Companion.installOn
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel


class BookingFragment : Fragment() {

    private lateinit var binding: FragmentBookingBinding
    private lateinit var touristAdapter: TouristAdapter
    private val bookingViewModel by viewModel<BookingViewModel>()
    private val touristsViewModel by viewModel<TouristsViewModel>()
    private var hotelName: String = ""

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentBookingBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bookingViewModel.getBooking()
        observeBookingViewModel()
        setupTouristAdapter()
        setPhoneNumberMask()
        setEmailEditText()
        setBackButton()
        setAddTouristButton()
        setPayButton()
    }

    override fun onResume() {
        super.onResume()
        observeTouristViewModel()
        observeBookingViewModel()
    }

    private fun observeBookingViewModel() {
        bookingViewModel.booking.observe(viewLifecycleOwner) { result ->
            when (result) {
                is Result.Success -> result.data?.let { data -> successUI(data) }

                is Result.Failure -> result.message?.let { message -> failureUI(message) }

                is Result.Loading -> {
                    loadingUI()
                }
            }
        }
    }

    private fun observeTouristViewModel() {
        viewLifecycleOwner.lifecycleScope.launch {
            touristsViewModel.getTourists()
            touristsViewModel.items.collect { result ->
                when (result) {
                    is Result.Success -> {
                        result.data?.let { data -> successTouristsUI(data) }
                    }

                    is Result.Failure -> {
                        failureTouristsUI(result.message.toString())
                    }

                    is Result.Loading -> loadingTouristsUI()
                }
            }
        }
    }

    private fun setupTouristAdapter() {
        touristAdapter =
            TouristAdapter(recyclerView = binding.rvTourists, receivedContext = requireContext())
        val myLayoutManager = LinearLayoutManager(requireContext(), RecyclerView.VERTICAL, false)
        binding.rvTourists.apply {
            adapter = touristAdapter
            layoutManager = myLayoutManager
        }
    }

    private fun successUI(booking: Booking) {
        binding.layoutCommon.visibility = View.VISIBLE
        binding.tvError.visibility = View.GONE
        binding.progressBar.visibility = View.GONE
        observeTouristViewModel()

        with(binding) {
            hotelName = booking.hotelName.toString()
            tvRating.text = booking.horating.toString()
            tvRatingName.text = booking.ratingName
            tvName.text = booking.hotelName
            tvAddress.text = booking.hotelAddress
            tvDeparture.text = booking.departure
            tvArrivalCountry.text = booking.arrivalCountry
            val dates = booking.tourDateStart + "-" + booking.tourDateStop
            tvDates.text = dates
            tvNumberOfNights.text = booking.numberOfNights.toString()
            tvHotelName.text = booking.hotelName
            tvRoom.text = booking.room
            tvNutrition.text = booking.nutrition
            editPhoneNumber.setText(bookingViewModel.getPhoneNumber())
            editEmail.setText(bookingViewModel.getEmail())

            val tourPrice = booking.tourPrice?.let {
                it.div(1000).toString() + " " + it.rem(1000).toString() + " ₽"
            }
            tvTourPrice.text = tourPrice.toString()

            val fuelCharge = booking.fuelCharge?.let {
                it.div(1000).toString() + " " + it.rem(1000).toString() + " ₽"
            }
            tvFuelCharge.text = fuelCharge.toString()
            val serviceCharge = booking.serviceCharge?.let {
                it.div(1000).toString() + " " + it.rem(1000).toString() + " ₽"
            }
            tvServiceCharge.text = serviceCharge.toString()
            val commonPrice =
                (booking.tourPrice!! + booking.fuelCharge!! + booking.serviceCharge!!).let {
                    it.div(1000).toString() + " " + it.rem(1000).toString() + " ₽"
                }
            tvCommonPrice.text = commonPrice
            val buttonText = "Оплатить $commonPrice"
            btnPay.text = buttonText
        }
    }

    private fun loadingUI() {
        binding.layoutCommon.visibility = View.GONE
        binding.tvError.visibility = View.GONE
        binding.progressBar.visibility = View.VISIBLE
    }

    private fun failureUI(message: String) {
        binding.layoutCommon.visibility = View.GONE
        binding.tvError.visibility = View.VISIBLE
        binding.tvError.text = message
        binding.progressBar.visibility = View.GONE
    }

    private fun successTouristsUI(data: List<Tourist>) {
        binding.rvTourists.visibility = View.VISIBLE
        binding.tvErrorTourists.visibility = View.GONE
        binding.progressBarTourists.visibility = View.GONE
        touristAdapter.items = data
    }

    private fun failureTouristsUI(message: String) {
        binding.rvTourists.visibility = View.GONE
        binding.tvErrorTourists.visibility = View.VISIBLE
        binding.tvErrorTourists.text = message
        binding.progressBarTourists.visibility = View.GONE
    }

    private fun loadingTouristsUI() {
        binding.rvTourists.visibility = View.GONE
        binding.tvErrorTourists.visibility = View.GONE
        binding.progressBarTourists.visibility = View.VISIBLE
    }

    private fun setPhoneNumberMask() {
        installOn(
            binding.editPhoneNumber,
            PHONE_MASK,
            object : MaskedTextChangedListener.ValueListener {
                override fun onTextChanged(
                    maskFilled: Boolean,
                    extractedValue: String,
                    formattedValue: String,
                    tailPlaceholder: String
                ) {
                    bookingViewModel.setPhoneNumber(extractedValue)
                }
            }
        )
    }

    private fun setEmailEditText(): Boolean {
        var isEmailRight = false
        val errorBackgroundColor = ContextCompat.getColor(requireContext(), R.color.error)
        val backgroundColor = ContextCompat.getColor(requireContext(), R.color.boxBackgroundColor)

        binding.editEmail.onFocusChangeListener = View.OnFocusChangeListener { _, hasFocus ->
            if (!hasFocus) {
                val email = binding.editEmail.text.toString()
                if (!email.matches(EMAIL_PATTERN.toRegex())) {
                    isEmailRight = false
                    binding.tilEmail.error = "Почта введена некорректно"
                    binding.editEmail.backgroundTintList =
                        ColorStateList.valueOf(errorBackgroundColor)
                } else {
                    isEmailRight = true
                    binding.editEmail.backgroundTintList = ColorStateList.valueOf(backgroundColor)
                    binding.tilEmail.error = null
                    bookingViewModel.setEmail(email)
                }
            }
        }
        return isEmailRight
    }

    private fun setPayButton() {
        binding.btnPay.setOnClickListener {
            val isPhoneNumberFilled = setupEditText(
                requireContext(), binding.editPhoneNumber, binding.tilPhoneNumber
            )
            val isTouristsFilled = touristAdapter.checkEditTexts()
            val isEmailFilled = setupEditText(requireContext(), binding.editEmail, binding.tilEmail)
            if (isPhoneNumberFilled && isEmailFilled && isTouristsFilled) {
                touristsViewModel.saveTourists(touristAdapter.items)

                val uri = Uri.parse("example://paid")
                findNavController().navigate(uri)
            }
        }
    }

    private fun setAddTouristButton() {
        binding.btnAddTourist.setOnClickListener {
            val tourist = Tourist()
            touristsViewModel.insertTourist(tourist)
            Handler(Looper.getMainLooper()).postDelayed({
                observeTouristViewModel()
            }, 500)
        }
    }

    private fun setBackButton() {
        binding.icArrowBack.setOnClickListener {
            val navController = findNavController()
            val navOptions = NavOptions.Builder()
                .setLaunchSingleTop(true)
                .build()
            navController.popBackStack()
            val uri = Uri.parse("example://rooms")
            navController.navigate(uri, navOptions)
        }
    }

}