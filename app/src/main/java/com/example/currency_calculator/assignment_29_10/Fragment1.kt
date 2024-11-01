package com.example.currency_calculator.assignment_29_10

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.CalendarView
import android.widget.CheckBox
import android.widget.Spinner
import androidx.fragment.app.Fragment
import com.example.currency_calculator.R
import com.example.currency_calculator.databinding.Assignment29101Binding

class Fragment1: Fragment() {
    private lateinit var binding: Assignment29101Binding
    private var addressHelper = AddressHelper(resources)
    private lateinit var district: Spinner
    private lateinit var province: Spinner
    private lateinit var ward: Spinner
    private var favourites: MutableList<String> = mutableListOf()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = Assignment29101Binding.inflate(LayoutInflater.from(context))
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        initView()
    }

    private fun initView() {

        province = binding.province
        district = binding.district
        ward = binding.ward

        binding.submit.setOnClickListener {
            validateForm(
                binding.name.text.toString(),
                binding.sex.checkedRadioButtonId,
                binding.email.text.toString(),
                binding.phone.text.toString(),
                binding.dateView.text.toString(),
                province.selectedItem.toString(), // if not selected -> toString() = "null"
                district.selectedItem.toString(),
                ward.selectedItem.toString(),
                favourites,
                binding.policy.isChecked
            )
        }


        setUpSpinner(province, listOf()) {
            updateDistrict(province.selectedItem.toString())
        }

        setUpSpinner(district, listOf()) { position ->
            val currentDistrict = province.selectedItem.toString()
            updateWard(currentDistrict, addressHelper.getDistricts(currentDistrict)[position])
        }

        setUpCheckBoxListener(binding.movie)
        setUpCheckBoxListener(binding.sport)
        setUpCheckBoxListener(binding.music)

        binding.btnBirthday.setOnClickListener {
            showCalendarDialog()
        }

    }

    private fun setUpSpinner(spinner: Spinner, items: List<String>, onItemSelected: (position: Int) -> Unit) {
        spinner.adapter = ArrayAdapter<String>(
            requireContext(),
            android.R.layout.simple_spinner_item,
            items
        )
        spinner.onItemSelectedListener = object: AdapterView.OnItemSelectedListener {
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, position: Int, p3: Long) {
                onItemSelected(position)
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {
                TODO("Not yet implemented")
            }

        }
    }

    private fun setUpCheckBoxListener(checkBox: CheckBox) {
        checkBox.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                favourites.plus(checkBox.text)
            } else {
                favourites.remove(checkBox.text)
            }
        }
    }

    private fun updateDistrict(province: String) {
        val districts = addressHelper.getDistricts(province)
        (district.adapter as ArrayAdapter<String>).clear()
        (district.adapter as ArrayAdapter<String>).addAll(districts)
    }

    private fun updateWard(province: String, district: String) {
        val wards = addressHelper.getWards(province, district)
        (ward.adapter as ArrayAdapter<String>).clear()
        (ward.adapter as ArrayAdapter<String>).addAll(wards)
    }

    private fun validateForm(
        name: String,
        male: Int,
        email: String,
        phone: String,
        dayOfBirth: String,
        province: String,
        district: String,
        ward: String,
        favourites: List<String>,
        policy: Boolean
    ): Boolean {
        if (province != "null" && district != "null" && ward != "null") {
            return true
        } else {
            return false
        }
    }

    private fun showCalendarDialog() {
        val dialog = Dialog(requireContext())
        dialog.setContentView(R.layout.dialog_calendar)

        val calendarView: CalendarView = dialog.findViewById(R.id.calendarView)
        calendarView.setOnDateChangeListener { _, year, month, dayOfMonth ->
            val date = "$dayOfMonth/${month+1}/$year"
            binding.dateView.text = date
            dialog.dismiss()
        }
        dialog.show()
    }
}