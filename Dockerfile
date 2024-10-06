# FROM openjdk:17-jdk-alpine <- 오라클쪽은 deprecated 되었습니다.
FROM eclipse-temurin:21

# 애플리케이션 JAR 파일의 위치 (빌드 시 변경될 수 있음)
ARG JAR_FILE=build/libs/*.jar

# 작업 디렉토리 설정
WORKDIR /app

# JAR 파일 복사
COPY ${JAR_FILE} app.jar

# 환경 변수 설정 (필요에 따라 추가)
ARG SPRING_PROFILES_ACTIVE=prod
ENV SPRING_PROFILES_ACTIVE=${SPRING_PROFILES_ACTIVE}

# 실행 명령 (JVM 옵션 포함)
CMD ["java", "-Xmx2g", "-Xms2g", "-XX:+UseZGC", "-XX:+ZGenerational", "-jar", "app.jar"]

#- java: Java 실행 명령
#- -jar: JAR 파일을 실행한다는 의미
#- -Xmx2g: 최대 힙 메모리를 2GB로 설정
#- -Xms2g: 초기 힙 메모리를 2GB로 설정
#- your-app.jar: 실행할 JAR 파일 이름
#
#**자주 사용되는 JVM 옵션**
#
#- -Xmx: 최대 힙 메모리 설정
#- -Xms: 초기 힙 메모리 설정
#- -Xss: 스택 크기 설정
#- -XX:+UseG1GC: G1 가비지 컬렉터 사용. G1 가비지 컬렉터는 Java 9부터 기본 가비지 컬렉터로 설정되었습니다.
#-  Z 가비지 컬렉터 사용. : https://wiki.openjdk.org/display/zgc/Main
#- -XX:+UseZGC -XX:+ZGenerational -Xlog:gc
#- -XX:+PrintGCDetails: 가비지 컬렉션 상세 정보 출력
#- -Dspring.profiles.active=prod: 프로필 활성화

# - ARG: 빌드 시에만 사용되는 변수로, 이미지 빌드 후에는 사라집니다. 외부에서 값을 전달하여 이미지를 유연하게 생성하는 데 사용합니다. 
# - ENV: 컨테이너 실행 시에 사용되는 환경 변수입니다. 이미지 빌드 시에 설정되며, 컨테이너 실행 시에도 계속 유지됩니다.\

# application.properties 에 spring.profiles.active=${SPRING_PROFILES_ACTIVE}
# docker build --build-arg SPRING_PROFILES_ACTIVE=stage