package com.backbase.assignment.ui.adapter

import android.content.Context
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.backbase.assignment.R
import com.backbase.assignment.model.Movie
import com.backbase.assignment.ui.custom.RatingView

class MoviesAdapter(var items: List<Movie> = ArrayList()) :
    RecyclerView.Adapter<MoviesAdapter.ViewHolder>() {
    constructor(items: Context?) : this()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.movie_item,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) =
        holder.bind(items.get(position))

    override fun getItemCount() = items.size

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        lateinit var poster: ImageView
        lateinit var title: TextView
        lateinit var releaseDate: TextView
        lateinit var rating: RatingView

        fun bind(item: Movie) = with(itemView) {
            poster = itemView.findViewById(R.id.poster)
            poster.setImageURI(Uri.parse("https://image.tmdb.org/t/p/original/${item.posterPath}"))

            // Load the image into image view
            /*Glide.with(context)
                .load("https://image.tmdb.org/t/p/original/${item.posterPath}")
                .placeholder(R.drawable.logo_moviebox)
                .into(poster)*/

            title = itemView.findViewById(R.id.title)
            title.text = item.title

            releaseDate = itemView.findViewById(R.id.releaseDate)
            releaseDate.text = item.releaseDate

            rating = itemView.findViewById(R.id.rating)

            val progressBar: ProgressBar = rating.findViewById(R.id.circularProgressBar)
            val textView: TextView = rating.findViewById(R.id.caption)
            val percentage: Int = item.voteAverage?.times(10)?.toInt() ?: 0 ?: 0
            textView.text = percentage.toString()
            progressBar.progress = percentage

            when (percentage) {
                in 1..49 -> progressBar.progressDrawable =
                    context.getDrawable(R.drawable.progressbar_red)
                in 50..69 -> progressBar.progressDrawable =
                    context.getDrawable(R.drawable.progressbar_yellow)
                in 70..100 -> progressBar.progressDrawable =
                    context.getDrawable(R.drawable.progressbar_green)
                else -> progressBar.progressDrawable =
                    context.getDrawable(R.drawable.progressbar_red)
            }

        }
    }
}