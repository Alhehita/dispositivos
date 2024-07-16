package com.andrade.dennisse.proyectoandrade.ui.fragments.main

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.andrade.dennisse.proyectoandrade.R
import com.andrade.dennisse.proyectoandrade.databinding.FragmentListarMoviesPopularityBinding
import com.andrade.dennisse.proyectoandrade.databinding.FragmentListarTvPopularityBinding
import com.andrade.dennisse.proyectoandrade.ui.adapters.ListarMoviesPopularityAdapter
import com.andrade.dennisse.proyectoandrade.ui.adapters.ListarTVPopularityAdapter
import com.andrade.dennisse.proyectoandrade.ui.core.ManageUIStates
import com.andrade.dennisse.proyectoandrade.ui.viewmodels.main.ListarMovieInfoVM
import com.andrade.dennisse.proyectoandrade.ui.viewmodels.main.ListarTVInfoVM

class ListarTVPopularityFragment : Fragment() {

    private lateinit var binding: FragmentListarTvPopularityBinding
    private lateinit var adapter: ListarTVPopularityAdapter
    private val listarTVInfoVM: ListarTVInfoVM by viewModels()
    private lateinit var manageUIStates: ManageUIStates

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentListarTvPopularityBinding.bind(
            inflater.inflate(
                R.layout.fragment_listar_tv_popularity, container, false
            )
        )
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
        adapter = ListarTVPopularityAdapter()
        binding.rvListTVInfo.adapter = adapter
        binding.rvListTVInfo.layoutManager = LinearLayoutManager(
            requireActivity(), LinearLayoutManager.VERTICAL,
            false
        )
    }

    private fun initListeners() {}

    private fun initObservers() {
        listarTVInfoVM.itemsTV.observe(viewLifecycleOwner) {
            Log.d("ListarTVPopularityFragment", "Datos observados: $it")
            adapter.submitList(it)
        }

        listarTVInfoVM.uiState.observe(viewLifecycleOwner) {
            Log.d("ListarTVPopularityFragment", "Estado de UI observado: $it")
            manageUIStates.invoke(it)
        }
    }

    private fun initData() {
        listarTVInfoVM.initData()
        Log.d("ListarTVPopularityFragment", "Iniciando Datos...!")
    }
}
