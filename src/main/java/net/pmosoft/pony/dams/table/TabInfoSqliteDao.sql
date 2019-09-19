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

    WHERE ('EOSLPC' = '' OR UPPER(A.JDBC_NM  ) LIKE (UPPER('EOSLPC')))
    AND   ('cellplan' = '' OR UPPER(A.OWNER  ) LIKE (UPPER('cellplan')))
    AND   ('' = '' OR UPPER(A.TAB_NM ) LIKE (UPPER('')))
    AND   ('' = '' OR UPPER(A.TAB_HNM ) LIKE (UPPER('')))
    AND   (
            (0 = 1 AND UPPER(A.TAB_NM ) IN ('ANALYSIS_LIST','ANALYSIS_RESULT','CELLDB_5G_EXCEL_IMPORT','COLOR','COLOR_USER','DU','GEO_GEOMETRYQUERY','LTESECTORPARAMETER','LTESYSTEM','LTETRAFFIC','LTE_IO_ANALYSIS','MOBILE_PARAMETER','NRSECTORPARAMETER','NRSYSTEM','NRUETRAFFIC','NR_CANDIDATE_PARAMETER','NR_CANDIDATE_SITE_INFO','RU','RU_ANTENA','SCENARIO','SCHEDULE','SECTOR','SITE','SITE_DRAW_OPTION','SWING_PARAMETER','TILT_BUILDING_DENSITY','TILT_BUILDING_DENSITY_BK','TILT_NR_ANT_LIST','TILT_NR_PARAMETER','TILT_RUNRESULT','TILT_SCENARIO_EVO','TILT_SCENARIO_FINE','TILT_TARGET_INFO'))
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
    AND    A.TAB_REG_DT     >= (CASE WHEN A.TAB_REG_DT = '' THEN '' WHEN '' = '' THEN '19010101' ELSE '' END)
    AND    A.TAB_UPD_DT     >= (CASE WHEN A.TAB_UPD_DT = '' THEN '' WHEN '' = '' THEN '19010101' ELSE '' END)
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

           
                AND (
                     (UPPER(A.COL_NM ) LIKE (UPPER(IFNULL(#{colNm},'')   )||'%')) 
                      OR
                     (UPPER(IFNULL(A.COL_HNM,'')) LIKE (UPPER(IFNULL(#{colHnm},'')  )||'%'))
                    )
           
