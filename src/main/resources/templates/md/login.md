#### THE-DIONISIO-API
##### resource : /login 
##### methods : post
#
>This resource support the login for all entitys
##### json structure:
#
```json
{
  "_id": "58d1b31255eb441fee7d414a",
  "name": "alan@turing.com",
  "password": "you shall not pass",
  "token": "5112fe4cc58d1b31255eb441fee7d414a97ba26920a1d25f929ba0c258d1b31255eb441fee7d414a3055241e",
  "entity": "person"
}
```
##### Example

##### [ post ]
#
The post method has required attributes (name, password and entity).
###### request:
#
```json
{
  "name": "alan@turing.com",
  "password": "turing",
  "entity": "person"
}
```
###### response: [ loged ]
#
```json
{
  "_id": "58d1b31255eb441fee7d414a",
  "name": "alan@turing.com",
  "password": "you shall not pass",
  "token": "5112fe4cc58d1b31255eb441fee7d414a97ba26920a1d25f929ba0c258d1b31255eb441fee7d414a3055241e",
  "entity": "person"
}
```
###### response: [ not loged ]
#
```json
{
  "_id": "58d1b31255eb441fee7d414a",
  "name": "alan@turing.com",
  "password": "you shall not pass",
  "token": "fail",
  "entity": "person"
}
```
##### Main possible fail response
#
###### NOT_ACCEPTABLE:
#
```json
{
  "status": "NOT_ACCEPTABLE",
  "description": "required information not declared",
  "additional": password_shodow
}
```
