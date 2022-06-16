package com.umbertoemonds.dharmonie.presentation.activity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.Snackbar
import com.umbertoemonds.dharmonie.R
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

            val emailInput = binding.emailInput.text
            val passwordInput = binding.passwordInput.text

            val loginData = LoginData(emailInput.toString(), passwordInput.toString())

            loginViewModel.login(loginData, object : UserRepository.LoginCallback {
                override fun onLoggedIn(token: String) {
                    val sharedPreferences = getSharedPreferences(getString(R.string.app_name), Context.MODE_PRIVATE)

                    sharedPreferences.edit()
                        .putString("USER_TOKEN", token)
                        .apply()

                    val chordsIntent = Intent(applicationContext, GrillesActivity::class.java)
                    startActivity(chordsIntent)
                }

                override fun onError(t: Throwable) {
                    Snackbar.make(binding.root, getString(R.string.connexion_error), Snackbar.LENGTH_SHORT).show()
                }

            })

        }
    }

}