package com.example.projectunion.common

import com.example.projectunion.domain.model.UserProfile

object Constants {

	// Arguments navigation
	const val ARGUMENT_PROJECT_ID_KEY = "projectId"
	const val ARGUMENT_PROJECT_PRICE_KEY = "projectPrice"
	const val ARGUMENT_PROJECT_TYPE_KEY = "typeProject"
	const val ARGUMENT_USER_ID_KEY = "userId"
	const val ARGUMENT_USER_NAME_KEY = "userName"
	const val ARGUMENT_USER_EMAIL_KEY = "email"
	const val ARGUMENT_USER_DESCRIPTION_KEY = "userDescription"

	// Navigation main routes
	const val ROOT_ROUTE = "root_route"
	const val MAIN_ROUTE = "main_route"
	const val AUTHENTICATION_ROUTE = "authentication_route"

	// Errors
	const val NAME_INVALID = "Имя и фамилия от 1 до 30 символов"
	const val EMAIL_INVALID = "Неверная почта"
	const val PASSWORD_INVALID = "Пароль не менее 6 символов"
	const val FIELD_INVALID = "Запись не верна"
	const val INVALID_USER = "Нет такого пользователя"
	const val INVALID_PASSWORD = "Пароль не верный"
	const val INVALID_REGISTER = "Эта почта уже используется"
	const val ERROR_SERVER = "Ошибка на стороне сервера"
	const val ERROR_VERIFY_EMAIL = "Для того чтобы войти в аккаунт, подтвердите почту"

	// Errors Firebase
	const val INVALID_LOGIN_PASSWORD = "The password is invalid or the user does not have a password."
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
	const val DESCRIPTION_PROFILE = "О себе"
	const val MESSAGE_FIELD = "Сообщение"

	// Screens
	const val MAIN_SCREEN = "Главная"
	const val LOGIN_SCREEN = "Вход"
	const val REGISTER_SCREEN = "Регистрация"
	const val VERIFY_EMAIL_SCREEN = "Подтверждение почты"
	const val PROJECT_SCREEN = "Проект"
	const val MESSAGES_SCREEN = "Сообщения"
	const val PROFILE_SCREEN = "Профиль"
	const val NOTIFICATIONS_SCREEN = "Уведомления"
	const val SEARCH_SCREEN = "Поиск"
	const val CREATE_SCREEN = "Новый проект"
	const val SETTINGS_SCREEN = "Настройки"
	const val FAVORITES_SCREEN = "Избранное"
	const val HOME_SCREEN = "Домашняя"
	const val ADDITIONALLY_SCREEN = "Еще"
	const val EDIT_PROFILE_SCREEN = "Редактирование"
	const val ABOUT_APP_SCREEN = "О приложении"
	const val THEMES_SCREEN = "Настройка темы"
	const val CHAT_SCREEN = "Чат"

	// Screens routes
	const val MAIN_SCREEN_ROUTE = "main_screen"
	const val LOGIN_SCREEN_ROUTE = "login_screen"
	const val VERIFY_EMAIL_SCREEN_ROUTE = "verify_email_screen"
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
	const val EDIT_PROFILE_SCREEN_ROUTE = "edit_profile_screen"
	const val ABOUT_APP_SCREEN_ROUTE = "about_app_screen"
	const val THEMES_SCREEN_ROUTE = "themes_screen"
	const val CHAT_SCREEN_ROUTE = "chat_screen"
	const val SPLASH_SCREEN_ROUTE = "splash_screen"

	// Buttons
	const val LOGIN = "Войти"
	const val REGISTER = "Зарегистрироваться"
	const val LOGOUT = "Выйти"
	const val CREATE_PROJECT = "Создать проект"
	const val BUY_PROJECT = "Купить"
	const val COUNT_PROJECTS = "Проектов"
	const val COUNT_FOLLOWS = "Подписчиков"
	const val COUNT_FOLLOWINGS = "Подписок"
	const val EDIT_PROFILE = "Редактировать"
	const val SUBSCRIBE_USER = "Подписаться"
	const val UNSUBSCRIBE_USER = "Отписаться"
	const val TO_WRITE_USER = "Написать"

	// Titles
	const val TITLE_TYPE_PROJECT = "Выберите тип проекта"
	const val TITLE_NO_DIALOGS = "Пока нет диалогов"
	const val TITLE_NO_PROJECTS = "Пока нет проектов"
	const val TITLE_NO_MESSAGES = "Пока нет сообщений"

	// Types project
	const val TYPE_PROJECT_SALE_TEXT = "Для продажи"
	const val TYPE_PROJECT_DONATE_TEXT = "Сбор донатов"
	const val TYPE_PROJECT_TEAM_TEXT = "Набор команды"
	const val TYPE_PROJECT_SALE = "sale"
	const val TYPE_PROJECT_DONATE = "donate"
	const val TYPE_PROJECT_TEAM = "team"

	// Text
	const val TEXT_VERIFY_EMAIL = "Мы отправили письмо со ссылкой для подтверждения вашей почты"

	// Types messages
	const val TYPE_MESSAGE_TEXT = "text"

	// Standard messages
	const val BUY_PROJECT_MESSAGE = "Покупка проекта"

	// Firestore collections
	const val USERS_COLLECTION = "users"
	const val PROJECTS_COLLECTION = "projects"

	// Firebase storage path
	const val PATH_IMAGES_PROJECTS = "images/projects"
	const val PATH_IMAGES_USERS = "images/users"

	// Fields firestore
	const val TITLE_PROJECT_FIELD = "title"
	const val DESCRIPTION_FIELD = "description"
	const val IMAGES_PROJECT_FIELD = "images"
	const val TYPE_PROJECT_FIELD = "type"
	const val PRICE_PROJECT_FIELD = "price"
	const val VIEWS_PROJECT_FIELD = "views"
	const val LIKES_PROJECT_FIELD = "likes"
	const val CREATOR_ID_PROJECT_FIELD = "creatorID"
	const val CREATED_AT_FIELD = "createdAt"
	const val UPDATED_AT_FIELD = "updatedAt"

	const val NAME_USER_FIELD = "name"
	const val PHOTO_USER_FIELD = "photo"
	const val DESCRIPTION_USER_FIELD = "description"
	const val EMAIL_USER_FIELD = "email"

	// Nodes realtime database
	const val NODE_MESSAGES = "messages"

	// Fields realtime database
	const val TEXT_MESSAGE_FIELD = "text"
	const val FROM_MESSAGE_FIELD = "from"
	const val TYPE_MESSAGE_FIELD = "type"
	const val TIMESTAMP_MESSAGE_FIELD = "timestamp"

	// Other
	const val TAG = "AppLog"
	var countMessagesChat = 10
	var USER = UserProfile()
	val NAME_APP = "С новой строки"
}