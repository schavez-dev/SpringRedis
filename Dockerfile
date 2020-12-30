FROM openjdk
COPY "./target/pruebas-fonyou-curso-0.0.1-SNAPSHOT.jar" "appFonyou.jar"
EXPOSE 8080
ENTRYPOINT ["java","-jar","appFonyou.jar"]