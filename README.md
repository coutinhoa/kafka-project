# Getting Started kafka-project
 1. Open the project kafka in the terminal and run the docker file with the following command:
    docker-compose up --build

2. Go to Spring boot terminal, mak sure you are in the directory that contains the file gradlew and run the following command:
    ./gradlew bootRun 

3. Go to Postman or insomnia and do a get request. The response is empty.
    Then do a post request with the following JSON body: {"content": "message"}
    Do another get request, you'll see that the response has the message from the previous post request.
