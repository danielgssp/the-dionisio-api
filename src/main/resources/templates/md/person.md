#### THE-DIONISIO-API
##### resource : /person 
##### methods : get, post, put e delete
#
>This resource support the crud in collection person
##### json structure:
#
```json
{
    "_id": "58d1b31255eb441fee7d414a",
    "name": "Alan Turing",
    "name": "alan@turing.com",
    "password": "you shall not pass",
    "birth": [1994,11,5],
    "cpf": "418.035.898.09",
    "sex": "M",
    "genres": ["rock","inde"],
    "card": [
                {
                  "flag": "VISA",
                  "validity": "03/18",
                  "codSafe": "4545",
                  "cardNumber": "6277.8015.3720.7676",
                  "owner": "ALAN TURING",
                  "isActive": true
                }
            ],
    "isActive": true
  }
```
##### Examples
#
##### [ get ]
#
The get method can be used in two ways.
>responds a list of person
###### request: /person
###### response:
#
```json
{
  "people": [
                {
                "_id": "58d1b31255eb441fee7d414a",
                "name": "Alan Turing",
                "name": "alan@turing.com",
                "password": "you shall not pass",
                "birth": [1912,6,23],
                "cpf": "000.000.000.00",
                "sex": "M",
                "genres": ["rock","inde"],
                "card": [
                            {
                              "flag": "VISA",
                              "validity": "03/18",
                              "codSafe": "2806",
                              "cardNumber": "0000.0000.0000.0000",
                              "owner": "ALAN TURING",
                              "isActive": true
                            }
                        ],
                "isActive": true
              },
              {
                "_id": "66d1b31255eb441fee7d415z",
                "name": "Dennis Ritchie",
                "name": "dennis@ritchie.com",
                "password": "you shall not pass",
                "birth": [1941,9,9],
                "cpf": "000.000.000.00",
                "sex": "M",
                "genres": ["rap","pop"],
                "card": [
                            {
                              "flag": "MASTER",
                              "validity": "03/18",
                              "codSafe": "4212",
                              "cardNumber": "0000.0000.0000.0000",
                              "owner": "DENNIS RITCHIE",
                              "isActive": true
                            }
                        ],
                "isActive": true
              }
            ]
}
```
> /pelple/ + [ id ] => responds one people to that id
###### request: /person/58d1b31255eb441fee7d414
###### response:
#
```json
[
    {
    "_id": "58d1b31255eb441fee7d414a",
    "name": "Alan Turing",
    "name": "alan@turing.com",
    "password": "you shall not pass",
    "birth": [1912,6,23],
    "cpf": "000.000.000.00",
    "sex": "M",
    "genres": ["rock","inde"],
    "card": [
                {
                  "flag": "VISA",
                  "validity": "03/18",
                  "codSafe": "2806",
                  "cardNumber": "0000.0000.0000.0000",
                  "owner": "ALAN TURING",
                  "isActive": true
                }
            ],
    "isActive": true
  }
 ]
```
##### [ post ]
#
The post method has required attributes (name, name, password and genres) the rest are optional.
###### request:
#
```json
{
    "name": "Alan Turing",
    "name": "alan@turing.com",
    "password": password_shodow,
    "birth": [1912,6,23],
    "genres": ["rock","inde"]
}
```
###### response:
#
```json
{
  "status": "OK",
  "description": "register created successfully",
  "additional": "reference _id = 58d6f24c55eb443598d12abf"
}
```
##### [ put ]
#
For update you must inform the id and the attributes with your values
###### request:
#
```json
{
     "_id": "58d1b31255eb441fee7d414a",
     "genres": ["metal","punk"]
}
```
###### reponse:
#
```json
{
  "status": "OK",
  "description": "register updated successfully",
  "additional": "reference _id = 58d6f24c55eb443598d12abf"
}
```
##### [ delete ]
#
For delete you must inform the id
###### request:
#
```json
{
     "_id": "58d6f24c55eb443598d12abf"
}
```

###### response:
#
```json
{
  "status": "OK",
  "description": "register deleted successfully",
  "additional": "reference _id = 58d6f24c55eb443598d12abf"
}
```
#
##### Possible fail response
#
###### NOT_FOUND:
#
```json
{
  "status": "NOT_FOUND",
  "description": "register not found",
  "additional": "no register were found that satisfies the condition"
}
```
###### NOT_ACCEPTABLE:
#
```json
{
  "status": "NOT_ACCEPTABLE",
  "description": "register already exists",
  "additional": "name <alan@turing.com> already registered in the system"
}
```
###### NOT_ACCEPTABLE:
#
```json
{
  "status": "NOT_ACCEPTABLE",
  "description": "required information not declared",
  "additional": "field <_id> is required"
}
```
###### NOT_ACCEPTABLE:
#
```json
{
  "status": "NOT_ACCEPTABLE",
  "description": "required information not declared",
  "additional": password_shodow
}
```
