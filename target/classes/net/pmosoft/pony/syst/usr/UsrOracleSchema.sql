------------------------------
-- 사용자  스키마
------------------------------
DROP TABLE TSYUR0001 CASCADE CONSTRAINTS;

CREATE TABLE TSYUR0001 (
Usr_ID      VARCHAR2(20)    NOT NULL,
Usr_EMAIL   VARCHAR2(40)    NOT NULL,
Usr_PW      VARCHAR2(20)    NOT NULL,
Usr_NM      VARCHAR2(40)    NOT NULL,
Usr_AGE     INT                 NULL,
USE_YN       CHAR(1)         NOT NULL,
REG_DT       DATE                NULL,
REG_Usr_ID  VARCHAR2(20)        NULL,   
UPD_DT       DATE                NULL,   
UPD_Usr_ID  VARCHAR2(20)        NULL,
PRIMARY KEY (Usr_ID)
);

COMMENT ON TABLE  TSYUR0001             IS '사용자(TSYUR0001)';
COMMENT ON COLUMN TSYUR0001.Usr_ID     IS '사용자아이디';
COMMENT ON COLUMN TSYUR0001.Usr_EMAIL  IS '사용자이메일';
COMMENT ON COLUMN TSYUR0001.Usr_PW     IS '사용자암호';
COMMENT ON COLUMN TSYUR0001.Usr_NM     IS '사용자명';
COMMENT ON COLUMN TSYUR0001.Usr_AGE    IS '사용자나이';
COMMENT ON COLUMN TSYUR0001.USE_YN      IS '사용여부';
COMMENT ON COLUMN TSYUR0001.REG_DT      IS '등록일시';
COMMENT ON COLUMN TSYUR0001.REG_Usr_ID IS '등록사용자아이디';
COMMENT ON COLUMN TSYUR0001.UPD_DT      IS '변경일시';
COMMENT ON COLUMN TSYUR0001.UPD_Usr_ID IS '변경사용자아이디';

ANALYZE TABLE TSYUR0001 COMPUTE STATISTICS;

INSERT INTO TSYUR0001 VALUES('admin','admin@pmosoft.net','1','ADMIN',50,'Y',SYSDATE,'admin',SYSDATE,'admin');
INSERT INTO TSYUR0001 VALUES('admin2','admin2@pmosoft.net','1','ADMIN',50,'Y',SYSDATE,'admin2',SYSDATE,'admin2');
INSERT INTO TSYUR0001 VALUES('admin3','admin3@pmosoft.net','1','ADMIN',50,'Y',SYSDATE,'admin3',SYSDATE,'admin3');
INSERT INTO TSYUR0001 VALUES('admin4','admin4@pmosoft.net','1','ADMIN',50,'Y',SYSDATE,'admin4',SYSDATE,'admin4');

