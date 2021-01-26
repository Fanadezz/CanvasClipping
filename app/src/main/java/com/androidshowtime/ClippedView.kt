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
        }