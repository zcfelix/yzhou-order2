# Tasks  (***264min***    ***R: 380min***)

## /products  (***E: 58min***   ***R: 80min***)

- post() （***E: 40min***    ***R: 63min***)

  1. return 201
     - return 201 when create product   (***E: 5min***   ***R: 5min*** )
     - return uri when create product     (***E: 5min***   ***R: 5min***)
     - save and find product in product repository   (***E: 20min***   ***R: 34min***)
       - ProductRepository.createProduct()
       - ProductMapper.save()
       - ProductMapper.findById()
     - return 201 when create product with specified parameters (***E: 3min***   ***R: 3min***)
  2. return 400  
     - return 400 when name, description or price is null (***E: 10min***   ***R: 19min***)
       - exception handing

- get()  (***E: 18min***   ***R: 17min***)

  1. return 200
     - return 200 when get products   (***E: 3min***   ***R: 2min***)
     - get products in product repository  (***E: 10min***   ***R: 6min***)
       - ProductRepository.getAllProducts()
       - ProductMapper.getAll()
     - return products list when get products (***E: 5min***   ***R: 9min***)
       - override toJson(), toRefJson()

  ## /products/{productId}  (***E: 14min***   ***R: 25min***)

- get() 

  1. return 200
     - return 200 when get product （***E: 3min***   ***R: 5min***)
     - find product in product repository  (***E: 8min***   ***R: 15min***)
       - ProductRepository.findProductById()
       - ProductMapper.findProductById()
       - return product details when get product
  2. return 404  
     - return 404 when product not found with the given id   (***E: 3min***   ***R: 5min***)

## /users  (***E: 31min***    ***R: 46min***)

- post()
  1. return 201 
     - return 201 when create user  （***E: 3min***   ***R: 3min***)
     - return uri when create user      (***E: 5min***   ***R: 5min***)
     - save and find user in user repository  (***E: 15min***   ***R: 30min***)
       - UserRepository.createUser()
       - UserMapper.save()
       - UserMapper.findById() 
     - return 201 when create user with specified parameters  (***E: 3min***   ***R: 4min***)
  2. return 400   (***E: 5min***   ***R: 4min***)
     - return 400 when name is null 
       - exception handing

## /users/{id}  (***E: 16min***   ***R: 19min***)

- get()
  1. return 200
     - return 200 when get user (***E: 3min***    ***R: 3min***)
     - find user in user repository (***E: 5min***   ***R: 6min***)
       - UserRepository.findUserById()
       - UserMapper.findUserById()
     - return details when get find user by id (***E: 5min***   ***R: 6min***)
  2. return 404 
     - return 404 when user not found with the given id (***E: 3min***   ***R: 4min***)

## /users/{id}/orders  (***E: 76min***    ***R: 121min***)

- post()  (***E: 58min***   ***R: 104min***)
  1. return 201
     - return 201 when create order  (***E: 5min***   ***R: 4min***)
     - return uri when create order    (***E: 15min***   ***R: 15min***)
     - save and find order in user       (***E: 30min***   ***R: 30min***)
       - User.createOrder()
       - OrderMapper.save()
       - OrderMapper.findById()
     - return 201 when create order with specified parameters  (***E: 3min***    ***R: 50min***)
  2. return 400  
     - return 400 when name, address, phone, or order items list is null (***E: 5min***   ***R: 5min***)
- get()  (***E: 18min***   ***R: 17min***)
  1. return 200
     - return 200 when get orders  (***E: 3min***    ***R: 2min***)
     - get orders in user repository (***E: 10min***    ***R: 10min***)
       - User.getAllOrders()
       - OrderMapper.getAll()
     - return orders list when get orders  (***E: 5min***    ***R: 5min***)
       - Override toJson(), toRefJson()

## users/{id}/orders/{orderId}  (***E: 21min***    ***R: 13min***)

- get()  
  1. return 200
     - return 200 when get order  (***E: 3min***   ***R: 2min***)
     - find order by orderId in user  (***E: 5min***    ***R: 5min***)
       - User.findOrderbyId()
     - return detail when get order  (***E: 10min***    ***R: 5min***)
  2. return 404 when order not found with given id (***E: 3min***   ***R: 1min***)

## users/{id}/orders/{orderId}/payment (***E: 45min***   ***R: 76min***)

- post()  (***E: 26min***   ***R: 48min***)
  1. return 201  
     - return 201 when create payment  (***E: 5min***   ***R: 10min***)
     - save payment in order  (***E: 15min***   ***R: 31min***)
       - Order.createPayment()  
       - PaymentMapper.save()
       - PaymentMapper.findById()
     - return 201 when create payment with specified parameters (***E: 3min***   ***R: 3min***)
  2. return 400
     - return 400 when payType or amount is null  (***E: 3min***    ***R: 4min***)
- get()  (***E: 19min***   ***R: 28min***)
  1. return 200
     - return 200 when get payment  （***E: 3min***   ***R: 2min***)
     - find payment in order (***E: 5min***   ***R: 3min***)
       - Order.findPayment()
     - return details when get payment (***E: 8min***   ***R: 20min***)
  2. return 404
     - return 404 when no payment found (***E: 3min***    ***R: 3min***)



