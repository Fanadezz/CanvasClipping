package com.androidshowtime

import android.content.Context
import android.graphics.Paint
import android.graphics.Path
import android.util.AttributeSet
import android.view.View

class ClippedView @JvmOverloads constructor(
        context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0) : View(
        context, attrs, defStyleAttr) {


            private val paint = Paint().apply {

                strokeWidth = resources.getDimension(R.dimen.strokeWidth)
                textSize = resources.getDimension(R.dimen.textSize)

                //smooth out edges without affecting the shape
                isAntiAlias = true
            }
    //create path to locally store the path of what has been drawn
    private val path = Path()


    //variables for dimensions for a clipping rectangle
    private val clipRectRight = resources.getDimension(R.dimen.clipRectRight)
    private val clipRectBottom = resources.getDimension(R.dimen.clipRectBottom)
    private val clipRectTop = resources.getDimension(R.dimen.clipRectTop)
    private val clipRectLeft = resources.getDimension(R.dimen.clipRectLeft)

    //variables for the inset of a rectangle and offset of a small rect
    private val rectInset = resources.getDimension(R.dimen.rectInset   )
    private val smallRectOffset = resources.getDimensionPixelOffset(R.dimen.smallRectOffset)

    //radius of a circle drawn inside the rectangle
    private val circleRadius = resources.getDimension(R.dimen.circleRadius)

    //offset and textSize for the text drawn inside rectangle
    private val textOffset = resources.getDimension(R.dimen.textOffset)
    private val textSize = resources.getDimension(R.dimen.textSize)

    //coordinates for two columns
    private val columnOne = rectInset
    private val columnTwo = columnOne + rectInset + clipRectRight


    //coordinates for each row, including the final row for the transformed text

    private val rowOne = rectInset
    private val rowTwo = rowOne + rectInset + clipRectBottom
    private val rowThree = rowTwo + rectInset + clipRectBottom
    private val rowFour = rowThree + rectInset + clipRectBottom
    private val textRow = rowFour + (1.5f * clipRectBottom)

        }