/*
 * 테이블 작성 및 생성은 스크립트로 하고
 * 
 * 엑셀이나 디비접속으로 systable 정보를 읽어서 약어, 인포타입, 표준용어 추출 및
 * 
 * 테이블 스키마 품질 지적 및 가이드
 * 
 * 
 * 표준용어 혹은 약어 정보 검색 기능. public or private.
 * 
 * 표준용어 분석          
 * 
 * 웹버젼과 어플버젼
 * 
 * QA용
 * 
 * 
 * 프로젝트 거버넌스 툴
 * 
 * 
 * 정합성 체크
 * */
;

SELECT * FROM INFORMATION_SCHEMA.COLUMNS

SELECT TABLE_NAME FROM INFORMATION_SCHEMA.TABLES
WHERE TABLE_NAME LIKE '%INDEX%'

SELECT * FROM INFORMATION_SCHEMA.INNODB_SYS_INDEXES

SELECT * FROM INFORMATION_SCHEMA.INDEX_STATISTICS


SELECT * FROM INFORMATION_SCHEMA.INNODB_FT_INDEX_TABLE


SELECT * FROM FFRAME.tdacm00060;
WHERE 1=1
--AND COL_NM LIKE '%TRADE_EXCPT_RSN_MK_CD%'
;

SELECT                                                                                     
        NULL                     AS DB_NM                                                     
       ,UPPER(A.TABLE_SCHEMA) AS OWNER                                                     
       ,UPPER(A.TABLE_NAME)   AS TAB_NM                                                    
       ,A.ORDINAL_POSITION    AS COL_ID                                                    
       ,A.COLUMN_NAME         AS COL_NM                                                    
       ,A.COLUMN_COMMENT      AS COL_HNM                                                   
       ,CASE WHEN UPPER(A.DATA_TYPE) = 'INT' THEN UPPER(A.DATA_TYPE)                       
             ELSE UPPER(A.COLUMN_TYPE)                                                     
        END                   AS DATA_TYPE_DESC                                            
       ,CASE WHEN IS_NULLABLE = 'NO' THEN 'NOT NULL' ELSE '' END AS NULLABLE               
       ,CASE WHEN A.COLUMN_KEY = 'PRI' THEN 'Y' ELSE '' END    AS PK                                                        
       ,UPPER(A.DATA_TYPE)    AS DATA_TYPE_NM                                              
       ,CASE WHEN UPPER(A.DATA_TYPE) IN ('CHAR','VARCHAR') THEN A.CHARACTER_MAXIMUM_LENGTH 
             WHEN UPPER(A.DATA_TYPE) IN ('INT','NUMERIC') THEN A.NUMERIC_PRECISION         
        END                   AS LEN                                                       
       ,A.NUMERIC_SCALE       AS DECIMAL_CNT                                               
       ,' '                   AS COL_DESC                                                  
       ,DATE_FORMAT(NOW(),'%Y%m%d%H%i') AS REG_DTM                                         
       ,''                    AS REG_USR_ID                                                
       ,DATE_FORMAT(NOW(),'%Y%m%d%H%i') AS UPD_DTM                                         
       ,''                    AS UPD_USR_ID                                                
FROM   INFORMATION_SCHEMA.COLUMNS A                                                        
WHERE  1=1                                                                                 
AND    A.TABLE_SCHEMA = 'FFRAME'                                                                  
AND    A.TABLE_NAME LIKE CONCAT(CONCAT('%','TDACM00060'),'%')                                         
ORDER BY A.TABLE_NAME,A.ORDINAL_POSITION
 


update `tdacm00060` set `CD_DESC`='메타정보 관리2' where `CD_ID_NM`='CD_GRP_CD' and `CD_ID_HNM`='코드그룹코드' and `CD_ID_GRP_NM`='META' and `CD`='001' and `CD_NM`='META' and `CD_HNM`='메타' and `CD_DESC`='메타정보 관리' and `CD_TY_CD`='1' and `CD_STS_CD`='03' and `REG_DTM`='2017-10-11' and `REG_USR_ID`='ADMIN' and `UPD_DTM`='2017-12-23' and `UPD_USR_ID`='ADMIN'


UPDATE TDACM00081
SET COL_NM = 'TRADE_EXCPT_RSN_MK_CD_DEL'
WHERE TAB_NM = 'SUMM_CUST_PYOFF_BFR_IN_FRGN_TRAN'
AND COL_ID = 30
;

