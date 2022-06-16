package com.example.projectunion.common

import java.text.SimpleDateFormat

object Constants {

	// Navigation
	const val ARGUMENT_PROJECT_KEY = "projectID"
	const val ROOT_ROUTE = "root_route"
	const val MAIN_ROUTE = "main_route"
	const val AUTHENTICATION_ROUTE = "authentication_route"

	// App
	const val TAG = "AppLog"
	val TIME_FORMAT = SimpleDateFormat("dd/M/yyyy hh:mm:ss")

	// Errors
	const val NAME_INVALID = "Имя и фамилия от 1 до 30 символов"
	const val EMAIL_INVALID = "Неверная почта"
	const val PASSWORD_INVALID = "Пароль не менее 6 символов"
	const val INVALID_LOGIN = "Не верная почта или пароль"
	const val INVALID_REGISTER = "Ошибка при регистрации"

	// Placeholders
	const val NAME_PLACEHOLDER = "Имя и фамилия"
	const val EMAIL_PLACEHOLDER = "Почта"
	const val PASSWORD_PLACEHOLDER = "Пароль"
	const val SEARCH_PLACEHOLDER = "Поиск"

	// Screens
	const val MAIN_SCREEN = "Главная"
	const val MESSAGES_SCREEN = "Сообщения"
	const val PROFILE_SCREEN = "Профиль"
	const val NOTIFICATIONS_SCREEN = "Уведомления"
	const val SEARCH_SCREEN = "Поиск"
	const val CREATE_SCREEN = "Создание проекта"
	const val SETTINGS_SCREEN = "Настройки"

	// Buttons
	const val LOGIN_TITLE = "Вход"
	const val LOGIN = "Войти"
	const val REGISTER_TITLE = "Регистрация"
	const val REGISTER = "Зарегестрироваться"
	const val LOGOUT = "Выйти"

	// Firestore collections
	const val USERS_COLLECTION = "users"
	const val PROJECTS_COLLECTION = "projects"
}