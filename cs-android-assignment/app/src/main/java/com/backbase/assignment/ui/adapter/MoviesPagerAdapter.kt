package com.backbase.assignment.ui.adapter


import androidx.viewpager.widget.PagerAdapter
import com.backbase.assignment.model.Movie

import android.content.Context
import android.os.Parcelable

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.backbase.assignment.R
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.request.target.DrawableImageViewTarget
import java.util.ArrayList

class MoviesPagerAdapter(private val context: Context, private val movies: ArrayList<Movie>) : PagerAdapter() {
    private val inflater: LayoutInflater

    init {
        inflater = LayoutInflater.from(context)
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        container.removeView(`object` as View)
    }

    override fun getCount(): Int {
        return movies.size
    }

    override fun instantiateItem(view: ViewGroup, position: Int): Any {
        val imageLayout = inflater.inflate(R.layout.view_palying_movies, view, false)!!

        val imageView = imageLayout
            .findViewById(R.id.image) as ImageView

        // Load the image into image view
        Glide.with(context)
            .load("https://image.tmdb.org/t/p/original/${movies[position].posterPath}")
            .apply(RequestOptions().diskCacheStrategy(DiskCacheStrategy.AUTOMATIC))
            .into(DrawableImageViewTarget(imageView))

        //imageView.setImageResource(movies[position].)

        view.addView(imageLayout, 0)

        return imageLayout
    }

    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view == `object`
    }

    override fun restoreState(state: Parcelable?, loader: ClassLoader?) {}

    override fun saveState(): Parcelable? {
        return null
    }

}