UPDATE TDACM00081
SET COL_NM = 'TRADE_EXCPT_RSN_MK_CD_DEL'
WHERE TAB_NM = 'SUMM_FRGN_EXCH_TRAN'
AND COL_ID = 30
;

    SELECT   A.DB_NM
            ,A.OWNER
            ,A.TAB_NM
            ,B.TAB_HNM
            ,A.COL_ID
            ,A.COL_NM
            ,A.COL_HNM
            ,A.DATA_TYPE_DESC
            ,A.NULLABLE
            ,A.PK
            ,A.DATA_TYPE_NM
            ,A.LEN
            ,A.DECIMAL_CNT
            ,TRIM(A.COL_DESC) AS COL_DESC
            ,DATE_FORMAT(A.REG_DTM,'%Y.%m.%d %H:%i:%S') AS REG_DTM
            ,A.REG_USR_ID
            ,DATE_FORMAT(A.UPD_DTM,'%Y.%m.%d %H:%i:%S') AS UPD_DTM
            ,A.UPD_USR_ID
    FROM    TDACM00080 A 
            LEFT JOIN TDACM00070 B
            ON  A.DB_NM = B.DB_NM
            AND A.OWNER = B.OWNER
            AND A.TAB_NM = B.TAB_NM
    WHERE   1=1
    AND     A.DB_NM        LIKE CONCAT(CONCAT('%','SCASIS'),'%')
    AND  (
          ('true' = 'false' AND 'COL_NM' = 'DB_NM'        AND A.DB_NM        LIKE CONCAT(CONCAT('%','LOG'),'%'))
           OR   
          ('true' = 'false' AND 'COL_NM' = 'OWNER'        AND A.OWNER        LIKE CONCAT(CONCAT('%','LOG'),'%'))
           OR   
          ('true' = 'false' AND 'COL_NM' = 'TAB_NM'       AND A.TAB_NM       LIKE CONCAT(CONCAT('%','LOG'),'%'))
           OR   
          ('true' = 'false' AND 'COL_NM' = 'TAB_HNM'      AND B.TAB_HNM      LIKE CONCAT(CONCAT('%','LOG'),'%'))
           OR   
          ('true' = 'false' AND 'COL_NM' = 'COL_NM'       AND A.COL_NM       LIKE CONCAT(CONCAT('%','LOG'),'%'))
           OR   
          ('true' = 'false' AND 'COL_NM' = 'COL_HNM'      AND A.COL_HNM      LIKE CONCAT(CONCAT('%','LOG'),'%'))
           OR   
          ('true' = 'false' AND 'COL_NM' = 'DATA_TYPE_NM' AND A.DATA_TYPE_NM LIKE CONCAT(CONCAT('%','LOG'),'%'))
           OR   
          ('true' = 'true' AND 'COL_NM' = 'COL_NM' AND A.TAB_NM IN (SELECT TAB_NM FROM TDACM00080 WHERE COL_NM LIKE CONCAT(CONCAT('%','LOG'),'%')))
         )  
    ORDER BY A.DB_NM,OWNER,A.TAB_NM,A.COL_ID
 ;


 SELECT TAB_NM FROM TDACM00080 WHERE COL_NM LIKE CONCAT(CONCAT('%','LOG'),'%')
 ;
 
    SELECT   A.DB_NM
            ,A.OWNER
            ,A.TAB_NM
            ,B.TAB_HNM
            ,A.COL_ID
            ,A.COL_NM
            ,A.COL_HNM
            ,A.DATA_TYPE_DESC
            ,A.NULLABLE
            ,A.PK
            ,A.DATA_TYPE_NM
            ,A.LEN
            ,A.DECIMAL_CNT
            ,TRIM(A.COL_DESC) AS COL_DESC
            ,DATE_FORMAT(A.REG_DTM,'%Y.%m.%d %H:%i:%S') AS REG_DTM
            ,A.REG_USR_ID
            ,DATE_FORMAT(A.UPD_DTM,'%Y.%m.%d %H:%i:%S') AS UPD_DTM
            ,A.UPD_USR_ID
    FROM    TDACM00080 A 
            LEFT JOIN TDACM00070 B
            ON  A.DB_NM = B.DB_NM
            AND A.OWNER = B.OWNER
            AND A.TAB_NM = B.TAB_NM
    WHERE   1=1
    AND     A.DB_NM        LIKE CONCAT(CONCAT('%','SCASIS'),'%')
    AND  (
          ('COL_HNM' = 'DB_NM'        AND A.DB_NM        LIKE CONCAT(CONCAT('%',''),'%'))
           OR   
          ('COL_HNM' = 'OWNER'        AND A.OWNER        LIKE CONCAT(CONCAT('%',''),'%'))
           OR   
          ('COL_HNM' = 'TAB_NM'       AND A.TAB_NM       LIKE CONCAT(CONCAT('%',''),'%'))
           OR   
          ('COL_HNM' = 'TAB_HNM'      AND B.TAB_HNM      LIKE CONCAT(CONCAT('%',''),'%'))
           OR   
          ('COL_HNM' = 'COL_NM'       AND A.COL_NM       LIKE CONCAT(CONCAT('%',''),'%'))
           OR   
          ('COL_HNM' = 'COL_HNM'      AND A.COL_HNM      LIKE CONCAT(CONCAT('%',''),'%'))
           OR   
          ('COL_HNM' = 'DATA_TYPE_NM' AND A.DATA_TYPE_NM LIKE CONCAT(CONCAT('%',''),'%'))
         ) 
    AND  (
          ('1' = '1' AND A.TAB_NM IN (SELECT TAB_NM FROM TDACM00080 WHERE COL_NM LIKE CONCAT(CONCAT('%',''),'%') )
         ) 

         
    ORDER BY A.DB_NM,OWNER,A.TAB_NM,A.COL_ID
    ;

SELECT 

            CASE WHEN UPPER(A.DATA_TYPE) IN ('CHAR','VARCHAR') THEN A.CHARACTER_MAXIMUM_LENGTH \n";
                 WHEN UPPER(A.DATA_TYPE) IN ('INT','NUMERIC') THEN A.NUMERIC_PRECISION         \n";
            END
;

SELECT 
 CASE WHEN A.DATA_TYPE_NM IN ('CHAR') THEN concat(A.DATA_TYPE_NM,'(',A.LEN,')') 
      WHEN A.DATA_TYPE_NM IN ('DECIMAL') THEN concat(A.DATA_TYPE_NM,'(',A.LEN,',',A.DECIMAL_CNT,')') 
      ELSE A.DATA_TYPE_NM END
,A.*
FROM TDACM00080 A
WHERE DB_NM = 'SCASIS'
;

UPDATE  TDACM00080 A
SET DATA_TYPE_DESC =
 CASE WHEN A.DATA_TYPE_NM IN ('CHAR') THEN concat(A.DATA_TYPE_NM,'(',A.LEN,')') 
      WHEN A.DATA_TYPE_NM IN ('DECIMAL') THEN concat(A.DATA_TYPE_NM,'(',A.LEN,',',A.DECIMAL_CNT,')') 
      ELSE A.DATA_TYPE_NM END
WHERE DB_NM = 'SCASIS'
;

COMMIT
;

    INSERT INTO TDACM00070 
    SELECT A.*
    FROM   TDACM00071 A
           LEFT OUTER JOIN TDACM00070 B
           ON   A.DB_NM = B.DB_NM
           AND  A.OWNER = B.OWNER
           AND  A.TAB_NM = B.TAB_NM
    WHERE  B.TAB_NM IS NULL           
    ORDER BY A.TAB_NM    
;

COMMIT
;

Duplicate entry 'SCASIS-AML-SUMM_FRGN_EXCH_TRAN-TRADE_EXCPT_RSN_MK_CD' for key 'PRIMARY'



    INSERT INTO TDACM00080 
    SELECT A.*
    FROM   TDACM00081 A
           LEFT OUTER JOIN TDACM00080 B
           ON   A.DB_NM = B.DB_NM
           AND  A.OWNER = B.OWNER
           AND  A.TAB_NM = B.TAB_NM
           AND  A.COL_NM = B.COL_NM
           AND  A.COL_HNM = B.COL_HNM
           AND  A.DATA_TYPE_DESC = B.DATA_TYPE_DESC
    WHERE  B.COL_NM IS NULL           
    ORDER BY A.TAB_NM,A.COL_ID
    ;

 INSERT INTO TDACM00081
    (
         DB_NM         
        ,OWNER         
        ,TAB_NM        
        ,COL_ID        
        ,COL_NM        
        ,COL_HNM       
        ,DATA_TYPE_DESC
        ,NULLABLE      
        ,PK            
        ,DATA_TYPE_NM  
        ,LEN           
        ,DECIMAL_CNT   
        ,COL_DESC      
        ,REG_DTM       
        ,REG_USR_ID    
        ,UPD_DTM       
        ,UPD_USR_ID    
    ) VALUES (
         'SCASIS'
        ,'AML'
        ,'ACCT_PLDG'
        ,CAST('1.0' AS INT)
        ,'LOAN_ACCT_NO'
        ,'여신계좌번호'
        ,''
        ,'N'
        ,'PK'
        ,'CHAR'
        ,CAST('13.0' AS INT)
        ,CAST('' AS INT)
        ,''
        ,curdate()
        ,''
        ,curdate()
        ,''
    )
;
﻿
SELECT CAST(IFNULL('',0) AS INT)
;

SELECT IFNULL('',0)
;

SELECT CASE WHEN '' IN ('',NULL) THEN 0 ELSE '' END 
;


SELECT COALESCE('',NULL,0,'AAA')
;

SELECT CAST('10' as varchar(10))
;

SELECT '-10'
;


SELECT CHAR_LENGTH('한글')
;

SELECT CAST('10.0' AS INT)
;

﻿------------------------------
-- 패키지 정보
------------------------------

select @@lower_case_table_names
;

drop table TDACM00010
;

CREATE TABLE TDACM00010 (
 PKG_FUL_NM VARCHAR(20)      NULL COMMENT '패키지풀명'
,PKG2_NM    CHAR(2)          NULL COMMENT '패키지2자리명'
,PKG3_NM    CHAR(3)          NULL COMMENT '패키지3자리명'
,PKG4_NM    CHAR(4)          NULL COMMENT '패키지4자리명'
,PKG_HNM    VARCHAR(10)      NULL COMMENT '패키지한글명'
,PKG_DESC   VARCHAR(200)     NULL COMMENT '패키지설명'
,USE_YN     CHAR(1)          NULL COMMENT '사용여부'
,REG_DTM    VARCHAR(14)      NULL COMMENT '등록일시'
,REG_USR_ID VARCHAR(20)      NULL COMMENT '등록자'
,UPD_DTM    VARCHAR(14)      NULL COMMENT '변경일시'
,UPD_USR_ID VARCHAR(20)      NULL COMMENT '변경자'
,PRIMARY KEY(PKG_FUL_NM)
) ENGINE=INNODB DEFAULT CHARSET=utf8 COMMENT='패키지정보';
;

INSERT INTO TDACM00010 VALUES('system','sy','sys','syst','시스템','시스템','Y',date_format(now(),'%Y%m%d%H%i'),'admin',date_format(now(),'%Y%m%d%H%i'),'admin');
INSERT INTO TDACM00010 VALUES('data architecter','da','das','dams','데이터 아키텍쳐','테이블스키마관리 시스템','Y',date_format(now(),'%Y%m%d%H%i'),'admin',date_format(now(),'%Y%m%d%H%i'),'admin');
INSERT INTO TDACM00010 VALUES('user','ur','usr','user','유저','유저 관리 시스템','Y',date_format(now(),'%Y%m%d%H%i'),'admin',date_format(now(),'%Y%m%d%H%i'),'admin');

SELECT * FROM TDACM00010
;

    SELECT   A.CD_ID_NM      
            ,A.CD            
            ,A.CD_PARAM1_DESC
            ,A.CD_PARAM1     
            ,A.CD_PARAM2_DESC
            ,A.CD_PARAM2     
            ,A.CD_PARAM3_DESC
            ,A.CD_PARAM3     
            ,A.CD_PARAM4_DESC
            ,A.CD_PARAM4     
            ,A.CD_PARAM5_DESC
            ,A.CD_PARAM5     
            ,DATE_FORMAT(A.REG_DTM,'%Y.%m.%d %H:%i:%S') AS REG_DTM
            ,A.REG_USR_ID
            ,DATE_FORMAT(A.UPD_DTM,'%Y.%m.%d %H:%i:%S') AS UPD_DTM
            ,A.UPD_USR_ID
    FROM    TDACM00061 A
    ORDER BY A.CD_ID_NM, A.CD
    ;

﻿------------------------------
-- 약어 정보
------------------------------
DROP TABLE TDACM00020;

CREATE TABLE TDACM00020 (
 ABBR_NM     VARCHAR(10) NOT NULL COMMENT '약어명'
,ABBR_FUL_NM VARCHAR(20)     NULL COMMENT '약어풀명'
,ABBR_HNM    VARCHAR(10)     NULL COMMENT '약어한글명'
,ABBR_DESC   VARCHAR(200)    NULL COMMENT '약어설명'
,ABBR_APR_CD CHAR(2)         NULL COMMENT '약어승인코드' -- 01:요청 02:진행중 03:반려 04:반려취소 05:승인 06:승인취소 
,REG_DTM     VARCHAR(14)     NULL COMMENT '등록일시'
,REG_USR_ID  VARCHAR(20)     NULL COMMENT '등록자'
,UPD_DTM     VARCHAR(14)     NULL COMMENT '변경일시'
,UPD_USR_ID  VARCHAR(20)     NULL COMMENT '변경자'
,PRIMARY KEY(ABBR_NM)
) ENGINE=INNODB DEFAULT CHARSET=UTF8 COMMENT='약어정보';
;

SELECT * FROM TDACM00020
;

DROP TABLE TDACM00021;

CREATE TABLE TDACM00021 (
 ABBR_NM     VARCHAR(10)  NOT NULL COMMENT '약어명'
,ABBR_FUL_NM VARCHAR(20)      NULL COMMENT '약어풀명'
,ABBR_HNM    VARCHAR(10)      NULL COMMENT '약어한글명'
,ABBR_DESC   VARCHAR(200)     NULL COMMENT '약어설명'
,REG_DTM    VARCHAR(14)       NULL COMMENT '등록일시'
,REG_USR_ID VARCHAR(20)       NULL COMMENT '등록자'
,UPD_DTM    VARCHAR(14)       NULL COMMENT '변경일시'
,UPD_USR_ID VARCHAR(20)       NULL COMMENT '변경자'
) ENGINE=INNODB DEFAULT CHARSET=UTF8 COMMENT='약어임시정보';
;

SELECT * FROM TDACM00021
;


------------------------------
-- 인포타입
------------------------------
DROP TABLE TDACM00030;

CREATE TABLE TDACM00030 (
 INFO_TYPE_NM   VARCHAR(30)  NOT NULL COMMENT '인포타입명' 
,INFO_TYPE_HNM  VARCHAR(30)      NULL COMMENT '인포타입한글명' 
,DOMAIN_NM      VARCHAR(20)      NULL COMMENT '도메인명' 
,DOMAIN_HNM     VARCHAR(20)      NULL COMMENT '도메인한글명' 
,DATA_TYPE_NM   VARCHAR(20)      NULL COMMENT '데이터타입명' 
,LEN            INT              NULL COMMENT '길이'
,DECIMAL_CNT    INT              NULL COMMENT '소수점수'
,DATA_TYPE_DESC VARCHAR(50)      NULL COMMENT '데이터타입설명'
,REG_DTM        VARCHAR(14)      NULL COMMENT '등록일시'
,REG_USR_ID     VARCHAR(20)      NULL COMMENT '등록자'
,UPD_DTM        VARCHAR(14)      NULL COMMENT '변경일시'
,UPD_USR_ID     VARCHAR(20)      NULL COMMENT '변경자'
,PRIMARY KEY(INFO_TYPE_NM)
) ENGINE=INNODB DEFAULT CHARSET=UTF8 COMMENT='인포타입';
;

SELECT * FROM TDACM00030
;


------------------------------
-- 용어사전
------------------------------
DROP TABLE TDACM00040
;

CREATE TABLE TDACM00040 (
 COL_NM         VARCHAR(20)  NOT NULL COMMENT '컬럼명' 
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
) ENGINE=INNODB DEFAULT CHARSET=UTF8 COMMENT='용어사전';
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
 CD_ID_NM       VARCHAR(20)  NOT NULL COMMENT '코드아이디명'     -- BIZ_CD
,CD_ID_HNM      VARCHAR(20)      NULL COMMENT '코드아이디한글명' -- 업무구분코드
,CD_ID_GRP_NM   VARCHAR(20)      NULL COMMENT '코드아이디그룹명' -- AML
,CD             VARCHAR(20)  NOT NULL COMMENT '코드'             -- 01
,CD_NM          VARCHAR(20)      NULL COMMENT '코드명'           -- DEPOSIT
,CD_HNM         VARCHAR(20)      NULL COMMENT '코드한글명'       -- 수신
,CD_DESC        VARCHAR(200)     NULL COMMENT '코드설명'
,CD_TY_CD       CHAR(1)          NULL COMMENT '코드유형코드' -- 1:필드 2:화면 3:프로그램
,CD_STS_CD      CHAR(2)          NULL COMMENT '코드상태코드'
,REG_DTM        VARCHAR(14)      NULL COMMENT '등록일시'
,REG_USR_ID     VARCHAR(20)      NULL COMMENT '등록자'
,UPD_DTM        VARCHAR(14)      NULL COMMENT '변경일시'
,UPD_USR_ID     VARCHAR(20)      NULL COMMENT '변경자'
,PRIMARY KEY(CD_ID_NM,CD)
) ENGINE=INNODB DEFAULT CHARSET=UTF8 COMMENT='코드'
;

