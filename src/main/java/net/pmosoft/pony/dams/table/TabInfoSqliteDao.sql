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

