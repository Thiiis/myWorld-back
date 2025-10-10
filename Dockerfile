# 베이스 이미지 선택 (Java 17 버전을 사용한다고 가정)
FROM openjdk:21-jdk-slim

# JAR 파일이 위치할 경로를 변수로 지정
ARG JAR_FILE=build/libs/*.jar

# 위 경로의 JAR 파일을 컨테이너 안으로 복사
COPY ${JAR_FILE} app.jar

# 컨테이너가 시작될 때 실행할 명령어
ENTRYPOINT ["java","-jar","/app.jar"]