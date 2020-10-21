package com.jun.booklivecoding.livecoding.bmicalculator

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.findNavController
import com.jun.booklivecoding.R
import kotlinx.android.synthetic.main.fragment_bmi_result.*
import kotlin.math.pow

class BmiResultFragment: Fragment() {
    lateinit var navController: NavController

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_bmi_result, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        navController = view.findNavController()

        val bmiModel = ViewModelProvider(viewModelStore, ViewModelFactory).get(BmiViewModel::class.java)
        bmiModel.getBmiData().observe(viewLifecycleOwner,
            Observer<BmiDTO> {
                val bmi = it.weight/ it.height.toDouble().pow(2.0)

                when {
                    bmi >= 35 -> resultTextView?.text="고도비만"
                    bmi >= 30 -> resultTextView?.text="2단계 비만"
                    bmi >= 25 -> resultTextView?.text="1단계 비만"
                    bmi >= 23 -> resultTextView?.text="과체중"
                    bmi >= 18.5 -> resultTextView?.text="정상"
                    else -> resultTextView?.text="저체중"
                }

                when {
                    bmi >= 23 -> resultImageView?.setImageResource(R.drawable.ic_baseline_sentiment_very_dissatisfied_24)
                    bmi >= 18.5 -> resultImageView?.setImageResource(R.drawable.ic_baseline_sentiment_satisfied_alt_24)
                    else -> resultImageView?.setImageResource(R.drawable.ic_baseline_sentiment_dissatisfied_24)
                }
            })
        bmiModel.loadBmiData()
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)


    }
}