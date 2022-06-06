package com.example.projectunion

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class MainViewModel(application: Application) : AndroidViewModel(application) {
	fun intiDatabase() {

	}
}

class MainViewModelFactory(private val application: Application): ViewModelProvider.Factory {
	override fun <T : ViewModel?> create(modelClass: Class<T>): T {
		if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
			return MainViewModel(application = application)	as T
		}
		throw IllegalAccessException("Unknown ViewModel class")
	}
}