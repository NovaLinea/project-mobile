package com.example.novalinea.common

import com.example.novalinea.domain.model.UserProfile

object Constants {

	// Arguments navigation
	const val ARGUMENT_PROJECT_DATA = "project_data"
	const val ARGUMENT_CHAT_DATA = "chat_data"
	const val ARGUMENT_PROJECT_ID_KEY = "project_id"
	const val ARGUMENT_USER_ID_KEY = "user_id"
	const val ARGUMENT_USER_NAME_KEY = "user_name"
	const val ARGUMENT_USER_EMAIL_KEY = "user_email"
	const val ARGUMENT_USER_DESCRIPTION_KEY = "user_description"
	const val ARGUMENT_PHOTOS_KEY = "photos"

	// Navigation main routes
	const val MAIN_ROUTE = "main_route"
	const val HOME_ROUTE = "home_route"
	const val MESSAGES_ROUTE = "messages_route"
	const val AUTHENTICATION_ROUTE = "authentication_route"

	// Errors
	const val NAME_INVALID = "Имя и фамилия от 1 до 20 символов"
	const val EMAIL_INVALID = "Неверная почта"
	const val PASSWORD_INVALID = "Пароль не менее 6 символов"
	const val FIELD_INVALID = "Запись не верна"
	const val INVALID_USER = "Нет такого пользователя"
	const val INVALID_PASSWORD = "Пароль не верный"
	const val INVALID_REGISTER = "Эта почта уже используется"
	const val ERROR_SERVER = "Ошибка на стороне сервера"
	const val ERROR_VERIFY_EMAIL = "Для того чтобы войти в аккаунт, подтвердите почту"
	const val ERROR_BY_GET_PROJECTS = "Ошибка при получении проектов"
	const val ERROR_BY_GET_CHATS = "Ошибка при получении чатов"
	const val ERROR_BY_LOGOUT = "Ошибка при выходе из аккаунта"
	const val ERROR_BY_UPDATE_PHOTO = "Ошибка при изменении фотографии"
	const val ERROR_BY_GET_MESSAGES = "Ошибка при получении сообщений"
	const val ERROR_BY_SEND_MESSAGE = "Ошибка при отправке сообщения"
	const val ERROR_BY_CREATE_PROJECT = "Ошибка при создании проекта"
	const val ERROR_BY_EDIT_PROFILE = "Ошибка при редактировании профиля"
	const val ERROR_BY_SEND_BUY_MESSAGE_PROJECT = "Ошибка при отправке заявки на покупку"
	const val ERROR_BY_GET_ADDITIONALLY_DATA_PROJECT = "Ошибка при получении дополнительной информации проекта"
	const val ERROR_BY_GET_PROFILE = "Ошибка при полчении данных профиля"

	// Errors Firebase
	const val INVALID_LOGIN_PASSWORD = "The password is invalid or the user does not have a password."
	const val USER_NOT_FOUND = "There is no user record corresponding to this identifier. The user may have been deleted."
	const val EMAIL_IS_USED = "The email address is already in use by another account."

	// Placeholders
	const val NAME_PLACEHOLDER = "Имя и фамилия"
	const val EMAIL_PLACEHOLDER = "Почта"
	const val PASSWORD_PLACEHOLDER = "Пароль"
	const val SEARCH_PLACEHOLDER = "Поиск"
	const val TITLE_PROJECT_PLACEHOLDER = "Заголовок"
	const val DESCRIPTION_PROJECT_PLACEHOLDER = "Описание проекта"
	const val PRICE_PROJECT_PLACEHOLDER = "Цена"
	const val DESCRIPTION_PROFILE_PLACEHOLDER = "О себе"
	const val MESSAGE_FIELD_PLACEHOLDER = "Сообщение"
	const val STAFF_PLACEHOLDER = "Сотрудник"

	// Screens
	const val LOGIN_SCREEN = "Вход"
	const val REGISTER_SCREEN = "Регистрация"
	const val VERIFY_EMAIL_SCREEN = "Подтверждение почты"
	const val MESSAGES_SCREEN = "Сообщения"
	const val HOME_SCREEN = "Главная"
	const val EDIT_PROFILE_SCREEN = "Редактирование"
	const val ABOUT_APP_SCREEN = "О приложении"
	const val THEMES_SCREEN = "Настройка темы"
	const val SETTINGS_SCREEN = "Настройки"
	const val FAVORITES_SCREEN = "Избранное"

	// Screens routes
	const val LOGIN_SCREEN_ROUTE = "login_screen"
	const val VERIFY_EMAIL_SCREEN_ROUTE = "verify_email_screen"
	const val REGISTER_SCREEN_ROUTE = "register_screen"
	const val PROJECT_SCREEN_ROUTE = "project_screen"
	const val CREATE_SCREEN_ROUTE = "create_screen"
	const val HOME_SCREEN_ROUTE = "home_screen"
	const val MESSAGES_SCREEN_ROUTE = "messages_screen"
	const val PROFILE_SCREEN_ROUTE = "profile_screen"
	const val EDIT_PROFILE_SCREEN_ROUTE = "edit_profile_screen"
	const val ABOUT_APP_SCREEN_ROUTE = "about_app_screen"
	const val THEMES_SCREEN_ROUTE = "themes_screen"
	const val CHAT_SCREEN_ROUTE = "chat_screen"
	const val VIEWING_PHOTO_SCREEN_ROUTE = "viewing_photo_screen"

