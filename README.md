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
     <td></td>
     <td><a href="README.md#create">Info</a></td>
     <td></td>
  </tr>
  <tr>
     <td>POST</td>
     <td>/create?mobileNumber={mobileNumber}</td>
     <td>Create Loan for Client</td>
     <td></td>
     <td></td>
     <td><a href="README.md#create">Info</a></td>
     <td></td>
  </tr>
  <tr>
      <td>GET</td>
      <td>/fetchCustomerDetails?mobileNumber={mobileNumber}</td>
      <td>Fetch Customer details based on a mobile number</td>
      <td></td>
      <td><a href="README.md#alladvertisementsFromAdmin">Info</a></td>
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
      <td><a href="README.md#alladvertisementsFromAdmin">Info</a></td>
  </tr>
  <tr>
      <td>GET</td>
      <td>/java-version</td>
      <td>Get Java versions details (implemented Redis RateLimiter - Recilience4j)</td>
      <td></td>
      <td></td>
      <td></td>
      <td><a href="README.md#alladvertisementsFromAdmin">Info</a></td>
  </tr>
  <tr>
      <td>GET</td>
      <td>/contact-info</td>
      <td>Contact info details</td>
      <td></td>
      <td></td>
      <td></td>
      <td><a href="README.md#alladvertisementsFromAdmin">Info</a></td>
  </tr>
  <tr>
      <td>GET</td>
      <td>/fetch?mobileNumber={mobileNumber}</td>
      <td>Fetch card details based on a mobile number</td>
      <td></td>
      <td><a href="README.md#alladvertisementsFromAdmin">Info</a></td>
      <td></td>
      <td></td>
  </tr>
  <tr>
      <td>GET</td>
      <td>/fetch?mobileNumber={mobileNumber}</td>
      <td>Fetch loan details based on a mobile number</td>
      <td></td>
      <td><a href="README.md#alladvertisementsFromAdmin">Info</a></td>
      <td></td>
      <td></td>
  <tr>
      <td>PUT</td>
      <td>/update</td>
      <td>Update Customer & Account details</td>
      <td><a href="README.md#alladvertisementsFromAdmin">Info</a></td>
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
      <td><a href="README.md#delete">Info</a></td>
      <td></td>
      <td></td>
  </tr>
  <tr>
      <td>DELETE</td>
      <td>/delete?mobileNumber{mobileNumber} </td>
      <td>Delete Card details based on a mobile number</td>
      <td></td>
      <td><a href="README.md#delete">Info</a></td>
      <td></td>
      <td></td>
  </tr>
  <tr>
      <td>DELETE</td>
      <td>/delete?mobileNumber{mobileNumber} </td>
      <td>Delete Loan details based on a mobile number</td>
      <td></td>
      <td><a href="README.md#delete">Info</a></td>
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