package com.example.currency_calculator.assignment_29_10

import android.os.Bundle
import android.text.Editable
import android.text.Layout
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.currency_calculator.databinding.Assignment29103Binding

val names: List<String> = listOf(
    "Nguyễn Trung Thành",
    "Thành",
    "Nguyeenx",
    "Trung"
)

class Fragment3: Fragment() {
    private var _binding: Assignment29103Binding? = null
    private val binding: Assignment29103Binding get() = _binding!!
    private lateinit var adapter: ArrayAdapter<String>

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = Assignment29103Binding.inflate(LayoutInflater.from(context))
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        initView()
    }

    private fun initView() {

        adapter = ArrayAdapter(requireContext(), android.R.layout.simple_list_item_1, listOf())

        binding.listItems.adapter = adapter

        binding.edtSearch.addTextChangedListener(object: TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(query: CharSequence?, p1: Int, p2: Int, p3: Int) {
                val filterList = updateSearchResult(query.toString()).toMutableList()
                adapter.apply {
                    clear()
                    addAll(filterList)
                    notifyDataSetChanged()
                }
            }

            override fun afterTextChanged(p0: Editable?) {
            }
        })
    }

    private fun updateSearchResult(query: String): List<String> {

        return when {
            query.isEmpty() -> emptyList()
            query.length <= 2 -> names
            else -> names.filter { it.contains(query, ignoreCase = true) }
        }
    }
}