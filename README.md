The Project Kata based on Java8, Spring Boot 2, Maven, Sawgger, Mockito, JUnit.

Since it was not required to implement the persitance layer, I use a fake 
JPA repository to manage objects (account and transaction).

Unit Tests was implmented with Mockito and JUnit.

To see how the API Service work, you can run the application then visit 
the following url: http://localhost:8080/swagger-ui.html

As a TODO tasks that can be added :
* Create Custom Exception 
* Add Logger