package com.umbertoemonds.dharmonie.presentation.activity

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.google.gson.Gson
import com.umbertoemonds.dharmonie.domain.models.LoginData
import com.umbertoemonds.dharmonie.databinding.LoginActivityBinding
import com.umbertoemonds.dharmonie.domain.repositories.UserRepository
import com.umbertoemonds.dharmonie.presentation.viewmodel.LoginViewModel
import com.umbertoemonds.dharmonie.utils.injection.ViewModelFactory

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: LoginActivityBinding
    private val loginViewModel = ViewModelFactory.getInstance().create(LoginViewModel::class.java)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = LoginActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.seConnecterBtn.setOnClickListener {

            val loginData = LoginData("umberto.emonds@gmail.com", "admin");

            loginViewModel.login(loginData, object : UserRepository.LoginCallback {
                override fun onLoggedIn(token: String) {

                }

                override fun onError(t: Throwable) {

                }

            })

        }
    }

}