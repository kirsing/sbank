# Spring Boot Microservice SBank example(Eureka Server, Config Server, API Gateway, Services , Kafka, Keycloak)

<img src=https://github.com/kirsing/sbank/assets/86996284/c0f37006-92b0-49b7-b583-eb05dbdd1635>


# About the project
<ul style="list-style-type:disc">
  <li>User can't register himself (not implemented yet) but you can add users using the KeyCloak console and login through Keycloak</li>
  <li>It's possible to give ROLEs to the users</li>
  <li>Admin can approve and reject advertisement from advertisement service to report service by using management service through API Gateway</li>
  <li>User can create/update/delete/fetch account details including card, loan details, through API Gateway</li>
  <li>User can create/update/delete/fetch card details, through API Gateway</li>
  <li>User can create/update/delete/fetch loan details, through API Gateway</li>
</ul>

7 services whose name are shown below have been devised within the scope of this project.

- Config Server
- Eureka Server
- API Gateway
- Account Service
- Card Service
- Loan Service
- Message Service

Explore Rest APIs
<table style="width:100%">
  <tr>
      <th>Method</th>
      <th>Url</th>
      <th>Description</th>
      <th>Valid Request Body</th>
      <th>Valid Request Params</th>
      <th>Valid Request Params and Body</th>
      <th>No Request or Params</th>
  </tr>
  <tr>
     <td>POST</td>
     <td>/create</td>
     <td>Create Account</td>
     <td><a href="README.md#create">Info</a></td>
     <td></td>
     <td></td>
     <td></td>
  </tr>
  <tr>
     <td>POST</td>
     <td>/create?mobileNumber={mobileNumber}</td>
     <td>Create Card for Client</td>
     <td></td>
     <td><a href="README.md#create">Info</a></td>
     <td></td>
     <td></td>
  </tr>
  <tr>
     <td>POST</td>
     <td>/create?mobileNumber={mobileNumber}</td>
     <td>Create Loan for Client</td>
     <td></td>
     <td><a href="README.md#create">Info</a></td>
     <td></td>
     <td></td>
  </tr>
  <tr>
      <td>GET</td>
      <td>/fetchCustomerDetails?mobileNumber={mobileNumber}</td>
      <td>Fetch Customer details based on a mobile number</td>
      <td></td>
      <td><a href="README.md#fetchCustomerDetailsByNumber">Info</a></td>
      <td></td>
      <td></td>
  </tr>
  <tr>
      <td>GET</td>
      <td>/build-info</td>
      <td>Get Build information (implemented Retry pattern - Recilience4j)</td>
      <td></td>
      <td></td>
      <td></td>
      <td><a href="README.md#info">Info</a></td>
  </tr>
  <tr>
      <td>GET</td>
      <td>/java-version</td>
      <td>Get Java versions details (implemented Redis RateLimiter - Recilience4j)</td>
      <td></td>
      <td></td>
      <td></td>
      <td><a href="README.md#javaVersion">Info</a></td>
  </tr>
  <tr>
      <td>GET</td>
      <td>/contact-info</td>
      <td>Contact info details</td>
      <td></td>
      <td></td>
      <td></td>
      <td><a href="README.md#contactInfo">Info</a></td>
  </tr>
  <tr>
      <td>GET</td>
      <td>/fetch?mobileNumber={mobileNumber}</td>
      <td>Fetch card details based on a mobile number</td>
      <td></td>
      <td><a href="README.md#fetchCardDetailsByNumber">Info</a></td>
      <td></td>
      <td></td>
  </tr>
  <tr>
      <td>GET</td>
      <td>/fetch?mobileNumber={mobileNumber}</td>
      <td>Fetch loan details based on a mobile number</td>
      <td></td>
      <td><a href="README.md#fetchLoanDetailsByNumber">Info</a></td>
      <td></td>
      <td></td>
  <tr>
      <td>PUT</td>
      <td>/update</td>
      <td>Update Customer & Account details</td>
      <td><a href="README.md#update">Info</a></td>
      <td></td>
      <td></td>
      <td></td>
  </tr>
  <tr>
      <td>PUT</td>
      <td>/update</td>
      <td>Update card details</td>
      <td><a href="README.md#update">Info</a></td>
      <td></td>
      <td></td>
      <td></td>
  </tr>
  <tr>
      <td>PUT</td>
      <td>/update</td>
      <td>Update loan details</td>
      <td><a href="README.md#update">Info</a></td>
      <td></td>
      <td></td>
      <td></td>
  </tr>
  <tr>
      <td>DELETE</td>
      <td>/delete?mobileNumber{mobileNumber} </td>
      <td>Delete Customer & Account details based on the mobile number</td>
      <td></td>
      <td><a href="README.md#deleteCustomerDetailsByNumber">Info</a></td>
      <td></td>
      <td></td>
  </tr>
  <tr>
      <td>DELETE</td>
      <td>/delete?mobileNumber{mobileNumber} </td>
      <td>Delete Card details based on a mobile number</td>
      <td></td>
      <td><a href="README.md#deleteCardDetailsByNumber">Info</a></td>
      <td></td>
      <td></td>
  </tr>
  <tr>
      <td>DELETE</td>
      <td>/delete?mobileNumber{mobileNumber} </td>
      <td>Delete Loan details based on a mobile number</td>
      <td></td>
      <td><a href="README.md#deleteLoanDetailsByNumber">Info</a></td>
      <td></td>
      <td></td>
  </tr>
