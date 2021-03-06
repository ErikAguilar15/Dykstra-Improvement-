SELECT	
		CP1.SYS_I AS CP1_ID,
		--SUBSTRING(CP1.CNCT_I, 1,3) AS FROM_PANNEL,
		--SUBSTRING(CP1.CNCT_I, 5, 3) AS FROM_PORT,
		CP1.CNCT_I AS CP1_CN,
		--CP1.ALT_CNCT_I,
		CP2.SYS_I AS CP2_ID,
		--SUBSTRING(CP2.CNCT_I, 1,3) AS TO_PANNEL,
		--SUBSTRING(CP2.CNCT_I, 5, 3) AS TO_PORT,
		CP2.CNCT_I AS CP2_CN,
		--CP2.ALT_CNCT_I,
		PIPE.LTH_N AS 'Pipe Length'--,*
FROM 
	MNFLD.DBO.WMGMA08_PIPE AS PIPE,
	MNFLD.DBO.wmgma01_cnct_pt AS CP1,
	MNFLD.DBO.wmgma01_cnct_pt AS CP2
WHERE 
	CP1.SITE_SYS_I = 10
	AND CP2.SITE_SYS_I = 10
	AND PIPE.CNCT_PT_SIDE1_SYS_I = CP1.SYS_I
	AND PIPE.CNCT_PT_SIDE2_SYS_I = CP2.SYS_I
	AND PIPE.LTH_N IS NOT NULL
	--AND SUBSTRING(CP1.CNCT_I, 4,1) = '-'
	--AND SUBSTRING(CP2.CNCT_I, 4,1) <> '-'
	--AND SUBSTRING(CP2.CNCT_I, 1,3) = '038'
	--AND PIPE.ACTV_X = 0
	ORDER BY PIPE.LTH_N, CP1.CNCT_I