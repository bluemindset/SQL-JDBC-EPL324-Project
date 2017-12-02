IF OBJECT_ID ( 'query8_a', 'P' ) IS NOT NULL
	DROP PROCEDURE query8_a
GO
CREATE PROCEDURE query8_a
@year INT
AS
	BEGIN
    --for test
--     DECLARE @year INT;
--     SET @year = 2010;
--
--     SELECT * FROM PARTICIPATION WHERE @year = DATEPART(yyyy, PARTICIPATION.meeting_date)
--     SELECT * FROM PARTICIPATION

		SELECT
			H.id, H.name,
			SUM(CASE WHEN P.end_pos = 1 THEN 1 ELSE 0 END) AS countFirstPositions,
			SUM(CASE WHEN P.end_pos = 2 THEN 1 ELSE 0 END) AS countSecondPositions,
			SUM(CASE WHEN P.end_pos = 3 THEN 1 ELSE 0 END) AS countThirdPositions,
			COUNT(*) countParticipations,
			ROUND( (CONVERT(FLOAT,SUM(CASE WHEN P.end_pos = 1 THEN 1 ELSE 0 END))/COUNT(*)),5,2) AS successRatio,
			SUM(P.winnings) AS totalWinnings INTO #TEMP
		FROM PARTICIPATION P,HORSE H
		WHERE H.id = P.horse_id AND  @year = DATEPART(yyyy, P.meeting_date)
		GROUP BY H.id, H.name
		ORDER BY totalWinnings DESC;

--     SELECT * FROM #TEMP;
--     SELECT COUNT(*) FROM #TEMP;
--     DROP TABLE #TEMP;
--  SELECT * FROM MEETING;
--  SELECT COUNT(*) FROM #TEMP;

		SELECT T.id, T.name, T.countFirstPositions, T.countSecondPositions, T.countThirdPositions, T.countParticipations, T.successRatio, T.totalWinnings
		FROM #TEMP T
		UNION
		SELECT H.id, H.name, 0 AS countFirstPositions, 0 countSecondPositions, 0 countThirdPositions, 0 AS countParticipations, 0 AS successRatio, 0 AS totalWinnings
		FROM HORSE H WHERE H.id NOT IN ( SELECT T2.id FROM #TEMP T2)
		ORDER BY successRatio DESC;

--     SELECT COUNT(*) FROM
--       (SELECT T.id, T.name, T.countFirstPositions, T.countSecondPositions, T.countThirdPositions, T.countParticipations, T.successRatio, T.totalWinnings
--     FROM #TEMP T
--     UNION
--     SELECT H.id, H.name, 0 AS countFirstPositions, 0 countSecondPositions, 0 countThirdPositions, 0 AS countParticipations, 0 AS successRatio, 0 AS totalWinnings
--     FROM HORSE H WHERE H.id NOT IN ( SELECT T2.id FROM #TEMP T2)) TEST
		DROP TABLE #TEMP;
	END;

EXEC query8_a 20;
--SELECT *
--FROM PARTICIPATION P,HORSE H
--WHERE H.id = P.horse_id