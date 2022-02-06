package com.test.core.utils

import android.content.res.TypedArray
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Rect
import android.graphics.Typeface
import android.text.TextPaint
import com.arrayyan.context.FastApp
import com.test.core.R
import kotlin.math.abs

class LetterTile {

    private val mPaint = TextPaint()
    private val mBounds = Rect()
    private val mCanvas = Canvas()
    private val mColors: TypedArray
    private val mTileLetterFontSize: Int

    private object Instantiator {
        val mInstance = LetterTile()
    }

    init {
        val res = FastApp.getAppContext().resources
        mPaint.typeface = Typeface.create("sans-serif-light", Typeface.BOLD)
        mPaint.color = Color.WHITE
        mPaint.textAlign = Paint.Align.CENTER
        mPaint.isAntiAlias = true
        mColors = res.obtainTypedArray(R.array.letter_tile_colors)
        mTileLetterFontSize = res.getDimensionPixelSize(R.dimen.distance_35dp)
    }

    fun getLetterTitle(name: String?): Bitmap {
        val res = FastApp.getAppContext().resources
        val tileSize = res.getDimensionPixelSize(R.dimen.distance_100dp)
        val letterTile = LetterTile()
        val tempName = name?.replace("\\s".toRegex(), "")
        var validName = "A"
        if (!tempName.isNullOrEmpty())
            validName = name

        return letterTile.getLetterTile(validName, tileSize, tileSize)
    }

    private fun setLetter(name: String): String {
        val stringBuilder = StringBuilder()
        val regexDelimiters = "[\\s,\"():;.!?\\-]+"
        val arrayOfWords = name.replace("^$regexDelimiters".toRegex(), "")
            .split(regexDelimiters.toRegex())
            .dropLastWhile {
                it.isEmpty()
            }
            .toTypedArray()
        for (s in arrayOfWords) {
            stringBuilder.append(s.trim()[0].uppercase())
        }
        return stringBuilder.toString()
    }

    private fun getLetterTile(displayName: String, width: Int, height: Int): Bitmap {
        val bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888)
        val c = mCanvas
        c.setBitmap(bitmap)
        c.drawColor(pickColor(displayName))
        mPaint.textSize = mTileLetterFontSize.toFloat()
        mPaint.getTextBounds(setLetter(displayName), 0, 1, mBounds)
        if (setLetter(displayName).length > 1) {
            c.drawText(
                setLetter(displayName), 0, 2, (width / 2).toFloat(),
                (height / 2 + (mBounds.bottom - mBounds.top) / 2).toFloat(), mPaint
            )
        } else {
            c.drawText(
                setLetter(displayName), 0, 1, (width / 2).toFloat(),
                (height / 2 + (mBounds.bottom - mBounds.top) / 2).toFloat(), mPaint
            )
        }
        return bitmap
    }

    private fun pickColor(key: String): Int {
        val color = abs(key.hashCode()) % NUM_OF_TILE_COLORS
        try {
            return mColors.getColor(color, Color.BLACK)
        } finally {
            mColors.recycle()
        }
    }

    companion object {
        val instance: LetterTile by lazy { Instantiator.mInstance }
        private const val NUM_OF_TILE_COLORS = 8
    }
}
