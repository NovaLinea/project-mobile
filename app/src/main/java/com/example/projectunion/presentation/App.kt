package com.example.projectunion.presentation

import android.app.Application
import com.example.projectunion.di.appModule
import com.example.projectunion.di.dataModule
import com.example.projectunion.di.domainModule
import org.koin.core.context.startKoin

class App: Application() {

	override fun onCreate() {
		super.onCreate()

		startKoin {
			modules(listOf(appModule, domainModule, dataModule))
		}
	}
}