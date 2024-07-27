package com.andrade.dennisse.proyectoandrade.ui.fragments.main

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.andrade.dennisse.proyectoandrade.R
import com.andrade.dennisse.proyectoandrade.databinding.FragmentSaveFireStoreBinding
import com.andrade.dennisse.proyectoandrade.ui.core.ManageUIStates
import com.andrade.dennisse.proyectoandrade.ui.core.UIStates
import com.andrade.dennisse.proyectoandrade.ui.entities.users.UserLogin
import com.andrade.dennisse.proyectoandrade.ui.viewmodels.login.SaveFirestoreVM
import com.google.firebase.Firebase
import com.google.firebase.auth.auth

class SaveFireStoreFragment : Fragment() {

    private lateinit var binding: FragmentSaveFireStoreBinding
    private lateinit var auth: FirebaseAuth
    private lateinit var manageUIStates: ManageUIStates

    private val saveFirestoreVM: SaveFirestoreVM by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSaveFireStoreBinding.bind(
            inflater.inflate(
                R.layout.fragment_save_fire_store, container, false
            )
        )
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        auth = Firebase.auth

        manageUIStates = ManageUIStates(requireActivity(), binding.lytLoading.mainLayout)

        initListeners()
        initObservers()

        val email = auth.currentUser?.email ?: ""
        val password = ""
        saveFirestoreVM.getUserByIdFireStore(auth.currentUser!!.uid, email, password)
    }

    private fun initObservers() {
        saveFirestoreVM.userUI.observe(viewLifecycleOwner) { state ->
            manageUIStates.invoke(state)
        }

        saveFirestoreVM.userLogin.observe(viewLifecycleOwner) { user ->
            binding.etName.setText(user?.name)
            binding.etLastName.setText(user?.lastName)
            binding.etEmail.setText(user?.email)
            binding.etPassword.setText(user?.pass)
        }
    }

    private fun initListeners() {
        binding.btnUpdate.setOnClickListener {
            val user = UserLogin(
                auth.currentUser!!.uid,
                binding.etName.text.toString(),
                binding.etLastName.text.toString(),
                binding.etEmail.text.toString(),
                binding.etPassword.text.toString()
            )
            saveFirestoreVM.updateUserFirestore(user)
        }

    }
}
