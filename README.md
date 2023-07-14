# Backend сервиса по аренде вещей среди студентов "StudProkat"




## Функционал
Реализована работа с пользователями и личный счёт с пополнением по купонам.
Работает создание и поиск объявлений.
Так же реализована, но ещё не протестированна возможность менять статус объявлений, смотреть историю.

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
Всё стандартно, скрипт для БД - ```scripts/cassandra/init.cql``` \
Так же приложение развернуто в Yandex Cloud по адресу: ```http://158.160.107.164:8080```\
Доступна swagger документация: http://158.160.107.164:9090/actuator/swagger-ui/index.html 

```studprokat.postman_collection.json``` - коллекция с запросами в Postman \
```studprokat.postman_environment.json``` - окружение для теста на удалённом сервере