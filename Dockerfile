FROM openjdk:8-jdk-alpine
ADD sample-start/target/cola-sample-service.jar /cola-sample-service.jar
# 同步容器时间
RUN ln -snf /usr/share/zoneinfo/Asia/Shanghai /etc/localtime
RUN apk add --update font-adobe-100dpi ttf-dejavu fontconfig
ENTRYPOINT ["java", "-jar", "-Dfile.encoding=UTF-8", "-Dsun.jnu.encoding=UTF-8"]
CMD ["/cola-sample-service.jar"]