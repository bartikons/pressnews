# pressnews

To run ./mvnw clean spring-boot:run
//before run change in application.properties file path where to save article 

Endpointy

/api/Publication          Delete  required parameter PublicationId

Example of use
curl -X DELETE http://localhost:8080/api/Publication?PublicationId=1 return information if publication was delete

/api/Publication          Put     required parameter PublicationId,  title,  name,  date, MultipartFile file

Example of use
curl -F file=@"hello.txt"  -X PUT 'http://localhost:8080/api/Publication?PublicationId=1&title=example&name=example&authorId=1&date=2000-01-01' return information Publication that was change

/api/Publication          Post    required parameter title, name, authorId, date, MultipartFile file

Example of use
curl -X POST -F file=@"hello.txt" 'http://localhost:8080/api/Publication?title=example&name=example&authorId=1&date=2000-01-01' return information Publication that was create

/api/Publication          Get     required parameter  PublicationId

Example of use
curl http://localhost:8080/api/Publication?PublicationId=1 return publication of given id 

/api/Publications         Get     required parameter None

Example of use
curl http://localhost:8080/api/Publication return list of all publication in order from new to old 

/api/PublicationSearch    Get     required parameter keyword

Example of use
curl http://localhost:8080/api/PublicationSearch?keyword=world return list of all publication which have given keyword 

