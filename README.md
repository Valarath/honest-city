# Honest City
Project hugely inspired by Honest Prague Guide and their fight with scam exchange places. Goal of this project is to provide tourists with information about exchanges and other subjects providing services to tourists at Prague. Main benefit of this project is provided information about trustworthiness of subjects and ability to get response from users in form of suggestions and votes for these suggestions.

 ## Project Architecture
This part of project is backend. Architecture is shown on picture.

[![image11.jpg](https://i.postimg.cc/50TY45y5/image11.jpg)](https://postimg.cc/Kk7vQMn4)

Principles of this architecture are:
- classes in module Model represent all businness DTO.
- All modules depend on module Model to use it's DTO.
- all business logic is in module Service.
- services in module Service use gateways (java interfaces) in order to communicate outside of it's module.
- services in modules Database and Crawling implement gateways from module Service.
- module Database handle communication with database.
- module Crawling handle communication with services outside of this project.
- module Endpoints represents public api of backend. 
- comunication with backend use REST architecture.
- for every incomming request to endpoints contollers exists class representing its parameters.
- for every response from endpoints contollers exists class representing its parameters.

## Project extending
In case of extending project by yourself, to use already existing features make sure that
- Services dealing with new subjects are extending SubjectService
- Services dealing with voting are extendind VoteService
- Services dealing with suggestions are extending SuggestionService

for all these services exists Enums classes containing names of services, you need to add original service names and name services accordingly.

For creating tests make sure that your test classes extend AbstractServiceTest.

## Used frameworks and libraries
- java 12
- Lombok, mainly used to generate setters and getters in project.
- Liquibase, all files are in resources/db/changelog of module Database
- Mybatis-spring, used to map database entities to business DTO objects in modul Model.
- Gradle 5.3.1, building tool used in project.
- Spring modules
    - Spring boot: autoconfigure
    - Spring boot: spring-boot-starter-web
    - Spring: context

## Instalation
To run this part of project locally you need to:
- install java 12 or newer [a link]https://www.oracle.com/technetwork/java/javase/downloads/index.html
- change database setting in application.properties to correspond with your database
- in case of using intellj idea ide
    - download lombok plugin 
    - make sure you enabled annotation processing.
    - open project as gradle project
    - in gradle menu: tasks > build > build
    - in gradle menu: tasks > application > bootRun
 



