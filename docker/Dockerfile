# docker/Dockerfile
# マルチステージビルドで最適化
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
# 実行用の軽量イメージ
# =============================
FROM eclipse-temurin:17-jre-alpine

WORKDIR /app

# 非rootユーザーを作成
RUN addgroup -S spring && adduser -S spring -G spring

# ビルドステージからjarファイルをコピー
COPY --from=builder /build/target/*.jar app.jar

# ユーザーを切り替え
USER spring:spring

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "/app/app.jar"]