package com.example.currency_calculator.assignment_29_10

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import androidx.fragment.app.Fragment
import com.example.currency_calculator.databinding.Assignment29102Binding
import kotlin.math.sqrt

class Fragment2: Fragment() {

    private var _binding: Assignment29102Binding? = null
    private val binding get() = _binding!!
    private lateinit var typeOfNumber: String
    private var listNumbers: List<Int> = listOf()
    private var number: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = Assignment29102Binding.inflate(LayoutInflater.from(context))
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
    }

    private fun initView() {
        binding.typeOfNumber.setOnCheckedChangeListener {
            group, checkedId -> typeOfNumber = view?.findViewById<RadioButton>(checkedId)?.text.toString()
        }
        binding.edtNumber.addTextChangedListener ( object: TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                number = s.toString()
            }

            override fun afterTextChanged(s: Editable?) {
            }
        })

//        var adapter = ArrayAdapter(this, R.layout.list_item, R.id.textViewItem, arrayOf(listNumbers.map {it.toString()}))

//        binding.listNumbers.adapter = adapter

        binding.show.setOnClickListener {
            binding.tvNotiWrongInput.visibility = View.GONE
            val intNumber = try {
                number.toInt()
            } catch (e: NumberFormatException) {
                binding.tvNotiWrongInput.visibility = View.VISIBLE
                return@setOnClickListener
            }
            if (intNumber <= 0) {
                binding.tvNotiWrongInput.visibility = View.VISIBLE
                return@setOnClickListener
            }

            when(typeOfNumber) {
                "even" ->
                    for (i in 1..intNumber) if (i % 2 == 0) listNumbers.plus(i)
                "odd" ->
                    for (i in 1..intNumber) if (i % 2 == 0) listNumbers.plus(i)
                "square" ->
                    listNumbers = getListSquareNumbers(intNumber)
            }

        }

    }
    private fun getListSquareNumbers(n: Int): List<Int> {
        var sqrtN = sqrt(n.toFloat()).toInt()
        var squareNumbers: List<Int> = listOf()
        for (i: Int in 2..sqrtN) {
            squareNumbers.plus(i)
        }
        return squareNumbers
    }
}
