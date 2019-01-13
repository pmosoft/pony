    -- TableDao.selectMetaTabInfoList
    --SELECT  '추출'                AS STS_NM
    SELECT
           'mariadb'                                                      AS DB_NM
         , UPPER(A.TABLE_SCHEMA)                                          AS OWNER
         , UPPER(A.TABLE_NAME)                                            AS TAB_NM
         , UPPER(B.TABLE_COMMENT)                                         AS TAB_HNM
         , A.ORDINAL_POSITION                                             AS COL_ID
         , A.COLUMN_NAME                                                  AS COL_NM
         , A.COLUMN_COMMENT                                               AS COL_HNM
         , CASE WHEN UPPER(A.DATA_TYPE) = 'INT' THEN UPPER(A.DATA_TYPE)
                ELSE UPPER(A.COLUMN_TYPE)
           END                                                            AS DATA_TYPE_DESC
         , CASE WHEN IS_NULLABLE = 'NO' THEN 'NOT NULL' ELSE '' END       AS NULLABLE
         , CASE WHEN A.COLUMN_KEY = 'PRI' THEN 'Y' ELSE '' END            AS PK
         , UPPER(A.DATA_TYPE)                                             AS DATA_TYPE_NM
         , CASE WHEN UPPER(A.DATA_TYPE) IN ('CHAR','VARCHAR') THEN A.CHARACTER_MAXIMUM_LENGTH
                WHEN UPPER(A.DATA_TYPE) IN ('INT','NUMERIC') THEN A.NUMERIC_PRECISION
           END                                                            AS LEN
         , A.NUMERIC_SCALE                                                AS DECIMAL_CNT
         , ' '                                                            AS COL_DESC
         , DATE_FORMAT(NOW(),'%Y%m%d%H%i')                                AS REG_DTM
         , ''                                                             AS REG_USR_ID
         , DATE_FORMAT(NOW(),'%Y%m%d%H%i')                                AS UPD_DTM
         , ''                                                             AS UPD_USR_ID
    FROM   INFORMATION_SCHEMA.COLUMNS A
         , INFORMATION_SCHEMA.TABLES B
    WHERE  A.TABLE_SCHEMA = B.TABLE_SCHEMA
    AND    A.TABLE_NAME = B.TABLE_NAME
    AND    A.TABLE_SCHEMA = 'push'
    ORDER BY A.TABLE_NAME,A.ORDINAL_POSITION




            //원본쿼리 : net.pmosoft.pony.dams.table.TabMariadbDao.xml - insertMetaTabColList
            qry  = "--                                                                  \n";
            qry += "SELECT                                                              \n";
            qry += "       ?                      AS DB_NM                              \n";
            qry += "     , UPPER(A.TABLE_SCHEMA)  AS OWNER                              \n";
            //qry += "     , UPPER(A.TABLE_SCHEMA)||'.'||UPPER(A.TABLE_NAME) AS OWNER_TAB_NM   \n";
            qry += "     , UPPER(A.TABLE_NAME)    AS TAB_NM                             \n";
            qry += "     , UPPER(A.TABLE_COMMENT) AS TAB_HNM                            \n";
            qry += "     , ''                     AS TAB_DESC                           \n";
            qry += "     , A.TABLE_ROWS           AS ROW_CNT                            \n";
            qry += "     , DATE_FORMAT(NOW(),'%Y%m%d%H%i') AS REG_DTM                   \n";
            qry += "     , ''                    AS REG_USR_ID                          \n";
            qry += "     , DATE_FORMAT(NOW(),'%Y%m%d%H%i') AS UPD_DTM                   \n";
            qry += "     , ''                    AS UPD_USR_ID                          \n";
            qry += "FROM   INFORMATION_SCHEMA.TABLES A                                  \n";
            qry += "WHERE  1=1                                                          \n";
            qry += "AND    A.TABLE_SCHEMA = ?                                           \n";
            qry += "AND   (UPPER(A.TABLE_NAME) LIKE UPPER(CONCAT(CONCAT('%',?),'%'))    \n";
            qry += "       OR                                                           \n";
            qry += "       UPPER(A.TABLE_COMMENT) LIKE UPPER(CONCAT(CONCAT('%',?),'%')) \n";
            qry += "       )                                                            \n";
            qry += "ORDER BY A.TABLE_SCHEMA, A.TABLE_NAME                               \n";
