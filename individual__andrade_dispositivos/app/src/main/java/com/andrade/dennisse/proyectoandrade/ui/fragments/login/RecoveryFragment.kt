package com.andrade.dennisse.proyectoandrade.ui.fragments.login

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.andrade.dennisse.proyectoandrade.R
import com.andrade.dennisse.proyectoandrade.databinding.FragmentRecoveryBinding

class RecoveryFragment : Fragment() {
    private lateinit var binding: FragmentRecoveryBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding= FragmentRecoveryBinding.bind(

            inflater.inflate(R.layout.fragment_recovery, container, false))
        return binding.root
        }


}
