IF OBJECT_ID ( 'query8_a', 'P' ) IS NOT NULL
	DROP PROCEDURE query8_a
GO
CREATE PROCEDURE query8_a
AS
	BEGIN
		SELECT
			H.id, H.name,
			SUM(CASE WHEN P.end_pos = 1 THEN 1 ELSE 0 END) AS countFirstPositions,
			SUM(CASE WHEN P.end_pos = 2 THEN 1 ELSE 0 END) AS countSecondPositions,
			SUM(CASE WHEN P.end_pos = 3 THEN 1 ELSE 0 END) AS countThirdPositions,
			COUNT(*) countParticipations,
			ROUND( (CONVERT(FLOAT,SUM(CASE WHEN P.end_pos = 1 THEN 1 ELSE 0 END))/COUNT(*)),5,2) AS successRatio,
			SUM(P.winnings) AS totalWinnings INTO #TEMP
		FROM PARTICIPATION P,HORSE H
		WHERE H.id = P.horse_id
		GROUP BY H.id, H.name
		ORDER BY totalWinnings DESC;

-- 		SELECT * FROM #TEMP;
--     SELECT COUNT(*) FROM #TEMP;

		SELECT T.id, T.name, T.countFirstPositions, T.countSecondPositions, T.countThirdPositions, T.countParticipations, T.successRatio, T.totalWinnings
		FROM #TEMP T
		UNION
		SELECT H.id, H.name, 0 AS countFirstPositions, 0 countSecondPositions, 0 countThirdPositions, 0 AS countParticipations, 0 AS successRatio, 0 AS totalWinnings
		FROM HORSE H WHERE H.id NOT IN ( SELECT T2.id FROM #TEMP T2)

--     SELECT COUNT(*) FROM
--       (SELECT T.id, T.name, T.countFirstPositions, T.countSecondPositions, T.countThirdPositions, T.countParticipations, T.successRatio, T.totalWinnings
--     FROM #TEMP T
--     UNION
--     SELECT H.id, H.name, 0 AS countFirstPositions, 0 countSecondPositions, 0 countThirdPositions, 0 AS countParticipations, 0 AS successRatio, 0 AS totalWinnings
--     FROM HORSE H WHERE H.id NOT IN ( SELECT T2.id FROM #TEMP T2)) TEST

		DROP TABLE #TEMP;
	END;

--EXEC query8_a
--SELECT *
--FROM PARTICIPATION P,HORSE H
--WHERE H.id = P.horse_id