


# Spring Boot generic paging, sorting and filtering for PrimeNg tables

## Goal of the project

PrimeNg tables have a 'lazy' mode when displaying data, it sends all the requests of paging, sorting and filtering to the server to be processed.
The goal of the this project is to make this server side processing the most generic possible (Spring boot).

## Structure of the project

 - The 'ng' folder contains the sample front-end Angular 10 project
 - The Spring Boot project is a minimal showcase and can be used as a base for other projects, it contains : a sample entity, dao, controller and service and two core classes responsible of building queries

## How it works

The idea is to make an utility class that parse and convert a PrimeNg json request to a paging and sorting query and build an **RSQL**  query for the columns and general filters, this rsql query will then converted to Jpa specification with **[rsql-jpa-specification](https://github.com/perplexhub/rsql-jpa-specification)** and executed against the dao.

## Core classes and initial setup
The two most important java classes in this project are :

 - **org.nd.primeng.search.PrimengRequestData** : a bean to hold the data parsed from the PrimeNg table request
 - **org.nd.primeng.search.SearchBuilder** : responsible for parsing the PrimeNg table json request, generating the paging and sorting jpa query and building an Rsql query from the filters
 - Please refer to the class **org.nd.primeng.services.UserService** for an example to how to use those classes
 - Your repository class needs to extends **JpaSpecificationExecutor<Class>** and **QuerydslPredicateExecutor<Class>**, please refer to UsersDao class for an example

 This project uses **[rsql-jpa-specification](https://github.com/perplexhub/rsql-jpa-specification)** to work, please refer to its documentation to see how the intial setup is done.

## <font color="red">Very important notes about dates filtering</font>
In order to properly  filter against date columns, you need to do two things :

 - Never use java.util.Date or any other java date types as type in your entity classes, use only **java.time.LocalDateTime**
 - You need to properly setup your timezone in the jvm with the parameter **-Duser.timezone**, example : 

     -Duser.timezone=Europe/Paris

## Run the project

 - Create a database in mysql with name : **app_db** 
 - Execute the sample data sql file **/db/sample-data.sql** againt the database
 - Set your timezone in pom.xml in order to the date filtering to work properly :

```xml
<configuration> 	
	    <jvmArguments> 		 
		 -Duser.timezone=Europe/Paris 	
	 </jvmArguments> 
</configuration>
```

 - Run the Spring boot project
 - Run the Angular 10 project

