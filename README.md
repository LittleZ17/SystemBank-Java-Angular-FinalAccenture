
# :bank: Z · Bank

This is a midterm project developed for the IronHack Bootcamp in collaboration with Accenture. The application is built using the Spring framework and provides a set of functionalities for managing different types of bank accounts.

## :books: Features
The application includes the following features:

Admin: who can create all accounts, delete them and modify balance.

Customers: who can consult the balance of his account and can make money transfers.

User Types: The system supports three types of users: Admins and Customers.

### Description

This application is a complete system developed with Java and Spring Boot that offers a REST API through two microservices, implements several CRUD (Create, Read, Update, Delete) to manage users, accounts and transfers between clients, as well as the ability to upload files. The application has two types of users: Administrator and Client.

The Administrator has special privileges and can create and delete accounts and users in the system. On the other hand, Clients can have multiple accounts and make money transfers to other clients.

##  CaseDiagram & ClassDiagram
![CaseD.png](/assets/CaseD.png)
------------------------------------------------------------------------
![UseD.png](/assets/UseD.png)
## :woman_technologist: Technologies Used
The application is built using the following technologies:
## Backend:
·Java with Spring framework

·Spring Boot

·Spring Test

·Spring MVC

·MySQL database

## :card_file_box: Database
A MySQL database is used to store the application data. The necessary database schema and sample data are provided.

## Frontend:

·Angular

·Css

## Complementary:

### ·Figma : [Mockup](https://www.figma.com/file/e37vRD3ZzoHtEfDHFpC8dj/Z-Bank?type=design&node-id=513%3A2&t=RO29a6NyvQCXuU9s-1)

### ·Trello : [Work Flow](https://www.figma.com/file/e37vRD3ZzoHtEfDHFpC8dj/Z-Bank?type=design&node-id=513%3A2&t=RO29a6NyvQCXuU9s-1)

### :orange_circle: Postman Collection
A Postman collection is provided that includes the API routes and sample requests for testing the application.
Please refer to the provided files in the "assets" folder for collection Postman.

## :pushpin: Getting Started
To run the application, follow these steps:

Clone the repository to your local machine.

### Backend:
Open Folders Microservices:  TransactionService & FileStorage
Set up a MySQL database and import the provided schema and sample data.
Configure the database connection details in the application's configuration files.
Build and run the application using your preferred Java IDE or Maven command.
Use the provided Postman collection to test the application's functionality.

### Frontend:
Run `ng serve` for a dev server. Navigate to `http://localhost:4200/`. The application will automatically reload if you change any of the source files.

## Author: Zindy Lucio Martinez
### Linkedin : [Profile](https://www.linkedin.com/in/zindy-lucio-martinez/)
### Github : [@LittleZ17](https://github.com/LittleZ17)
# Happy banking! 



