IF OBJECT_ID('query8_b','P') IS NOT NULL
  DROP PROCEDURE query8_b
GO
CREATE PROCEDURE query8_b
AS
	BEGIN
		SELECT
			J.id, J.first_name, J.last_name, J.compressed_name,
			SUM(CASE WHEN P.end_pos = 1 THEN 1 ELSE 0 END) AS countFirstPositions,
			SUM(CASE WHEN P.end_pos = 2 THEN 1 ELSE 0 END) AS countSecondPositions,
			SUM(CASE WHEN P.end_pos = 3 THEN 1 ELSE 0 END) AS countThirdPositions,
			COUNT(*) countParticipations,
			ROUND( CONVERT(FLOAT,SUM(CASE WHEN P.end_pos = 1 THEN 1 ELSE 0 END))/COUNT(*) ,5,1) AS successRatio,
			SUM(P.winnings) AS totalWinnings INTO #TEMP
		FROM PARTICIPATION P, JOCKEY J
		WHERE J.id = P.jockey_id
		GROUP BY J.id, J.first_name, J.last_name, J.compressed_name
		ORDER BY countFirstPositions DESC;

-- 		SELECT * FROM #TEMP;
-- 		SELECT COUNT(*) FROM #TEMP;

		--ADD all Jockeys with no participations.
	  SELECT T.id, T.first_name, T.last_name, T.compressed_name,
			T.countFirstPositions, T.countSecondPositions, T.countThirdPositions, T.countParticipations, T.successRatio, T.totalWinnings FROM #TEMP T
		UNION
		SELECT J.id, J.first_name, J.last_name, J.compressed_name,
			0 AS countFirstPositions, 0 AS countSecondPositions, 0 AS countThirdPositions, 0 AS countParticipations, 0 AS successRatio, 0 AS totalWinnings
		FROM JOCKEY J, PARTICIPATION P WHERE J.id NOT IN ( SELECT T2.id FROM #TEMP T2);

-- SELECT COUNT(*) totalNumberOfJockeys FROM JOCKEY;
--  //test if all jockeys are added.
-- 		SELECT COUNT(*) AS numberOfJockeys FROM
-- 			(SELECT T.id, T.first_name, T.last_name, T.compressed_name,
-- 		T.countFirstPositions, T.countSecondPositions, T.countThirdPositions, T.countParticipations, T.successRatio, T.totalWinnings FROM #TEMP T
-- 		UNION
-- 		SELECT J.id, J.first_name, J.last_name, J.compressed_name,
-- 			0 AS countFirstPositions, 0 AS countSecondPositions, 0 AS countThirdPositions, 0 AS countParticipations, 0 AS successRatio, 0 AS totalWinnings
-- 		FROM JOCKEY J WHERE J.id NOT IN ( SELECT T2.id FROM #TEMP T2)) TEST;

		DROP TABLE #TEMP;
	END;

--EXEC query8_b;