INSERT INTO TDACM00060 SELECT * FROM TDATM00060;

CREATE TABLE TDATM00060 AS SELECT * FROM TDACM00060
;

COMMIT
;

SELECT * FROM TDACM00060
;




INSERT INTO SELECT * TDACM00060 FROM TDACM00060
;

DROP TABLE TDACM00061
;

INSERT INTO TDACM00061 SELECT * FROM TDATM00161;

CREATE TABLE TDATM00161 AS SELECT * FROM TDACM00061
;

SELECT * FROM TDATM00161
;

SELECT * FROM TDACM00061
;



    SELECT   A.CD_ID_NM      
            ,A.CD    
            ,B.CD_NM       
            ,B.CD_HNM      
            ,B.CD_DESC      
            ,A.CD_PARAM1_DESC
            ,A.CD_PARAM1     
            ,A.CD_PARAM2_DESC
            ,A.CD_PARAM2     
            ,A.CD_PARAM3_DESC
            ,A.CD_PARAM3     
            ,A.CD_PARAM4_DESC
            ,A.CD_PARAM4     
            ,A.CD_PARAM5_DESC
            ,A.CD_PARAM5
            ,A.CD_PARAM6_DESC
            ,A.CD_PARAM6     
            ,A.CD_PARAM7_DESC
            ,A.CD_PARAM7     
            ,A.CD_PARAM8_DESC
            ,A.CD_PARAM8     
            ,A.CD_PARAM9_DESC
            ,A.CD_PARAM9     
            ,DATE_FORMAT(A.REG_DTM,'%Y.%m.%d %H:%i:%S') AS REG_DTM
            ,A.REG_USR_ID
            ,DATE_FORMAT(A.UPD_DTM,'%Y.%m.%d %H:%i:%S') AS UPD_DTM
            ,A.UPD_USR_ID
    FROM    TDACM00061 A,
            TDACM00060 B 
    WHERE   A.CD_ID_NM = B.CD_ID_NM
    AND     A.CD       = B.CD  
    AND     A.CD_ID_NM = 'DYN_DAO_CD'
    AND     A.CD       = (CASE WHEN NULL IS NULL THEN A.CD ELSE NULL END)
    AND     B.CD_NM    = (CASE WHEN 'MARIADB' IS NULL THEN B.CD_NM ELSE 'MARIADB' END)
    ORDER BY A.CD_ID_NM, A.CD
 ;

















