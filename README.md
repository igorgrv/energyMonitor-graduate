# Energy Monitor :battery: :bar_chart:
Tech Challenge - Graduate/Pós-Graduação

GitHub Repository: https://github.com/igorgrv/energyMonitor-graduate

## About :book:

Welcome to Energy Monitor! An innovative project that combines the powerful technologies of:

* Java 17;
* Maven; 
* Spring Boot;
* Spring Validation;
* Lombok;



## Postman collection

[Pos_tech_-_energyMonitor-graduate.postman_collection.json](Pos_tech_-_energyMonitor-graduate.postman_collection.json) 

# Inputs/outputs :coffee:

## Challenges

* Finding best practices for a restfull api 
* Solution: https://www.alura.com.br/artigos/rest-principios-e-boas-praticas

# FASE 1 - inputs/outputs :coffee:

* BaseUrl: http://localhost:8080

## Location/Address :house:

### Find All Locations

* Request: `GET -> {baseUrl}/locations`

* Input: `N/A`

* Output:

  * ```json
    // HTTP 200
    [
    {
      "idLocation": 1,
      "address": "Rua 1",
      "number": 1,
      "neighborhood": "Vila Olímpia",
      "city": "São Paulo",
      "state": "SP"
    },
    {
      "idLocation": 2,
      "address": "Rua 1.1",
      "number": 1,
      "neighborhood": "Pirituba",
      "city": "São Paulo",
      "state": "SP"
    },
    {
      "idLocation": 3,
      "address": "Rua 2",
      "number": 2,
      "neighborhood": "Ipanema",
      "city": "Rio de Janeiro",
      "state": "RJ"
    },
    {
      "idLocation": 4,
      "address": "Rua 3",
      "number": 3,
      "neighborhood": "Camanducaia",
      "city": "Belo Horizonte",
      "state": "MG"
    }
    ]
    ```



### FindBy Id

* Request: `GET -> {baseUrl}/locations/{id_location}`

* Input: `GET -> {baseUrl}/locations`/ 1

* Output:

  * ```json
    // HTTP 200
    {
      "idLocation": 1,
      "address": "Rua 1",
      "number": 1,
      "neighborhood": "Vila Olímpia",
      "city": "São Paulo",
      "state": "SP"
    }
    ```



### Create

* Request: `POST -> {baseUrl}/locations`

* Input:

  * ```json
    {
      "address": "Rua 4",
      "number": 1,
      "neighborhood": "Vila Olímpia",
      "city": "São Paulo",
      "state": "SP"
    }
    ```

* Output: 

  * ```json
    // HTTP 201
    {
      "idLocation": 1151439646,
      "address": "Rua 4",
      "number": 1,
      "neighborhood": "Vila Olímpia",
      "city": "São Paulo",
      "state": "SP"
    }
    ```



### UpdateBy Id

* Request: `PUT -> {baseUrl}/locations/{id_location}`

* Input: `PUT -> {baseUrl}/locations`/ 3

  * ```json
    {
      "address": "Rua 10",
      "number": 1,
      "neighborhood": "Vila Olímpia",
      "city": "São Paulo",
      "state": "SP"
    }
    ```

* Output:

  * ```json
    // HTTP 200
    {
      "address": "Rua 10",
      "number": 1,
      "neighborhood": "Vila Olímpia",
      "city": "São Paulo",
      "state": "SP"
    }
    ```



### Delete

* Request: `DELETE -> {baseUrl}/locations/{id_location}`

* Input: `DELETE -> {baseUrl}/locations`/ 3

* Output:

  * ```json
    // 200
    Location was deleted
    ```



## User :busts_in_silhouette:

### Find All Users

* Request: `GET -> {baseUrl}/users`

* Input: `N/A`

