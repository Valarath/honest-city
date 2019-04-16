# Honest City
Project hugely inspired by Honest Prague Guide and their fight with scam exchange places. Goal of this project is to provide tourists with information about exchanges and other subjects providing services to tourists at Prague. Main benefit of this project is provided information about trustworthiness of subjects and ability to get response from users in form of suggestions and votes for these suggestions.

## Used frameworks and libraries
- Lombok, mainly used to generate setters and getters in project.
- Liquibase, all files are in module Database
- Mybatis-spring, used to map database entities to business DTO objects in modul Model.
- Gradle, building tool used in project.
- Spring modules
    - Spring boot: autoconfigure
    - Spring boot: spring-boot-starter-web
    - Spring: context

## Instalation
To run this part of project locally you need to:
- install java 12 or newer [a link]https://www.oracle.com/technetwork/java/javase/downloads/index.html
- change database setting in application.properties to correspond with your database
- in case of using intellj idea ide download lombok plugin and make sure you enabled annotation processing. How may differs according to ide version.
 - 
 
 ## Project Architecture
This part of project is backend. Architecture is shown on picture.


