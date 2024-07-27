package com.andrade.dennisse.proyectoandrade.ui.fragments.main

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.GridLayoutManager
import com.andrade.dennisse.proyectoandrade.R
import com.andrade.dennisse.proyectoandrade.databinding.FragmentListarMoviesPopularityBinding
import com.andrade.dennisse.proyectoandrade.ui.adapters.ListarMoviesPopularityAdapter
import com.andrade.dennisse.proyectoandrade.ui.core.ManageUIStates
import com.andrade.dennisse.proyectoandrade.ui.viewmodels.main.ListarMovieInfoVM

class ListarMoviesPopularityFragment : Fragment() {

    private lateinit var binding: FragmentListarMoviesPopularityBinding
    private val listarMovieInfoVM: ListarMovieInfoVM by viewModels()
    private lateinit var manageUIStates: ManageUIStates
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentListarMoviesPopularityBinding.inflate(inflater, container, false)
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

        val adapter = ListarMoviesPopularityAdapter { movie ->
            val bundle = Bundle().apply {
                putString("id", movie.id.toString())
                putString("title", movie.title)
                putString("original_title", movie.original_title)
                putString("original_language", movie.original_language)
                putString("poster_path", movie.poster_path)
                putString("description", movie.overview)
                putString("date", movie.release_date)

                // Asegúrate de tener una descripción en tu modelo
            }
            findNavController().navigate(R.id.action_listarMoviesPopularityFragment_to_movieDetailFragment, bundle)
        }

        binding.rvListMovieInfo.adapter = adapter
        binding.rvListMovieInfo.layoutManager = GridLayoutManager(requireActivity(), 3)

        // Agregar DividerItemDecoration para el espacio entre elementos
        val dividerItemDecoration = DividerItemDecoration(requireContext(), GridLayoutManager.VERTICAL)
        dividerItemDecoration.setDrawable(requireContext().getDrawable(R.drawable.divider)!!)
        binding.rvListMovieInfo.addItemDecoration(dividerItemDecoration)
    }

    private fun initListeners() {
        // binding.btnlist.setOnClickListener {
        //     findNavController().navigate(R.id.action_opcionesFragment_to_listarMoviesPopularityFragment)
        // }
    }

    private fun initObservers() {
        listarMovieInfoVM.itemsMovies.observe(viewLifecycleOwner) {
            (binding.rvListMovieInfo.adapter as ListarMoviesPopularityAdapter).submitList(it)
        }

        listarMovieInfoVM.uiState.observe(viewLifecycleOwner) {
            manageUIStates.invoke(it)
        }
    }

    private fun initData() {
        listarMovieInfoVM.initData()
        Log.d("TAG", "Iniciando Datos...!")
    }
}
