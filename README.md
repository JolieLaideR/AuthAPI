# AuthAPI
### 1. 사용기술
1) spring-boot
2) spring-security
3) Java
4) Maven
5) MariaDB
6) JDBC
7) Lombok
8) Logger

### 2. 구현 스펙
1) 전화번호와 인증번호를 MAP을 이용하여 메모리에 올리고 CHECK
2) spring security를 이용하여 비밀번호 암호화 후 저장 & 로그인 인증
3) AESEncode를 이용해서 전화번호 암호화 후 DB 저장
4) JSON 이용하여 RESTFUL API 통신 & Return

### 3. 구현 범위 
1) 전화번호 인증
2) 회원가입
3) 로그인
4) 내 정보 보기
5) 비밀번호 

### 4. 실행 방법
1) application.yml 에서 DB 설정, 포트 등 설정
2) 로컬에서 프로젝트를 실행한다(spring-boot 기반)
3) PostMan 등을 이용하여 API 통신
4) 전화번호 인증
- 주소 예시 : http://localhost:8080/api/numberCheck.do
- JSON 예시
{
    "phoneNo" : "01000000000"
}
5) 회원가입
**(회원 가입 시 닉네임, 번호, 이메일 중복 체크 (고유값))**
- 주소 예시 : http://localhost:8080/api/auth.do
- JSON 예시
{
    "verifyNumber":"123456",
    "UserName":"a",
    "UserPw":"1234",
    "NickName":"a",
    "Email":"a@test.com"
}
6) 로그인
**(로그인 후 내 회원 정보 리턴)**
- 주소 예시 : http://localhost:8080/api/login.do
- JSON 예시
a.{
    "Nickname":"Lee",
    "UserPw":"1234"
}
b.{
    "Email":"a@test.com",
    "UserPw":"1234"
}
c.{
    "PhoneNo":"01000000000",
    "UserPw":"1234"
}
7) 비밀번호 리셋
**(4번의 전화번호 인증 후 가능)**
- 주소 예시 : http://localhost:8080/api/resetPw.do
- JSON 예시
{
    "verifyNumber":"123456",
    "UserPw":"1234"
}
