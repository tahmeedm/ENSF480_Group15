<!-- start gradle -->
./gradlew bootRun  --console=plain
<!-- Testing code -->
curl http://localhost:8083/hello
<!-- Test the frontend test -->
curl "http://localhost:8083/test?args=hello"