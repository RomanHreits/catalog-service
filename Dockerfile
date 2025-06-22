# First stage: Build the application and extract layers
FROM eclipse-temurin:21 as builder
WORKDIR workspace
ARG JAR_FILE=build/libs/*.jar
COPY ${JAR_FILE} catalog-service.jar

RUN java -Djarmode=layertools -jar catalog-service.jar extract

# Second stage: Create the final image with the extracted layers
FROM eclipse-temurin:21

RUN useradd nonroot-user
USER nonroot-user

WORKDIR workspace
COPY --from=builder workspace/dependencies/ ./
COPY --from=builder workspace/springs-boot-loader/ ./
COPY --from=builder workspace/snapshot-dependencies/ ./
COPY --from=builder workspace/application/ ./

ENTRYPOINT ["java", "org.springframework.boot.loader.JarLauncher"]