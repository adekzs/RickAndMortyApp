package com.adeks.rickandmortyapp

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.adeks.rickandmortyapp.api.Repository
import java.lang.IllegalArgumentException

class MainActivityProviderViewModel(private val repository: Repository) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainActivityViewModel::class.java)) {
            return MainActivityViewModel(repository) as T
        } else {
            throw IllegalArgumentException("Unknown Class")
        }
    }
}