### How to build project locally
The below command will build and save the packaged jar file to the target folder. Once this is completed the docker image for the project will be built and pushed to local docker registry.

> ./mvnw clean package -Ddocker-image.tag=latest -D skipTests

I am skipping tests on main branch as I have with dependencies with flyway/docker when running tests locally. Please checkout  **unit-tests-branch** to see successful unit tests withouth those dependencies

### How to run the project
A docker-compose file has been created at the root level of the project to deploy the pre-built coolplanet-demo docker image and postgres image 
> docker-compose up -d 

### How to stop the project
> docker-compose down

### How to query the project
In ./requests directory there is the API call collection 'all_requests.http'. This can be used directly from Intellij IDEA to perform api calls similar to Postman.

### Project Improvements
Future improvements for the project would include introducing Spring Security/Spring Authorization Server into the project in the future to provide a Bearer Token for users to authorize all incoming rest calls to the CoolPlanet Demo application.

I
