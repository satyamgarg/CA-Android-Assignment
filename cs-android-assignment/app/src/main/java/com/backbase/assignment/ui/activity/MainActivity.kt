package com.backbase.assignment.ui.activity

import android.os.Bundle
import android.view.Window
import androidx.appcompat.app.AppCompatActivity
import com.backbase.assignment.R
import com.backbase.assignment.model.Movie
import com.backbase.assignment.ui.fragment.MovieBoxFragment


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        setContentView(R.layout.activity_main)

        if (savedInstanceState == null) {
            supportFragmentManager
                .beginTransaction()
                .add(R.id.fragment_container, MovieBoxFragment.newInstance(supportFragmentManager, Movie()), "movieList")
                .commit()
        }

        //fetchMovies()
    }

    private fun fetchMovies() {
        /* val jsonString =
             URL("$baseUrl/movie/now_playing?language=en-US&page=undefined&api_key=$yourKey").readText()
         val jsonObject = JsonParser.parseString(jsonString).asJsonObject
         moviesAdapter.items = jsonObject["results"] as JsonArray
         moviesAdapter.notifyDataSetChanged()*/
    }
}
