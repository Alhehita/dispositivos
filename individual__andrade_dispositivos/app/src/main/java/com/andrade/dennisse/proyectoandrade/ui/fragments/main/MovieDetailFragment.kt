package com.andrade.dennisse.proyectoandrade.ui.fragments.main

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import coil.load
import com.andrade.dennisse.proyectoandrade.R
import com.andrade.dennisse.proyectoandrade.databinding.FragmentMovieDetailBinding
import androidx.navigation.fragment.findNavController
import com.andrade.dennisse.proyectoandrade.ui.fragments.main.MovieDetailFragmentDirections

class MovieDetailFragment : Fragment() {

    private lateinit var binding: FragmentMovieDetailBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = FragmentMovieDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        arguments?.let {
            val id = it.getString("id") ?: return
            val title = it.getString("title")
            val originalTitle = it.getString("original_title")
            val originalLanguage = it.getString("original_language")
            val posterPath = it.getString("poster_path")
            val description = it.getString("description")
            val date = it.getString("date")
            val userScore = it.getDouble("vote_average", 0.0)
            val popularity = it.getDouble("popularity", 0.0)

            // Logging values for debugging
            Log.d("MovieDetailFragment", "UserScore: $userScore, Popularity: $popularity")
            Log.d("MovieDetailFragment", "PosterPath: $posterPath")

            // Verifica que el posterPath no sea null antes de cargar la imagen
            posterPath?.let {
                binding.imageView.load("https://image.tmdb.org/t/p/w500$posterPath") {
                    placeholder(R.drawable.movies)
                    error(R.drawable.face_24px)
                }
            }

            binding.titulo.text = title
            binding.tituloOriginal.text = originalTitle
            binding.idioma.text = originalLanguage
            binding.descripcion.text = description
            binding.date.text = date
        }

        // Añade el listener para navegar al ImageFullScreenFragment
        binding.imageView.setOnClickListener {
            val action = MovieDetailFragmentDirections.actionMovieDetailFragmentToImageFullScreenFragment(
                posterPath = arguments?.getString("poster_path") ?: ""
            )
            findNavController().navigate(action)
        }
    }
}
