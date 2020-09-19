package com.jun.booklivecoding.joyce.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.jun.booklivecoding.R
import kotlinx.android.synthetic.main.fragment_selection.*

/**
 * A simple [Fragment] subclass.
 * Use the [SelectionFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class SelectionFragment : Fragment(), View.OnClickListener {
    lateinit var navController: NavController

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_selection, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        navController = Navigation.findNavController(view)

        btn_prev.setOnClickListener(this)
        option1.setOnClickListener(this)
        option2.setOnClickListener(this)
        option3.setOnClickListener(this)
        option4.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when(v?.id) {
            R.id.option1 -> {
                navigateWithIdx(1)
            }
            R.id.option2 -> {
                navigateWithIdx(2)
            }
            R.id.option3 -> {
                navigateWithIdx(3)
            }
            R.id.option4 -> {
                navigateWithIdx(4)
            }
            R.id.btn_prev -> {
                navController.popBackStack()
            }
        }
    }
    fun navigateWithIdx(idx : Int) {
        val bundle = bundleOf("index" to idx)
        navController.navigate(R.id.action_selectionFragment_to_resultFragment, bundle)
    }
}