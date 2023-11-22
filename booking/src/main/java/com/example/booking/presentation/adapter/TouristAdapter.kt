package com.example.booking.presentation.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.booking.databinding.ItemTouristBinding
import com.example.booking.domain.model.Tourist
import com.example.booking.util.russianOrdinalNumber
import com.example.booking.util.setupEditText

class TouristAdapter(private val recyclerView: RecyclerView, private val receivedContext: Context) :
    RecyclerView.Adapter<TouristAdapter.TouristViewHolder>() {

    var items: List<Tourist> = emptyList()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    inner class TouristViewHolder(
        val binding: ItemTouristBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(position: Int, tourist: Tourist) = with(binding) {
            val touristNumber: String = if (position < 30) {
                russianOrdinalNumber[position] + " турист"
            } else {
                "Больше тридцати туристов"
            }
            tvTouristNumber.text = touristNumber
            btnToggle.setOnCheckedChangeListener { buttonView, isChecked ->
                if (isChecked) {
                    mainPart.visibility = View.GONE
                } else {
                    mainPart.visibility = View.VISIBLE
                }
            }

            editName.setText(tourist.name)
            editLastname.setText(tourist.lastname)
            editBirthday.setText(tourist.birthday)
            editCitizenship.setText(tourist.citizenship)
            editInterPassportNumber.setText(tourist.interPassportNumber)
            editPeriodOfPassport.setText(tourist.periodOfPassport)
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TouristViewHolder {
        val binding = ItemTouristBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return TouristViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: TouristViewHolder, position: Int) {
        val tourist = items[position]
        holder.bind(position, tourist)
    }

    fun checkEditTexts(): Boolean {
        var isAllFieldsFilled = false
        var isLastnameFilled: Boolean
        var isNameFilled: Boolean
        var isBirthdayFilled: Boolean
        var isCitizenshipFilled: Boolean
        var isPassportNumberFilled: Boolean
        var isPeriodOfPassportFilled: Boolean
        for (i in 0 until itemCount) {
            val viewHolder = recyclerView.findViewHolderForAdapterPosition(i) as? TouristViewHolder
            with(viewHolder!!.binding) {
                isLastnameFilled = setupEditText(receivedContext, editLastname, tilLastname)
                isNameFilled = setupEditText(receivedContext, editName, tilName)
                isBirthdayFilled = setupEditText(receivedContext, editBirthday, tilBirthday)
                isCitizenshipFilled =
                    setupEditText(receivedContext, editCitizenship, tilCitizenship)
                isPassportNumberFilled =
                    setupEditText(receivedContext, editInterPassportNumber, tilInterPassportNumber)
                isPeriodOfPassportFilled =
                    setupEditText(receivedContext, editPeriodOfPassport, tilPeriodOfPassport)

                isAllFieldsFilled =
                    isLastnameFilled && isNameFilled
                            && isBirthdayFilled && isCitizenshipFilled
                            && isPassportNumberFilled && isPeriodOfPassportFilled

                if (isAllFieldsFilled) {
                    items[i].name = editName.text.toString()
                    items[i].lastname = editLastname.text.toString()
                    items[i].birthday = editBirthday.text.toString()
                    items[i].citizenship = editCitizenship.text.toString()
                    items[i].interPassportNumber = editInterPassportNumber.text.toString()
                    items[i].periodOfPassport = editPeriodOfPassport.text.toString()
                }
            }
        }
        return isAllFieldsFilled
    }
}