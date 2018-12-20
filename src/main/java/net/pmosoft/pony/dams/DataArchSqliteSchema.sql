------------------------------
-- 패키지 정보
------------------------------

drop table TDACM00010
;

CREATE TABLE TDACM00010 (
 PKG_FUL_NM VARCHAR(20)      NULL --COMMENT '패키지풀명'
,PKG2_NM    CHAR(2)          NULL --COMMENT '패키지2자리명'
,PKG3_NM    CHAR(3)          NULL --COMMENT '패키지3자리명'
,PKG4_NM    CHAR(4)          NULL --COMMENT '패키지4자리명'
,PKG_HNM    VARCHAR(10)      NULL --COMMENT '패키지한글명'
,PKG_DESC   VARCHAR(200)     NULL --COMMENT '패키지설명'
,USE_YN     CHAR(1)          NULL --COMMENT '사용여부'
,REG_DTM    VARCHAR(14)      NULL --COMMENT '등록일시'
,REG_USR_ID VARCHAR(20)      NULL --COMMENT '등록자'
,UPD_DTM    VARCHAR(14)      NULL --COMMENT '변경일시'
,UPD_USR_ID VARCHAR(20)      NULL --COMMENT '변경자'
,PRIMARY KEY(PKG_FUL_NM)
)
;

INSERT INTO TDACM00010 VALUES('system','sy','sys','syst','시스템','시스템','Y',strftime('%Y%m%d%H%M%S','now','localtime'),'admin',strftime('%Y%m%d%H%M%S','now','localtime'),'admin');
INSERT INTO TDACM00010 VALUES('data architecter','da','das','dams','데이터 아키텍쳐','테이블스키마관리 시스템','Y',strftime('%Y%m%d%H%M%S','now','localtime'),'admin',strftime('%Y%m%d%H%M%S','now','localtime'),'admin');
INSERT INTO TDACM00010 VALUES('user','ur','usr','user','유저','유저 관리 시스템','Y',strftime('%Y%m%d%H%M%S','now','localtime'),'admin',strftime('%Y%m%d%H%M%S','now','localtime'),'admin');


﻿------------------------------
-- 약어 정보
------------------------------
DROP TABLE TDACM00020;

CREATE TABLE TDACM00020 (
 ABBR_NM     VARCHAR(10) NOT NULL -- COMMENT '약어명'
,ABBR_FUL_NM VARCHAR(20)     NULL -- COMMENT '약어풀명'
,ABBR_HNM    VARCHAR(10)     NULL -- COMMENT '약어한글명'
,ABBR_DESC   VARCHAR(200)    NULL -- COMMENT '약어설명'
,ABBR_APR_CD CHAR(2)         NULL -- COMMENT '약어승인코드' -- 01:요청 02:진행중 03:반려 04:반려취소 05:승인 06:승인취소 
,REG_DTM     VARCHAR(14)     NULL -- COMMENT '등록일시'
,REG_USR_ID  VARCHAR(20)     NULL -- COMMENT '등록자'
,UPD_DTM     VARCHAR(14)     NULL -- COMMENT '변경일시'
,UPD_USR_ID  VARCHAR(20)     NULL -- COMMENT '변경자'
,PRIMARY KEY(ABBR_NM)
)
;


------------------------------
-- 인포타입
------------------------------
DROP TABLE TDACM00030;

CREATE TABLE TDACM00030 (
 INFO_TYPE_NM   VARCHAR(30)  NOT NULL -- COMMENT '인포타입명' 
,INFO_TYPE_HNM  VARCHAR(30)      NULL -- COMMENT '인포타입한글명' 
,DOMAIN_NM      VARCHAR(20)      NULL -- COMMENT '도메인명' 
,DOMAIN_HNM     VARCHAR(20)      NULL -- COMMENT '도메인한글명' 
,DATA_TYPE_NM   VARCHAR(20)      NULL -- COMMENT '데이터타입명' 
,LEN            INT              NULL -- COMMENT '길이'
,DECIMAL_CNT    INT              NULL -- COMMENT '소수점수'
,DATA_TYPE_DESC VARCHAR(50)      NULL -- COMMENT '데이터타입설명'
,REG_DTM        VARCHAR(14)      NULL -- COMMENT '등록일시'
,REG_USR_ID     VARCHAR(20)      NULL -- COMMENT '등록자'
,UPD_DTM        VARCHAR(14)      NULL -- COMMENT '변경일시'
,UPD_USR_ID     VARCHAR(20)      NULL -- COMMENT '변경자'
,PRIMARY KEY(INFO_TYPE_NM)
)
;

SELECT * FROM TDACM00030
;


------------------------------
-- 용어사전
------------------------------
DROP TABLE TDACM00040
;

