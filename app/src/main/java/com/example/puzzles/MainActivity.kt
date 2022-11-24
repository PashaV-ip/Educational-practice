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
import kotlinx.android.synthetic.main.activity_main.*

const val ELEMENT_SIZE = 100
const val PLAYING_FIND_X = 10
const val PLAYING_FIND_Y = 5

class MainActivity : AppCompatActivity() {
    private val allField = mutableListOf<Element?>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE)
        val imageForGame = View(this)
        imageForGame.layoutParams = LinearLayout.LayoutParams(ELEMENT_SIZE * PLAYING_FIND_X, ELEMENT_SIZE * PLAYING_FIND_Y)
        imageForGame.background = ContextCompat.getDrawable(this, R.drawable.image_number_one)
        playingField.addView(imageForGame)
        playingField.setOnClickListener{
            imageForGame.background = ContextCompat.getDrawable(this, R.color.teal_700)
            drawElementOfField(0, 0)
        }
    }
    private fun startTheGame(){

    }
    private fun arrangeElementsInAllField(element: Element){
        for(index in 0 until PLAYING_FIND_Y * PLAYING_FIND_X){

        }
    }
    private fun randomPositionElement(element: Element){
        val elmentItem
    }
    private fun addElementOfAllFieldArray(marginTop:Int, marginLeft:Int, index:Int){
        val elementImage = drawElementOfField(marginTop, marginLeft)
        for(item in allField){
            if(elementImage.top == marginTop && elementImage.left == marginLeft){

            }
        }
        allField.add(Element(marginTop, marginLeft, elementImage, index))
    }
    private fun drawElementOfField(marginTop:Int, marginLeft:Int) : ImageView{
        val elementImage = ImageView(this)
        elementImage.setImageResource(R.drawable.style_buttons_on_main_menu)
        elementImage.layoutParams = FrameLayout.LayoutParams(ELEMENT_SIZE, ELEMENT_SIZE)
        (elementImage.layoutParams as FrameLayout.LayoutParams).topMargin = marginTop
        (elementImage.layoutParams as FrameLayout.LayoutParams).leftMargin = marginLeft

        playingField.addView(elementImage)
        return elementImage
    }
}