# Project : The Factory 

## Introduce

"The Factory" project is a project that develops various types of systems used in the manufacturing industry into integrated solutions. 

## 로컬환경에서의 데이터베이스 설정
- `/local-container` 폴더에 docker-compose.yml 을 이용하여 PostgreSQL 컨테이너를 실행한다. 
- 스키마가 두 개 필요한데 `resources/schema.sql` 파일 내용을 카피하여 `business`와 `personal` 스키마를 생성한다.
- 스프링부트를 구동하면 JPA에 의해 테이블이 자동 생성된다. 
- `sysadm.sql`파일의 스크립트를 참조하여 임시 관리자 아이디를 추가해준다. (비번은 1234)