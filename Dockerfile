FROM adoptopenjdk:11-jdk-hotspot

WORKDIR /app

COPY mvnw .
COPY .mvn .mvn
COPY pom.xml .

COPY src src

RUN ./mvnw package -DskipTests

EXPOSE 8080

RUN apt-get update && apt-get install -y mysql-server && \
    sed -i 's/bind-address.*= 127.0.0.1/bind-address = 0.0.0.0/' /etc/mysql/mysql.conf.d/mysqld.cnf && \
    service mysql start && \
    mysql -e "ALTER USER 'root'@'localhost' IDENTIFIED WITH mysql_native_password BY 'pmpm'; FLUSH PRIVILEGES;"

CMD service mysql start && java -jar target/NvpProject-0.0.1-SNAPSHOT.jar
