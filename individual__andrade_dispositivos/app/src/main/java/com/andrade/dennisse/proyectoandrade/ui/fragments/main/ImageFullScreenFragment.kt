package com.andrade.dennisse.proyectoandrade.ui.fragments.main

import android.app.DownloadManager
import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.os.Environment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import coil.load
import com.andrade.dennisse.proyectoandrade.R
import com.andrade.dennisse.proyectoandrade.databinding.FragmentImageFullScreenBinding

class ImageFullScreenFragment : Fragment() {

    private lateinit var binding: FragmentImageFullScreenBinding
    private var posterPath: String? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentImageFullScreenBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        arguments?.let {
            posterPath = it.getString("poster_path")
            Log.d("ImageFullScreenFragment", "Image URL: https://image.tmdb.org/t/p/w500$posterPath")

            binding.imageViewFullScreen.load("https://image.tmdb.org/t/p/w500$posterPath") {
                placeholder(R.drawable.movies)
                error(R.drawable.face_24px)
            }
        }

        // Cierra el fragmento al hacer clic en la imagen
        binding.imageViewFullScreen.setOnClickListener {
            requireActivity().supportFragmentManager.popBackStack()
        }

        // Configura el botón de descarga
        binding.buttonDownload.setOnClickListener {
            posterPath?.let {
                downloadImage(it)
            } ?: Toast.makeText(context, "No image URL found", Toast.LENGTH_SHORT).show()
        }
    }

    private fun downloadImage(posterPath: String) {
        val url = "https://image.tmdb.org/t/p/w500$posterPath"
        val request = DownloadManager.Request(Uri.parse(url))
            .setTitle("Downloading image")
            .setDescription("Downloading image from $url")
            .setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED)
            .setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS, "image.jpg")
            .setAllowedOverMetered(true)
            .setAllowedOverRoaming(true)

        val downloadManager = requireContext().getSystemService(Context.DOWNLOAD_SERVICE) as DownloadManager
        downloadManager.enqueue(request)

        Toast.makeText(context, "Download started...", Toast.LENGTH_SHORT).show()
    }
}
