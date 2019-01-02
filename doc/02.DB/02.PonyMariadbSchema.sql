SELECT ROW_NUMBER() OVER()
FROM   sttl.tsscm00020 A
    
SELECT ROW_NUMBER() OVER()
FROM   STTL.TSSCM00020 A
 
    SELECT DISTINCT
           A.USR_ID          -- 사용자아이디    
         , A.STTL_NM         -- 자막명      
         , A.STTL_CD         -- 자막구분(1:외국어,2:모국어) 
         , A.STTL_STM        -- 자막시작시각      
         , A.STTL_ETM        -- 자막종료시각      
         , A.STTL_DESC       -- 자막문장내용
         , A.REG_DTM AS REG_DTM
         , A.REG_USR_ID
         , A.UPD_DTM AS UPD_DTM
         , A.UPD_USR_ID
    FROM   STTL.TSSCM00020 A
    WHERE  A.USR_ID = 'lifedomy@gmail.com'
    AND    A.STTL_NM = (
                        SELECT MAX(STTL_NM) 
                        FROM   STTL.TSSCM00010 
                        WHERE  USR_ID = 'lifedomy@gmail.com'
                        AND    REG_DTM = (SELECT MAX(REG_DTM) 
                                          FROM STTL.TSSCM00010 
                                          WHERE USR_ID = 'lifedomy@gmail.com'
                                          ) 
                        )
    AND    LENGTH(TRIM(A.STTL_DESC)) > 0
    ORDER BY A.STTL_CD,CAST(STTL_STM AS INT), A.REG_DTM


SELECT SUBSTRING('lifedomy@gmail.com',1,INSTR('lifedomy@gmail.com','@')-1)

UPDATE STTL.TSYUR00010 SET USR_NM = 'lifedomy'


SELECT * FROM STTL.TSYUR00010

SELECT A.USR_ID          -- 사용자아이디    
         , A.STTL_NM         -- 자막명      
         , A.FSTTL_NM        -- 외국어자막명      
         , A.MSTTL_NM        -- 모국어자막명      
         , DATE_FORMAT(A.REG_DTM,'%Y.%m.%d %H:%i:%S') AS REG_DTM
         , A.REG_USR_ID
         , DATE_FORMAT(A.UPD_DTM,'%Y.%m.%d %H:%i:%S') AS UPD_DTM
         , A.UPD_USR_ID
    FROM   STTL.TSSCM00010 A

SELECT * FROM STTL.TSSCM00010
    
SELECT USR_ID, STTL_NM, COUNT(*)
FROM STTL.TSSCM00020
GROUP BY USR_ID, STTL_NM
    
select 3600*24*365
31536000
    SELECT DISTINCT
           A.USR_ID          -- 사용자아이디    
         , A.STTL_NM         -- 자막명      
         , A.STTL_CD         -- 자막구분(1:외국어,2:모국어) 
         , A.STTL_STM        -- 자막시작시각      
         , A.STTL_ETM        -- 자막종료시각      
         , A.STTL_DESC       -- 자막문장내용
         , A.REG_DTM AS REG_DTM
         , A.REG_USR_ID
         , A.UPD_DTM AS UPD_DTM
         , A.UPD_USR_ID
    FROM   STTL.TSSCM00020 A
    WHERE  A.USR_ID = 'lifedomy@gmail.com'
    AND    A.STTL_NM = (
                        SELECT MAX(STTL_NM) 
                        FROM   STTL.TSSCM00010 
                        WHERE  USR_ID = 'lifedomy@gmail.com'
                        AND    REG_DTM = (SELECT MAX(REG_DTM) 
                                          FROM STTL.TSSCM00010 
                                          WHERE USR_ID = 'lifedomy@gmail.com'
                                          ) 
                        )
    ORDER BY A.STTL_CD,CAST(STTL_STM AS INT), A.REG_DTM


DELIMITER

BEGIN

INSERT INTO STTL.TSSCM00020 VALUES ('lifedomy@gmail.com','Silicon.Valley.S01E02.720p.HDTV.x264-2HD.smi','2','1602830','1606670','- Thank you.<BR>- Sorry. Ok.',now(),NULL,now(),NULL);
INSERT INTO STTL.TSSCM00020 VALUES ('lifedomy@gmail.com','Silicon.Valley.S01E02.720p.HDTV.x264-2HD.smi','2','1602830','1606670','- Thank you.<BR>- Sorry. Ok.',now(),NULL,now(),NULL);
		

