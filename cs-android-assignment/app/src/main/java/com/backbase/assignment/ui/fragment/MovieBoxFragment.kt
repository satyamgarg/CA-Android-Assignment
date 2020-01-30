package com.backbase.assignment.ui.fragment

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.paging.PagedList
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ItemDecoration
import com.backbase.assignment.R
import com.backbase.assignment.model.Movie
import com.backbase.assignment.ui.adapter.MovieMostPopularAdapter
import com.backbase.assignment.ui.adapter.MoviePlayingNowAdapter
import com.backbase.assignment.ui.utils.DividerItemDecorator
import com.backbase.assignment.ui.viewmodel.MovieBoxViewModel
import com.backbase.assignment.utilities.RepositoryUtils


class MovieBoxFragment : Fragment() {

    private var recyclerViewMostPopulat: RecyclerView? = null
    private lateinit var moviesAdapter: MovieMostPopularAdapter
    private lateinit var viewModel: MovieBoxViewModel
    private lateinit var rvPlayingNow: RecyclerView
    private lateinit var moviePlayingNowAdapter: MoviePlayingNowAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view: View = inflater.inflate(R.layout.fragment_movie_box, container, false)
        val activity = activity as Context

        recyclerViewMostPopulat = view.findViewById(R.id.recyclerViewPopular)
        rvPlayingNow = view.findViewById(R.id.rvPlayingNow)
        moviePlayingNowAdapter = MoviePlayingNowAdapter(ArrayList())
        rvPlayingNow.layoutManager =
            LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false)

        rvPlayingNow.adapter = moviePlayingNowAdapter

        return view
    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val factory = RepositoryUtils.provideMovieBoxViewModelFactory()
        viewModel = ViewModelProvider(this, factory).get(MovieBoxViewModel::class.java)

        setObservers()

    }

    /**
     * Set observers
     */
    private fun setObservers() {
        viewModel.getPlayingNowMovies().observe(viewLifecycleOwner, Observer { movies ->
            moviePlayingNowAdapter.items = movies.results ?: ArrayList()
            moviePlayingNowAdapter.notifyDataSetChanged()
        })

        viewModel.getPopularMovies().observe(viewLifecycleOwner, Observer { movies ->
            setPopularMoviesData(movies)
        })
    }

    /**
     * Setting received popular movies list
     */
    private fun setPopularMoviesData(movies: PagedList<Movie>) {

        moviesAdapter =
            MovieMostPopularAdapter(object : MovieMostPopularAdapter.OnItemClickListener {
                override fun onItemClick(view: View?, item: Movie?) {
                    val action =
                        MovieBoxFragmentDirections.actionMovieBoxToMovieDetails(item?.id.toString())
                    Navigation.findNavController(view!!).navigate(action)
                }
            })

        moviesAdapter.submitList(movies)

        recyclerViewMostPopulat?.layoutManager =
            LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
        val dividerItemDecoration: ItemDecoration =
            DividerItemDecorator(ContextCompat.getDrawable(context!!, R.drawable.divider)!!)
        recyclerViewMostPopulat?.addItemDecoration(dividerItemDecoration)

        recyclerViewMostPopulat?.adapter = moviesAdapter

    }

}