CREATE TABLE TDACM00040 (
 COL_NM         VARCHAR(20)  NOT NULL -- COMMENT '컬럼명' 
,COL_HNM        VARCHAR(20)      NULL -- COMMENT '컬럼한글명'
,COL_DESC       VARCHAR(200)     NULL -- COMMENT '컬럼설명'
,COL_ABBR_HNM   VARCHAR(30)      NULL -- COMMENT '컬럼약어한글명'
,DATA_TYPE_DESC VARCHAR(50)      NULL -- COMMENT '데이터타입설명'
,COL_STS_CD     CHAR(2)          NULL -- COMMENT '컬럼상태코드'
,REG_DTM        VARCHAR(14)      NULL -- COMMENT '등록일시'
,REG_USR_ID     VARCHAR(20)      NULL -- COMMENT '등록자'
,UPD_DTM        VARCHAR(14)      NULL -- COMMENT '변경일시'
,UPD_USR_ID     VARCHAR(20)      NULL -- COMMENT '변경자'
,PRIMARY KEY(COL_NM)
)
;

DROP TABLE TDACM00041
;

CREATE TABLE TDACM00041 (
 COL_NM         VARCHAR(20)      NULL COMMENT '컬럼명' 
,COL_HNM        VARCHAR(20)      NULL COMMENT '컬럼한글명'
,COL_DESC       VARCHAR(200)     NULL COMMENT '컬럼설명'
,COL_ABBR_HNM   VARCHAR(30)      NULL COMMENT '컬럼약어한글명'
,DATA_TYPE_DESC VARCHAR(50)      NULL COMMENT '데이터타입설명'
,COL_STS_CD     CHAR(2)          NULL COMMENT '컬럼상태코드'
,REG_DTM        VARCHAR(14)      NULL COMMENT '등록일시'
,REG_USR_ID     VARCHAR(20)      NULL COMMENT '등록자'
,UPD_DTM        VARCHAR(14)      NULL COMMENT '변경일시'
,UPD_USR_ID     VARCHAR(20)      NULL COMMENT '변경자'
) ENGINE=INNODB DEFAULT CHARSET=UTF8 COMMENT='용어사전임시1';
;

SELECT * FROM TDACM00041
;

DROP TABLE TDACM00042
;

CREATE TABLE TDACM00042 (
 COL_NM         VARCHAR(20)      NULL COMMENT '컬럼명' 
,COL_HNM        VARCHAR(20)      NULL COMMENT '컬럼한글명'
,COL_DESC       VARCHAR(200)     NULL COMMENT '컬럼설명'
,COL_ABBR_HNM   VARCHAR(30)      NULL COMMENT '컬럼약어한글명'
,DATA_TYPE_DESC VARCHAR(50)      NULL COMMENT '데이터타입설명'
,COL_STS_CD     CHAR(2)          NULL COMMENT '컬럼상태코드'
,REG_DTM        VARCHAR(14)      NULL COMMENT '등록일시'
,REG_USR_ID     VARCHAR(20)      NULL COMMENT '등록자'
,UPD_DTM        VARCHAR(14)      NULL COMMENT '변경일시'
,UPD_USR_ID     VARCHAR(20)      NULL COMMENT '변경자'
,PRIMARY KEY(COL_NM)
) ENGINE=INNODB DEFAULT CHARSET=UTF8 COMMENT='용어사전임시2';
;


------------------------------
-- 코드
------------------------------
DROP TABLE TDACM00060
;

CREATE TABLE TDACM00060 (
 CD_ID_NM       VARCHAR(20)  NOT NULL --COMMENT '코드아이디명'     -- BIZ_CD
,CD_ID_HNM      VARCHAR(20)      NULL --COMMENT '코드아이디한글명' -- 업무구분코드
,CD_ID_GRP_NM   VARCHAR(20)      NULL --COMMENT '코드아이디그룹명' -- AML
,CD             VARCHAR(20)  NOT NULL --COMMENT '코드'             -- 01
,CD_NM          VARCHAR(20)      NULL --COMMENT '코드명'           -- DEPOSIT
,CD_HNM         VARCHAR(20)      NULL --COMMENT '코드한글명'       -- 수신
,CD_DESC        VARCHAR(200)     NULL --COMMENT '코드설명'
,CD_TY_CD       CHAR(1)          NULL --COMMENT '코드유형코드' -- 1:필드 2:화면 3:프로그램
,CD_STS_CD      CHAR(2)          NULL --COMMENT '코드상태코드'
,REG_DTM        VARCHAR(14)      NULL --COMMENT '등록일시'
,REG_USR_ID     VARCHAR(20)      NULL --COMMENT '등록자'
,UPD_DTM        VARCHAR(14)      NULL --COMMENT '변경일시'
,UPD_USR_ID     VARCHAR(20)      NULL --COMMENT '변경자'
,PRIMARY KEY(CD_ID_NM,CD)
)
;

