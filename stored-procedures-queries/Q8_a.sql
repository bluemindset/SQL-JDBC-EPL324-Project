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
			SUM(CASE WHEN P.end_pos = 1 THEN 1 ELSE 0 END)/COUNT(*) AS successRatio,
			SUM(P.winnings) AS totalWinnings
		FROM PARTICIPATION P,HORSE H
		WHERE H.id = P.horse_id
		GROUP BY H.id, H.name
		ORDER BY totalWinnings DESC
	END;

--EXEC query8_a
--SELECT *
--FROM PARTICIPATION P,HORSE H
--WHERE H.id = P.horse_id
