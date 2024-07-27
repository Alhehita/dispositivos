package com.andrade.dennisse.proyectoandrade.ui.fragments.login

import android.content.Intent
import android.os.Bundle
import android.text.method.HideReturnsTransformationMethod
import android.text.method.PasswordTransformationMethod
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.andrade.dennisse.proyectoandrade.R
import com.andrade.dennisse.proyectoandrade.databinding.FragmentLoginBinding
import com.andrade.dennisse.proyectoandrade.ui.activities.MainActivity
import com.andrade.dennisse.proyectoandrade.ui.core.ManageUIStates
import com.andrade.dennisse.proyectoandrade.ui.core.UIStates
import com.andrade.dennisse.proyectoandrade.ui.viewmodels.login.LoginFragmentVM
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class LoginFragment : Fragment() {

    private lateinit var binding: FragmentLoginBinding
    private lateinit var managerUIStates: ManageUIStates
    private val loginFragmentVM: LoginFragmentVM by viewModels()
    private lateinit var auth: FirebaseAuth

    // Variable para manejar la visibilidad de la contraseña
    private var isPasswordVisible: Boolean = false

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentLoginBinding.bind(inflater.inflate(R.layout.fragment_login, container, false))
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initVariables()
        initListeners()
        initiObservers()
    }

    private fun initVariables() {
        // Initialize Firebase Auth
        auth = Firebase.auth
        managerUIStates = ManageUIStates(requireActivity(), binding.lytLoading.mainLayout)
    }

    private fun initiObservers() {
        loginFragmentVM.uiState.observe(viewLifecycleOwner) { state ->
            if (state is UIStates.Success) {
                startActivity(Intent(requireActivity(), MainActivity::class.java))
            } else {
                managerUIStates.invoke(state)
            }
        }

        loginFragmentVM.idUser.observe(viewLifecycleOwner) { id ->
            startActivity(
                Intent(
                    requireActivity(),
                    MainActivity::class.java
                )
            )
            requireActivity().finish()
        }
    }

    private fun initListeners() {
        binding.btnRegister.setOnClickListener {
            findNavController().navigate(R.id.action_loginFragment_to_registerFragment)
        }

        binding.btnLogin.setOnClickListener {
            val username = binding.etxtUser.text.toString().trim()
            val password = binding.etxtPassword.text.toString().trim()

            if (username.isBlank() || password.isBlank()) {
                showErrorDialog("Por favor, ingrese usuario y contraseña.")
            } else {
                loginFragmentVM.authWhitFireBase(username, password, auth, requireActivity())
            }


        }

        binding.btnShowPassword.setOnClickListener {
            isPasswordVisible = !isPasswordVisible
            togglePasswordVisibility(isPasswordVisible)
        }

//        binding.btnRecovery.setOnClickListener {
//            findNavController().navigate(R.id.action_loginFragment_to_recoveyFragment)
//        }
    }

    private fun togglePasswordVisibility(isVisible: Boolean) {
        if (isVisible) {
            binding.etxtPassword.transformationMethod = HideReturnsTransformationMethod.getInstance()
            binding.btnShowPassword.setImageResource(R.drawable.visibility_off_24px) // Reemplaza con el icono de "ojo cerrado"
            binding.btnShowPassword.contentDescription = getString(R.string.hide_password)
        } else {
            binding.etxtPassword.transformationMethod = PasswordTransformationMethod.getInstance()
            binding.btnShowPassword.setImageResource(R.drawable.visibility_24px) // Reemplaza con el icono de "ojo abierto"
            binding.btnShowPassword.contentDescription = getString(R.string.show_password)
        }
        binding.etxtPassword.setSelection(binding.etxtPassword.text.length) // Mueve el cursor al final del texto
    }

    private fun showErrorDialog(message: String) {
        MaterialAlertDialogBuilder(requireContext())
            .setTitle("Error")
            .setMessage(message)
            .setPositiveButton("Aceptar") { dialog, _ ->
                dialog.dismiss()
            }
            .show()
    }
}
