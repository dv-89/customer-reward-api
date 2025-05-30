Note for Devs:
Didnt use lombok intentionally

# Customer Reward API

This is a Spring Boot REST API that calculates reward points for a retailers customers based on their transaction history.

Reward points are awarded as:

- 2 points for every dollar spent over 100$
- 1 point for every dollar spent between 50$ and 100$ in a transaction.

Example:

A 120$ purchase = 2x(120 - 100) + 1x50 = 90 points

whereas,

A 70$ purchase = 1 x (70 - 50) = 20 points


The API will start on port 8080

Query Parameters:

- customerId
- from (start date - inclusive)
- to (end date - inclusive)

### Sample URL to test in postman:

http://localhost:8080/api/rewards?customerId=1&from=2024-01-01&to=2024-03-15


### Sample JSON Response

```json
{
"customerId": "1",
"customerName": "Dinesh",
"totalPoints": 365,
"transactionCount": 3,
"fromDate": "2024-01-01",
"toDate": "2024-03-15",
    "monthlyBreakdown": {
        "January": 90,
        "February": 25,
        "March": 250
    }
}
```

### Explanation

Basically requesting the api to give the reward points for the customer ID 1, based on their transactions between Jan 1, 2024 and Mar 15, 2024.

