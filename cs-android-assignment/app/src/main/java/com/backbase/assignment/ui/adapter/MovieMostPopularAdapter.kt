package com.backbase.assignment.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.backbase.assignment.R
import com.backbase.assignment.data.NetworkApiConfig
import com.backbase.assignment.model.Movie
import com.backbase.assignment.ui.custom.RatingView
import com.backbase.assignment.ui.utils.DateUtils
import com.backbase.assignment.ui.utils.DateUtils.API_DATE_FORMAT
import com.backbase.assignment.ui.utils.DateUtils.RELEASE_DATE_FORMAT
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.request.target.DrawableImageViewTarget


class MovieMostPopularAdapter() :
    PagedListAdapter<Movie, MovieMostPopularAdapter.ViewHolder>(Movie.CALLBACK) {

    private var listener: OnItemClickListener? = null

    constructor(listener: OnItemClickListener?) : this() {
        this.listener = listener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.movie_item,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int)  {
        val movie = getItem(position)
        movie?.let { holder.bind(it) }
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private lateinit var poster: ImageView
        private lateinit var title: TextView
        private lateinit var releaseDate: TextView
        private lateinit var rating: RatingView

        fun bind(item: Movie) = with(itemView) {
            poster = itemView.findViewById(R.id.poster)

            Glide.with(context)
                .load("${NetworkApiConfig.imageUrl}${item.posterPath}")
                .apply(RequestOptions().diskCacheStrategy(DiskCacheStrategy.AUTOMATIC))
                .into(DrawableImageViewTarget(poster))

            title = itemView.findViewById(R.id.title)
            title.text = item.title

            releaseDate = itemView.findViewById(R.id.tvReleaseDate)

            item.releaseDate?.let {
                releaseDate.text =
                    DateUtils.formattedDate(item.releaseDate, API_DATE_FORMAT, RELEASE_DATE_FORMAT)
            }


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

            itemView.setOnClickListener { view ->
                listener?.onItemClick(view, item)
            }
        }
    }

    interface OnItemClickListener {
        fun onItemClick(view: View?, item: Movie?)
    }
}