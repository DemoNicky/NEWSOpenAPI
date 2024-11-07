#untuk memberitahu ke docker jika runtuime menggunakan java dengan versi sdk seperti berikut
FROM openjdk:17-jdk-slim

#untuk menginformasikan siapa yang maintance image
MAINTAINER dobudobu.com

#add aplikasi ke dalam image
COPY target/gateway-server-0.0.1-SNAPSHOT.jar gateway-server-0.0.1-SNAPSHOT.jar

#untuk mengeksekusi aplikasi commandnya seperti java -jar target/article-management-service-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java", "-jar", "gateway-server-0.0.1-SNAPSHOT.jar"]