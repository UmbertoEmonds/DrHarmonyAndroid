package com.umbertoemonds.dharmonie.presentation.activity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import com.umbertoemonds.dharmonie.R
import com.umbertoemonds.dharmonie.data.models.Grille
import com.umbertoemonds.dharmonie.databinding.ActivityGrilleBinding
import com.umbertoemonds.dharmonie.domain.repositories.GrilleRepository
import com.umbertoemonds.dharmonie.presentation.adapter.GrilleAdapter
import com.umbertoemonds.dharmonie.presentation.viewmodel.GrilleViewModel
import com.umbertoemonds.dharmonie.utils.injection.ViewModelFactory

class GrillesActivity : AppCompatActivity() {

    private lateinit var binding: ActivityGrilleBinding
    private lateinit var grilleRV: RecyclerView
    private lateinit var grilleAdapter: GrilleAdapter

    private val grilleViewModel = ViewModelFactory.getInstance().create(GrilleViewModel::class.java)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityGrilleBinding.inflate(layoutInflater)
        setContentView(binding.root)

        grilleRV = binding.contentMain.grilleRv

        setSupportActionBar(binding.toolbar)
        supportActionBar?.title = getString(R.string.grilles_title)

        val loginIntent = Intent(this, LoginActivity::class.java)

        val sharedPreferences = getSharedPreferences(getString(R.string.app_name), Context.MODE_PRIVATE)
        val token = sharedPreferences.getString("USER_TOKEN", null)

        if(token == null){
            startActivity(loginIntent)
        }else {
            grilleViewModel.getGrilles(token, object : GrilleRepository.GrilleCallback  {
                override fun onGrillesLoaded(grilles: List<Grille>) {
                    configureRecyclerView(grilles)
                }

                override fun onSessionExpired() {
                    startActivity(loginIntent)
                }

                override fun onError(t: Throwable) {
                    Snackbar.make(binding.root, t.message.toString(), Snackbar.LENGTH_SHORT).show()
                }

            })
        }

        binding.fab.setOnClickListener {
            val addChordIntent = Intent(this, AjouterGrilleActivity::class.java)
            startActivity(addChordIntent)
        }
    }

    private fun configureRecyclerView(grilles: List<Grille>){
        grilleAdapter = GrilleAdapter(grilles)
        grilleRV.adapter = grilleAdapter
        grilleRV.layoutManager = LinearLayoutManager(this)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }
}