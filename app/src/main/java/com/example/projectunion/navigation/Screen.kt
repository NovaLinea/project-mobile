package com.example.projectunion.navigation

import com.example.projectunion.R

sealed class Screen(val name: String, val icon: Int) {
	object Main: Screen("main", -1)
	object Project: Screen("project", -1)
	object Home: Screen("home", R.drawable.ic_home)
	object Messages: Screen("messages", R.drawable.ic_messages)
	object Create: Screen("create", R.drawable.ic_add)
	object Notifications: Screen("notifications", R.drawable.ic_notifications)
	object Additionally: Screen("additionally", R.drawable.ic_more)
}