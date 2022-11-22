package com.example.puzzles

import android.annotation.SuppressLint
import android.content.pm.ActivityInfo
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.content.ContextCompat
const val ELEMENT_SIZE = 100

class MainActivity : AppCompatActivity() {

    lateinit var container: LinearLayout
    private val elements = mutableListOf<Element>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE)
    }
    private fun addElementOfElements(top:Int, left:Int){
        val partElement = drawElement(top, left)
        elements.add(Element(top, left, partElement))
    }

    private fun drawElement(top: Int, left: Int): ImageView {
        val elementImage = ImageView(this)
        elementImage.setImageResource(R.drawable.style_for_title_main_menu)
        elementImage.setBackgroundColor(ContextCompat.getColor(this, R.color.black))
        elementImage.layoutParams = FrameLayout.LayoutParams(ELEMENT_SIZE, ELEMENT_SIZE)
        (elementImage.layoutParams as FrameLayout.LayoutParams).topMargin = top
        (elementImage.layoutParams as FrameLayout.LayoutParams).topMargin = left

        container.addView(elementImage)
        return elementImage
    }
}