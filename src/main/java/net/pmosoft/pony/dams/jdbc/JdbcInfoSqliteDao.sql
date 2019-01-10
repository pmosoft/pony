SELECT *
FROM TDACM00060
WHERE   CD LIKE ('%'||'001'||'%')
;

SELECT   A.CD_ID_NM    
        ,A.CD_ID_HNM   
        ,A.CD_ID_GRP_NM
        ,A.CD          
        ,A.CD_NM       
        ,A.CD_HNM      
        ,A.CD_DESC      
        ,A.CD_TY_CD
        ,C.CD_HNM AS CD_TY_CD_NM   
        ,A.CD_STS_CD   
        ,B.CD_HNM AS CD_STS_CD_NM   
FROM    TDACM00060 A
        LEFT JOIN TDACM00060 B
        ON  B.CD_ID_NM = 'CD_STS_CD'
        AND B.CD = A.CD_STS_CD
        LEFT JOIN TDACM00060 C
        ON  C.CD_ID_NM = 'CD_TY_CD'
        AND C.CD = A.CD_TY_CD
ORDER BY A.CD_TY_CD,A.CD_ID_NM, A.CD


<select id="selectJdbcInfoRegList" parameterType="java.util.HashMap" resultType="java.util.LinkedHashMap">

    -- JdbcInfoDao.selectJdbcInfoRegList
    SELECT   A.CD_ID_NM    
            ,A.CD_ID_HNM   
            ,A.CD_ID_GRP_NM
            ,A.CD
            ,A.CD_NM       
            ,A.CD_HNM      
            ,A.CD_DESC      
            ,A.CD_TY_CD
            ,A.CD_STS_CD   
            ,DATE_FORMAT(A.REG_DTM,'%Y.%m.%d %H:%i:%S') AS REG_DTM
            ,A.REG_USR_ID
            ,DATE_FORMAT(A.UPD_DTM,'%Y.%m.%d %H:%i:%S') AS UPD_DTM
            ,A.UPD_USR_ID
    FROM    TDACM00060 A
    WHERE   A.CD_ID_HNM LIKE CONCAT(CONCAT('%',#{searchValue}),'%')
    OR      A.CD LIKE CONCAT(CONCAT('%',#{searchValue}),'%')
    OR      A.CD_NM LIKE CONCAT(CONCAT('%',#{searchValue}),'%')
    OR      A.CD_HNM LIKE CONCAT(CONCAT('%',#{searchValue}),'%')
    OR      A.CD_ID_GRP_NM LIKE CONCAT(CONCAT('%',#{searchValue}),'%')
    ORDER BY A.CD_TY_CD,A.CD_ID_NM, A.CD
</select>


<select id="selectJdbcInfoExtList" parameterType="java.util.HashMap" resultType="java.util.LinkedHashMap">

    -- JdbcInfoDao.selectJdbcInfoExtList
    SELECT   A.CD_ID_NM      
            ,A.CD
            ,B.CD_NM            
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
    AND     A.CD = B.CD  
    AND    (   A.CD_ID_NM LIKE CONCAT(CONCAT('%',#{searchValue}),'%')
            OR A.CD LIKE CONCAT(CONCAT('%',#{searchValue}),'%')
           ) 
    AND     B.CD_STS_CD = '03'  
    ORDER BY A.CD_ID_NM, A.CD
</select>


<select id="selectJdbcInfoExtRegList" parameterType="java.util.HashMap" resultType="java.util.LinkedHashMap">

    -- JdbcInfoDao.selectJdbcInfoExtRegList
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
    FROM    TDACM00061 A
    WHERE   A.CD_ID_NM LIKE CONCAT(CONCAT('%',#{searchValue}),'%')
    OR      A.CD LIKE CONCAT(CONCAT('%',#{searchValue}),'%')
    ORDER BY A.CD_ID_NM, A.CD
</select>

<select id="selectJdbcInfoExt" parameterType="java.util.HashMap" resultType="java.util.LinkedHashMap">

    -- JdbcInfoDao.selectJdbcInfoExt
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
    AND     A.CD_ID_NM = #{CD_ID_NM}
    AND     A.CD       = (CASE WHEN #{CD} IS NULL THEN A.CD ELSE #{CD} END)
    AND     B.CD_NM    = (CASE WHEN #{CD_NM} IS NULL THEN B.CD_NM ELSE #{CD_NM} END)
    ORDER BY A.CD_ID_NM, A.CD
</select>

<select id="selectJdbcInfoCnt" parameterType="java.util.HashMap" resultType="int">
    -- JdbcInfoDao.selectJdbcInfoCnt
    SELECT  COUNT(*) CNT
    FROM    TDACM00060 A
    WHERE   A.CD_ID_HNM = #{CD_ID_HNM}
    AND     A.CD        = #{CD}
</select>

<select id="selectJdbcInfoExtCnt" parameterType="java.util.HashMap" resultType="int">
    -- JdbcInfoDao.selectJdbcInfoExtCnt
    SELECT  COUNT(*) CNT
    FROM    TDACM00061 A
    WHERE   A.CD_ID_NM = #{CD_ID_NM}
    AND     A.CD       = #{CD}
</select>


<select id="selectJdbcInfoCombo" parameterType="java.util.HashMap" resultType="java.util.LinkedHashMap">

    -- JdbcInfoDao.selectJdbcInfoRegList
    SELECT   A.CD_ID_NM    
            ,A.CD_ID_HNM   
            ,A.CD_ID_GRP_NM
            ,A.CD
            ,A.CD_NM       
            ,A.CD_HNM      
            ,A.CD_DESC      
            ,A.CD_TY_CD
            ,A.CD_STS_CD
            ,B.CD_PARAM1_DESC
            ,B.CD_PARAM1     
            ,B.CD_PARAM2_DESC
            ,B.CD_PARAM2     
            ,B.CD_PARAM3_DESC
            ,B.CD_PARAM3     
            ,B.CD_PARAM4_DESC
            ,B.CD_PARAM4     
            ,B.CD_PARAM5_DESC
            ,B.CD_PARAM5
            ,B.CD_PARAM6_DESC
            ,B.CD_PARAM6     
            ,B.CD_PARAM7_DESC
            ,B.CD_PARAM7     
            ,B.CD_PARAM8_DESC
            ,B.CD_PARAM8     
            ,B.CD_PARAM9_DESC
            ,B.CD_PARAM9     
    FROM    TDACM00060 A
            LEFT JOIN TDACM00061 B
            ON  A.CD_ID_NM = B.CD_ID_NM
            AND A.CD       = B.CD
    WHERE   A.CD_ID_NM = #{CD_ID_NM}
    AND     A.CD_STS_CD = '03' -- 승인   
    ORDER BY A.CD_TY_CD,A.CD_ID_NM, A.CD
</select>


<insert id="insertJdbcInfo" parameterType="java.util.HashMap">

    -- JdbcInfoDao.insertJdbcInfo
    INSERT INTO TDACM00060
    (
         CD_ID_NM     
        ,CD_ID_HNM    
        ,CD_ID_GRP_NM 
        ,CD           
        ,CD_NM        
        ,CD_HNM       
        ,CD_DESC      
        ,CD_TY_CD     
        ,CD_STS_CD    
        ,REG_DTM      
        ,REG_USR_ID   
        ,UPD_DTM      
        ,UPD_USR_ID   
    ) VALUES (
         #{CD_ID_NM}
        ,#{CD_ID_HNM}
        ,#{CD_ID_GRP_NM}
        ,#{CD}
        ,#{CD_NM}
        ,#{CD_HNM}
        ,#{CD_DESC}
        ,#{CD_TY_CD}
        ,#{CD_STS_CD}
        ,curdate()
        ,#{REG_USR_ID}
        ,curdate()
        ,#{UPD_USR_ID}
    )
</insert>

<insert id="insertJdbcInfoExt" parameterType="java.util.HashMap">

    -- JdbcInfoDao.insertJdbcInfoExt
    INSERT INTO TDACM00061
    (
         CD_ID_NM      
        ,CD            
        ,CD_PARAM1_DESC
        ,CD_PARAM1     
        ,CD_PARAM2_DESC
        ,CD_PARAM2     
        ,CD_PARAM3_DESC
        ,CD_PARAM3     
        ,CD_PARAM4_DESC
        ,CD_PARAM4     
        ,CD_PARAM5_DESC
        ,CD_PARAM5     
        ,CD_PARAM6_DESC
        ,CD_PARAM6     
        ,CD_PARAM7_DESC
        ,CD_PARAM7     
        ,CD_PARAM8_DESC
        ,CD_PARAM8     
        ,CD_PARAM9_DESC
        ,CD_PARAM9     
        ,REG_DTM      
        ,REG_USR_ID   
        ,UPD_DTM      
        ,UPD_USR_ID   
    ) VALUES (
         #{CD_ID_NM}
        ,#{CD}
        ,#{CD_PARAM1_DESC}
        ,#{CD_PARAM1}
        ,#{CD_PARAM2_DESC}
        ,#{CD_PARAM2}
        ,#{CD_PARAM3_DESC}
        ,#{CD_PARAM3}
        ,#{CD_PARAM4_DESC}
        ,#{CD_PARAM4}
        ,#{CD_PARAM5_DESC}
        ,#{CD_PARAM5}
        ,#{CD_PARAM6_DESC}
        ,#{CD_PARAM6}
        ,#{CD_PARAM7_DESC}
        ,#{CD_PARAM7}
        ,#{CD_PARAM8_DESC}
        ,#{CD_PARAM8}
        ,#{CD_PARAM9_DESC}
        ,#{CD_PARAM9}
        ,curdate()
        ,#{REG_USR_ID}
        ,curdate()
        ,#{UPD_USR_ID}
    )
</insert>


<update id="updateJdbcInfo" parameterType="java.util.HashMap">

    -- JdbcInfoDao.updateJdbcInfo
    UPDATE TDACM00060
    SET  CD_ID_NM      = #{CD_ID_NM}
        ,CD_ID_HNM     = #{CD_ID_HNM}
        ,CD_ID_GRP_NM  = #{CD_ID_GRP_NM}
        ,CD            = #{CD}
        ,CD_NM         = #{CD_NM}
        ,CD_HNM        = #{CD_HNM}
        ,CD_DESC       = #{CD_DESC}
        ,CD_TY_CD      = #{CD_TY_CD}
        ,CD_STS_CD     = #{CD_STS_CD}
        ,UPD_DTM       = curdate()
        ,UPD_USR_ID    = #{UPD_USR_ID}
    WHERE  CD_ID_NM = #{CD_ID_NM}
    AND    CD       = #{CD}

</update>

<update id="updateJdbcInfoExt" parameterType="java.util.HashMap">

    -- JdbcInfoDao.updateExtJdbcInfo
    UPDATE TDACM00061
    SET  CD_ID_NM           = #{CD_ID_NM}      
        ,CD                 = #{CD}            
        ,CD_PARAM1_DESC     = #{CD_PARAM1_DESC}
        ,CD_PARAM1          = #{CD_PARAM1}     
        ,CD_PARAM2_DESC     = #{CD_PARAM2_DESC}
        ,CD_PARAM2          = #{CD_PARAM2}     
        ,CD_PARAM3_DESC     = #{CD_PARAM3_DESC}
        ,CD_PARAM3          = #{CD_PARAM3}     
        ,CD_PARAM4_DESC     = #{CD_PARAM4_DESC}
        ,CD_PARAM4          = #{CD_PARAM4}     
        ,CD_PARAM5_DESC     = #{CD_PARAM5_DESC}
        ,CD_PARAM5          = #{CD_PARAM5}     
        ,CD_PARAM6_DESC     = #{CD_PARAM6_DESC}
        ,CD_PARAM6          = #{CD_PARAM6}     
        ,CD_PARAM7_DESC     = #{CD_PARAM7_DESC}
        ,CD_PARAM7          = #{CD_PARAM7}     
        ,CD_PARAM8_DESC     = #{CD_PARAM8_DESC}
        ,CD_PARAM8          = #{CD_PARAM8}     
        ,CD_PARAM9_DESC     = #{CD_PARAM9_DESC}
        ,CD_PARAM9          = #{CD_PARAM9}     
        ,UPD_DTM       = curdate()
        ,UPD_USR_ID    = #{UPD_USR_ID}
    WHERE  CD_ID_NM = #{CD_ID_NM}
    AND    CD       = #{CD}

</update>


<delete id="deleteJdbcInfo" parameterType="java.util.HashMap">
    -- JdbcInfoDao.deleteJdbcInfo
    DELETE FROM TDACM00060 WHERE CD_ID_NM = #{CD_ID_NM} AND CD = #{CD}
</delete> 

<delete id="deleteJdbcInfoExt" parameterType="java.util.HashMap">
    -- JdbcInfoDao.deleteJdbcInfoExt
    DELETE FROM TDACM00061 WHERE CD_ID_NM = #{CD_ID_NM} AND CD = #{CD}
</delete> 


</mapper>
