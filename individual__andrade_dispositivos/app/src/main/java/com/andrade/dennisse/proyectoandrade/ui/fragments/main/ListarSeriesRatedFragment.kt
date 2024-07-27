package com.andrade.dennisse.proyectoandrade.ui.fragments.main

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.andrade.dennisse.proyectoandrade.R
import com.andrade.dennisse.proyectoandrade.databinding.FragmentListarRatedSeriesBinding
import com.andrade.dennisse.proyectoandrade.ui.adapters.ListarMoviesPopularityAdapter
import com.andrade.dennisse.proyectoandrade.ui.adapters.ListarSeriesRatedAdapter
import com.andrade.dennisse.proyectoandrade.ui.core.ManageUIStates
import com.andrade.dennisse.proyectoandrade.ui.viewmodels.main.ListarRatedSeriesInfoVM


class ListarSeriesRatedFragment : Fragment() {
    private lateinit var binding: FragmentListarRatedSeriesBinding

    private val listarRatedSeriesInfoVM: ListarRatedSeriesInfoVM by viewModels()
    private lateinit var manageUIStates: ManageUIStates

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentListarRatedSeriesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initVariables()
        initListeners()
        initObservers()
        initData()
    }

    private fun initVariables() {
        manageUIStates = ManageUIStates(requireActivity(), binding.lytLoading.mainLayout)
        val adapter = ListarSeriesRatedAdapter { movie ->
            val bundle = Bundle().apply {
                putString("id", movie.id.toString())
                putString("title", movie.name)
                putString("original_title", movie.original_name)
                putString("original_language", movie.original_language)
                putString("poster_path", movie.poster_path)
                putString("description", movie.overview)
                putString("date", movie.first_air_date)

                // Asegúrate de tener una descripción en tu modelo
            }
            findNavController().navigate(R.id.action_listarSeriesRatedFragment_to_movieDetailFragment, bundle)
        }
        binding.rvListFavoriteMovieInfo.adapter = adapter
        binding.rvListFavoriteMovieInfo.layoutManager = GridLayoutManager(
            requireActivity(), 3 // Dos columnas
        )
    }

    private fun initListeners() {}

    private fun initObservers() {
        listarRatedSeriesInfoVM.itemsSeries.observe(viewLifecycleOwner) {
            (binding.rvListFavoriteMovieInfo.adapter as ListarSeriesRatedAdapter).submitList(it)
        }

        listarRatedSeriesInfoVM.uiState.observe(viewLifecycleOwner) {
            manageUIStates.invoke(it)
        }
    }

    private fun initData() {
        listarRatedSeriesInfoVM.initData()
        Log.d("TAG", "Iniciando Datos...!")
    }
}
