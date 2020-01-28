package com.backbase.assignment.ui.fragment

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ItemDecoration
import com.backbase.assignment.R
import com.backbase.assignment.ui.adapter.MoviesAdapter
import com.backbase.assignment.ui.utils.DividerItemDecorator
import com.backbase.assignment.ui.viewmodel.MovieBoxViewModel
import com.company.mvvm.utilities.RepositoryUtils


class MovieBoxFragment : Fragment() {

    val TAG = MovieBoxFragment::class.java.simpleName

    companion object {
        fun newInstance() =
            MovieBoxFragment()
    }

    private lateinit var moviesAdapter: MoviesAdapter
    private lateinit var viewModel: MovieBoxViewModel

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
        moviesAdapter = MoviesAdapter(context)
        recyclerView.adapter = moviesAdapter

        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val factory = RepositoryUtils.provideBooksViewModelFactory()
        viewModel = ViewModelProvider(this, factory).get(MovieBoxViewModel::class.java)

        viewModel.getMovies().observe(viewLifecycleOwner, Observer { movies ->
            Log.d(TAG, "Movies List--:> ${movies.results?.size}")
           /* moviesAdapter.items = movies.results ?: ArrayList()
            moviesAdapter.notifyDataSetChanged()*/
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

}
