<!-- start gradle -->
./gradlew bootRun  --console=plain
<!-- Testing code -->
curl http://localhost:8083/hello
<!-- Test the frontend test -->
curl "http://localhost:8083/test?args=hello"

<!-- mySQL setup -->
ALTER USER 'root'@'localhost' IDENTIFIED BY 'ensf480g15';
GRANT ALL PRIVILEGES ON AcmePlex.* TO 'root'@'localhost';
FLUSH PRIVILEGES;
CREATE DATABASE AcmePlex;
