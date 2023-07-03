# Energy Monitor :battery: :bar_chart:
Tech Challenge - Graduate/Pós-Graduação

## About :book:

Welcome to Energy Monitor! An innovative project that combines the powerful technologies of:

*  Java 17;
* Maven; 
* Spring Boot;
* Spring Validation;
* Lombok;

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
        "address": "Rua 2",
        "number": 2,
        "neighborhood": "Ipanema",
        "city": "Rio de Janeiro",
        "state": "Rio de Janeiro"
      },
      {
        "address": "Rua 3",
        "number": 3,
        "neighborhood": "Camanducaia",
        "city": "Belo Horizonte",
        "state": "Minas Gerais"
      },
      {
        "address": "Rua 1",
        "number": 1,
        "neighborhood": "Vila Olímpia",
        "city": "São Paulo",
        "state": "São Paulo"
      }
    ]
    ```



### FindBy City

* Request: `GET -> {baseUrl}/locations/{city}`

* Input: `GET -> {baseUrl}/locations`/ **`são paulo`**

* Output:

  * ```json
    // HTTP 200
    {
      "address": "Rua 1",
      "number": 1,
      "neighborhood": "Vila Olímpia",
      "city": "São Paulo",
      "state": "São Paulo"
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
    // HTTP 200
    {
      "address": "Rua 4",
      "number": 1,
      "neighborhood": "Vila Olímpia",
      "city": "São Paulo",
      "state": "São Paulo"
    }
    ```



### Delete

* Request: `DELETE -> {baseUrl}/locations`

* Input:

  * ```json
    {
        "address": "Rua 1",
        "number": 1,
        "neighborhood": "Vila Olímpia",
        "city": "São Paulo",
        "state": "SP"
     }
    ```

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
        "name": "Vlad",
        "birth": "1986-03-08",
        "gender": "MALE",
        "relative": "SON"
      },
      {
        "name": "Joao",
        "birth": "1996-03-08",
        "gender": "MALE",
        "relative": "FATHER"
      },
      {
        "name": "Maria",
        "birth": "1996-03-08",
        "gender": "FEMALE",
        "relative": "MOTHER"
      }
    ]
    ```



### FindBy Name

* Request: `GET -> {baseUrl}/users/{name}`

* Input: `GET -> {baseUrl}/users`/ **`joão`**

* Output:

  * ```json
    // HTTP 200
    {
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
    // HTTP 200
    {
      "name": "Joao2",
      "birth": "1996-03-08",
      "gender": "MALE",
      "relative": "SON"
    }
    ```



### Delete

* Request: `DELETE -> {baseUrl}/users`

* Input:

  * ```json
    {
      "name": "Joao",
      "birth": "1996-03-08",
      "gender": "MALE",
      "relative": "FATHER"
    }
    ```

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
        "name": "XBOX",
        "model": "Series X",
        "watts": 100
      },
      {
        "name": "Playstation",
        "model": "Verison 5",
        "watts": 100
      },
      {
        "name": "TV",
        "model": "Samsung QN85A",
        "watts": 100
      }
    ]
    ```



### FindBy Name

* Request: `GET -> {baseUrl}/appliances/{applianceName}`

* Input: `GET -> {baseUrl}/appliances`/ **`xbox`**

* Output:

  * ```json
    // HTTP 200
    {
      "name": "XBOX",
      "model": "Series X",
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
    // HTTP 200
    {
      "name": "XBOX",
      "model": "Series ONE",
      "watts": 100
    }
    ```



### Delete

* Request: `DELETE -> {baseUrl}/appliances`

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
    // 200
    Appliance was deleted
    ```

