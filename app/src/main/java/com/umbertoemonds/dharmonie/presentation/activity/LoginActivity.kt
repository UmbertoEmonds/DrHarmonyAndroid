package com.umbertoemonds.dharmonie.presentation.activity

import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.umbertoemonds.dharmonie.data.bodies.LoginData
import com.umbertoemonds.dharmonie.databinding.LoginActivityBinding
import com.umbertoemonds.dharmonie.presentation.activity.login.LoginViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class LoginActivity : AppCompatActivity() {

    private lateinit var binding: LoginActivityBinding

    private val loginViewModel: LoginViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = LoginActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.seConnecterBtn.setOnClickListener {
            lifecycleScope.launch {
                loginViewModel.login(LoginData("umberto.emonds@gmail.com", "admin")).collect {
                    Log.v("AZERTY", it);
                }
            }

        }
    }

}