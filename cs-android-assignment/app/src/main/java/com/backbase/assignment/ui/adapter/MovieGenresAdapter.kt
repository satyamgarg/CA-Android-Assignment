package com.backbase.assignment.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.backbase.assignment.R
import com.backbase.assignment.model.Genres

class MovieGenresAdapter(var items: List<Genres> = ArrayList()) :
    RecyclerView.Adapter<MovieGenresAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.row_movie_genres,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) =
        holder.bind(items[position])

    override fun getItemCount() = items.size

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private lateinit var genres: TextView

        fun bind(item: Genres) = with(itemView) {
            genres = itemView.findViewById(R.id.tvGenres)
            genres.text = item.name
            itemView.setOnClickListener {}
        }
    }

}