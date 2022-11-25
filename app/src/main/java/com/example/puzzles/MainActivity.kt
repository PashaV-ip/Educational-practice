package com.example.puzzles

import android.content.pm.ActivityInfo
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.core.content.ContextCompat
import kotlinx.android.synthetic.main.activity_main.*

//Константы для формирования сетки игрового поля
const val ELEMENT_SIZE = 100
const val PLAYING_FIND_X = 10
const val PLAYING_FIND_Y = 5

class MainActivity : AppCompatActivity() {
    private val allField = mutableListOf<Element>()
    var tmpElementForTransferElement:Element? = null
    private var indexElements:Int = 0 //Вспомогательный индекс

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE)

        //Создаётся элемент для отображения картинки-примера
        val imageForGame = View(this)
        imageForGame.layoutParams = LinearLayout.LayoutParams(ELEMENT_SIZE * PLAYING_FIND_X, ELEMENT_SIZE * PLAYING_FIND_Y)
        imageForGame.background = ContextCompat.getDrawable(this, R.drawable.image_number_one)
        playingField.addView(imageForGame)

        //Устанавливается обработчик нажатия на примерочное изображение, для его разбеения на кусочки
        playingField.setOnClickListener{
            imageForGame.background = ContextCompat.getDrawable(this, R.color.teal_700)
            for(index in (0 until PLAYING_FIND_X * PLAYING_FIND_Y)){
                randomPositionElement(indexElements)
                indexElements++
            }
            textView1.text = allField.size.toString()
        }
    }
    //Метод необходим для корректной работы метода randomPositionElement(), для предотвращения пересечений объектов
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

    //Метод для генерации случайной позиции элемента
    private fun randomPositionElement(indexElemnt:Int){
        val element:Element = Element((0 until PLAYING_FIND_Y).random() * ELEMENT_SIZE,
            (0 until PLAYING_FIND_X).random() * ELEMENT_SIZE,
            null,
            indexElemnt)
        checkIfElementRepeatMargin(element)
        addElementOfAllFieldArray(element.top, element.left, element.indexElement)
    }

    //Метод добавляет элемент(Кусочек) в массив, формирующий игровое поле
    private fun addElementOfAllFieldArray(marginTop:Int, marginLeft:Int, index:Int){
        var elementItem : Element = Element(marginTop, marginLeft, null, index)
        if(checkIfElementRepeatMargin(elementItem)) {
            val elementImage :ImageView = createImage(index)
            elementItem = Element(marginTop, marginLeft, elementImage, index)
            drawElementOfField(elementItem)
            elementItem.imageView?.setOnClickListener{
                when (tmpElementForTransferElement) {
                    null -> {
                        tmpElementForTransferElement = elementItem
                    }
                    elementItem -> {
                        //В дальнейшем будет возможность поворота элемента
                    }
                    else -> {
                        clearViewArray(allField)
                        allField[tmpElementForTransferElement!!.indexElement].top = elementItem.top
                        allField[tmpElementForTransferElement!!.indexElement].left = elementItem.left
                        allField[elementItem.indexElement].top = tmpElementForTransferElement!!.top
                        allField[elementItem.indexElement].left = tmpElementForTransferElement!!.left
                        drawAllArray(allField)
                        tmpElementForTransferElement = null
                    }
                }
            }
            allField.add(elementItem)
        }
        else{
            randomPositionElement(index)
        }
    }

    //Метод для отчистки игрового поля, для дальнейшей перерисовки игрового поля
    private fun clearViewArray(array:MutableList<Element>){
        for(item in array){
            playingField.removeView(item.imageView)
        }
    }

    //Метод для отрисовки всех элементов игрового поля по средствам многократного вызова drawElementOfField
    private fun drawAllArray(array:MutableList<Element>){
        for(item in array){
            drawElementOfField(item)
        }
    }

    //Метод для отрисовки 1 элемента
    private fun drawElementOfField(element: Element) {
        if (element.imageView == null) {
            element.imageView = createImage(element.indexElement)
        }
        (element.imageView!!.layoutParams as FrameLayout.LayoutParams).topMargin = element.top
        (element.imageView!!.layoutParams as FrameLayout.LayoutParams).leftMargin = element.left
        playingField.addView(element.imageView)
    }

    //Метод для установки изображения в нужный элемент
    private fun createImage(indexElemnt: Int) : ImageView{
        val elementImage = ImageView(this)
        if(indexElemnt != -1) {
            when (indexElemnt) {
                0 -> elementImage.setImageResource(R.drawable.image_one_fragment_1)
                1 -> elementImage.setImageResource(R.drawable.image_one_fragment_2)
                2 -> elementImage.setImageResource(R.drawable.image_one_fragment_3)
                3 -> elementImage.setImageResource(R.drawable.image_one_fragment_4)
                4 -> elementImage.setImageResource(R.drawable.image_one_fragment_5)
                5 -> elementImage.setImageResource(R.drawable.image_one_fragment_6)
                6 -> elementImage.setImageResource(R.drawable.image_one_fragment_7)
                7 -> elementImage.setImageResource(R.drawable.image_one_fragment_8)
                8 -> elementImage.setImageResource(R.drawable.image_one_fragment_9)
                9 -> elementImage.setImageResource(R.drawable.image_one_fragment_10)
                10 -> elementImage.setImageResource(R.drawable.image_one_fragment_11)
                11 -> elementImage.setImageResource(R.drawable.image_one_fragment_12)
                12 -> elementImage.setImageResource(R.drawable.image_one_fragment_13)
                13 -> elementImage.setImageResource(R.drawable.image_one_fragment_14)
                14 -> elementImage.setImageResource(R.drawable.image_one_fragment_15)
                15 -> elementImage.setImageResource(R.drawable.image_one_fragment_16)
                16 -> elementImage.setImageResource(R.drawable.image_one_fragment_17)
                17 -> elementImage.setImageResource(R.drawable.image_one_fragment_18)
                18 -> elementImage.setImageResource(R.drawable.image_one_fragment_19)
                19 -> elementImage.setImageResource(R.drawable.image_one_fragment_20)
                20 -> elementImage.setImageResource(R.drawable.image_one_fragment_21)
                21 -> elementImage.setImageResource(R.drawable.image_one_fragment_22)
                22 -> elementImage.setImageResource(R.drawable.image_one_fragment_23)
                23 -> elementImage.setImageResource(R.drawable.image_one_fragment_24)
                24 -> elementImage.setImageResource(R.drawable.image_one_fragment_25)
                25 -> elementImage.setImageResource(R.drawable.image_one_fragment_26)
                26 -> elementImage.setImageResource(R.drawable.image_one_fragment_27)
                27 -> elementImage.setImageResource(R.drawable.image_one_fragment_28)
                28 -> elementImage.setImageResource(R.drawable.image_one_fragment_29)
                29 -> elementImage.setImageResource(R.drawable.image_one_fragment_30)
                30 -> elementImage.setImageResource(R.drawable.image_one_fragment_31)
                31 -> elementImage.setImageResource(R.drawable.image_one_fragment_32)
                32 -> elementImage.setImageResource(R.drawable.image_one_fragment_33)
                33 -> elementImage.setImageResource(R.drawable.image_one_fragment_34)
                34 -> elementImage.setImageResource(R.drawable.image_one_fragment_35)
                35 -> elementImage.setImageResource(R.drawable.image_one_fragment_36)
                36 -> elementImage.setImageResource(R.drawable.image_one_fragment_37)
                37 -> elementImage.setImageResource(R.drawable.image_one_fragment_38)
                38 -> elementImage.setImageResource(R.drawable.image_one_fragment_39)
                39 -> elementImage.setImageResource(R.drawable.image_one_fragment_40)
                40 -> elementImage.setImageResource(R.drawable.image_one_fragment_41)
                41 -> elementImage.setImageResource(R.drawable.image_one_fragment_42)
                42 -> elementImage.setImageResource(R.drawable.image_one_fragment_43)
                43 -> elementImage.setImageResource(R.drawable.image_one_fragment_44)
                44 -> elementImage.setImageResource(R.drawable.image_one_fragment_45)
                45 -> elementImage.setImageResource(R.drawable.image_one_fragment_46)
                46 -> elementImage.setImageResource(R.drawable.image_one_fragment_47)
                47 -> elementImage.setImageResource(R.drawable.image_one_fragment_48)
                48 -> elementImage.setImageResource(R.drawable.image_one_fragment_49)
                49 -> elementImage.setImageResource(R.drawable.image_one_fragment_50)
            }
        }
        elementImage.layoutParams = FrameLayout.LayoutParams(ELEMENT_SIZE, ELEMENT_SIZE)
        return elementImage
    }
}