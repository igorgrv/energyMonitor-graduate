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

* Request: `GET -> {baseUrl}/location/findAll`

* Input: `N/A`

* Output:

  * ```json
    // HTTP 200
    [
      {
        "address": "Rua 3",
        "number": 3,
        "neighborhood": "Barra Funda",
        "city": "Belo Horizonte",
        "state": "Minas Gerais"
      },
      {
        "address": "Rua 1",
        "number": 1,
        "neighborhood": "Vila Olímpia",
        "city": "São Paulo",
        "state": "São Paulo"
      },
      {
        "address": "Rua 2",
        "number": 2,
        "neighborhood": "Santo Amaro",
        "city": "Rio de Janeiro",
        "state": "Rio de Janeiro"
      }
    ]
    ```



### FindBy City

* Request: `GET -> {baseUrl}/location/findBy/city/{city}`

* Input: `GET -> {baseUrl}/location/findBy/city/` **`são paulo`**

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

* Request: `POST -> {baseUrl}/location/create`

* Input:

  * ```json
    {
      "address": "Rua 4",
      "number": 1,
      "neighborhood": "Vila Olímpia",
      "city": "São Paulo",
      "state": "São Paulo"
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

* Request: `DELETE -> {baseUrl}/location/delete`

* Input:

  * ```json
    {
        "address": "Rua 1",
        "number": 1,
        "neighborhood": "Vila Olímpia",
        "city": "São Paulo",
        "state": "São Paulo"
     }
    ```

* Output:

  * ```json
    // 200
    Location was deleted
    ```



## User :busts_in_silhouette:

### Find All Locations

* Request: `GET -> {baseUrl}/user/findAll`

* Input: `N/A`

* Output:

  * ```json
    // HTTP 200
    [
      {
        "name": "Vlad",
        "birth": "1986-03-08",
        "gender": "MALE"
      },
      {
        "name": "Maria",
        "birth": "1996-03-08",
        "gender": "FEMALE"
      },
      {
        "name": "Joao",
        "birth": "1996-03-08",
        "gender": "MALE"
      }
    ]
    ```



### FindBy Name

* Request: `GET -> {baseUrl}/user/findBy/name/{name}`

* Input: `GET -> {baseUrl}/user/findBy/name/` **`joão`**

* Output:

  * ```json
    // HTTP 200
    {
      "name": "Joao",
      "birth": "1996-03-08",
      "gender": "MALE"
    }
    ```



### Create

* Request: `POST -> {baseUrl}/user/create`

* Input:

  * ```json
    {
      "name": "Joao2",
      "birth": "1996-03-08",
      "gender": "MALE"
    }
    ```

* Output: 

  * ```json
    // HTTP 200
    {
      "name": "Joao2",
      "birth": "1996-03-08",
      "gender": "MALE"
    }
    ```



### Delete

* Request: `DELETE -> {baseUrl}/user/delete`

* Input:

  * ```json
    {
      "name": "Joao",
      "birth": "1996-03-08",
      "gender": "MALE"
    }
    ```

* Output:

  * ```json
    // 200
    User was deleted
    ```



## Appliance :tv:

### Find All Locations

* Request: `GET -> {baseUrl}/appliance/findAll`

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

* Request: `GET -> {baseUrl}/appliance/findBy/name/{applianceName}`

* Input: `GET -> {baseUrl}/appliance/findBy/name/` **`xbox`**

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

* Request: `POST -> {baseUrl}/appliance/create`

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

* Request: `DELETE -> {baseUrl}/appliance/delete`

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

