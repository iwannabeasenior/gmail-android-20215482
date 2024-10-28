package com.example.currency_calculator

import android.os.Bundle
import android.os.PersistableBundle
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.currency_calculator.databinding.AcitivityGmailBinding

class GmailActivity: AppCompatActivity() {

    private lateinit var binding: AcitivityGmailBinding
    private val adapter = GmailAdapter()

    override fun onStart() {
        super.onStart()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = AcitivityGmailBinding.inflate(LayoutInflater.from(this))
        initView()
    }

    private fun initView() {
        var listItem = listOf(
            Item(
                 "E",
                "Edurila.com",
                "$19 Only (First 10 spots) - Bestselling of all time",
                "Are you looking to Learn Web Designing",
                "12:34 PM",
                R.color.blue
            ),
            Item(
                 "E",
                "Edurila.com",
                "$19 Only (First 10 spots) - Bestselling of all time",
                "Are you looking to Learn Web Designing",
                "12:34 PM",
                R.color.blue
            ),
            Item(
                 "E",
                "Edurila.com",
                "$19 Only (First 10 spots) - Bestselling of all time",
                "Are you looking to Learn Web Designing",
                "12:34 PM",
                R.color.blue
            ),
            Item(
                 "E",
                "Edurila.com",
                "$19 Only (First 10 spots) - Bestselling of all time",
                "Are you looking to Learn Web Designing",
                "12:34 PM",
                R.color.blue
            ),
        )


        val recyclerView = binding.listItem
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter

        adapter.submitList(listItem)
    }
}