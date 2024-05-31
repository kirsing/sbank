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
## 🔨 Run the App
<b>1 )</b> Download your project from this link `https://github.com/kirsing/sbank`

<b>2 )</b> Go to the project's home directory :  `cd ~/sbank-main`

<b>Docker</b>

<b>1 )</b> Install <b>Docker Desktop</b>. Here is the installation `https://www.docker.com/get-started/`

<b>2 )</b> Go to the specific directory: `cd ~/sbank-main/docker-compose/prod`

<b>3 )</b> Run all <b>Containers</b> through this command - `docker compose up -d`

<b>Kubernetes</b>

<b>1 )</b> Install <b>minikube</b> or enable Kubernetes on Docker Desktop

<b>2 )</b> Install <b>Helm</b>  `https://helm.sh/docs/intro/quickstart/`

<b>3 )</b> Go to the directory :  `cd ~/sbank-main/helm/environments/`

<b>4 )</b> Install all <b>Helm Charts</b> using **Helm** through this command
`helm install <your-application-name> dev-env`

### Implement KeyCloak settings
<details>
 <p> 1 ) Open Keycloak on the Browser through localhost:7080 </p>
  <p>2 ) Enter username and password (admin : admin) </p>
<img src=https://github.com/kirsing/sbank/assets/86996284/f784b52b-95b1-4e2a-a6ea-468620a29418>
 

### <p>Authorization code grant flow </p>
<p> Create Client </p>
<img src=https://github.com/kirsing/sbank/assets/86996284/36470979-6624-47e4-b3df-de5db877e236>

<img src=https://github.com/kirsing/sbank/assets/86996284/e324d8e5-f135-45bb-8524-1ce1d98a433c>
<img src=https://github.com/kirsing/sbank/assets/86996284/fefdde7e-3356-4f42-9e02-18c221d9d0a1>
<img src=https://github.com/kirsing/sbank/assets/86996284/e51ae88d-0f2e-4758-99d0-203cfb4659bb>

<p>Save client secret </p>
<img src=https://github.com/kirsing/sbank/assets/86996284/9715e531-e0af-4deb-8bc7-1b4860687d95>

<p>Create ROLES: ACCOUNTS, CARDS, LOANS </p>
<img src=https://github.com/kirsing/sbank/assets/86996284/ad99ed7b-d608-4bcb-8848-583dd67a22d8>

<p>Create users </p>
<img src=https://github.com/kirsing/sbank/assets/86996284/27de10e8-6ffa-4afe-b84d-145d0d691786>

<img src=https://github.com/kirsing/sbank/assets/86996284/a138332b-0b72-45b1-b880-17fe473510e7>

<img src=https://github.com/kirsing/sbank/assets/86996284/d505f874-3450-4e21-bfb2-90efb1122a2a>

<p>Assign ROLE to the user </p>
<img src=https://github.com/kirsing/sbank/assets/86996284/d3b12ca2-ab61-4aa0-8b88-8dd7a4915415>

<img src=https://github.com/kirsing/sbank/assets/86996284/439e2228-df83-4320-afed-f929d497f792>

</details>  


### Screenshots
<details>
<summary>Click here to show the screenshot of project</summary>
<p> Grafana </p>
<img src=https://github.com/kirsing/sbank/assets/86996284/9eda73c7-45bc-4e97-bb76-836f5a53a2e8>

<p> Kubernetes Dashboard </p>
<img src=https://github.com/kirsing/sbank/assets/86996284/6da595cc-de00-4951-860d-d3dfc96ca7c5>
</details>