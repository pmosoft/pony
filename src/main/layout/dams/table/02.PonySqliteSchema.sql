------------------------------
-- JDBC
------------------------------
DROP TABLE TDACM00010;

CREATE TABLE TDACM00010 (
 JDBC_NM     VARCHAR(30) NOT NULL -- COMMENT 'JDBC명'
,DRIVER      VARCHAR(100)    NULL -- COMMENT '드라이버'
,URL         VARCHAR(100)    NULL -- COMMENT 'URL'
,USR_ID      VARCHAR(50)     NULL -- COMMENT '유저아이디'
,USR_PW      VARCHAR(300)    NULL -- COMMENT '유저패스워드'
,REG_DTM     VARCHAR(14)     NULL -- COMMENT '등록일시'
,REG_USR_ID  VARCHAR(20)     NULL -- COMMENT '등록자'
,UPD_DTM     VARCHAR(14)     NULL -- COMMENT '변경일시'
,UPD_USR_ID  VARCHAR(20)     NULL -- COMMENT '변경자'
,PRIMARY KEY(JDBC_NM)
)
;

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
------------------------------
-- 코드
------------------------------
DROP TABLE TDACM00060
;

SELECT * FROM TDACM00060

SELECT * FROM TDACM00062

INSERT INTO TDACM00060
SELECT * FROM TDACM00062

COMMIT;

INSERT INTO TDACM00060
SELECT
A.CD_ID_GRP_NM  AS CD_GRP
, A.CD_ID_GRP_NM AS CD_GRP_NM
, A.CD_ID_NM AS CD_ID
, A.CD_ID_HNM AS CD_ID_NM
, A.CD
, A.CD_HNM AS CD_NM
,A.CD_DESC
,A.CD_TY_CD
, A.REG_DTM
,A.REG_USR_ID
,A.UPD_DTM
,A.UPD_USR_ID
FROM TDACM00060 A

CREATE TABLE TDACM00060 (
 CD_GRP         VARCHAR(20)  NOT NULL --COMMENT '코드그룹'       -- META
,CD_GRP_NM      VARCHAR(20)      NULL --COMMENT '코드그룹명'      -- 메타
,CD_ID          VARCHAR(20)  NOT NULL --COMMENT '코드아이디'     -- COL_STS_CD
,CD_ID_NM       VARCHAR(20)      NULL --COMMENT '코드아이디명'    -- 컬럼상태코드
,CD             VARCHAR(20)  NOT NULL --COMMENT '코드'          -- 01
,CD_NM          VARCHAR(20)      NULL --COMMENT '코드명'        -- 요청
,CD_DESC        VARCHAR(200)     NULL --COMMENT '코드설명'
,CD_TY_CD       CHAR(1)          NULL --COMMENT '코드유형코드'    -- 1:필드 2:화면 3:프로그램
,REG_DTM        VARCHAR(14)      NULL --COMMENT '등록일시'
,REG_USR_ID     VARCHAR(20)      NULL --COMMENT '등록자'
,UPD_DTM        VARCHAR(14)      NULL --COMMENT '변경일시'
,UPD_USR_ID     VARCHAR(20)      NULL --COMMENT '변경자'
,PRIMARY KEY(CD_GRP,CD_ID,CD)
)
;

