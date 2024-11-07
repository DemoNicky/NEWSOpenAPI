#untuk memberitahu ke docker jika runtuime menggunakan java dengan versi sdk seperti berikut
FROM openjdk:17-jdk-slim

#untuk menginformasikan siapa yang maintance image
MAINTAINER dobudobu.com

#add aplikasi ke dalam image
COPY target/discovery-server-0.0.1-SNAPSHOT.jar discovery-server-0.0.1-SNAPSHOT.jar

#untuk mengeksekusi aplikasi commandnya seperti java -jar target/file-service-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java", "-jar", "discovery-server-0.0.1-SNAPSHOT.jar"]