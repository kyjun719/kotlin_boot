package com.jun.booklivecoding.livecoding.bmicalculator

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.findNavController
import com.jun.booklivecoding.R
import kotlinx.android.synthetic.main.fragment_bmi_start.*
import java.lang.Integer.parseInt

class BmiStartFragment: Fragment() {
    lateinit var navController: NavController
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_bmi_start, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        navController = view.findNavController()
        btn_done.setOnClickListener(btnClickListener)
    }
    private val btnClickListener = View.OnClickListener {
        val height = parseInt(edit_height.text.toString())
        val weight = parseInt(edit_weight.text.toString())

        Thread {
            PreferenceHandler.saveData(height, weight)
        }.start()
        navController.navigate(R.id.action_bmiStartFragment_to_bmiResultFragment)
    }
}