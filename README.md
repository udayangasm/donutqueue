# Donut Priority Queue

## Description

This application implements following operations related to order processing.

* Adding orders. 
* Checking order status. 
* List all orders. 
* List next delivery orders. 
* Cancel existing orders.


## Build and Run

gradle clean build

java -jar build/libs/donut-queue-1.0.0.jar

## Sample Data to test the application

once you run the application following curl commands can be executed to test the process

###Adding orders
* curl --location --request POST 'http://localhost:8888/order' --header 'Content-Type: application/json' --data-raw '{"clientId":1,"donutQty": 10}'
* curl --location --request POST 'http://localhost:8888/order' --header 'Content-Type: application/json' --data-raw '{"clientId":2,"donutQty": 20}'
* curl --location --request POST 'http://localhost:8888/order' --header 'Content-Type: application/json' --data-raw '{"clientId":1050,"donutQty": 10}'
* curl --location --request POST 'http://localhost:8888/order' --header 'Content-Type: application/json' --data-raw '{"clientId":1051,"donutQty": 30}'
* curl --location --request POST 'http://localhost:8888/order' --header 'Content-Type: application/json' --data-raw '{"clientId":1052,"donutQty": 15}'
* curl --location --request POST 'http://localhost:8888/order' --header 'Content-Type: application/json' --data-raw '{"clientId":1053,"donutQty": 25}'
* curl --location --request POST 'http://localhost:8888/order' --header 'Content-Type: application/json' --data-raw '{"clientId":55,"donutQty": 10}'
* curl --location --request POST 'http://localhost:8888/order' --header 'Content-Type: application/json' --data-raw '{"clientId":56,"donutQty": 15}'

###List orders
curl --location --request GET 'http://localhost:8888/order'

###Check Order Status
curl --location --request GET 'http://localhost:8888/order/1

###List Next Delivery
curl --location --request GET 'http://localhost:8888/order/delivery'

###Cancel Order
curl --location --request DELETE 'http://localhost:8888/order/1'