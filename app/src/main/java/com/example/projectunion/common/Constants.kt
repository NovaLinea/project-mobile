package com.example.projectunion.common

import java.text.SimpleDateFormat

object Constants {

	// Navigation
	const val ARGUMENT_PROJECT_KEY = "projectID"
	const val ARGUMENT_CREATE_KEY = "typeProject"
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
	const val FIELD_INVALID = "Запись не верна"
	const val INVALID_LOGIN = "Не верная почта или пароль"
	const val INVALID_REGISTER = "Эта почта уже используется"
	const val ERROR_SERVER = "Ошибка на стороне сервера"

	// Errors Firebase
	const val USER_NOT_FOUND = "There is no user record corresponding to this identifier. The user may have been deleted."
	const val EMAIL_IS_USED = "The email address is already in use by another account."

	// Placeholders
	const val NAME_PLACEHOLDER = "Имя и фамилия"
	const val EMAIL_PLACEHOLDER = "Почта"
	const val PASSWORD_PLACEHOLDER = "Пароль"
	const val SEARCH_PLACEHOLDER = "Поиск"
	const val TITLE_PROJECT_PLACEHOLDER = "Название проекта"
	const val DESCRIPTION_PROJECT_PLACEHOLDER = "Описание проекта"
	const val PRICE_PROJECT_PLACEHOLDER = "Цена"

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
	const val CREATE_PROJECT = "Создать проект"

	// Titles
	const val TITLE_TYPE_PROJECT = "Выберите тип проекта"

	// Types project
	const val TYPE_PROJECT_SALE_TEXT = "Для продажи"
	const val TYPE_PROJECT_DONATE_TEXT = "Сбор донатов"
	const val TYPE_PROJECT_TEAM_TEXT = "Набор команды"
	const val TYPE_PROJECT_SALE = "sale"
	const val TYPE_PROJECT_DONATE = "donate"
	const val TYPE_PROJECT_TEAM = "team"

	// Firestore collections
	const val USERS_COLLECTION = "users"
	const val PROJECTS_COLLECTION = "projects"

	// Firebase storage path
	const val IMAGES_PROJECTS = "images/projects"
	const val IMAGES_USERS = "images/users"

	// Fields
	const val IMAGES_PROJECT_FIELD = "images"
}