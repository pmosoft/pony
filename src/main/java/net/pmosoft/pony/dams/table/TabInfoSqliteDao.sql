 SELECT DISTINCT
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

    WHERE ('' = '' OR UPPER(A.JDBC_NM  ) LIKE (UPPER('')))
    AND   ('' = '' OR UPPER(A.OWNER  ) LIKE (UPPER('')))
    AND   ('' = '' OR UPPER(A.TAB_NM ) LIKE (UPPER('')))
    AND   ('' = '' OR UPPER(A.TAB_HNM ) LIKE (UPPER('')))
    AND   (
            (0 = 1 AND UPPER(A.TAB_NM ) IN (''))
             OR
            (0 = 0)
          )
    AND   (
            ('COL' = 'COL'
                AND UPPER(A.COL_NM ) LIKE (UPPER(IFNULL('','')   )||'%')
                AND UPPER(IFNULL(A.COL_HNM,'')) LIKE (UPPER(IFNULL('','')  )||'%')
            )
           OR
            ('COL' = 'TAB' AND UPPER(A.TAB_NM ) IN (
                SELECT DISTINCT UPPER(TAB_NM)
                FROM   TDACM00080
                WHERE  UPPER(COL_NM ) LIKE (UPPER(''   )||'%')
                AND    UPPER(COL_HNM) LIKE (UPPER(''  )||'%')
                )
             )
           )
    AND    UPPER(A.JDBC_NM) IN (SELECT DISTINCT UPPER(JDBC_NM) FROM TDACM00010)
    AND    A.TAB_ROWS       = A.TAB_ROWS
    AND    A.TAB_REG_DT     >= (CASE WHEN '' = '' THEN '19010101' ELSE '' END)
    AND    A.TAB_UPD_DT     >= (CASE WHEN '' = '' THEN '19010101' ELSE '' END)
    AND   (
           (LENGTH('') = 0)
            OR
           (LENGTH('') > 0 AND UPPER(A.TAB_NM ) IN (
                SELECT DISTINCT UPPER(TAB_NM)
                FROM   TDACM00080
                WHERE  UPPER(COL_NM ) LIKE (UPPER(''   ))
                AND    UPPER(COL_HNM) LIKE (UPPER(''  ))
                )
            )
           )



select * FROM   INFORMATION_SCHEMA.COLUMNS a
where 1=1
-- AND    UPPER(A.TABLE_SCHEMA) NOT IN ('PUBLIC','INFORMATION_SCHEMA','PG_CATALOG')
 AND    A.TABLE_NAME LIKE '%adm%'


 
 SELECT * FROM CELLPLAN.ADMIN_NOTICE
 
 DROP TABLE ADMIN_NOTICE
 
 CREATE TABLE CELLPLAN.ADMIN_NOTICE
(
 CONTENT VARCHAR(4000)  
,MODIFY_DT DATE  
);
 
 
 
 
 select * from admin_notice
 
SELECT DISTINCT
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
    WHERE  UPPER(A.JDBC_NM) LIKE (UPPER(''  )||'%')
    AND    UPPER(A.OWNER  ) LIKE (UPPER(''   )||'%')
    AND    UPPER(A.TAB_NM ) LIKE (UPPER(''   )||'%')
    AND    UPPER(IFNULL(A.TAB_HNM,'')) LIKE (UPPER(''  )||'%')
    AND    UPPER(A.JDBC_NM) IN (SELECT DISTINCT UPPER(JDBC_NM) FROM TDACM00010)
    AND    A.TAB_ROWS       = A.TAB_ROWS
    AND    A.TAB_REG_DT     >= (CASE WHEN '' = '' THEN '19010101' ELSE '' END)
    AND    A.TAB_UPD_DT     >= (CASE WHEN '' = '' THEN '19010101' ELSE '' END)
    AND   (
           (LENGTH('SC') = 0)
            OR
           (LENGTH('SC') > 0 AND UPPER(A.TAB_NM ) IN (
                SELECT DISTINCT UPPER(TAB_NM)
                FROM   TDACM00080
                WHERE  UPPER(COL_NM ) LIKE (UPPER('SC'   )||'%')
                AND    UPPER(COL_HNM) LIKE (UPPER(''  )||'%')                    
                )
            )    
           )       
    ORDER BY 1 ASC,A.JDBC_NM,A.OWNER,A.TAB_NM

