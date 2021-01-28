package com.androidshowtime

import android.content.Context
import android.graphics.*
import android.os.Build
import android.util.AttributeSet
import android.view.View
import androidx.core.graphics.withTranslation
import timber.log.Timber

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
myDrawBackAndUnclippedRectangle(canvas)
        //drawBackAndUnclippedRectangle(canvas)
        drawDifferenceClippingExample(canvas)
        drawCircularClippingExample(canvas)
        drawIntersectionClippingExample(canvas)
        drawCombinedClippingExample(canvas)
        drawRoundedRectangleClippingExample(canvas)
        drawOutsideClippingExample(canvas)
        drawSkewedTextExample(canvas)
        drawTranslatedTextExample(canvas)
    }

    private fun myDrawBackAndUnclippedRectangle(canvas: Canvas) {

        canvas.withTranslation(columnOne, rowOne) {


            drawColor(Color.GRAY)

            drawClippedRectangle(canvas)
        }
    }
    private fun drawDifferenceClippingExample(canvas: Canvas) {
// Move the origin to the right for the next rectangle.
        canvas.withTranslation (columnTwo, rowOne){
            // Use the subtraction of two clipping rectangles to create a frame.
            canvas.clipRect(
                    2 * rectInset,2 * rectInset,
                    clipRectRight - 2 * rectInset,
                    clipRectBottom - 2 * rectInset
                           )
            // The method clipRect(float, float, float, float, Region.Op
            // .DIFFERENCE) was deprecated in API level 26. The recommended
            // alternative method is clipOutRect(float, float, float, float),
            // which is currently available in API level 26 and higher.
            if (Build.VERSION.SDK_INT < Build.VERSION_CODES.O)
                canvas.clipRect(
                        4 * rectInset,4 * rectInset,
                        clipRectRight - 4 * rectInset,
                        clipRectBottom - 4 * rectInset,
                        Region.Op.DIFFERENCE
                               )
            else {
                canvas.clipOutRect(
                        4 * rectInset,4 * rectInset,
                        clipRectRight - 4 * rectInset,
                        clipRectBottom - 4 * rectInset
                                  )
            }
            drawClippedRectangle(canvas)
        }

    }

    private fun drawCircularClippingExample(canvas: Canvas) {
        canvas.withTranslation (columnOne, rowTwo){
            // Clears any lines and curves from the path but unlike reset(),
            // keeps the internal data structure for faster reuse.
            path.rewind()
            path.addCircle(
                    circleRadius,clipRectBottom - circleRadius,
                    circleRadius,Path.Direction.CCW
                          )
            // The method clipPath(path, Region.Op.DIFFERENCE) was deprecated in
            // API level 26. The recommended alternative method is
            // clipOutPath(Path), which is currently available in
            // API level 26 and higher.
            if (Build.VERSION.SDK_INT < Build.VERSION_CODES.O) {
                canvas.clipPath(path, Region.Op.DIFFERENCE)
            } else {
                canvas.clipOutPath(path)
            }
            drawClippedRectangle(canvas)


        }}


    private fun drawIntersectionClippingExample(canvas: Canvas) {
canvas.withTranslation (columnTwo, rowTwo){
    canvas.clipRect(
            clipRectLeft,clipRectTop,
            clipRectRight - smallRectOffset,
            clipRectBottom - smallRectOffset
                   )
    // The method clipRect(float, float, float, float, Region.Op
    // .INTERSECT) was deprecated in API level 26. The recommended
    // alternative method is clipRect(float, float, float, float), which
    // is currently available in API level 26 and higher.
    if (Build.VERSION.SDK_INT < Build.VERSION_CODES.O) {
        canvas.clipRect(
                clipRectLeft + smallRectOffset,
                clipRectTop + smallRectOffset,
                clipRectRight,clipRectBottom,
                Region.Op.INTERSECT
                       )
    } else {
        canvas.clipRect(
                clipRectLeft + smallRectOffset,
                clipRectTop + smallRectOffset,
                clipRectRight,clipRectBottom
                       )
    }
    drawClippedRectangle(canvas)
    canvas.restore()


}
    }

    private fun drawCombinedClippingExample(canvas: Canvas) {
        canvas.withTranslation(columnOne, rowThree){
            path.rewind()
            path.addCircle(
                    clipRectLeft + rectInset + circleRadius,
                    clipRectTop + circleRadius + rectInset,
                    circleRadius,Path.Direction.CCW
                          )
            path.addRect(
                    clipRectRight / 2 - circleRadius,
                    clipRectTop + circleRadius + rectInset,
                    clipRectRight / 2 + circleRadius,
                    clipRectBottom - rectInset,Path.Direction.CCW
                        )
            canvas.clipPath(path)
            drawClippedRectangle(canvas)


        }
    }


    private fun drawTranslatedTextExample(canvas: Canvas) {

    }

    private fun drawSkewedTextExample(canvas: Canvas?) {

    }

    private fun drawOutsideClippingExample(canvas: Canvas?) {

    }

    private fun drawRoundedRectangleClippingExample(canvas: Canvas?) {

    }











    private fun drawBackAndUnclippedRectangle(canvas: Canvas) {
        //change color
        canvas.drawColor(Color.GRAY)


        /*Saves the current matrix and clip onto a private stack.

Subsequent calls to translate,scale,rotate,skew,concat or clipRect, clipPath will
all operate as usual, but when the balancing call to restore() is made, those
calls will be forgotten, and the settings that existed before the save() will
be reinstated.*/
        //save the current state of canvas
        canvas.save()

        canvas.translate(columnOne, rowOne)


        drawClippedRectangle(canvas)

        //Restores the previous state of the canvas that was saved
        canvas.restore()

        val translateCheckpoint = canvas.save()
        Timber.i("The checkpoint is: $translateCheckpoint")
        val translateCheckpoint2 = canvas.save()
        Timber.i("The checkpoint 2 is: $translateCheckpoint2")
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
        paint.color = Color.GREEN
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
        canvas.drawText(context.getString(R.string.clipping), clipRectRight, textOffset, paint)

    }


}