CREATE TABLE TDACM00061 (
 CD_ID_NM        VARCHAR(20)  NOT NULL -- COMMENT '코드아이디명'     -- BIZ_CD
,CD              VARCHAR(20)  NOT NULL -- COMMENT '코드'             -- 01
,CD_PARAM1_DESC VARCHAR(200)      NULL -- COMMENT '코드인자1설명'
,CD_PARAM1      VARCHAR(100)      NULL -- COMMENT '코드인자1'
,CD_PARAM2_DESC VARCHAR(200)      NULL -- COMMENT '코드인자2설명'
,CD_PARAM2      VARCHAR(100)      NULL -- COMMENT '코드인자2'
,CD_PARAM3_DESC VARCHAR(200)      NULL -- COMMENT '코드인자3설명'
,CD_PARAM3      VARCHAR(100)      NULL -- COMMENT '코드인자3'
,CD_PARAM4_DESC VARCHAR(200)      NULL -- COMMENT '코드인자4설명'
,CD_PARAM4      VARCHAR(100)      NULL -- COMMENT '코드인자4'
,CD_PARAM5_DESC VARCHAR(200)      NULL -- COMMENT '코드인자5설명'
,CD_PARAM5      VARCHAR(100)      NULL -- COMMENT '코드인자5'
,CD_PARAM6_DESC VARCHAR(200)      NULL -- COMMENT '코드인자6설명'
,CD_PARAM6      VARCHAR(100)      NULL -- COMMENT '코드인자6'
,CD_PARAM7_DESC VARCHAR(200)      NULL -- COMMENT '코드인자7설명'
,CD_PARAM7      VARCHAR(100)      NULL -- COMMENT '코드인자7'
,CD_PARAM8_DESC VARCHAR(200)      NULL -- COMMENT '코드인자8설명'
,CD_PARAM8      VARCHAR(100)      NULL -- COMMENT '코드인자8'
,CD_PARAM9_DESC VARCHAR(200)      NULL -- COMMENT '코드인자9설명'
,CD_PARAM9      VARCHAR(100)      NULL -- COMMENT '코드인자9'
,REG_DTM         VARCHAR(14)      NULL -- COMMENT '등록일시'
,REG_USR_ID      VARCHAR(20)      NULL -- COMMENT '등록자'
,UPD_DTM         VARCHAR(14)      NULL -- COMMENT '변경일시'
,UPD_USR_ID      VARCHAR(20)      NULL -- COMMENT '변경자'
,PRIMARY KEY(CD_ID_NM,CD)
)
;

------------------------------
-- 테이블정보
------------------------------
DROP TABLE TDACM00070;

CREATE TABLE TDACM00070 (
 DB_NM          VARCHAR(10)  NOT NULL -- COMMENT 'DB명' 
,OWNER          VARCHAR(15)      NULL -- COMMENT '소유자'
,TAB_NM         VARCHAR(40)      NULL -- COMMENT '테이블명'
,TAB_HNM        VARCHAR(40)      NULL -- COMMENT '테이블한글명'
,TAB_DESC       VARCHAR(200)     NULL -- COMMENT '테이블설명'
,REG_DTM        VARCHAR(14)      NULL -- COMMENT '등록일시'
,REG_USR_ID     VARCHAR(20)      NULL -- COMMENT '등록자'
,UPD_DTM        VARCHAR(14)      NULL -- COMMENT '변경일시'
,UPD_USR_ID     VARCHAR(20)      NULL -- COMMENT '변경자'
,PRIMARY KEY(DB_NM,OWNER,TAB_NM)
)
;

DROP TABLE TDACM00071;

CREATE TABLE TDACM00071 (
 DB_NM          VARCHAR(10)  NOT NULL COMMENT 'DB명' 
,OWNER          VARCHAR(15)      NULL COMMENT '소유자'
,TAB_NM         VARCHAR(40)      NULL COMMENT '테이블명'
,TAB_HNM        VARCHAR(40)      NULL COMMENT '테이블한글명'
,TAB_DESC       VARCHAR(200)     NULL COMMENT '테이블설명'
,REG_DTM        VARCHAR(14)      NULL COMMENT '등록일시'
,REG_USR_ID     VARCHAR(20)      NULL COMMENT '등록자'
,UPD_DTM        VARCHAR(14)      NULL COMMENT '변경일시'
,UPD_USR_ID     VARCHAR(20)      NULL COMMENT '변경자'
) ENGINE=INNODB DEFAULT CHARSET=UTF8 COMMENT='테이블임시정보'
;



------------------------------
-- 컬럼정보
------------------------------
DROP TABLE TDACM00080
;


