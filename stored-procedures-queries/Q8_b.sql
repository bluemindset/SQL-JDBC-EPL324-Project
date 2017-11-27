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
			SUM(CASE WHEN P.end_pos = 1 THEN 1 ELSE 0 END)/COUNT(*) AS successRatio,
			SUM(P.winnings) AS totalWinnings
		FROM PARTICIPATION P, JOCKEY J
		WHERE J.id = P.jockey_id
		GROUP BY J.id, J.first_name, J.last_name, J.compressed_name
		ORDER BY countFirstPositions DESC
	END;