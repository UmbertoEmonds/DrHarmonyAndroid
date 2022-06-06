package com.umbertoemonds.dharmonie.presentation.activity

import android.os.Bundle
import android.os.PersistableBundle
import androidx.appcompat.app.AppCompatActivity
import com.umbertoemonds.dharmonie.databinding.ActivityAddChordsBinding

class AddChordsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAddChordsBinding

    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)

        binding = ActivityAddChordsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)
    }
}