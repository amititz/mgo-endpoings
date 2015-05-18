

Notes:

Deployment -  Jar file can be built using maven or you can use the already included jar file and 
just run it with no command line arguments. It runs an embedded Tomcat server. The only deployment necessary is to change 
application.properties to point to proper MySql DB. The database should get created by the application and populated
with some sample data according to schema.sql.


  
Using JPA, configured to use Hibernate
Using Spring Data JPA tp reduce boilerplate JPA code
Using Spring Boot, for ease of configuration and deployment

Versioning - I've implemented a simple form of versioning.
 
Pagination - I haven't had the time to implement that. 

Other notes -
I didn't have time to finish everything, so there's one endpoint missing (the health-checker), as well as certain 
unit tests.
Regarding authentication - It will need to be changed to use hashed passwords as opposed to cleartext. Using Spring 
Security can make it easier to implement and includes options to use different authentication protocols.   
