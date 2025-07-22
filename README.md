#  Mustache Blog 프로젝트

Spring Boot 기반의 블로그 웹 애플리케이션입니다. 기본적인 게시글 CRUD, 사용자 인증, 댓글, 평점 등 블로그에서 제공할 수 있는 핵심 기능들을 구현했습니다.

---

## 주요 기능 (Features)

* 회원가입 / 로그인 / 로그아웃

* 게시글 등록, 수정, 삭제

* 게시글 상세보기

* 사용자 프로필

* H2 Console 접속 지원 (개발용 DB 확인)

---

##  기술 스택 (Tech Stack)

* **Java 17**
* **Spring Boot 3.2+**
* **Spring Data JPA**
* **H2 Database (In-memory)**
* **Lombok**
* **Mustache Template Engine**
* **Bootstrap 5 (UI 구성)**
* **Maven or Gradle (확인 필요)**

---

## 🗂 디렉토리 구조

```bash
src
├── main
│   ├── java/com/tenco/mustache
│   │   ├── aop
│   │   ├── config
│   │   ├── controller
│   │   ├── domain (엔티티)
│   │   ├── dto
│   │   ├── errors
│   │   ├── repository
│   │   ├── service
│   │   └── MustacheApplication.java
│   └── resources
│       ├── static (이미지, CSS 등)
│       ├── templates (mustache 페이지)
│       ├── application.yml
│       └── db/data.sql (초기 더미데이터)
```

---

## ⚙️ 실행 방법 (Run Project)

```bash
# 1. Git 클론
$ git clone https://github.com/jangseungwon123/class_blog_JSW.git

# 2. 프로젝트 실행 (IDE 또는 CLI)
$ ./gradlew bootRun   # 또는 mvn spring-boot:run

# 3. 웹 접속
http://localhost:8080/

# 4. H2 DB 콘솔 (개발 전용)
http://localhost:8080/h2-console

# JDBC URL (application.yml 기준)
jdbc:h2:mem:test;MODE=MySQL
```

---

## 💬 기타 정보

* 게시판 상세 화면에서 `댓글`, `작성자`, `작성 시간`을 확인할 수 있습니다.
* 화면은 `Mustache` 템플릿 엔진으로 구성되어 있으며 `Bootstrap` 기반의 UI 디자인 적용

---

##  개발자 정보

* 이름: 장승원 (Jang Seungwon)
* GitHub: [jangseungwon123](https://github.com/jangseungwon123)

---

 감사합니다! 이 프로젝트는 개인 포트폴리오 및 실습용으로 제작되었습니다.

---

 **이 README는 자동 생성되었습니다. 원하시는 내용이나 스타일이 있다면 자유롭게 수정해주세요!**

---

 일본어 표현:

* ブログを作成しました！見ていただいてありがとうございます！ 🙇‍♂️
