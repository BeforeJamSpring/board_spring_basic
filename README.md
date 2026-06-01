# SPRING_BASIC_BOARD
Spring 기본 사용을 위한 Project로 **USER 도메인의 핵심 CRUD API**를 구축하는 프로젝트
단순한 기능 구현을 넘어, **TDD(테스트 주도 개발)** 방법론을 적용하여 안정적인 백엔드 코어 시스템 설계 집중

## 🛠️ 기술 스택 (Tech Stack)
- **Language**: Java 17 (or 21)
- **Framework**: Spring Boot 3.x, Spring Web MVC
- **Database**: H2 Database (In-Memory)
- **Data Access**: MyBatis Framework
- **Test**: JUnit5, AssertJ, Mockito (`@SpringBootTest`, `@WebMvcTest`, `@MybatisTest`)
- **Build Tool**: Gradle

## ✨ 핵심 구현 목표 (Key Features)
- [x] **TDD 기반 설계**: 실패하는 테스트(Red) -> 성공(Green) -> 리팩토링 사이클 적용
- [x] **계층형 아키텍처**: Controller - Service - Mapper (Repository) 구조 분리
- [x] **유저 CRUD**: 회원가입, 내 정보 조회, 이름 수정, 회원 탈퇴 기능 통합 테스트 통과
- [x] **예외 처리**: `@ExceptionHandler`를 활용한 중복 아이디 가입 방지 및 400 Bad Request 처리

## 📡 API 명세서 (API Specification)

클라이언트(Front-end/App)와 통신하기 위한 RESTful API 명세입니다.

| 기능 | HTTP Method | Endpoint (URI) | 상태 코드 (Success) | 상태 코드 (Fail) |
| :--- | :--- | :--- | :--- | :--- |
| **회원 가입** | `POST` | `/users/signup` | `201 Created` | `400 Bad Request` |
| **회원 조회** | `GET` | `/users/{username}` | `200 OK` | `404 Not Found` |
| **이름 수정** | `PUT` | `/users/{username}` | `200 OK` | `404 Not Found` |
| **회원 탈퇴** | `DELETE` | `/users/{username}` | `204 No Content` | `404 Not Found` |

<br>

### 1. 회원 가입 (Create)
- **Description**: 새로운 유저를 생성합니다. (단, `username`은 중복될 수 없습니다.)
- **Request Body** (application/json)
  ```json
  {
    "username": "testuser",
    "password": "password123!",
    "email": "test@example.com",
    "name": "홍길동"
  }


- Response: 201 Created (Body 없음)
- Error: 이미 존재하는 username일 경우 400 Bad Request와 에러 메시지 반환.

### 2. 회원 정보 조회 (Read)
- **Description**: 특정 유저의 정보를 조회합니다. 비밀번호는 보안상 반환하지 않습니다.
- **Path Variable**: username (조회할 유저의 아이디)

- **Response 200 OK** (application/json)
    ```json
  {
    "id": 1,
    "username": "testuser",
    "email": "test@example.com",
    "name": "홍길동"
  }


### 3. 회원 이름 수정 (Update)
- **Description**: 특정 유저의 '이름(name)'을 수정합니다.
- **Request Body**:  username (수정할 유저의 아이디)
    ```json
    {
      "name": "수정된이름"
    }
- **Response**: 200 OK (Body 없음)

### 4. 회원 탈퇴 (Delete)
- **Description**: 특정 유저의 데이터를 데이터베이스에서 삭제합니다.

- **Request Body**: username (삭제할 유저의 아이디)
    ```json
    {
      "name": "수정된이름"
    }
- **Response**: 204 No Content (Body 없음)


---

### 📂 2. 실무 표준 패키지 구조 (Directory Structure)

API 명세와 통합 테스트를 원활하게 구현하기 위해, 코드를 역할별로 나누는 **계층형 아키텍처(Layered Architecture)** 패키지 구조입니다. 인텔리제이에서 아래 트리에 맞게 폴더와 클래스(껍데기)들을 생성해 주시면 됩니다.

**[Java 소스 코드 영역]**
```text
src/main/java/com/insta/boards
 ├── BoardsApplication.java        (스프링 부트 메인 실행 클래스)
 │
 ├── controller/                   (안내 데스크: HTTP 요청 처리 및 응답)
 │    └── UserController.java
 │
 ├── service/                      (핵심 두뇌: 비즈니스 로직 및 트랜잭션 관리)
 │    └── UserService.java
 │
 ├── mapper/                       (DB 통신병: MyBatis 인터페이스)
 │    └── UserMapper.java
 │
 └── domain/                       (데이터 가방: DB 테이블과 1:1 매핑되는 객체)
      └── User.java
[리소스 영역 (설정 및 쿼리)]

Plaintext
src/main/resources
 ├── application.yml               (서버 및 DB 접속 등 전체 환경 설정)
 ├── schema.sql                    (H2 DB가 켜질 때 실행될 테이블 생성 스크립트)
 │
 └── mapper/                       (실제 SQL 쿼리문이 작성될 XML 파일들)
      └── UserMapper.xml           (★ 우리가 곧 가장 먼저 작성해야 할 파일)
[테스트 영역]

Plaintext
src/test/java/com/insta/boards
 └── UserIntegrationTest.java      (서버 전체를 띄워 CRUD 시나리오를 검증하는 통합 테스트)