CREATE TABLE TDACM00061 (
 CD_ID_NM        VARCHAR(20)  NOT NULL COMMENT '코드아이디명'     -- BIZ_CD
,CD              VARCHAR(20)  NOT NULL COMMENT '코드'             -- 01
,CD_PARAM1_DESC VARCHAR(200)      NULL COMMENT '코드인자1설명'
,CD_PARAM1      VARCHAR(100)      NULL COMMENT '코드인자1'
,CD_PARAM2_DESC VARCHAR(200)      NULL COMMENT '코드인자2설명'
,CD_PARAM2      VARCHAR(100)      NULL COMMENT '코드인자2'
,CD_PARAM3_DESC VARCHAR(200)      NULL COMMENT '코드인자3설명'
,CD_PARAM3      VARCHAR(100)      NULL COMMENT '코드인자3'
,CD_PARAM4_DESC VARCHAR(200)      NULL COMMENT '코드인자4설명'
,CD_PARAM4      VARCHAR(100)      NULL COMMENT '코드인자4'
,CD_PARAM5_DESC VARCHAR(200)      NULL COMMENT '코드인자5설명'
,CD_PARAM5      VARCHAR(100)      NULL COMMENT '코드인자5'
,CD_PARAM6_DESC VARCHAR(200)      NULL COMMENT '코드인자6설명'
,CD_PARAM6      VARCHAR(100)      NULL COMMENT '코드인자6'
,CD_PARAM7_DESC VARCHAR(200)      NULL COMMENT '코드인자7설명'
,CD_PARAM7      VARCHAR(100)      NULL COMMENT '코드인자7'
,CD_PARAM8_DESC VARCHAR(200)      NULL COMMENT '코드인자8설명'
,CD_PARAM8      VARCHAR(100)      NULL COMMENT '코드인자8'
,CD_PARAM9_DESC VARCHAR(200)      NULL COMMENT '코드인자9설명'
,CD_PARAM9      VARCHAR(100)      NULL COMMENT '코드인자9'
,REG_DTM         VARCHAR(14)      NULL COMMENT '등록일시'
,REG_USR_ID      VARCHAR(20)      NULL COMMENT '등록자'
,UPD_DTM         VARCHAR(14)      NULL COMMENT '변경일시'
,UPD_USR_ID      VARCHAR(20)      NULL COMMENT '변경자'
,PRIMARY KEY(CD_ID_NM,CD)
) ENGINE=INNODB DEFAULT CHARSET=UTF8 COMMENT='코드확장'
;

