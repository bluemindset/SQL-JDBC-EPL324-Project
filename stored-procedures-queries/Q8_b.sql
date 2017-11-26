CREATE PROCEDURE query8_b
AS 
BEGIN 
	SELECT 
	SUM(CASE WHEN P.end_pos = 1 THEN 1 ELSE 0 END) AS countFirstPositions, 
	SUM(CASE WHEN P.end_pos = 2 THEN 1 ELSE 0 END) AS countSecondPositions, 
	SUM(CASE WHEN P.end_pos = 3 THEN 1 ELSE 0 END) AS countThirdPositions,
	COUNT(*) countParticipations,  
	SUM(CASE WHEN P.end_pos = 1 THEN 1 ELSE 0 END)/COUNT(*) AS successRatio,
	SUM(P.winnings) AS totalWinnings  
	FROM PARTICIPATION P, JOCKEY J
	WHERE J.id = P.jockey_id
	ORDER BY countFirstPositions
END;