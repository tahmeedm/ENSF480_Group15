<!-- start gradle -->
./gradlew bootRun  
<!-- Testing code -->
curl http://localhost:8083/hello
<!-- Test the frontend test -->
curl "http://localhost:8083/test?args=hello"