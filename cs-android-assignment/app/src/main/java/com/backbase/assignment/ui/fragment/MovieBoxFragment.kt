package com.backbase.assignment.ui.fragment

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ItemDecoration
import androidx.viewpager.widget.ViewPager
import com.backbase.assignment.R
import com.backbase.assignment.model.Movie
import com.backbase.assignment.ui.adapter.MoviesAdapter
import com.backbase.assignment.ui.adapter.MoviesPagerAdapter
import com.backbase.assignment.ui.utils.DividerItemDecorator
import com.backbase.assignment.ui.viewmodel.MovieBoxViewModel
import com.company.mvvm.utilities.RepositoryUtils


class MovieBoxFragment : Fragment(), MoviesAdapter.OnItemClickListener {

    val TAG = MovieBoxFragment::class.java.simpleName

    companion object {
        private lateinit var fragmentManager: FragmentManager

        fun newInstance(movie: Movie) =
            MovieBoxFragment()

        fun newInstance(fragmentManager: FragmentManager, movie: Movie): MovieBoxFragment {
            this.fragmentManager = fragmentManager
            return MovieBoxFragment()
        }
    }

    private lateinit var moviesAdapter: MoviesAdapter
    private lateinit var viewModel: MovieBoxViewModel

    private lateinit var viewPager: ViewPager
    private lateinit var moviePagerAdapter: MoviesPagerAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view: View = inflater.inflate(R.layout.fragment_movie_box, container, false)
        val activity = activity as Context

        val recyclerView = view.findViewById<RecyclerView>(R.id.recyclerView)
        recyclerView.layoutManager =
            LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
        val dividerItemDecoration: ItemDecoration =
            DividerItemDecorator(ContextCompat.getDrawable(context!!, R.drawable.divider)!!)
        recyclerView.addItemDecoration(dividerItemDecoration)
        moviesAdapter = MoviesAdapter(object : MoviesAdapter.OnItemClickListener {
            override fun onItemClick(item: Movie?) {
                Toast.makeText(context, "Item Clicked ${item?.id}.", Toast.LENGTH_LONG).show()
            }
        })
        recyclerView.adapter = moviesAdapter

        viewPager = view.findViewById(R.id.viewPager)
        viewPager.offscreenPageLimit = 3
        //viewPager.pageMargin = -100

        moviePagerAdapter = MoviesPagerAdapter(context!!, ArrayList())
        viewPager.adapter = moviePagerAdapter
        return view
    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val factory = RepositoryUtils.provideBooksViewModelFactory()
        viewModel = ViewModelProvider(this, factory).get(MovieBoxViewModel::class.java)

        viewModel.getMovies().observe(viewLifecycleOwner, Observer { movies ->
            Log.d(TAG, "Movies List--:> ${movies.results?.size}")
            moviePagerAdapter = context?.let {
                MoviesPagerAdapter(
                    it,
                    movies.results as java.util.ArrayList<Movie>
                )
            }!!

            viewPager.adapter = moviePagerAdapter
        })

        viewModel.getPopularMovies().observe(this@MovieBoxFragment, Observer { movies ->
            Log.d(TAG, "Movies List--:> ${movies.results?.size}")
            moviesAdapter.items = movies.results ?: ArrayList()
            moviesAdapter.notifyDataSetChanged()
        })

//419704
/* viewModel.getMovieDetails("38700").observe(this@MovieBoxFragment, Observer { movies ->
     Log.d(TAG, "Movies Details--:> $movies.")
 })*/

    }

    override fun onItemClick(item: Movie?) {

        Toast.makeText(context, "Item Clicked ${item?.id}.", Toast.LENGTH_LONG).show()
    }

}
