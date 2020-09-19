package com.jun.booklivecoding.joyce.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.jun.booklivecoding.R
import kotlinx.android.synthetic.main.fragment_result.*


/**
 * A simple [Fragment] subclass.
 * Use the [ResultFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ResultFragment : Fragment(), View.OnClickListener {
    var option = -1
    lateinit var navController: NavController

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        //null일 경우 -1
        option = arguments?.getInt("index")?:-1

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_result, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        navController = Navigation.findNavController(view)

        setResult(option)
        btn_home.setOnClickListener(this)
    }

    fun setResult(option : Int) {
        when(option) {
            1 -> {
                text_main.text = "You ar a Quitter!"
                text_content.text = "You can let the person easily."
            }
            2 -> {
                text_main.text = "You should focus on yourself"
                text_content.text = "You become really clingy to your ex."
            }
            3 -> {
                text_main.text = "You should take it easy"
                text_content.text = "You can do crazy things no matter what it takes."
            }
            4 -> {
                text_main.text = "You are pretty mature."
                text_content.text = "You can easily accept the break-up."
            }
        }
    }

    override fun onClick(p0: View?) {
        when(p0?.id) {
            R.id.btn_home -> {
                navController.popBackStack(R.id.mainFragment, false)
            }
        }
    }
}