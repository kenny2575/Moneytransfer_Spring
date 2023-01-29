# Курсовая работа "Перевод денег с карты на карту"
## Описание программы
### Входные данные
Клиент посылает POST запросы на следующие URL:
- /transfer - в теле передается информация о транзакции
- /confirmOperation - в теле передается код подверждения с привязкой к транзакции
### Контроллеры
`TransferController`
Точка входа и маршрутизации запросов.
### Сервсы
`TransferService`
- выполняет обработку запросов, пришедших на `/transfer`
- находит карты в `CardsRepository`
- выполняет валидацию карты на совпадение срока и CVV кода
- проверяет остаток и холдирует средства
- прокидывает ошибки BAD_REQUEST и INTERNAL_SERVER_ERROR
- записывает данные в `TransactionRepository`
- создает экземпряр `OperationId` и возвращает id операции 

`checkConfirmOperation`
- выполняет обработку запросов, пришедших на  `/confirmOperation`
- выполняет валидацию данных с `TransactionRepository`
- прокидывает ошибки BAD_REQUEST и INTERNAL_SERVER_ERROR
- если при выполнении транзакции возникли ошибки выполняется откат
- 
### Уровень репозитория
`CardsRepository`
- `ConcurrentHashMap` с ключом `String` и значением `Card`
- ключом служит PAN карты
  В хранилище создаются 2 карты для теста приложения:
- number:4276441544164417, CVV: 675, ValidTill: 03/23, Balance: 100_000_00
- number:4276441544144413, CVV: 879, ValidTill: 05/23, Balance: 100_000_00

`TransactionRepository`
- `ConcurrentHashMap` с ключом `String` и значением`TransactionInfo`
- ключом служит `operationId`

### Логгер
- Логирует работу приложения
- Время выводится по UTC+3