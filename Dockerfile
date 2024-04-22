From openjdk:17
copy ./target/OnlineSeatBook-0.0.1-SNAPSHOT.jar OnlineSeatBook-0.0.1-SNAPSHOT.jar
CMD ["java","-jar","OnlineSeatBook-0.0.1-SNAPSHOT.jar"]