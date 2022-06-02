FROM java:8
COPY *.jar /app.jar
CMD ["--server.port=8085"]
EXPOSE 8085
ENTRYPOINT ["java","-jar","/app.jar"]