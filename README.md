# HackApp

#### **Реализованная функциональность**
* PUSH уведомления
* Авторизация/регистрация
* Карта доступных для бронирования мест
* Лавка
* Список событий
* Список мест
* Список новостей
* Профиль
* Друзья

#### **Основной стек технологий:**
* Android SDK
* Hilt (Dagger2)
* Firebase Cloud Messaging (FCM)
* Cicerone
* Retrofit
* Coil

## Среда запуска
* Android смартфон/эмулятор

## Установка & Запуск
После запуска сервера необходимо изменить данные для подключения к нему в приложении.
Чтобы это сделать переходим по пути core/network/di/NetworkModule в директории с kotlin кодом проекта
и изменяем BASE_URL и BASE_AUTH_URL на адреса запущенных апи

Далее можно собирать и запускать проект из Android Studio:
* Открываем проект в Android Studio
* Дожидаемся окончания загрузки зависимостей
* Собираем проект
* Запускаем приложение на устройстве/эмуляторе
