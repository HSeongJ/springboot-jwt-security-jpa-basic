## 프로젝트 설명
이 프로젝트는 Spring Boot를 기반으로 JWT(JSON Web Token), Spring Security, JPA(Java Persistence API)를 이용하여 기본적인 보안 인증과 데이터베이스 연동을 구현한 예제입니다. 사용자 인증 및 권한 부여를 위해 Spring Security와 JWT를, 데이터베이스 연동을 위해 JPA를 사용합니다.


## 프로젝트 구조

      ├── src/
      │   ├── main/
      │   │   ├── java/
      │   │   │   ├── com/
      │   │   │   │   ├── example/
      │   │   │   │   │   │   ├── basic/
      │   │   │   │   │   │   ├── aop/
      │   │   │   │   │   │   ├── config/
      │   │   │   │   │   │   ├── controller/
      │   │   │   │   │   │   ├── dto/
      │   │   │   │   │   │   │   ├── account/
      │   │   │   │   │   │   │   ├── common/
      │   │   │   │   │   │   ├── entity/
      │   │   │   │   │   │   │   ├── account/
      │   │   │   │   │   │   │   ├── common/
      │   │   │   │   │   │   ├── exception/
      │   │   │   │   │   │   ├── repository/
      │   │   │   │   │   │   ├── service/
      │   │   │   │   │   │   ├── serviceImpl/
      │   │   │   │   │   │   ├── util/
      │   │   │   │   │   │   ├── BasicApplication.java
      │   ├── resources/
      │   │   ├── application.properties

## 프로젝트 설정
#### 1. 이 프로젝트를 클론합니다.
```
git clone https://github.com/HSeongJ/springboot-jwt-security-jpa-basic
```

application.properties 파일을 열고 데이터베이스 정보를 설정합니다.

    spring.datasource.driverClassName=org.mariadb.jdbc.Driver
    spring.datasource.url=jdbc:mariadb://localhost:3306/dbname
    spring.datasource.username=dbusername
    spring.datasource.password=dbpassword

    spring.jpa.hibernate.ddl-auto=update
    spring.jpa.show-sql=true
    spring.jpa.properties.hibernate.format_sql=true

    app.jwtSecret: jwtKey
    app.jwtAccessExpirationInMs: accessTokenExpirationTime
    app.jwtRefreshExpirationInMs: refreshTokenExpirationTime

#### 2. 애플리케이션을 실행합니다.

## 사용법
프로젝트를 실행한 후, 브라우저나 API 클라이언트를 사용하여 http://localhost:8080/auth/signup 로 POST 요청을 보내어 새로운 사용자를 등록합니다.

등록한 사용자로 로그인을 하기 위해 http://localhost:8080/auth/signin 로 POST 요청을 보내어 JWT 토큰을 획득합니다.

얻은 JWT 토큰을 사용하여 보호된 엔드포인트에 접근할 수 있습니다. Header에 Authorization: Bearer {획득한 토큰}을 포함하여 요청을 보냅니다.

주의사항
실제 프로덕션 환경에서는 보안에 더 신경을 써야 하며, 예제로 제공된 코드를 그대로 사용하지 않는 것이 좋습니다.
데이터베이스 연결 정보와 JWT 시크릿 키 등 민감한 정보는 환경 변수나 보안 관리 도구를 통해 관리하는 것이 좋습니다.
