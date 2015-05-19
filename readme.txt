


General -
I didn't have time to finish everything, so there's one endpoint missing (the health-checker), as well as certain 
unit tests.
Regarding authentication - It will need to be changed to use hashed passwords as opposed to cleartext. Using Spring 
Security can make it easier to implement and includes options to use different authentication protocols.   

Deployment -  
Jar file can be built using maven or you can use the already included jar file and 
just run it with no command line arguments. It runs an embedded Tomcat server. The only deployment necessary is to change 
application.properties to point to proper MySql DB. The database table should get created for you automatically and 
populated with some sample data using the schema.sql script that runs on startup.

Running -
Sample requests:

1. Get users filtered by state and grouped by city:
http://localhost:8080/endpoints/1.0/users?filterField=state&filterValue=CA&groupBy=city

2. Get the list of files in a given directory:
http://localhost:8080/endpoints/1.0/dir?path=src/main/java/com/mgo/amititz/endpoints/service

3. Authenticate - 
You can test it by issuing a POST request using a web debugging tool such as 
Fiddler, to the following URL:
http://localhost:8080/endpoints/1.0/authenticate

with the following JSON body for example:
{ "userName": "hhancock", "password": "hhancock" }


Frameworks -

I'm using Spring framework with Spring boot, as it makes configuration and deployment easy. Spring framework itself 
is a powerful framework for dependency injection, which makes it easy to write loosely coupled and testable code.
    
For the data access, I'm Using JPA, configured to use Hibernate as its underlying implementation/provider. 
Using JPA removes the need to write any SQL code or deal with JDBC connections and result sets.
I'm also using Spring Data JPA since it minimizes the amount of code needed even further, so I don't even have to deal
with EntityManager, transactions and query objects.

Versioning - 
The version number is embedded as part of the URL and mapped to a specific method in the controller.

Pagination -
I started to add support for that by adding a paginating overload to each of the find methods on the UserRepository 
interface, using the help from Spring's JpaRepository capabilities.
     
Database -
Note that I haven't specified the table name or column names in the UserEntity class. Instead, I left it to the default
mappings which use the class and field names, converting camel case to the corresponding lowercase representation.



 

