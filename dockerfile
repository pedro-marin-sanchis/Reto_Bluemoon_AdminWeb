FROM openjdk:17-jdk-alpine
COPY ./target/Bluemoon_AdminWeb-0.0.1.jar app.jar

ENV API_URL=http://localhost:8080/api
ENV APP_USER_USERNAME=bluemoon_admin
ENV APP_USER_PASSWORD=ur%]SEmRPcvMqfB;2xs>!

ENTRYPOINT [                                                    \
                "java", "-jar", "/app.jar",                     \
                "--apiURL=$API_URL",                            \
                "--appUserUsername=$APP_USER_USERNAME",         \
                "--appUserPassword=$APP_USER_PASSWORD"          \
           ]

# docker build -t bluemoonadminweb .
# docker run -p 8081:8081 bluemoonadminweb
# docker run -p 8081:8081 -e API_URL=url APP_USER_USERNAME=usr APP_USER_PASSWORD=secret bluemoonadminweb