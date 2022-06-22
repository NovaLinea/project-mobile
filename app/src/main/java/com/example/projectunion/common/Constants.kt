package com.example.projectunion.common

import java.text.SimpleDateFormat

object Constants {

	// Navigation
	const val ARGUMENT_PROJECT_KEY = "projectID"
	const val ARGUMENT_CREATE_KEY = "typeProject"
	const val ARGUMENT_PROFILE_KEY = "userID"
	const val ROOT_ROUTE = "root_route"
	const val MAIN_ROUTE = "main_route"
	const val AUTHENTICATION_ROUTE = "authentication_route"

	// Errors
	const val NAME_INVALID = "Имя и фамилия от 1 до 30 символов"
	const val EMAIL_INVALID = "Неверная почта"
	const val PASSWORD_INVALID = "Пароль не менее 6 символов"
	const val FIELD_INVALID = "Запись не верна"
	const val INVALID_LOGIN = "Не верная почта или пароль"
	const val INVALID_REGISTER = "Эта почта уже используется"
	const val ERROR_SERVER = "Ошибка на стороне сервера"

	// Errors Firebase
	const val USER_NOT_FOUND = "The password is invalid or the user does not have a password."
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
	const val LOGIN_SCREEN = "Вход"
	const val REGISTER_SCREEN = "Регистрация"
	const val PROJECT_SCREEN = "Проект"
	const val MESSAGES_SCREEN = "Сообщения"
	const val PROFILE_SCREEN = "Профиль"
	const val NOTIFICATIONS_SCREEN = "Уведомления"
	const val SEARCH_SCREEN = "Поиск"
	const val CREATE_SCREEN = "Создание проекта"
	const val SETTINGS_SCREEN = "Настройки"
	const val FAVORITES_SCREEN = "Избранное"
	const val HOME_SCREEN = "Домашняя"
	const val ADDITIONALLY_SCREEN = "Дополнительно"

	// Screens routes
	const val MAIN_SCREEN_ROUTE = "main_screen"
	const val LOGIN_SCREEN_ROUTE = "login_screen"
	const val REGISTER_SCREEN_ROUTE = "register_screen"
	const val PROJECT_SCREEN_ROUTE = "project_screen"
	const val CREATE_SCREEN_ROUTE = "create_screen"
	const val HOME_SCREEN_ROUTE = "home_screen"
	const val MESSAGES_SCREEN_ROUTE = "messages_screen"
	const val PROFILE_SCREEN_ROUTE = "profile_screen"
	const val NOTIFICATIONS_SCREEN_ROUTE = "notifications_screen"
	const val SEARCH_SCREEN_ROUTE = "search_screen"
	const val SETTINGS_SCREEN_ROUTE = "settings_screen"
	const val FAVORITES_SCREEN_ROUTE = "favorites_screen"
	const val ADDITIONALLY_SCREEN_ROUTE = "additionally_screen"

	// Buttons
	const val LOGIN = "Войти"
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
	const val PATH_IMAGES_PROJECTS = "images/projects"
	const val PATH_IMAGES_USERS = "images/users"

	// Fields firestore
	const val IMAGES_PROJECT_FIELD = "images"
	const val CREATEDAT_FIELD = "createdAt"

	// Other
	const val TAG = "AppLog"
	val TIME_FORMAT = SimpleDateFormat("dd/M/yyyy hh:mm:ss")
}