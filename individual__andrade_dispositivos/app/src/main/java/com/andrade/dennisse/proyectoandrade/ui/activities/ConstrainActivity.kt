package com.andrade.dennisse.proyectoandrade.ui.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.andrade.dennisse.proyectoandrade.R
import com.andrade.dennisse.proyectoandrade.databinding.ActivityConstrainBinding

class ConstrainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityConstrainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityConstrainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initListeners()

    }


    private fun initListeners() {
        binding.bottomNavigation.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.listarItem -> {

                    true
                }

                R.id.FavItem -> {

                    true
                }

                R.id.NoFavItem -> {

                    true
                }

                else -> false
            }
        }
    }


}