	// Buttons
	const val BUTTON_LOGIN = "Войти"
	const val BUTTON_LOGIN_TO_ACCOUNT = "Войти в аккаунт"
	const val BUTTON_REGISTER = "Зарегистрироваться"
	const val BUTTON_LOGOUT = "Выйти"
	const val BUTTON_CREATE_PROJECT = "Создать проект"
	const val BUTTON_BUY_PROJECT = "Купить"
	const val BUTTON_JOIN_TEAM_PROJECT = "Вступить в команду"
	const val BUTTON_SEND_APPLICATION = "Отправить заявку"
	const val BUTTON_EDIT_PROFILE = "Редактировать"
	const val BUTTON_SUBSCRIBE_USER = "Подписаться"
	const val BUTTON_UNSUBSCRIBE_USER = "Отписаться"
	const val BUTTON_TO_WRITE_USER = "Написать"
	const val BUTTON_SEND = "Отправить"
	const val BUTTON_CANCEL = "Отмена"
	const val BUTTON_TRY_AGAIN = "Повторить попытку"
	const val BUTTON_COMPLAIN = "Пожаловаться"

	// Titles
	const val TITLE_TYPE_PROJECT = "Выберите тип проекта"
	const val TITLE_PRICE_PROJECT = "Стоимость проекта"
	const val TITLE_STAFF_PROJECT = "Требуемые сотрудники"
	const val TITLE_REQUIRED = "Требуются"
	const val TITLE_IMAGES_PROJECT = "Изображения проекта"
	const val TITLE_NO_DIALOGS = "Пока нет диалогов"
	const val TITLE_NO_PROJECTS = "Пока нет проектов"
	const val TITLE_NO_MESSAGES = "Пока нет сообщений"
	const val TITLE_BUY_PROJECT = "Покупка проекта"
	const val TITLE_JOIN_TEAM_PROJECT = "Вступление в команду"
	const val TITLE_COUNT_PROJECTS = "Проектов"
	const val TITLE_COUNT_FOLLOWS = "Подписчиков"
	const val TITLE_COUNT_FOLLOWINGS = "Подписок"
	const val TITLE_SOON = "скоро"
	const val TITLE_CHANGE_PHOTO = "Загрузить фотографию"
	const val TITLE_DELETE_PHOTO = "Удалить фотографию"
	const val TITLE_OPEN_PHOTO = "Открыть"

	// Types project
	const val TYPE_PROJECT_SALE_TEXT = "Для продажи"
	const val TYPE_PROJECT_DONATE_TEXT = "Сбор донатов"
	const val TYPE_PROJECT_TEAM_TEXT = "Набор команды"

	// Text
	const val TEXT_VERIFY_EMAIL = "Мы отправили письмо со ссылкой для подтверждения вашей почты"
	const val TEXT_SUCCESS_SEND_MESSAGE_BUY_PROJECT = "Заявка на покупку проекта успешно отправлена"
	const val TEXT_SUCCESS_SEND_MESSAGE_JOIN_TEAM_PROJECT = "Заявка на вступление в команду успешно отправлена"
	const val TEXT_BUY_YOURSELF_PROJECT = "Вы не можете купить свой проект"
	const val TEXT_JOIN_TEAM_YOURSELF_PROJECT = "Вы не можете вступить в команду своего проекта"
	const val TEXT_BUY_PROJECT = "Вы уверены, что хотите отправить заявку на покупку проекта?"
	const val TEXT_JOIN_TEAM_PROJECT = "Выберите, на какую позицию вы претендуете"
	const val TEXT_APPLICATION_BUY_PROJECT = "Заявка на покупку проекта"
	const val TEXT_APPLICATION_JOIN_TEAM_PROJECT = "Заявка на вступление в команду"
	const val TEXT_CHOICE_PROJECT_DONATES = "Создание проекта для сбора донатов находится в разработке"
	const val TEXT_ADDED_MAX_COUNT_STAFF = "Добавлено максимальное количество сотрудников"
	const val TEXT_ADD_EXISTING_STAFF = "Такой сотрудник уже добавлен"

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
	const val STAFF_PROJECT_FIELD = "staff"
	const val VIEWS_PROJECT_FIELD = "views"
	const val LIKES_PROJECT_FIELD = "likes"
	const val CREATOR_ID_PROJECT_FIELD = "creatorID"
	const val CREATED_AT_FIELD = "createdAt"
	const val UPDATED_AT_FIELD = "updatedAt"

	const val NAME_USER_FIELD = "name"
	const val PHOTO_USER_FIELD = "photo"
	const val DESCRIPTION_USER_FIELD = "description"
	const val EMAIL_USER_FIELD = "email"
	const val VERIFY_USER_FIELD = "verify"

	// Nodes realtime database
	const val NODE_MESSAGES = "messages"

	// Fields realtime database
	const val TEXT_MESSAGE_FIELD = "text"
	const val FROM_MESSAGE_FIELD = "from"
	const val TYPE_MESSAGE_FIELD = "type"
	const val TIMESTAMP_MESSAGE_FIELD = "timestamp"
	const val PROJECT_ID_MESSAGE_FIELD = "project_id"
	const val PROJECT_TITLE_MESSAGE_FIELD = "project_title"
	const val PROJECT_PRICE_MESSAGE_FIELD = "project_price"
	const val TEAM_STAFF_MESSAGE_FIELD = "team_staff"

	// LIMITATIONS
	const val MAX_NAME_USER_LENGTH = 25
	const val MAX_DESCRIPTION_USER_LENGTH = 70
	const val MAX_TITLE_PROJECT_LENGTH = 120
	const val MAX_DESCRIPTION_PROJECT_LENGTH = 10000
	const val MAX_STAFF_LENGTH = 30
	const val MAX_COUNT_STAFF = 15
	const val MIN_PASSWORD_LENGTH = 6
	const val MAX_IMAGES_PROJECT = 5
	const val LIMIT_MESSAGES_CHAT = 10
	const val LIMIT_PROJECTS_TAPE = 3

	// Other
	const val TAG = "AppLog"
	var USER = UserProfile()
}