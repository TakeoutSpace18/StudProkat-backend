# Backend сервиса по аренде вещей среди студентов "StudProkat"
Реализован в рамках летнего ШИФТ Интенсива 2023, с последующими доработками.

[![Build and Deploy](https://github.com/TakeoutSpace18/StudProkat-backend/actions/workflows/build-and-deploy.yml/badge.svg)](https://github.com/TakeoutSpace18/StudProkat-backend/actions/workflows/build-and-deploy.yml)
## Функционал
- регистрация, авторизация пользователей и личный счёт с пополнением по купонам.
- создание и поиск по категориям объявлений на аренду
- изменение статуса объявлений, добавление в историю

Существует два уровня доступа: user и admin.
Админ имеет право удалять любых пользователей и любые объявления, выпускать купоны, изменять баланс любого кошелька.

По умолчанию при запуске приложения создается учетная запись админа с почтой admin@admin.com и паролем admin.

Endpoint для авторизации: POST renting/login
```
{
	"email": "admin@admin.com",
	"password": "admin"
}
```
## Запуск
```docker-compose up -d``` - запуск БД Cassandra \
```cqlsh -f ./scripts/cassandra/init.cql``` - создание схемы БД \
```./gradlew bootRun``` - запуск приложения

## Документация
Доступна swagger документация: http://localhost:9090/actuator/swagger-ui/index.html 

```studprokat.postman_collection.json``` - коллекция с запросами в Postman