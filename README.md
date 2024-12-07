# Grocery App

This project is a Grocery Booking API designed to allow two main roles — Admin and User — to perform various operations related to grocery items. The API provides endpoints for adding, updating, removing, and viewing grocery items, as well as for managing inventory levels. Users can also view available grocery items and place orders for multiple items at once.

## Features

- **Admin Features**:
    - Add New Grocery Items: Admins can add new grocery items to the system by providing details like item name, price, and stock.
    - View Existing Grocery Items: Admins can view all existing grocery items with their details.
    - Remove Grocery Items: Admins can delete grocery items from the system.
    - Manage Inventory Levels: Admins can adjust the stock quantity for each grocery item to reflect the current inventory.

- **User Features**: 
  - View Available Grocery Items: Users can view a list of all available grocery items, including their details like name, price, and stock.
  - Place Orders: Users can place an order by booking multiple grocery items in a single transaction. The order will include the selected items and their quantities

## Technologies Used
- Java 17
- Spring-Boot 3
- Spring Data JPA
- Hibernate
- Spring Rest API
- Lombok
- Gradle
- Database: MySQL
- Containerization: Docker

## Setup Instructions (Using Docker)
1. First, clone the repository to your local machine
2.  Build Docker image  
```bash 
docker-compose build
```
3. Run Docker compose file
```bash
docker-compose up
```

### Running without Docker
- Prerequisites
  - Java 17
  - MySQL
  - Spring-Boot
  - Gradle
  - IDE
1. Open project in IDE
2. Build Gradle using `gradle build`
3. Start your spring-Boot Application

## API Endpoints
**Note:** Postman collection file is already present with FileName: `Api_endpoint.postman_collection` for usage with predefined storage of variable itemID and Pre-existing saved requests in postman collection
### Admin Endpoints
#### 1. Add New Grocery Item
- **Endpoint**: `/api/v1/admin/item`.
- **Description**: Adds a new grocery item to the system.
- **Response**:
  - 200 OK Item added.
  - 400 Bad Request Details Missing.
#### 2. Get Grocery Items
- **Endpoint**: `/api/v1/admin/item/{{itemId}}`. (Get Request)
- **Description**: Get Item with Provided ItemID.
- **Response**:
  - 200 OK Item Found return details.
  - 404 Not Found requested item Not Found. (Global Exception Handling)

#### 3. Remove grocery items
- **Endpoint**: `/api/v1/admin/item/{{itemId}}`. (Delete request)
- **Description**: Remove Item with Provided ItemID.
- **Response**:
  - 200 OK Item Deleted.
  - 404 Bad Request
#### 4. Update details of existing grocery items
- **Endpoint**: `/api/v1/admin/item/{{itemId}}`. (Update request)
- **Description**: Update Item with Provided ItemID.
- **Response**:
  - 200 OK Item Updated.
  - 404 Bad Request
#### 4. Manage inventory levels of grocery items
- **Endpoint**: `/api/v1/admin/item/{{itemId}}/update-stock`. (Patch request)
- **Description**: Update Item Details for provided ItemID.
- **Response**:
  - 200 OK Item Updated.
  - 404 Bad Request
  
### User Endpoints
#### 1. Get All Grocery Items
- **Endpoint**: `/api/v1/user/item?pageNumber=0&pageSize=1`. (Get Request)
- **Description**: Get All Items.
- **Note**: Page is been implemented here this can be used without pages too using `/api/v1/user/item`
- **Response**:
  - 200 OK Item Found return details.
  - 500 Internal Server Error
#### 2. Order multiple grocery items in a single order
- **Endpoint**: `/api/v1/user/order`. (Post Request)
- **Description**: Order single or multiple items.
- **Response**:
  - 200 OK order placed.
  - 400 Bad Request (Global Exception) Message according Use case
  - 500 Internal server Error
  
### Project Structure
````
src
└── main
├── java
│   └── com
│       └── qp
│           └── groceryapi
│               ├── GroceryApiApplication.java
│               ├── config
│               │   └── Constants.java
│               ├── controller
│               │   ├── AdminController.java
│               │   └── UserController.java
│               ├── dto
│               │   ├── CreateOrderItem.java
│               │   ├── CreateOrderRequest.java
│               │   └── UpdateStockRequest.java
│               ├── exception
│               │   ├── BadRequestException.java
│               │   ├── ErrorResponse.java
│               │   ├── GlobalExceptionHandler.java
│               │   ├── ItemNotFoundException.java
│               │   └── ItemOutOfStockException.java
│               ├── model
│               │   ├── Item.java
│               │   ├── Order.java
│               │   └── OrderItem.java
│               ├── repository
│               │   ├── ItemRepository.java
│               │   ├── OrderItemRepository.java
│               │   └── OrderRepository.java
│               └── service
│                   ├── ItemService.java
│                   └── OrderService.java
└── resources
├── application.yml
├── static
└── templates
  ````