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
    WHERE  JDBC_NM = 'pony'
    ORDER BY REG_DTM