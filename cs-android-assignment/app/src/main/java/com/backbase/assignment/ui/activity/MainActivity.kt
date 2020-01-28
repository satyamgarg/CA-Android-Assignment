package com.backbase.assignment.ui.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.backbase.assignment.R
import com.backbase.assignment.ui.adapter.MoviesAdapter
import com.backbase.assignment.ui.fragment.MovieBoxFragment
import com.google.gson.JsonArray
import com.google.gson.JsonParser
import java.net.URL


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (savedInstanceState == null) {
            supportFragmentManager
                .beginTransaction()
                .add(R.id.fragment_container, MovieBoxFragment.newInstance(), "movieList")
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