* Output:

  * ```json
    // HTTP 200
    [

      {
        "idUser": 1,
        "name": "Joao",
        "birth": "1996-03-08",
        "gender": "MALE",
        "relative": "FATHER"
      },
      {
        "idUser": 2,
        "name": "Maria",
        "birth": "1996-03-08",
        "gender": "FEMALE",
        "relative": "MOTHER"
      },
      {
        "idUser": 3,
        "name": "Vlad",
        "birth": "1986-03-08",
        "gender": "MALE",
        "relative": "SON"
      }
    ]
    ```



### FindBy Id

* Request: `GET -> {baseUrl}/users/{id_user}`

* Input: `GET -> {baseUrl}/users`/ 1

* Output:

  * ```json
    // HTTP 200
    {
      "idUser": 1,
      "name": "Joao",
      "birth": "1996-03-08",
      "gender": "MALE",
      "relative": "FATHER"
    }
    ```



### Create

* Request: `POST -> {baseUrl}/users`

* Input:

  * ```json
    {
      "name": "Joao2",
      "birth": "1996-03-08",
      "gender": "MALE",
      "relative": "SON"
    }
    ```

* Output: 

  * ```json
    // HTTP 201
    {
      "idUser": 1828481121,
      "name": "Joao2",
      "birth": "1996-03-08",
      "gender": "MALE",
      "relative": "SON"
    }
    ```



### UpdateBy Id

* Request: `PUT -> {baseUrl}/users/{id_user}`

* Input: `PUT -> {baseUrl}/users`/ 3

* ```json
    {
      "name": "Gustavo",
      "birth": "1992-02-02",
      "gender": "MALE",
      "relative": "SON"
    }
  ```

* Output:

* ```json
  // HTTP 200
    {
      "name": "Gustavo",
      "birth": "1992-02-02",
      "gender": "MALE",
      "relative": "SON"
    }
  ```


### Delete

* Request: `DELETE -> {baseUrl}/users/{id_user}`

* Input: `DELETE -> {baseUrl}/users`/ 3

* Output:

  * ```json
    // 200
    User was deleted
    ```



## Appliance :tv:

### Find All Appliances

* Request: `GET -> {baseUrl}/appliances`

* Input: `N/A`

* Output:

  * ```json
    // HTTP 200
    [
    {
      "idAppliance": 1,
      "name": "TV",
      "model": "Samsung QN85A",
      "watts": 100
    },
    {
      "idAppliance": 2,
      "name": "XBOX",
      "model": "Series X",
      "watts": 100
    },
    {
      "idAppliance": 3,
      "name": "Playstation",
      "model": "Verison 5",
      "watts": 100
    }
    ]
    ```



### FindBy Id

* Request: `GET -> {baseUrl}/appliances/{id_appliance}`

* Input: `GET -> {baseUrl}/appliances`/ 3

* Output:

  * ```json
    // HTTP 200
    {
      "idAppliance": 3,
      "name": "Playstation",
      "model": "Verison 5",
      "watts": 100
    }
    ```



### Create

* Request: `POST -> {baseUrl}/appliances`

* Input:

  * ```json
    {
      "name": "XBOX",
      "model": "Series ONE",
      "watts": 100
    }
    ```

* Output: 

  * ```json
    // HTTP 201
    {
      "idAppliance": 340550821,
      "name": "XBOX",
      "model": "Series ONE",
      "watts": 100
    }
    ```



### UpdateBy Id

* Request: `PUT -> {baseUrl}/appliances/{id_appliance}`

* Input: `PUT -> {baseUrl}/appliances`/ 3

* ```json
    {
      "name": "XBOX",
      "model": "Series X",
      "watts": 100
    }
  ```

* Output:

* ```json
  // HTTP 200
    {
      "name": "XBOX",
      "model": "Series X",
      "watts": 100
    }
  ```


### Delete

* Request: `DELETE -> {baseUrl}/appliances/{id_appliance}`

* Input: `PUT -> {baseUrl}/appliances`/ 3

* Output:

  * ```json
    // 200
    Appliance was deleted
    ```

