package com.umbertoemonds.dharmonie.utils.injection

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.umbertoemonds.dharmonie.presentation.viewmodel.GrilleViewModel
import com.umbertoemonds.dharmonie.presentation.viewmodel.LoginViewModel

class ViewModelFactory : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if(modelClass == LoginViewModel::class.java){
            return LoginViewModel(Injection.provideUserCaseHandler(), Injection.provideLoginUseCase()) as T
        } else if(modelClass == GrilleViewModel::class.java){
            return GrilleViewModel(Injection.provideUserCaseHandler(), Injection.provideGrilleUseCase()) as T
        }
        throw IllegalArgumentException("Unknow model class for $modelClass")
    }

    companion object {
        private var INSTANCE: ViewModelFactory? = null
        fun getInstance(): ViewModelFactory {
            if (INSTANCE == null) {
                INSTANCE = ViewModelFactory()
            }
            return INSTANCE!!
        }
    }
}