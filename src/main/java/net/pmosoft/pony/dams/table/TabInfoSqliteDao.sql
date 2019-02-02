

WITH TEMP01 AS (
SELECT OWNER
     , TAB_NM
     , TRIM(OWNER)||'.'||TAB_NM AS OWNER_TAB_NM
     , TAB_HNM
     , COL_ID
     , COL_NM
     , COL_HNM
     , DATA_TYPE_DESC
     , NULLABLE
     , PK
FROM TDACM00080
ORDER BY TAB_NM, COL_ID
------------------------
-- 테이블 정보
------------------------
), TEMP02 AS (
SELECT DISTINCT
       A.OWNER
     , A.TAB_NM
     , A.OWNER_TAB_NM
     , A.TAB_NM
FROM TEMP01 A
)
, TEMP20 AS (
SELECT A.OWNER_TAB_NM
     , MIN(A.TAB_HNM)                      AS TAB_HNM
     , MIN(A.COL_ID)                       AS MIN_COL_ID
     , MAX(A.COL_ID)                       AS MAX_COL_ID
     , MAX(LENGTH(TRIM(A.COL_NM)))         AS MAX_COL_NM_LEN
     , MAX(LENGTH(TRIM(A.COL_HNM)))        AS MAX_COL_HNM_LEN
     , MAX(LENGTH(TRIM(A.DATA_TYPE_DESC))) AS MAX_DATA_TYPE_DESC
FROM   TEMP01 A
GROUP BY A.OWNER_TAB_NM
---------------------------------
-- RENAME TABLE RENAME 문장 생성
---------------------------------
)
, TEMP21 AS (
SELECT DISTINCT
       OWNER_TAB_NM
     , -100 RNUM
     , 'RENAME '||OWNER_TAB_NM||' TO '||TAB_NM||'_T1;' C1
FROM   TEMP02
---------------------------------
-- DROP TABLE 문장 생성
---------------------------------
) SELECT * FROM TEMP21
, TEMP03 AS (
    SELECT DISTINCT
           OWNER_TAB_NM
         , -10 RNUM
         , 'DROP TABLE '||OWNER_TAB_NM||';' C1
    FROM   TEMP02
---------------------------------
-- CREATE TABLE 문장 생성
---------------------------------
)
, TEMP04 AS (
    SELECT
           OWNER_TAB_NM
         , -5 RNUM
         , 'CREATE TABLE '||OWNER_TAB_NM||'(' C1
    FROM   TEMP02
---------------------------------
-- CREATE TABLE 문장 생성
---------------------------------
) , TEMP05 AS (
    SELECT
           OWNER_TAB_NM
         , COL_ID RNUM
         , '    '|| A.COL_NM
         ||'    '|| A.DATA_TYPE_DESC
         ||'    '|| A.NULLABLE||' '
         ||',' AS C1
    FROM   TEMP01 A
---------------------------------
-- PRIMARY KEY 문장 생성
---------------------------------
)
, TEMP06 AS (
    SELECT OWNER_TAB_NM
         , 500 RNUM
         , ' CONSTRAINT '||TAB_NM||'_PK PRIMARY KEY(' ||
           NVL(MAX(CASE WHEN PK_NUM = 1 THEN A.COL_NM||',' ELSE NULL END),'') ||
           NVL(MAX(CASE WHEN PK_NUM = 2 THEN A.COL_NM||',' ELSE NULL END),'') ||
           NVL(MAX(CASE WHEN PK_NUM = 3 THEN A.COL_NM||',' ELSE NULL END),'') ||
           NVL(MAX(CASE WHEN PK_NUM = 4 THEN A.COL_NM||',' ELSE NULL END),'') ||
           NVL(MAX(CASE WHEN PK_NUM = 5 THEN A.COL_NM||',' ELSE NULL END),'') ||
           NVL(MAX(CASE WHEN PK_NUM = 6 THEN A.COL_NM||',' ELSE NULL END),'') ||
           NVL(MAX(CASE WHEN PK_NUM = 7 THEN A.COL_NM||',' ELSE NULL END),'') C1
    FROM  (
           SELECT A.OWNER_TAB_NM
                , A.TAB_NM
                , A.COL_NM
                , ROW_NUMBER() OVER (PARTITION BY A.TAB_NM ORDER BY A.TAB_NM, A.COL_ID) PK_NUM
           FROM   TEMP01 A
           WHERE  A.PK = 'PK'
           ORDER BY A.OWNER_TAB_NM
          ) A
    GROUP BY A.OWNER_TAB_NM, A.TAB_NM
    )
---------------------------------
-- TABLE COMMENT 문장 생성
---------------------------------
)
SELECT * FROM TEMP06, TEMP09 AS (
    SELECT OWNER_TAB_NM
         , 800 RNUM
         , ' COMMENT ON TABLE '||OWNER_TAB_NM||' IS '''||NVL(TAB_HNM,' ',TAB_HNM)||''';' C1
    FROM   TEMP01
---------------------------------
-- 데이터 복원
---------------------------------
), TEMP11 AS (
    SELECT OWNER_TAB_NM
         , 3000 RNUM
         , ' INSERT INTO '||OWNER_TAB_NM C1
    FROM   TEMP02
    UNION ALL
    SELECT OWNER_TAB_NM
         , 3001 RNUM
         , 'SELECT ' C1
    FROM   TEMP02
), TEMP12 AS (
    SELECT OWNER_TAB_NM
         , 3100+COL_ID RNUM
         , DECODE(COL_ID,0,' ',',')||COL_NM C1
    FROM   TEMP01
), TEMP13 AS (
    SELECT OWNER_TAB_NM
         , 4000 RNUM
         , 'FROM '||OWNER_TAB_NM||'_T1' C1
    FROM   TEMP02
---------------------------------
-- 데이터 확인 조회
---------------------------------
), TEMP14 AS (
    SELECT OWNER_TAB_NM
         , 5000 RNUM
         , 'SELECT * FROM '||OWNER_TAB_NM||';' C1
    FROM   TEMP02
---------------------------------
-- 데이터 확인 조회
---------------------------------
), TEMP15 AS (
    SELECT OWNER_TAB_NM
         , 6000 RNUM
         , 'DROP TABLE '||OWNER_TAB_NM||'_T1' C1
    FROM   TEMP02
), TEMP16 AS (
    SELECT *
    FROM  (SELECT * FROM TEMP021 UNION ALL
           SELECT * FROM TEMP03  UNION ALL
           SELECT * FROM TEMP04  UNION ALL
           SELECT * FROM TEMP05  UNION ALL
           SELECT * FROM TEMP06  UNION ALL
           SELECT * FROM TEMP07  UNION ALL
           SELECT * FROM TEMP08  UNION ALL
           SELECT * FROM TEMP09  UNION ALL
           SELECT * FROM TEMP10  UNION ALL
           SELECT * FROM TEMP11  UNION ALL
           SELECT * FROM TEMP12  UNION ALL
           SELECT * FROM TEMP13  UNION ALL
           SELECT * FROM TEMP14  UNION ALL
           SELECT * FROM TEMP15
          ) A
    ORDER BY OWNER_TAB_NM
) SELECT * FROM TEMP16





