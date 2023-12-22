# JPA SHOP

### Summary
JPA 실습을 위한 repo입니다.

## 프로젝트 전체 구조

## 기술스택
>Java, Spring Boot, JPA, Lombok, Gradle, Junit5

## 프로젝트 주요 관심사
 - JPA를 이용한 CRUD 구현 
 - Junit5를 이용한 테스트 코드 구현 


## 화면 설계 및 진행상황

### 회원가입 및 회원 정보
| Feature | Request | 주소           | 설명       | 체크  |
|-------|---------|--------------|----------|-----|
| 회원 가입 | POST    | /members/new | 회원 가입    | ☑️  |
| 회원 목록 | GET     | /members     | 회원 목록 조회 | ☑️  |

### 주문 신청 / 취소
| Feature | Request | 주소      | 설명       |체크
|---------|---------|---------|----------|-----|
| 주문 화면   | GET     | /order  | 주문 화면 호출 |☑️ |
| 주문 신청   | POST    | /order  | 주문 신청    |☑️ |
| 주문 검색   | GET     | /orders | 주문 목록 검색 |☑️ |
| 주문 취소   | POST    | /orders/{orderId}/cancel | 주문 취소    |☑️ |

### 상품 등록
| Feature | Request | 주소                   | 설명          |체크
|---------|---------|----------------------|-------------|-----|
| 상품 화면   | GET     | /items/new           | 상품 등록 화면 호출 |☑️ |
| 상품 등록   | POST    | /items/new           | 상품 등록 신청    |☑️ |
| 상품 목록   | GET     | /items               | 상품 목록       |☑️ |
| 상품 수정화면 | GET     | /items/{itemId}/edit | 상품 수정 화면 호출 |☑️ |
| 상품 정보 수정 | POST    | /items/{itemId}/edit | 상품 정보 수정    |☑️ |

## 프로젝트 구조
- 추후 추가 예정

## ERD
- 추후 추가 예정