INSERT INTO TDACM00061 VALUES ('DB_TY_CD','01',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',date_format(now(),'%Y%m%d%H%i'),'admin','','admin')
;

SELECT * FROM TDACM00061
;

INSERT INTO TDACM00060 VALUES ('CD_STS_CD','코드상태코드','META','01','REQUEST','요청','','1','03',date_format(now(),'%Y%m%d%H%i'),'admin','','admin');
INSERT INTO TDACM00060 VALUES ('CD_STS_CD','코드상태코드','META','02','REQUEST','반려','','1','03',date_format(now(),'%Y%m%d%H%i'),'admin','','admin');
INSERT INTO TDACM00060 VALUES ('CD_STS_CD','코드상태코드','META','03','REQUEST','승인','','1','03',date_format(now(),'%Y%m%d%H%i'),'admin','','admin');
INSERT INTO TDACM00060 VALUES ('CD_STS_CD','코드상태코드','META','04','REQUEST','승인취소','','1','03',date_format(now(),'%Y%m%d%H%i'),'admin','','admin');

INSERT INTO TDACM00060 VALUES ('CD_TY_CD','코드유형코드','META','1','COLUMN','컬럼','','1','03',date_format(now(),'%Y%m%d%H%i'),'admin','','admin');
INSERT INTO TDACM00060 VALUES ('CD_TY_CD','코드유형코드','META','2','UI','화면','','1','03',date_format(now(),'%Y%m%d%H%i'),'admin','','admin');
INSERT INTO TDACM00060 VALUES ('CD_TY_CD','코드유형코드','META','3','PROGRAM','프로그램','','1','03',date_format(now(),'%Y%m%d%H%i'),'admin','','admin');


update TDACM00060 set REG_USR_ID = 'ADMIN'
;

 SELECT   A.CD_ID_NM    
            ,A.CD_ID_HNM   
            ,A.CD_ID_GRP_NM
            ,A.CD          
            ,A.CD_NM       
            ,A.CD_HNM      
            ,A.CD_DESC      
            ,A.CD_STS_CD   
            ,B.CD_HNM AS CD_STS_CD_NM   
            ,date_format(A.REG_DTM,'%Y.%m.%d %H:%i:%S') AS REG_DTM
            ,A.REG_USR_ID
            ,date_format(A.UPD_DTM,'%Y.%m.%d %H:%i:%S') AS UPD_DTM
            ,A.UPD_USR_ID
    FROM    TDACM00060 A
            LEFT JOIN TDACM00060 B
            ON  A.CD_ID_NM = B.CD_ID_NM
            AND A.CD = B.CD
;

------------------------------
-- 테이블정보
------------------------------
DROP TABLE TDACM00070;

CREATE TABLE TDACM00070 (
 DB_NM          VARCHAR(10)  NOT NULL COMMENT 'DB명' 
,OWNER          VARCHAR(15)      NULL COMMENT '소유자'
,TAB_NM         VARCHAR(40)      NULL COMMENT '테이블명'
,TAB_HNM        VARCHAR(40)      NULL COMMENT '테이블한글명'
,TAB_DESC       VARCHAR(200)     NULL COMMENT '테이블설명'
,REG_DTM        VARCHAR(14)      NULL COMMENT '등록일시'
,REG_USR_ID     VARCHAR(20)      NULL COMMENT '등록자'
,UPD_DTM        VARCHAR(14)      NULL COMMENT '변경일시'
,UPD_USR_ID     VARCHAR(20)      NULL COMMENT '변경자'
,PRIMARY KEY(DB_NM,OWNER,TAB_NM)
) ENGINE=INNODB DEFAULT CHARSET=UTF8 COMMENT='테이블정보'
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

SELECT * FROM TDACM00083
;

INSERT INTO TDACM00080 SELECT * FROM TDACM00083
;

CREATE TABLE TDACM00083 AS SELECT * FROM TDACM00080
;

COMMIT
;

CREATE TABLE TDACM00080 (
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
,PRIMARY KEY(DB_NM,OWNER,TAB_NM,COL_NM)
) ENGINE=INNODB DEFAULT CHARSET=UTF8 COMMENT='컬럼정보'
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

    SELECT 
           'MARIADB'              AS DB_NM
           ,UPPER(A.TABLE_SCHEMA) AS OWNER
           ,UPPER(A.TABLE_NAME)   AS TAB_NM
           ,A.ORDINAL_POSITION    AS COL_ID
           ,A.COLUMN_NAME         AS COL_NM
           ,A.COLUMN_COMMENT      AS COL_HNM
           ,CASE WHEN UPPER(A.DATA_TYPE) = 'INT' THEN UPPER(A.DATA_TYPE)
                 ELSE UPPER(A.COLUMN_TYPE)
            END                   AS DATA_TYPE_DESC
           ,CASE WHEN IS_NULLABLE = 'NO' THEN 'Y' ELSE 'N' END          AS NULL_YN
           ,''                    AS PK_YN
           ,UPPER(A.DATA_TYPE)    AS DATA_TYPE_NM
           ,CASE WHEN UPPER(A.DATA_TYPE) IN ('CHAR','VARCHAR') THEN A.CHARACTER_MAXIMUM_LENGTH
                 WHEN UPPER(A.DATA_TYPE) IN ('INT','NUMERIC') THEN A.NUMERIC_PRECISION
            END                   AS LEN
           ,A.NUMERIC_SCALE       AS DECIMAL_CNT 
           ,' '                   AS COL_DESC
           ,DATE_FORMAT(NOW(),'%Y%m%d%H%i') AS REG_DTM
           ,''                    AS REG_USR_ID
           ,DATE_FORMAT(NOW(),'%Y%m%d%H%i') AS UPD_DTM
           ,''                    AS UPD_USR_ID
    FROM   INFORMATION_SCHEMA.COLUMNS A 
    WHERE  1=1
    AND    TABLE_NAME LIKE 'TD%'
;

SELECT '신규' AS STS_NM,A.*
FROM   TDACM00081 A
       LEFT OUTER JOIN TDACM00080 B
       ON   A.DB_NM = B.DB_NM
       AND  A.OWNER = B.OWNER
       AND  A.TAB_NM = B.TAB_NM
       AND  A.COL_NM = B.COL_NM
       AND  A.DATA_TYPE_DESC = B.DATA_TYPE_DESC
;

DELETE FROM TDACM00080
;

SELECT A.COL_NM
      ,A.COL_HNM
      ,''   AS COL_DESC
      ,''   AS COL_ABBR_HNM
      ,A.DATA_TYPE_DESC
      ,'01' AS COL_STS_CD
      ,date_format(NOW(),'%Y%m%d%H%i%S') AS REG_DTM
      ,'' REG_USR_ID
      ,date_format(NOW(),'%Y%m%d%H%i%S') AS UPD_DTM
      ,'' UPD_USR_ID
FROM 
       (
		SELECT COL_NM
		      ,COL_HNM
		      ,DATA_TYPE_DESC
		FROM   TDACM00080
		GROUP BY COL_NM,COL_HNM
		) A
		LEFT OUTER JOIN TDACM00040 B
		ON A.COL_NM = B.COL_NM
WHERE  	B.COL_NM IS NULL
;


 COL_NM         VARCHAR(20)  NOT NULL COMMENT '컬럼명' 
,COL_HNM        VARCHAR(20)      NULL COMMENT '컬럼한글명'
,COL_DESC       VARCHAR(200)     NULL COMMENT '컬럼설명'
,COL_ABBR_HNM   VARCHAR(30)      NULL COMMENT '컬럼약어한글명'
,INFO_TYPE_NM   VARCHAR(30)      NULL COMMENT '인포타입명'
,COL_STS_CD     CHAR(2)          NULL COMMENT '컬럼상태코드'
,REG_DTM        VARCHAR(14)      NULL COMMENT '등록일시'
,REG_USR_ID     VARCHAR(20)      NULL COMMENT '등록자'
,UPD_DTM        VARCHAR(14)      NULL COMMENT '변경일시'
,UPD_USR_ID     VARCHAR(20)      NULL COMMENT '변경자'



COMMIT
;

SELECT A.*
FROM   TDACM00081 A
       LEFT OUTER JOIN TDACM00080 B
       ON   A.DB_NM = B.DB_NM
       AND  A.OWNER = B.OWNER
       AND  A.TAB_NM = B.TAB_NM
       AND  A.COL_NM = B.COL_NM
       AND  A.DATA_TYPE_DESC = B.DATA_TYPE_DESC
WHERE  B.COL_NM IS NULL       
;


SELECT * FROM TDACM00080
;

SELECT * FROM TDACM00081
;


SELECT COUNT(*) FROM TDACM00081
ORDER BY COL_NM
;

INSERT INTO TDACM00080 SELECT * FROM TDACM00081
;


    SELECT 
           COUNT(*)
    FROM   INFORMATION_SCHEMA.COLUMNS A 
    WHERE  1=1
    AND    TABLE_NAME LIKE 'TD%'
    AND    COLUMN_NAME LIKE '%'
    ORDER BY A.COLUMN_NAME
    ;


    SELECT A.*
    FROM   TDACM00081 A
           LEFT OUTER JOIN TDACM00080 B
           ON   A.DB_NM = B.DB_NM
           AND  A.OWNER = B.OWNER
           AND  A.TAB_NM = B.TAB_NM
           AND  A.COL_NM = B.COL_NM
           AND  A.COL_HNM = B.COL_HNM
           AND  A.DATA_TYPE_DESC = B.DATA_TYPE_DESC
    WHERE  B.COL_NM IS NULL           
    ORDER BY A.TAB_NM,A.COL_ID  
    ;
    
    
    SELECT   A.DB_NM
            ,A.OWNER
            ,A.TAB_NM
            ,B.TAB_HNM
            ,A.COL_ID
            ,A.COL_NM
            ,A.COL_HNM
            ,A.COL_DESC
            ,A.DATA_TYPE_NM
            ,A.LEN
            ,A.DECIMAL_CNT
            ,A.DATA_TYPE_DESC
            ,DATE_FORMAT(A.REG_DTM,'%Y.%m.%d %H:%i:%S') AS REG_DTM
            ,A.REG_USR_ID
            ,DATE_FORMAT(A.UPD_DTM,'%Y.%m.%d %H:%i:%S') AS UPD_DTM
            ,A.UPD_USR_ID
    FROM    TDACM00080 A
            LEFT JOIN TDACM00070 B
            ON  A.DB_NM = B.DB_NM
            AND A.OWNER = B.OWNER
            AND A.TAB_NM = B.TAB_NM
    WHERE   1=1
    ;
    
    
SELECT * FROM TDACM00070
;



    INSERT INTO TDACM00070 
    SELECT 
           'MARIADB'               AS DB_NM
           ,UPPER(A.TABLE_SCHEMA)  AS OWNER
           ,UPPER(A.TABLE_NAME)    AS TAB_NM
           ,UPPER(A.TABLE_COMMENT) AS TAB_HNM
           ,''                     AS TAB_DESC
           ,DATE_FORMAT(NOW(),'%Y%m%d%H%i') AS REG_DTM
           ,''                    AS REG_USR_ID
           ,DATE_FORMAT(NOW(),'%Y%m%d%H%i') AS UPD_DTM
           ,''                    AS UPD_USR_ID
    FROM   INFORMATION_SCHEMA.TABLES A 
    WHERE  1=1
    AND    TABLE_NAME LIKE 'TD%'
;


------------------------------
-- 메뉴
------------------------------
DROP TABLE TDACM00070;

CREATE TABLE TDACM00070 (
 DB_NM          VARCHAR(10)  NOT NULL COMMENT 'DB명' 
,OWNER          VARCHAR(15)      NULL COMMENT '소유자'
,TAB_NM         VARCHAR(20)      NULL COMMENT '테이블명'
,TAB_HNM        VARCHAR(20)      NULL COMMENT '테이블한글명'
,TAB_DESC       VARCHAR(200)     NULL COMMENT '테이블설명'
,REG_DTM        VARCHAR(14)      NULL COMMENT '등록일시'
,REG_USR_ID     VARCHAR(20)      NULL COMMENT '등록자'
,UPD_DTM        VARCHAR(14)      NULL COMMENT '변경일시'
,UPD_USR_ID     VARCHAR(20)      NULL COMMENT '변경자'
,PRIMARY KEY(DB_NM,OWNER,TAB_NM)
) ENGINE=INNODB DEFAULT CHARSET=UTF8 COMMENT='테이블정보'
;
