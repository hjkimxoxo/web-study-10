CREATE TABLE MEMBER(
	name varchar2(20),
	userid varchar2(10),
	pwd varchar2(10),
	email varchar2(20),
	phone char(13),
	admin number(1) DEFAULT 0, --0사용자, 1관리자
	joinDate date DEFAULT sysdate,  
	PRIMARY KEY(userid)
);

SELECT * FROM USER_tables;

--테이블 컬럼 조회
SELECT * FROM USER_TAB_COLUMNS WHERE TABLE_name = 'MEMBER';

--제약조건 검색
SELECT * FROM USER_CONSTRAINTS WHERE table_name = 'MEMBER';

ALTER TABLE MEMBER MODIFY(name varchar2(20));

SELECT * FROM user_tables;

CREATE TABLE TITLE(
	TITLE_NO NUMBER PRIMARY KEY,
	TITLE_NAME VARCHAR2(40)
);

SELECT * FROM title;
select title_no, title_name from title;

CREATE TABLE department(
	dept_no NUMBER PRIMARY key, 
	dept_name varchar2(30), 
	floor NUMBER
);


SELECT * FROM employee;



-- EMPLOYEE 테이블 생성
CREATE TABLE EMPLOYEE (
    EMP_NO   NUMBER,
    EMP_NAME VARCHAR2(20) NOT NULL,
    TNO      NUMBER,
    MANAGER  NUMBER,
    SALARY   NUMBER,
    DNO      NUMBER,
    EMAIL    varchar2(60) NOT NULL,
    PASSWD   varchar2(60) NOT NULL,
    REGDATE  DATE DEFAULT SYSDATE,
    TEL      char(13),
    pic_url  varchar2(100),
    CONSTRAINT EMPLOYEE_EMPNO_PK   PRIMARY KEY (EMP_NO),
    CONSTRAINT EMPLOYEE_TNO_FK     FOREIGN KEY (TNO)     REFERENCES TITLE(TITLE_NO) ON DELETE SET NULL, 
    CONSTRAINT EMPLOYEE_MANAGER_FK FOREIGN KEY (MANAGER) REFERENCES EMPLOYEE(EMP_NO),
    CONSTRAINT EMPLOYEE_SALARY_CK  CHECK (SALARY < 6000000),
    CONSTRAINT EMPLOYEE_DNO_FK     FOREIGN KEY (DNO) REFERENCES DEPARTMENT(DEPT_NO) ON DELETE CASCADE
);


