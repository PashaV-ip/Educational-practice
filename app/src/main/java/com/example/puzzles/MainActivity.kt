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
    private val allField = mutableListOf<Element>()
    var tmpElementForTransferElement:Element? = null
    private var indexElements:Int = 0;

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
            //drawElementOfField(0, 0)
            for(index in (0 until 50)){
                randomPositionElement(indexElements)
                indexElements++
            }
            arrayElements.text = allField.size.toString()
        }
    }
    private fun startTheGame(){

    }
    private fun checkIfElementRepeatMargin(element: Element):Boolean{
        if(allField.size > 0) {
            for (index in (0 until allField.size))
                if(element.top == allField[index].top && element.left == allField[index].left){
                    return false
                }
            else continue
        }
        return true
    }
    private fun randomPositionElement(indexElemnt:Int){
        val element:Element = Element((0 until PLAYING_FIND_Y).random() * ELEMENT_SIZE,
            (0 until PLAYING_FIND_X).random() * ELEMENT_SIZE,
            null,
            indexElemnt)
        checkIfElementRepeatMargin(element)
        addElementOfAllFieldArray(element.top, element.left, element.indexElement)
    }
    private fun addElementOfAllFieldArray(marginTop:Int, marginLeft:Int, index:Int){
        var elementItem : Element = Element(marginTop, marginLeft, null, index)
        if(checkIfElementRepeatMargin(elementItem)) {
            val elementImage :ImageView = drawElementOfField(marginTop, marginLeft)
            elementItem = Element(marginTop, marginLeft, elementImage, index)
            elementItem.imageView?.setOnClickListener{
                when (tmpElementForTransferElement) {
                    null -> {
                        tmpElementForTransferElement = elementItem
                    }
                    elementItem -> {
                        //В дальнейшем будет возможность поворота элемента
                    }
                    else -> {
                        allField[tmpElementForTransferElement!!.indexElement] = elementItem
                        allField[index] = tmpElementForTransferElement!!
                        drawElementOfField(allField[index].top, allField[index].left)
                        drawElementOfField(allField[tmpElementForTransferElement!!.indexElement].top, allField[tmpElementForTransferElement!!.indexElement].left)
                        tmpElementForTransferElement = null
                        arrayElements.text = elementItem.indexElement.toString()
                    }
                }
                arrayElements.text = tmpElementForTransferElement.toString()
            }
            allField.add(elementItem)
        }
        else{
            randomPositionElement(index)
        }
    }
    private fun drawElementOfField(marginTop:Int, marginLeft:Int) : ImageView{
        val elementImage = ImageView(this)
        if(allField.size % 2 == 0)
        elementImage.setImageResource(R.drawable.style_buttons_on_main_menu)
        else elementImage.setImageResource(R.drawable.style_for_title_main_menu)
        elementImage.layoutParams = FrameLayout.LayoutParams(ELEMENT_SIZE, ELEMENT_SIZE)
        (elementImage.layoutParams as FrameLayout.LayoutParams).topMargin = marginTop
        (elementImage.layoutParams as FrameLayout.LayoutParams).leftMargin = marginLeft

        playingField.addView(elementImage)
        return elementImage
    }
}