END

SELECT  
           A.USR_ID          -- 사용자아이디    
         , A.STTL_NM         -- 자막명      
         , A.STTL_CD         -- 자막구분(1:외국어,2:모국어) 
         , A.STTL_STM        -- 자막시작시각      
         , A.STTL_ETM        -- 자막종료시각      
         , A.STTL_DESC       -- 자막문장내용
         , A.REG_DTM AS REG_DTM
         , A.REG_USR_ID
         , A.UPD_DTM AS UPD_DTM
         , A.UPD_USR_ID
    FROM   STTL.TSSCM00020 A
    WHERE  A.USR_ID = 'lifedomy@gmail.com'
    AND    A.STTL_NM = (
                        SELECT MAX(STTL_NM) 
                        FROM STTL.TSSCM00010 
                        WHERE USR_ID = 'lifedomy@gmail.com'
                        AND   REG_DTM = (SELECT MAX(REG_DTM) 
                                         FROM STTL.TSSCM00010 
                                         WHERE USR_ID = 'lifedomy@gmail.com'
                                         ) 
                        )
    ORDER BY A.REG_DTM

DELETE FROM STTL.TSSCM00010 
                        
DELETE FROM STTL.TSSCM00020 
    
SELECT * FROM STTL.TSSCM00010
    
SELECT * FROM STTL.TSSCM00020

SELECT * FROM STTL.TSYUR00010


DELETE FROM STTL.TSYUR00010

INSERT INTO STTL.TSYUR00010
	(
	     USR_ID
		,USR_EMAIL
		,USR_PW
		,USR_NM
		,USR_AGE
		,USE_YN
		,REG_DTM
		,REG_USR_ID
		,UPD_DTM
		,UPD_USR_ID
	) VALUES ( 
         'lifedomy@gmail.com'
		,'lifedomy@gmail.com'
		,'12345678'
		,0
		,CAST('' as INT)
		,'Y'
		,CURDATE()
		,'ADMIN'
		,CURDATE() 
		,'ADMIN' 
	)
 
-----------------------------
-- 유저
-----------------------------
DROP TABLE STTL.TSYUR00010;

CREATE TABLE STTL.TSYUR00010 (
 USR_EMAIL    VARCHAR(40)    NOT NULL comment '사용자이메일'    
,USR_ID       VARCHAR(40)        NULL COMMENT '사용자아이디'    
,USR_PW       VARCHAR(100)       NULL comment '사용자암호'      
,USR_NM       VARCHAR(40)        NULL comment '사용자명'        
,USR_AGE      INT                NULL comment '사용자나이'      
,USE_YN       CHAR(1)            NULL comment '사용여부'        
,REG_DTM      TIMESTAMP          NULL COMMENT '등록일시'
,REG_USR_ID   VARCHAR(40)        NULL COMMENT '등록자'
,UPD_DTM      TIMESTAMP          NULL COMMENT '변경일시'
,UPD_USR_ID   VARCHAR(40)        NULL COMMENT '변경자'
,PRIMARY KEY (USR_EMAIL)
) ENGINE=INNODB DEFAULT CHARSET=UTF8 COMMENT='사용자';
;

SELECT   
	         USR_ID
			,USR_EMAIL
			,USR_PW
			,USR_NM
			,USR_AGE
			,USE_YN
			,date_format(REG_DTM,'%Y.%m.%d %H:%i:%S') AS REG_DT
			,REG_USR_ID
			,date_format(UPD_DTM,'%Y.%m.%d %H:%i:%S') AS UPD_DT
			,UPD_USR_ID 
	FROM   TSYUR00010
    WHERE  USR_ID = 'lifedomy@gmail.com'
	ORDER BY USR_ID
 
-----------------------------
-- 유저자막목록
-----------------------------
	
	
DROP TABLE STTL.TSSCM00010;

