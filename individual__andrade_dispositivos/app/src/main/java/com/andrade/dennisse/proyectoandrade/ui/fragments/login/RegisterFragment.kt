package com.andrade.dennisse.proyectoandrade.ui.fragments.login

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.andrade.dennisse.proyectoandrade.R
import com.andrade.dennisse.proyectoandrade.databinding.FragmentRegisterBinding
import com.andrade.dennisse.proyectoandrade.ui.core.ManageUIStates
import com.andrade.dennisse.proyectoandrade.ui.viewmodels.login.RegisterFragmentVM
import com.google.firebase.auth.FirebaseAuth

class RegisterFragment : Fragment() {

    private lateinit var binding: FragmentRegisterBinding

    private val registerFragmentVM: RegisterFragmentVM by viewModels()

    private lateinit var managerUIStates: ManageUIStates

    private lateinit var auth: FirebaseAuth

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentRegisterBinding.bind(
            inflater.inflate(R.layout.fragment_register, container, false)
        )
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initVariables()
        initListeners()
        initObservers()
    }

    private fun initVariables() {
        managerUIStates = ManageUIStates(requireActivity(), binding.lytLoading.mainLayout)
        auth = FirebaseAuth.getInstance()
    }

    private fun initObservers() {
        registerFragmentVM.uiState.observe(viewLifecycleOwner) { states ->
            managerUIStates.invoke(states)
        }
    }

    private fun initListeners() {
        binding.btnRegresar.setOnClickListener {
            findNavController().navigate(RegisterFragmentDirections.actionRegisterFragmentToLoginFragment())
        }

        binding.btnRegister.setOnClickListener {
            val email = binding.txtEmail.text.toString().trim()
            val password = binding.etxtPassword.text.toString().trim()
            if (email.isEmpty() || password.isEmpty()) {
                Toast.makeText(
                    requireActivity(),
                    "Email y contraseña no pueden estar vacíos",
                    Toast.LENGTH_SHORT
                ).show()
                return@setOnClickListener
            }

            Log.d("TAG", "Email: $email, Password: $password")

            auth.createUserWithEmailAndPassword(
                binding.txtEmail.text.toString().trim(),
                binding.etxtPassword.text.toString().trim()
            )
                .addOnCompleteListener(requireActivity()) { task ->
                    if (task.isSuccessful) {
                        Log.d("TAG", "createUserWithEmail:success")
                        val user = auth.currentUser
                        // Mostrar mensaje de registro satisfactorio
                        Toast.makeText(
                            requireActivity(),
                            "Registro exitoso. ¡Bienvenido!",
                            Toast.LENGTH_SHORT
                        ).show()
                        // Navegar a LoginFragment
                        findNavController().navigate(RegisterFragmentDirections.actionRegisterFragmentToLoginFragment())
                    } else {
                        Log.d("TAG", "createUserWithEmail:failure", task.exception)
                        Toast.makeText(
                            requireActivity(),
                            task.exception?.message.toString(),
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
        }
    }


}
