package com.jun.booklivecoding.livecoding

import android.content.Intent
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.os.PersistableBundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.jun.booklivecoding.R
import com.jun.booklivecoding.livecoding.bmicalculator.BmiActivity
import kotlinx.android.synthetic.main.activity_live_coding_start.*
import java.util.*
import kotlin.collections.ArrayList

class LiveCodingStartActivity: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_live_coding_start)

        init()

    }

    private fun init() {
        supportActionBar?.title="LiveCoding App"

        layout_recycler.layoutManager = GridLayoutManager(this, 2)
        val itemList: ArrayList<AppShortCut> = arrayListOf()
        itemList.add(AppShortCut(R.drawable.ic_baseline_exposure_plus_1_24, "BMI Calculator"))

        layout_recycler.adapter = AppShortCutAdapter(itemList, object : AppShortCutAdapter.AppShortCutClickListener{
            override fun onClick(item: AppShortCut) {
                val nextIntent: Intent? = when(item.appName) {
                    "BMI Calculator" -> Intent(this@LiveCodingStartActivity, BmiActivity::class.java)
                    else -> null
                }
                if(nextIntent != null) {
                    startActivity(nextIntent)
                }
            }
        })

    }

    data class AppShortCut(val drawableResId: Int, val appName: String)

    private class AppShortCutAdapter(list: ArrayList<AppShortCut>, listener: AppShortCutClickListener) : RecyclerView.Adapter<AppShortCutAdapter.AppShortCutViewHolder>() {
        private class AppShortCutViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
            val appImage: ImageView = itemView.findViewById(R.id.item_image)
            val appName: TextView = itemView.findViewById(R.id.item_text)
            val appPanel: ConstraintLayout = itemView.findViewById(R.id.item_panel)
        }

        interface AppShortCutClickListener {
            fun onClick(item: AppShortCut)
        }

        val itemList: ArrayList<AppShortCut> = list
        val itemClickListener: AppShortCutClickListener = listener

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AppShortCutViewHolder {
            val v: View = LayoutInflater.from(parent.context).inflate(R.layout.item_app_shortcut, parent, false)
            return AppShortCutViewHolder(v)
        }

        override fun getItemCount(): Int {
            return itemList.size
        }

        override fun onBindViewHolder(holder: AppShortCutViewHolder, position: Int) {
            val item: AppShortCut = itemList[position]
            holder.appImage.setImageResource(item.drawableResId)
            holder.appName.text = item.appName
            holder.appPanel.setOnClickListener {
                itemClickListener.onClick(item)
            }
        }
    }
}