CREATE TABLE STTL.TSSCM00010 (
 USR_ID       VARCHAR(40)    NOT NULL COMMENT '사용자아이디'    
,STTL_NM      VARCHAR(100)   NOT NULL COMMENT '자막명'      
,FSTTL_NM     VARCHAR(100)       NULL COMMENT '외국어자막명'      
,MSTTL_NM     VARCHAR(100)       NULL COMMENT '모국어자막명'      
,REG_DTM      TIMESTAMP          NULL COMMENT '등록일시'
,REG_USR_ID   VARCHAR(40)        NULL COMMENT '등록자'
,UPD_DTM      TIMESTAMP          NULL COMMENT '변경일시'
,UPD_USR_ID   VARCHAR(40)        NULL COMMENT '변경자'
,PRIMARY KEY (USR_ID,STTL_NM)
) ENGINE=INNODB DEFAULT CHARSET=UTF8 COMMENT='유저자막목록';
;

-----------------------------
-- 유저자막내용
-----------------------------
SELECT * FROM STTL.TSSCM00020 
WHERE STTL_NM LIKE 'V%'
AND STTL_CD = '2'
ORDER BY CAST(STTL_STM AS INT);

    SELECT distinct A.STTL_CD         -- 자막구분(1:외국어,2:모국어) 
    FROM   STTL.TSSCM00020 A
    WHERE  A.USR_ID = 'lifedomy@gmail.com'
    AND    A.STTL_NM = (
                        SELECT STTL_NM 
                        FROM STTL.TSSCM00010 
                        WHERE USR_ID = 'lifedomy@gmail.com'
                        AND   REG_DTM = (SELECT MAX(REG_DTM) 
                                         FROM STTL.TSSCM00010 
                                         WHERE USR_ID = 'lifedomy@gmail.com'
                                         ) 
                        )
    ORDER BY A.REG_DTM

INSERT INTO STTL.TSSCM00021 SELECT * FROM STTL.TSSCM00020;    


SELECT USR_ID,STTL_NM,STTL_CD,STTL_STM,STTL_ETM,STTL_DESC,REG_DTM,REG_USR_ID,UPD_DTM,UPD_USR_ID
FROM STTL.TSSCM00021
ORDER BY USR_ID,STTL_NM,STTL_CD,STTL_STM,STTL_ETM,STTL_DESC,REG_DTM,REG_USR_ID,UPD_DTM,UPD_USR_ID    

SELECT 
ROW_NUMBER() as aa, 
USR_ID,STTL_NM,STTL_CD,STTL_STM,STTL_ETM,STTL_DESC,REG_DTM,REG_USR_ID,UPD_DTM,UPD_USR_ID
FROM STTL.TSSCM00021
ORDER BY USR_ID,STTL_NM,STTL_CD,STTL_STM,STTL_ETM,STTL_DESC,REG_DTM,REG_USR_ID,UPD_DTM,UPD_USR_ID    

INSERT INTO STTL.TSSCM00020
SELECT 
 USR_ID,STTL_NM,STTL_CD
,ROW_NUMBER() OVER(PARTITION BY USR_ID,STTL_NM,STTL_CD ORDER BY USR_ID,STTL_NM,STTL_CD ) as STTL_NUM
,STTL_STM,STTL_ETM,STTL_DESC,REG_DTM,REG_USR_ID,UPD_DTM,UPD_USR_ID
FROM STTL.TSSCM00021
ORDER BY USR_ID,STTL_NM,STTL_CD


select * FROM   STTL.TSSCM00020



SELECT * FROM   STTL.TSSCM00020 WHERE STTL_NM = 'Silicon.Valley.S03E06.720p.HDTV.x264-AVS.smi'  
                        

DELETE  FROM   STTL.TSSCM00010 WHERE STTL_NM = 'Silicon.Valley.S03E06.720p.HDTV.x264-AVS.smi'  
                        

SELECT DISTINCT
           A.STTL_NM         -- 자막명      
    FROM   STTL.TSSCM00020 A

SELECT DISTINCT
           A.USR_ID          -- 사용자아이디    
         , A.STTL_NM         -- 자막명      
         , A.STTL_CD         -- 자막구분(1:외국어,2:모국어) 
         , A.STTL_NUM        -- 자막순번      
         , A.STTL_STM        -- 자막시작시각      
         , A.STTL_ETM        -- 자막종료시각      
         , A.STTL_DESC       -- 자막문장내용
         , A.REG_DTM AS REG_DTM
         , A.REG_USR_ID
         , A.UPD_DTM AS UPD_DTM
         , A.UPD_USR_ID
    FROM   STTL.TSSCM00020 A
    WHERE  A.USR_ID = 'lifedomy@gmail.com'
    AND    A.STTL_NM = (
                        SELECT MAX(STTL_NM) 
                        FROM   STTL.TSSCM00020 
                        WHERE  USR_ID = 'lifedomy@gmail.com'
                        AND    REG_DTM = (SELECT MAX(REG_DTM) 
                                          FROM STTL.TSSCM00020 
                                          WHERE USR_ID = 'lifedomy@gmail.com'
                                          ) 
                        )
    AND    LENGTH(TRIM(A.STTL_DESC)) > 0
    AND    A.STTL_CD = IFNULL('2',A.STTL_CD)  
    ORDER BY A.STTL_CD,STTL_NUM