CREATE TABLE TDACM00080 (
 DB_NM          VARCHAR(10)  NOT NULL -- COMMENT 'DB명' 
,OWNER          VARCHAR(15)  NOT NULL -- COMMENT '소유자'
,TAB_NM         VARCHAR(40)  NOT NULL -- COMMENT '테이블명'
,COL_ID         INT          NOT NULL -- COMMENT '컬럼아이디' 
,COL_NM         VARCHAR(40)  NOT NULL -- COMMENT '컬럼명' 
,COL_HNM        VARCHAR(40)      NULL -- COMMENT '컬럼한글명'
,DATA_TYPE_DESC VARCHAR(30)      NULL -- COMMENT '데이터타입설명'
,NULLABLE       VARCHAR(10)      NULL -- COMMENT 'NULL'
,PK             VARCHAR(10)      NULL -- COMMENT 'PK' 
,DATA_TYPE_NM   VARCHAR(20)      NULL -- COMMENT '데이터타입명' 
,LEN            INT              NULL -- COMMENT '길이'
,DECIMAL_CNT    INT              NULL -- COMMENT '소수점수'
,COL_DESC       VARCHAR(1000)    NULL -- COMMENT '컬럼설명'
,REG_DTM        VARCHAR(14)      NULL -- COMMENT '등록일시'
,REG_USR_ID     VARCHAR(20)      NULL -- COMMENT '등록자'
,UPD_DTM        VARCHAR(14)      NULL -- COMMENT '변경일시'
,UPD_USR_ID     VARCHAR(20)      NULL -- COMMENT '변경자'
,PRIMARY KEY(DB_NM,OWNER,TAB_NM,COL_NM)
)
;

DROP TABLE TDACM00081
;

CREATE TABLE TDACM00081 (
 DB_NM          VARCHAR(10)  NOT NULL COMMENT 'DB명' 
,OWNER          VARCHAR(15)  NOT NULL COMMENT '소유자'
,TAB_NM         VARCHAR(40)  NOT NULL COMMENT '테이블명'
,COL_ID         INT          NOT NULL COMMENT '컬럼아이디' 
,COL_NM         VARCHAR(40)  NOT NULL COMMENT '컬럼명' 
,COL_HNM        VARCHAR(40)      NULL COMMENT '컬럼한글명'
,DATA_TYPE_DESC VARCHAR(30)      NULL COMMENT '데이터타입설명'
,NULLABLE       VARCHAR(10)      NULL COMMENT 'NULL'
,PK             VARCHAR(10)      NULL COMMENT 'PK' 
,DATA_TYPE_NM   VARCHAR(20)      NULL COMMENT '데이터타입명' 
,LEN            INT              NULL COMMENT '길이'
,DECIMAL_CNT    INT              NULL COMMENT '소수점수'
,COL_DESC       VARCHAR(1000)     NULL COMMENT '컬럼설명'
,REG_DTM        VARCHAR(14)      NULL COMMENT '등록일시'
,REG_USR_ID     VARCHAR(20)      NULL COMMENT '등록자'
,UPD_DTM        VARCHAR(14)      NULL COMMENT '변경일시'
,UPD_USR_ID     VARCHAR(20)      NULL COMMENT '변경자'
) ENGINE=INNODB DEFAULT CHARSET=UTF8 COMMENT='컬럼추출정보'
;

CREATE TABLE TDACM00082 (
 DB_NM          VARCHAR(10)  NOT NULL COMMENT 'DB명' 
,OWNER          VARCHAR(15)  NOT NULL COMMENT '소유자'
,TAB_NM         VARCHAR(40)  NOT NULL COMMENT '테이블명'
,COL_ID         INT          NOT NULL COMMENT '컬럼아이디' 
,COL_NM         VARCHAR(40)  NOT NULL COMMENT '컬럼명' 
,COL_HNM        VARCHAR(40)      NULL COMMENT '컬럼한글명'
,DATA_TYPE_DESC VARCHAR(30)      NULL COMMENT '데이터타입설명'
,NULL_YN        CHAR(1)      NULL COMMENT '데이터타입설명'
,PK_YN          CHAR(1)      NULL COMMENT '데이터타입설명' 
,DATA_TYPE_NM   VARCHAR(20)      NULL COMMENT '데이터타입명' 
,LEN            INT              NULL COMMENT '길이'
,DECIMAL_CNT    INT              NULL COMMENT '소수점수'
,COL_DESC       VARCHAR(200)     NULL COMMENT '컬럼설명'
,REG_DTM        VARCHAR(14)      NULL COMMENT '등록일시'
,REG_USR_ID     VARCHAR(20)      NULL COMMENT '등록자'
,UPD_DTM        VARCHAR(14)      NULL COMMENT '변경일시'
,UPD_USR_ID     VARCHAR(20)      NULL COMMENT '변경자'
) ENGINE=INNODB DEFAULT CHARSET=UTF8 COMMENT='컬럼추출정보'
;
