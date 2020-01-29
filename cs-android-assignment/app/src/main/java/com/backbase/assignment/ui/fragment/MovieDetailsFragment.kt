package com.backbase.assignment.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.backbase.assignment.R
import com.backbase.assignment.data.NetworkApiConfig
import com.backbase.assignment.ui.utils.DateUtils
import com.backbase.assignment.ui.viewmodel.MovieDetailsViewModel
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.request.target.DrawableImageViewTarget
import com.backbase.assignment.utilities.RepositoryUtils


class MovieDetailsFragment : Fragment() {

    private lateinit var viewModel: MovieDetailsViewModel
    private lateinit var poster: ImageView
    private lateinit var tvMovieName: TextView
    private lateinit var tvReleaseDate: TextView
    private lateinit var tvOverview: TextView
    private lateinit var tvOverviewHeading: TextView
    private lateinit var progressBar: ProgressBar

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view: View = inflater.inflate(R.layout.fragment_movie_details, container, false)

        poster = view.findViewById(R.id.poster)
        tvMovieName = view.findViewById(R.id.tvMovieName)
        tvReleaseDate = view.findViewById(R.id.tvReleaseDate)
        tvOverview = view.findViewById(R.id.tvOverview)
        tvOverviewHeading = view.findViewById(R.id.tvOverviewHeading)
        progressBar = view.findViewById(R.id.progressBar)
        return view
    }


    override fun onStart() {
        super.onStart()

        val args = arguments?.let { MovieDetailsFragmentArgs.fromBundle(it) }
        val movieId = args?.movieId
        val factory = RepositoryUtils.provideMovieDetailsViewModelFactory()
        viewModel = ViewModelProvider(this, factory).get(MovieDetailsViewModel::class.java)
        progressBar.visibility = VISIBLE
        movieId?.let { it ->
            viewModel.getMovieDetails(it).observe(viewLifecycleOwner, Observer { movie ->

                progressBar.visibility = GONE
                context?.let {
                    Glide.with(it).load("${NetworkApiConfig.imageUrl}${movie.poster_path}")
                        .apply(RequestOptions().diskCacheStrategy(DiskCacheStrategy.NONE))
                        .into(DrawableImageViewTarget(poster))
                }


                tvMovieName.text = movie.original_title
                tvReleaseDate.text = DateUtils.formattedDate(
                    movie.release_date,
                    DateUtils.API_DATE_FORMAT,
                    DateUtils.RELEASE_DATE_FORMAT
                )
                tvOverview.text = movie.overview
                tvOverviewHeading.visibility = VISIBLE
            })
        }
    }

}
