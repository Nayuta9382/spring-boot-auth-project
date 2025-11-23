# =============================
# ビルドステージ
# =============================
FROM eclipse-temurin:17-jdk-alpine AS builder

WORKDIR /build

# Mavenラッパーとpom.xmlをコピー
COPY spring-app-project/mvnw .
COPY spring-app-project/.mvn .mvn
COPY spring-app-project/pom.xml .

# 実行権限を付与
RUN chmod +x mvnw

# 依存関係をダウンロード
RUN ./mvnw dependency:go-offline -B

# ソースコードをコピー
COPY spring-app-project/src src

# アプリケーションをビルド
RUN ./mvnw clean package -DskipTests -B


# =============================
# 実行ステージ
# =============================
FROM eclipse-temurin:17-jre-alpine

WORKDIR /app

# 非rootユーザー作成
RUN addgroup -S spring && adduser -S spring -G spring

# jarファイルコピー
COPY --from=builder /build/target/*.jar app.jar

# application.properties を JAR と別パスに置く
COPY spring-app-project/src/main/resources/application.properties /app/config/application.properties

# Spring Boot に外部設定ファイルを読み込ませる
ENV SPRING_CONFIG_LOCATION=file:/app/config/application.properties

USER spring:spring

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "/app/app.jar"]