</table>


### Used Dependencies
* Core
    * Spring
        * Spring Boot
        * Spring Security
        * Spring Web
        * Spring Data
            * Spring Data JPA
        * Spring Cloud
            * Spring Cloud Gateway Server
            * Spring Cloud Config Server
            * Spring Cloud Config Client
            * OpenFeign
    * Netflix
        * Eureka Server
        * Eureka Client
* Database
    * Mysql
* Message Broker
    * Kafka
* Security
    * Keycloak Server
    * Keycloak OAuth2
    * Keycloak REST API

## Valid Request Body

##### <a id="create"> Create Account
```
    http://localhost:8072/sbank/accounts/api/create
    Bearer Token : Access Token from Keycloak
    {
        "name": "Kirill Yud",
        "email": "kirsing98@gmail.com",
        "mobileNumber": "3754478543"
    }
```
##### <a id="update"> Update Customer & Account details
```
    http://localhost:8072/sbank/accounts/api/update
    Bearer Token : Access Token from Keycloak
    {
        "name": "Kirill Yud",
        "email": "kirsing98@gmail.com",
        "mobileNumber": "4354437687",
        "accountsDto": {
            "accountNumber": 1105557729,
            "accountType": "Savings",
            "branchAddress": "123 Main Street, New York"
        }
```

##### <a id="update"> Update card details
```
    http://localhost:8072/sbank/cards/api/cards/update
    Bearer Token : Access Token from Keycloak
    {
        "mobileNumber": "3754478543",
        "cardNumber": "100107091026",
        "cardType": "Debit Card",
        "totalLimit": 100000,
        "amountUsed": 10000,
        "availableAmount": 90000
    }
```

##### <a id="update"> Update loan details
```
    http://localhost:8072/sbank/loans/api/loans/update
    Bearer Token : Access Token from Keycloak
    {
        "mobileNumber": "3754478543",
        "loanNumber": "10071469799154",
        "loanType": "House Loan",
        "totalLoan": -100000,
        "amountPaid": -10000,
        "outstandingAmount": -90000
    }
```
## Valid Request Params

##### <a id="fetchCustomerDetailsByNumber">Fetch Customer details based on a mobile number
```
    http://localhost:8072/eazybank/accounts/api/fetchCustomerDetails?mobileNumber={mobileNumber} 
    Bearer Token : Not required
```

##### <a id="fetchCardDetailsByNumber">Fetch Card details based on a mobile number
```
    http://localhost:8072/sbank/cards/api/cards/fetch?mobileNumber={mobileNumber} 
    Bearer Token : Not required
```

##### <a id="fetchLoanDetailsByNumber">Fetch Loan details based on a mobile number
```
    http://localhost:8072/sbank/loans/api/loans/fetch?mobileNumber={mobileNumber} 
    Bearer Token : Not required
```

##### <a id="deleteCustomerDetailsByNumber">Delete Customer & Account details based on the mobile number
```
    http://localhost:8072/eazybank/accounts/api/delete?mobileNumber={mobileNumber} 
    Bearer Token : Access Token from Keycloak
```

##### <a id="deleteCardDetailsByNumber">Delete Card details based on a mobile number
```
    http://localhost:8072/sbank/cards/api/cards/delete?mobileNumber={mobileNumber} 
    Bearer Token : Access Token from Keycloak
```

##### <a id="deleteLoanDetailsByNumber">Delete Loan details based on a mobile number
```
    http://localhost:8072/sbank/loans/api/loans/delete?mobileNumber={mobileNumber} 
    Bearer Token : Access Token from Keycloak
```

##### <a id="create">Create Card for Client
```
    http://localhost:8072/sbank/cards/api/cards/create?mobileNumber={mobileNumber} 
    Bearer Token : Access Token from Keycloak
```
##### <a id="create">Create Loan for Client
```
    http://localhost:8072/sbank/loans/api/loans/create?mobileNumber={mobileNumber} 
    Bearer Token : Access Token from Keycloak
```

## No Request or Params

##### <a id="info"> Get Build information
```
    http://localhost:8072/sbank/accounts/api/build-info
    Bearer Token : Not Required
```

##### <a id="javaVersion"> Get Java versions details
```
    http://localhost:8072/sbank/accounts/api/java-version
    Bearer Token : Not Required
```

##### <a id="contactInfo"> Contact info details
```
    http://localhost:8072/sbank/accounts/api/contact-info
    Bearer Token : Not Required
```