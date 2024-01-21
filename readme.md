# Веб-Приложение

Данное приложение предоставляет один эндпоинт для подсчета количества символов в строке, которую передал пользователь.

## Требования

- Java 8+
- Spring boot 2+

### Сборка и запуск приложения

1. Клонировать данный репозиторий на рабочий компьютер.
2. Перейти в каталог проекта.
3. Собрать проект.
4. Запустить приложение.

### Эндпоинт

- **Подсчет символов в переданной строке**

  Данный эндпоинт считает количество символов в строке, которую указал пользователь.

    - **URL**: `http://localhost:8080/count`
    - **Метод**: GET
    - **Пример запроса**:

      ```http
      GET http://localhost:8080/count?str=aaaaabcccc
      ```

    - **Пример ответа**:

      ```string
      "a": 5, "c": 4, "b": 1
      ```

    - Если переданная пользователем строка будет пустой, то приложение выведет ошибку.