CREATE TABLE TDACM00061 (
 CD_ID           VARCHAR(20)  NOT NULL -- COMMENT '코드아이디'     -- COL_STS_CD
,CD              VARCHAR(20)  NOT NULL -- COMMENT '코드'         -- 01
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
CREATE VIEW VDACM00070 AS
SELECT
     A.JDBC_NM
   , A.OWNER
   , A.TAB_NM
   , A.TAB_HNM
   , A.TAB_ROWS
   , A.TAB_REG_DT
   , A.TAB_REG_DT2
   , A.TAB_UPD_DT
   , A.TAB_UPD_DT2
FROM   TDACM00080 A

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
-- 테이블정보
------------------------------
DROP TABLE TDACM00080
;

-- COMMENT '테이블정보'
CREATE TABLE TDACM00080 (
 JDBC_NM        VARCHAR(10)  NOT NULL -- COMMENT 'JDBC명'
,OWNER          VARCHAR(15)  NOT NULL -- COMMENT '소유자'
,TAB_NM         VARCHAR(40)  NOT NULL -- COMMENT '테이블명'
,TAB_HNM        VARCHAR(40)      NULL -- COMMENT '테이블한글명'
,COL_ID         INT          NOT NULL -- COMMENT '컬럼아이디'
,COL_NM         VARCHAR(40)  NOT NULL -- COMMENT '컬럼명'
,COL_HNM        VARCHAR(40)      NULL -- COMMENT '컬럼한글명'
,DATA_TYPE_DESC VARCHAR(30)      NULL -- COMMENT '데이터타입설명'
,NULLABLE       VARCHAR(10)      NULL -- COMMENT 'NULL'
,PK             VARCHAR(10)      NULL -- COMMENT 'PK'
,DATA_TYPE_NM   VARCHAR(20)      NULL -- COMMENT '데이터타입명'
,LEN            INT              NULL -- COMMENT '길이'
,DECIMAL_CNT    INT              NULL -- COMMENT '소수점수'
,TAB_ROWS       INT              NULL -- COMMENT '테이블건수'
,TAB_REG_DT     VARCHAR(8)       NULL -- COMMENT '테이블등록일자'
,TAB_REG_DT2    VARCHAR(10)      NULL -- COMMENT '테이블등록일자2'
,TAB_UPD_DT     VARCHAR(8)       NULL -- COMMENT '테이블변경일자'
,TAB_UPD_DT2    VARCHAR(10)      NULL -- COMMENT '테이블변경일자2'
,REG_DTM        VARCHAR(14)      NULL -- COMMENT '등록일시'
,REG_USR_ID     VARCHAR(20)      NULL -- COMMENT '등록자'
,UPD_DTM        VARCHAR(14)      NULL -- COMMENT '변경일시'
,UPD_USR_ID     VARCHAR(20)      NULL -- COMMENT '변경자'
,PRIMARY KEY(JDBC_NM,OWNER,TAB_NM,COL_NM)
)
;

DROP TABLE TDACM00081
;

CREATE TABLE TDACM00081 (
 JDBC_NM        VARCHAR(10)  NOT NULL -- COMMENT 'JDBC명'
,OWNER          VARCHAR(15)  NOT NULL -- COMMENT '소유자'
,TAB_NM         VARCHAR(40)  NOT NULL -- COMMENT '테이블명'
,TAB_HNM        VARCHAR(40)      NULL -- COMMENT '테이블한글명'
,COL_ID         INT          NOT NULL -- COMMENT '컬럼아이디'
,COL_NM         VARCHAR(40)  NOT NULL -- COMMENT '컬럼명'
,COL_HNM        VARCHAR(40)      NULL -- COMMENT '컬럼한글명'
,DATA_TYPE_DESC VARCHAR(30)      NULL -- COMMENT '데이터타입설명'
,NULLABLE       VARCHAR(10)      NULL -- COMMENT 'NULL'
,PK             VARCHAR(10)      NULL -- COMMENT 'PK'
,DATA_TYPE_NM   VARCHAR(20)      NULL -- COMMENT '데이터타입명'
,LEN            INT              NULL -- COMMENT '길이'
,DECIMAL_CNT    INT              NULL -- COMMENT '소수점수'
,TAB_ROWS       INT              NULL -- COMMENT '테이블건수'
,TAB_REG_DT     VARCHAR(8)       NULL -- COMMENT '테이블등록일자'
,TAB_REG_DT2    VARCHAR(10)      NULL -- COMMENT '테이블등록일자2'
,TAB_UPD_DT     VARCHAR(8)       NULL -- COMMENT '테이블변경일자'
,TAB_UPD_DT2    VARCHAR(10)      NULL -- COMMENT '테이블변경일자2'
,REG_DTM        VARCHAR(14)      NULL -- COMMENT '등록일시'
,REG_USR_ID     VARCHAR(20)      NULL -- COMMENT '등록자'
,UPD_DTM        VARCHAR(14)      NULL -- COMMENT '변경일시'
,UPD_USR_ID     VARCHAR(20)      NULL -- COMMENT '변경자'
,PRIMARY KEY(JDBC_NM,OWNER,TAB_NM,COL_NM)
)
;