commit

DROP TABLE STTL.TSSCM00020;

CREATE TABLE STTL.TSSCM00020 (
 USR_ID       VARCHAR(40)    NOT NULL COMMENT '사용자아이디'    
,STTL_NM      VARCHAR(100)   NOT NULL COMMENT '자막명'      
,STTL_CD      CHAR(1)        NOT NULL COMMENT '자막구분(1:외국어,2:모국어)' 
,STTL_NUM     INT            NOT NULL COMMENT '자막순번'
,STTL_STM     VARCHAR(50)    NOT NULL COMMENT '자막시작시각'      
,STTL_ETM     VARCHAR(50)        NULL COMMENT '자막종료시각'      
,STTL_DESC    VARCHAR(1000)      NULL COMMENT '자막문장내용'
,REG_DTM      TIMESTAMP          NULL COMMENT '등록일시'
,REG_USR_ID   VARCHAR(40)        NULL COMMENT '등록자'
,UPD_DTM      TIMESTAMP          NULL COMMENT '변경일시'
,UPD_USR_ID   VARCHAR(40)        NULL COMMENT '변경자'
,PRIMARY KEY (USR_ID,STTL_NM,STTL_CD,STTL_NUM)
) ENGINE=INNODB DEFAULT CHARSET=UTF8 COMMENT='유저자막내용';
;

--CREATE INDEX TSSCM00020_IX01 ON TSSCM00020 (USR_ID,STTL_NM,STTL_CD,STTL_NUM);


-----------------------------
-- 유저자막 책갈피
-----------------------------
DROP TABLE STTL.TSSCM00030;

CREATE TABLE STTL.TSSCM00030 (
 USR_ID       VARCHAR(40)    NOT NULL COMMENT '사용자아이디'    
,STTL_NM      VARCHAR(100)   NOT NULL COMMENT '자막명'      
,STTL_CD      CHAR(1)        NOT NULL COMMENT '자막구분(1:외국어,2:모국어)' 
,STTL_NUM     INT            NOT NULL COMMENT '자막순번'
,REG_DTM      TIMESTAMP          NULL COMMENT '등록일시'
,REG_USR_ID   VARCHAR(40)        NULL COMMENT '등록자'
,UPD_DTM      TIMESTAMP          NULL COMMENT '변경일시'
,UPD_USR_ID   VARCHAR(40)        NULL COMMENT '변경자'
,PRIMARY KEY (USR_ID,STTL_NM,STTL_CD,STTL_NUM)
) ENGINE=INNODB DEFAULT CHARSET=UTF8 COMMENT='자막책갈피';
;


-----------------------------
-- 유저통합자막내용
-----------------------------
DROP TABLE STTL.TSSCM00040;

CREATE TABLE STTL.TSSCM00040 (
 USR_ID       VARCHAR(40)    NOT NULL COMMENT '사용자아이디'    
,STTL_NM      VARCHAR(100)   NOT NULL COMMENT '자막명'      
,STTL_STM     CHAR(6)        NOT NULL COMMENT '자막시작시각'      
,STTL_ETM     CHAR(6)            NULL COMMENT '자막종료시각'      
,FSTTL_SDESC  VARCHAR(1000)     NULL COMMENT '외국어자막문장내용'
,MSTTL_SDESC  VARCHAR(1000)     NULL COMMENT '모국어자막문장내용'
,REG_DTM      DATE               NULL COMMENT '등록일시'
,REG_USR_ID   VARCHAR(40)        NULL COMMENT '등록자'
,UPD_DTM      DATE               NULL COMMENT '변경일시'
,UPD_USR_ID   VARCHAR(40)        NULL COMMENT '변경자'
,PRIMARY KEY (USR_ID,STTL_NM,STTL_STM)
) ENGINE=INNODB DEFAULT CHARSET=UTF8 COMMENT='유저자막내용';
;
