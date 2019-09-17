SELECT *
FROM TDACM00010
;

    SELECT JDBC_NM
         , DRIVER
         , URL
         , USR_ID
         , USR_PW
         , REG_DTM
         , REG_USR_ID
         , UPD_DTM
         , UPD_USR_ID
         , JDBC_NM       AS ID   -- combo id
         , JDBC_NM       AS NAME -- combo name
    FROM   TDACM00010
    ORDER BY REG_DTM
    
    
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
