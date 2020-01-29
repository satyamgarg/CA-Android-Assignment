package com.backbase.assignment.ui.custom

import android.content.Context
import android.util.AttributeSet
import android.widget.RelativeLayout
import com.backbase.assignment.R

class RatingView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : RelativeLayout(context, attrs, defStyleAttr) {
    init {
        inflate(context, R.layout.view_rating, this)
    }
}