------------------------------
-- 패키지 설명
------------------------------
DROP TABLE TDACM0001 CASCADE CONSTRAINTS;

CREATE TABLE TDACM0001 (
 UID        INT          NOT NULL
,PKG2_NM    CHAR(2)          NULL
,PKG3_NM    CHAR(3)          NULL
,PKG4_NM    CHAR(4)          NULL
,PKG_FUL_NM VARCHAR2(20)      NULL
,PKG_HNM    VARCHAR2(10)      NULL
,PKG_DESC   VARCHAR2(200)     NULL
,USE_YN     CHAR(1)      NOT NULL -- DEFALULT : Y
,REG_DTM    VARCHAR2(14)      NULL
,REG_USR_ID VARCHAR2(20)      NULL
,UPD_DTM    VARCHAR2(14)      NULL
,UPD_USR_ID VARCHAR2(20)      NULL
,COMSTRAINT TDACM0001_PK PRIMARY_KEY(UID)
);

COMMENT ON TABLE  TDACM0001                 IS '패키지정보(TDACM0001)';
COMMENT ON COLUMN TDACM0001.UID             IS 'UniqueID';
COMMENT ON COLUMN TDACM0001.PKG2_NM         IS '패키지2자리명칭';
COMMENT ON COLUMN TDACM0001.PKG3_NM         IS '패키지3자리명칭';
COMMENT ON COLUMN TDACM0001.PKG4_NM         IS '패키지4자리명칭';
COMMENT ON COLUMN TDACM0001.PKG_FUL_NM      IS '패키지풀네임';
COMMENT ON COLUMN TDACM0001.PKG_HNM         IS '패키지한글명칭';
COMMENT ON COLUMN TDACM0001.PKG_DESC        IS '패키지설명';
COMMENT ON COLUMN TDACM0001.USE_YN          IS '사용여부';
COMMENT ON COLUMN TDACM0001.REG_DTM         IS '등록일시';
COMMENT ON COLUMN TDACM0001.REG_USR_ID      IS '등록자';
COMMENT ON COLUMN TDACM0001.UPD_DTM         IS '변경일시';
COMMENT ON COLUMN TDACM0001.UPD_USR_ID      IS '변경자';


INSERT INTO TDACM0001 VALUES(1,'sy','sys','syst','system','시스템','시스템','Y',SYSDATE,'admin',SYSDATE,'admin');
INSERT INTO TDACM0001 VALUES(2,'da','das','dams','data architecter','데이터 아키텍쳐','테이블스키마관리 시스템','Y',SYSDATE,'admin',SYSDATE,'admin');
INSERT INTO TDACM0001 VALUES(3,'ur','usr','user','user','유저','유저 관리 시스템','Y',SYSDATE,'admin',SYSDATE,'admin');
