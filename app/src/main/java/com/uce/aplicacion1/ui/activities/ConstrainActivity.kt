package com.uce.aplicacion1.ui.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.Snackbar
import com.uce.aplicacion1.R
import com.uce.aplicacion1.databinding.ActivityConstrainBinding
import com.uce.aplicacion1.ui.fragments.FavoriteFragment
import com.uce.aplicacion1.ui.fragments.ListarNews

class ConstrainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityConstrainBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityConstrainBinding.inflate(layoutInflater)
        setContentView(binding.root)



        initListeners()

        val x = supportFragmentManager.beginTransaction()
        x.replace(binding.lytFragment.id, ListarNews())
        x.commit()

        settingsDataStore

    }


    private fun initListeners() {

        binding.bottomNavigation.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.listarItem -> {
                    //Esta es la manera con la cual agregamos un fragment a un layout.
                    val x = supportFragmentManager.beginTransaction()
                    x.replace(binding.lytFragment.id, ListarNews())
                    x.addToBackStack(null)
                    x.commit()
                    true
                }

                R.id.favItem -> {
                    //Snackbar.make(binding.refreshRV, "Item Favoritos", Snackbar.LENGTH_LONG).show()
                    val x = supportFragmentManager.beginTransaction()
                    x.replace(binding.lytFragment.id, FavoriteFragment())
                    x.commit()
                    true
                    true
                }

                R.id.noFavItem -> {
                    Snackbar.make(binding.lytFragment, "Item no Fav", Snackbar.LENGTH_LONG).show()
                    true
                }

                else -> false
            }

        }
        binding.btnClose.setOnClickListener {
            finish()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
    }


}

//    private fun addItem(){
//        binding.pgbarLoadData.visibility = View.VISIBLE
//        lifecycleScope.launch( Dispatchers.IO){
//            val addNew = GetOneTopNewUserCase().invoke()
//            withContext(Dispatchers.Main){
//                binding.pgbarLoadData.visibility = View.INVISIBLE
//                addNew.onSuccess {
//                    items.add(it)
//                    newsAdapter.listItems=items
//                    newsAdapter.notifyItemInserted(items.size-1)
//                }
//                addNew.onFailure {
//                    Snackbar.make(binding.refreshRV,it.message.toString(),Snackbar.LENGTH_LONG).show()
//                }
//            }
//        }
//
//
//    }

