
1) **Need docker-compose, docker, Java JDK 11, Maven**
    + Java JDK 11, Maven, Spring-boot: build APIs in Backend side
    + MySQL: database  
	
2) **Start database**
    + cd crypto-trading-microservices
    + docker-compose up

2) **Building the Services**
	+ cd crypto-trading-microservices
    + mvn clean package
	
3) **Running the services by Spring-boot**

	+ cd data-api-gateway/
	+ mvn spring-boot:run

	+ cd crypto-service/
    + mvn spring-boot:run
   

4) **Test APIs:** 
  
	   Postman for testing API.

	    + Get close price for ticker by date ranges .
	
        	localhost: http://localhost:8080/api/v1/best-prices
        	Method: GET
        	 