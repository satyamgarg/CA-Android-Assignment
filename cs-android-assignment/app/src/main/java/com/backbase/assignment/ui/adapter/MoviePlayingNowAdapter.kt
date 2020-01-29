package com.backbase.assignment.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.backbase.assignment.R
import com.backbase.assignment.data.NetworkApiConfig
import com.backbase.assignment.model.Movie
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.request.target.DrawableImageViewTarget


class MoviePlayingNowAdapter(var items: List<Movie> = ArrayList()) :
    RecyclerView.Adapter<MoviePlayingNowAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.view_palying_movies,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) =
        holder.bind(items[position])

    override fun getItemCount() = items.size

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private lateinit var poster: ImageView


        fun bind(item: Movie) = with(itemView) {
            poster = itemView.findViewById(R.id.poster)

            // Load the image into image view
            Glide.with(context)
                .load("${NetworkApiConfig.imageUrl}${item.posterPath}")
                .apply(RequestOptions().diskCacheStrategy(DiskCacheStrategy.AUTOMATIC))
                .into(DrawableImageViewTarget(poster))

            itemView.setOnClickListener {}
        }
    }


}