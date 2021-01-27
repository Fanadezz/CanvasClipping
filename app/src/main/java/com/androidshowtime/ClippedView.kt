package com.androidshowtime

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Path
import android.util.AttributeSet
import android.view.View
import androidx.core.content.res.TypedArrayUtils.getText

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
    private val rectInset = resources.getDimension(R.dimen.rectInset)
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

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        drawBackAndUnclippedRectangle(canvas)
        drawDifferenceClippingExample(canvas)
        drawCircularClippingExample(canvas)
        drawIntersectionClippingExample(canvas)
        drawCombinedClippingExample(canvas)
        drawRoundedRectangleClippingExample(canvas)
        drawOutsideClippingExample(canvas)
        drawSkewedTextExample(canvas)
        drawTranslatedTextExample(canvas)
    }

    private fun drawTranslatedTextExample(canvas: Canvas) {

    }

    private fun drawSkewedTextExample(canvas: Canvas?) {

    }

    private fun drawOutsideClippingExample(canvas: Canvas?) {

    }

    private fun drawRoundedRectangleClippingExample(canvas: Canvas?) {

    }

    private fun drawCombinedClippingExample(canvas: Canvas?) {

    }

    private fun drawIntersectionClippingExample(canvas: Canvas?) {

    }

    private fun drawCircularClippingExample(canvas: Canvas?) {

    }

    private fun drawDifferenceClippingExample(canvas: Canvas?) {

    }

    private fun drawBackAndUnclippedRectangle(canvas: Canvas) {
//change color
        canvas.drawColor(Color.GRAY)


        /*Saves the current matrix and clip onto a private stack.

Subsequent calls to translate,scale,rotate,skew,concat or clipRect, clipPath will
all operate as usual, but when the balancing call to restore() is made, those
calls will be forgotten, and the settings that existed before the save() will
be reinstated.*/
        //save
        canvas.save()

        canvas.translate(columnOne, rowOne)


        drawClippedRectangle(canvas)

//restore the canvas to its previous state
        canvas.restore()

    }

    //method for drawing the blueprint rectangle

    private fun drawClippedRectangle(canvas: Canvas) {
        /*Canvas.clipRect(left, top, right, bottom) reduces the region
        of the screen that future draw operations can write to*/
        //clip rectangle
        canvas.clipRect(clipRectLeft, clipRectTop, clipRectRight, clipRectBottom)

        //change canvas regional color
        canvas.drawColor(Color.WHITE)

        //change paint color in preparation to draw a line
        paint.color = Color.RED

        //draw line
        canvas.drawLine(clipRectLeft, clipRectTop, clipRectRight, clipRectBottom, paint)
//change paint color in preparation to drawing a green 
        paint.color = Color. GREEN
        //draw circle
        canvas.drawCircle(circleRadius, clipRectBottom - circleRadius, circleRadius, paint)

        //change paint color to blue in preparation to draw text
        paint.color = Color.BLUE

        //set textSize
        paint.textSize = textSize

        //set alignment

        /*Paint.Align specifies which side of the text to aligh to the origin
        * and not which side of origin the text goes*/
        paint.textAlign = Paint.Align.RIGHT

        //drawText
        canvas.drawText(context.getString(R.string.clipping),clipRectRight,textOffset,paint)

    }
}


