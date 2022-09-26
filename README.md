# Acme Bank
A coding exercise to build an account-manager for the bank

## Running the Project

```./mvnw spring-boot:run```

## Get Account Balance

```http://localhost:8080/accounts/12345678```

## Perform Account Transfer
```
http://localhost:8080/transfer
Request Body:
{
    "from":12345678,
    "to": 88888888,
    "amount": 100,
    "currency": "HKD"
}
```

## Additional Implementation
 - Transaction table
 - DB locking when performing a transfer
 - User Authentication
 - Better code organisation with inheritance
 - Some more validation
 - Dockerization

