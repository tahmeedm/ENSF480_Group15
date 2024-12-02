In order for the functionality of the project, an SQL database needs to be made with
the title AcmePlex, running on localhost:3306, username is root, password is ensf480g15

ALTER USER 'root'@'localhost' IDENTIFIED BY 'ensf480g15';
GRANT ALL PRIVILEGES ON AcmePlex.* TO 'root'@'localhost';
FLUSH PRIVILEGES;
CREATE DATABASE AcmePlex;

Using the MySql workbench, import the database file called 15.sql.
- if any conflicts are present, delete the existing AcmePlex database
and reimport the 15.sql file.

Use the following command to build the gradle.
"./gradlew bootRun  --console=plain"


Use the following command to install the react dependencies "npm install"
Use the following command to run the React frontend "npm start"